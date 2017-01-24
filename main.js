
var width = window.innerWidth;
var height = window.innerHeight;

var mouseX = 0, mouseY = 0;

document.addEventListener( 'mousemove', onDocumentMouseMove, false );


var clock = new THREE.Clock();

var renderer = new THREE.WebGLRenderer({ antialias: true });
renderer.setSize(width, height);
document.body.appendChild(renderer.domElement);
 
// load cube textures
/*var textureURLs = [  // URLs of the six faces of the cube map 
            "posx.jpg",   // Note:  The order in which
            "negx.jpg",   //   the images are listed is
            "posy.jpg",   //   important.
            "negy.jpg",  
            "posz.jpg",   
            "negz.jpg"
       ];
*/


var textureURLs = [  // URLs of the six faces of the cube map 
            "posx.jpg",   // Note:  The order in which
            "negx.jpg",   //   the images are listed is
            "posy.jpg",   //   important.
            "negy.jpg",  
            "posz.jpg",   
            "negz.jpg"
       ];



// create scene object
var scene = new THREE.Scene;

// create simple geometry and add to scene

cubeMap = THREE.ImageUtils.loadTextureCube( textureURLs ) ;

var objects = [];
var geometry = new THREE.BoxGeometry( 100, 100, 100 );

for ( var i = 0; i < 10; i ++ ) {

	var object = new THREE.Mesh( geometry, new THREE.MeshBasicMaterial( { color: Math.random() * 0xffffff, opacity: 0.5 } ) );
	object.position.x = Math.random() * 2400 - 1200;
	object.position.y = -400;
	object.position.z = 4000 + Math.random() * 2400 - 1200;
	
	scene.add( object );

	objects.push( object );
}

//This is where the zombies will be moving toward you!! loop through all of the objects[]

function moveZombies()
{
	for(var i = 0; i < objects.length; i++) {
		objects[i].position.z -= 10;
		if(objects[i].position.z <= -2000)
			alert("Death By Obesity!");
	}
	
	
}
// create perspective camera
var camera = new THREE.PerspectiveCamera(45, width / height, 0.1, 10000);
camera.position.y = -4999;
camera.position.z = -3000;
// add to scene and renderer
scene.add(camera); 
renderer.render(scene, camera);
// create the view matrix

forShader = {
	tCube: {type: "t", value: cubeMap }
}


var customMaterial = new THREE.ShaderMaterial( {
							uniforms: forShader,
							vertexShader: document.getElementById( 'vertexShader' ).textContent,
							fragmentShader: document.getElementById( 'fragmentShader' ).textContent
						} );
customMaterial.needsUpdate = true;


						
var refMap = new THREE.MeshLambertMaterial( { envMap: cubeMap, reflectivity: 1} );

camera.lookAt(new THREE.Vector3(0, 0, 0));


// add lighting and add to scene 
// 

var pointLight1 = new THREE.PointLight(0xffffff); pointLight1.position.set(-50, -50, 0);scene.add(pointLight1);
var pointLight2 = new THREE.PointLight(0xffffff); pointLight2.position.set(50, 50, 0);scene.add(pointLight2);
var ambient = new THREE.AmbientLight( 0x404040 ); // soft white light scene.add( light );

var directions  = ["posx", "negx", "posy", "negy", "posz", "negz"];
var imageSuffix = ".jpg";
var skyGeometry = new THREE.CubeGeometry( 5000, 5000, 5000 );	
var materialArray = [];
for (var i = 0; i < 6; i++)
materialArray.push( new THREE.MeshBasicMaterial({
			map: THREE.ImageUtils.loadTexture( directions[i] + imageSuffix ),
			side: THREE.BackSide
}));
var skyMaterial = new THREE.MeshFaceMaterial( materialArray );
var skyBox = new THREE.Mesh( skyGeometry, skyMaterial );
console.log(skyBox.position.y);
skyBox.position.y = 2000;
scene.add( skyBox );

renderer.render(scene, camera);



function onDocumentMouseMove( event ) {

	mouseX = ( event.clientX - width/2 ) / 2;
	mouseY = ( event.clientY - height/2) / 2;

}

var currentlyPressedKeys = [];
window.addEventListener("keydown", function(event) {
	currentlyPressedKeys[event.keyCode] = true;
});
window.addEventListener("keyup", function(event) {
	currentlyPressedKeys[event.keyCode] = false;
});
	
function handleInput(){
	if(currentlyPressedKeys[38] == true){//Up, forward
		camera.position.z += 10;
	}
	
	else if(currentlyPressedKeys[37] == true){//Left
		camera.position.x -= 10;
	}
	
	else if(currentlyPressedKeys[39] == true){//right
		camera.position.x += 10;
	}
	
	else if(currentlyPressedKeys[40] == true){//down, backward
		camera.position.z -= 10;
	}

}



function render() {

	handleInput();
	moveZombies();
	
	camera.position.x += ( mouseX - camera.position.x ) * .05;
	camera.position.y += (  mouseY - camera.position.y ) * .05;

	camera.lookAt(new THREE.Vector3(0, 0, 5000));

	
	var delta = clock.getDelta();

	forShader.camX = camera.position.x;
	forShader.camY = camera.position.y;
	forShader.camZ = camera.position.z;
	
	renderer.render(scene, camera);
	requestAnimationFrame(render);

	
}
render();

