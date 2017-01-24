
"use strict";

var gl, program;

var geometry = {p1:new vec2(-1, 1), p2:new vec2(-1, -1), p3:new vec2(1, 0)};

var position = vec2(-0.9, 0);
var translationMatrix;
var scaleMatrix, scale_factor = 0.1;
var rotationMatrix, alpha = 0;
var GPUvertices;

var userMouse = {oldX: 0, oldY: 0, deltaX: 0, deltaY: 0};

window.onload = function init()
{
    var canvas = document.getElementById( "gl-canvas" );

    gl = WebGLUtils.setupWebGL( canvas );
    if ( !gl ) { alert( "WebGL isn't available" ); }
    //
    //  Configure WebGL
    //
    gl.viewport( 0, 0, canvas.width, canvas.height );
    gl.clearColor( 1.0, 1.0, 1.0, 1.0 );

    //  Load shaders and initialize attribute buffers

    program = initShaders( gl, "vertex-shader", "fragment-shader" );
    gl.useProgram( program );


    //add logic here to change the position of your spaceship
window.addEventListener("mousemove", function (event) {
    //event.clientX and event.clientY contain the relative position of the cursor in the browser window, in pixels
    userMouse.deltaX = (event.clientX - userMouse.oldX) * 0.01;
    userMouse.deltaY = (event.clientY - userMouse.oldY) * 0.01;
    if(userMouse.deltaX > 1 || userMouse.deltaY > 1 || userMouse.deltaX < -1 || userMouse.deltaY < -1) //this is to prevent "large movements" that resilt from mouse cursor outside the window
    {
      userMouse.deltaX = 0;
      userMouse.deltaY = 0;
    }
    userMouse.oldX = event.clientX;
    userMouse.oldY = event.clientY;
  });

/**/

    //initialize triangle position
    position = vec2(-0.9, 0);
    render();
};


function mat3vec2mult(myMat, myVec) {
	var temp_result = [];
	var vec = vec3(myVec[0], myVec[1], 1.0); // add w so we can do regular matrix vector multiplication
	for(var resRow = 0; resRow<3; resRow++)
	{
		var sum = 0; // each one of the below loops is a dot product
		for(var dpIndex = 0; dpIndex<3; dpIndex++)
		{
			sum = sum + myMat[resRow][dpIndex] * vec[dpIndex];
		}
		temp_result.push(sum); // add to result
	}

	var result = [];
	result.push(temp_result[0] / temp_result[2]); // normalize, i.e., divide by w
	result.push(temp_result[1] / temp_result[2]);
	return result; // return the result
}

function update_geometry()
{
	geometry.p1 = vec2(- 1,  1);
	geometry.p2 = vec2(- 1, - 1);
	geometry.p3 = vec2(1, 0);

  alpha++;
  rotationMatrix = new mat3(Math.cos(alpha), -Math.sin(alpha), 0, Math.sin(alpha), Math.cos(alpha), 0, 0, 0, 1);
  scaleMatrix = new mat3(0.1, 0.0, 0.0, 0.0, 0.1, 0.0, 0, 0, 1);
	translationMatrix = new mat3(1, 0, position[0], 0, 1, position[1], 0, 0, 1);

	// model space transformation
	geometry.p1 = mat3vec2mult(rotationMatrix, geometry.p1);
	geometry.p2 = mat3vec2mult(rotationMatrix, geometry.p2);
	geometry.p3 = mat3vec2mult(rotationMatrix, geometry.p3);

	// model space transformation
	geometry.p1 = mat3vec2mult(scaleMatrix, geometry.p1);
	geometry.p2 = mat3vec2mult(scaleMatrix, geometry.p2);
	geometry.p3 = mat3vec2mult(scaleMatrix, geometry.p3);

	// world space transformation
	geometry.p1 = mat3vec2mult(translationMatrix, geometry.p1);
	geometry.p2 = mat3vec2mult(translationMatrix, geometry.p2);
	geometry.p3 = mat3vec2mult(translationMatrix, geometry.p3);

	// "flatten" our Javascript vertex (vec2) objects
	GPUvertices = flatten(new Array(geometry.p1, geometry.p2, geometry.p3));


}

function render() {

    update_geometry();

    // Create a buffer and populate it
    var bufferId = gl.createBuffer();
    gl.bindBuffer( gl.ARRAY_BUFFER, bufferId );
    gl.bufferData( gl.ARRAY_BUFFER, GPUvertices, gl.STATIC_DRAW );
    // Associate out shader variables with our data buffer
    var vPosition = gl.getAttribLocation( program, "vPosition" );
    gl.vertexAttribPointer( vPosition, 2, gl.FLOAT, false, 0, 0 );
    gl.enableVertexAttribArray( vPosition );


    gl.clear( gl.COLOR_BUFFER_BIT );
    gl.drawArrays( gl.TRIANGLES, 0, 3);
    requestAnimFrame( render );

}
