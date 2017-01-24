
var width = window.innerWidth;
var height = window.innerHeight;
var spheres = [];

var mouseX = 0, mouseY = 0;

document.addEventListener( 'mousemove', onDocumentMouseMove, false );

//update code

document.addEventListener( 'mousedown', onDocumentMouseDown, false );
document.addEventListener( 'touchstart', onDocumentTouchStart, false );

var clock = new THREE.Clock();

var renderer = new THREE.WebGLRenderer({ antialias: true });
renderer.setSize(width, height);
document.body.appendChild(renderer.domElement);

// load cube textures

var textureURLs = [  // URLs of the six faces of the cube map
            "E.posx.jpg",   // Note:  The order in which
            "E.negx.jpg",   //   the images are listed is
            "E.posy.jpg",   //   important.
            "E.negy.jpg",
            "E.posz.jpg",
            "E.negz.jpg"
       ];

// create scene object
var scene = new THREE.Scene;

// create simple geometry and add to scene

cubeMap = THREE.ImageUtils.loadTextureCube( textureURLs ) ;

// create perspective camera
var camera = new THREE.PerspectiveCamera(45, width / height, 0.1, 10000);
camera.position.y = 100;
camera.position.z = 100;
// add to scene and renderer
scene.add(camera);

// create the view matrix

forShader = {
	camPos: {type: "v3", value: new THREE.Vector3(0, 0, 0)},
	tCube: {type: "t", value: cubeMap }
}

var customMaterial = new THREE.ShaderMaterial( {
							uniforms: forShader,
							vertexShader: document.getElementById( 'vertexShader' ).textContent,
							fragmentShader: document.getElementById( 'fragmentShader' ).textContent
						} );
customMaterial.needsUpdate = true;

var refMap = new THREE.MeshLambertMaterial( { envMap: cubeMap, reflectivity: 1} );

var cubeGeometry = new THREE.CubeGeometry(50, 50, 50);
var cube = new THREE.Mesh(cubeGeometry, customMaterial);
cube.position.x = 200;


var texture = THREE.ImageUtils.loadTexture( 'basketball.jpg' );
var sphereGeometry = new THREE.SphereBufferGeometry( 20, 20, 20 );
var material = new THREE.MeshBasicMaterial( { map: texture } );

mesh = new THREE.Mesh( sphereGeometry, material );


// code researched from from Three.js

for ( var i = 0; i < 500; i ++ ) {

  var mesh = new THREE.Mesh(sphereGeometry, material);

  mesh.position.x = Math.random() * 10000 - 5000;
  mesh.position.y = Math.random() * 10000 - 5000;
  mesh.position.z = Math.random() * 10000 - 5000;

  mesh.scale.x = mesh.scale.y = mesh.scale.z = Math.random() * 3 + 1;

  scene.add( mesh );
  spheres.push( mesh );

}

camera.lookAt(new THREE.Vector3(0, 0, 0));

// add lighting and add to scene
//

var pointLight1 = new THREE.PointLight(0xffffff); pointLight1.position.set(-50, -50, 0);scene.add(pointLight1);
var pointLight2 = new THREE.PointLight(0xffffff); pointLight2.position.set(50, 50, 0);scene.add(pointLight2);


scene.add(cube);

var directions  = ["E.posx", "E.negx", "E.posy", "E.negy", "E.posz", "E.negz"];
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
scene.add( skyBox );

renderer.render(scene, camera);


function onDocumentMouseMove( event ) {

	mouseX = ( event.clientX - width/2 ) / 5;
	mouseY = ( event.clientY - height/2) / 5;
}

//movement code provided by Three.js

//this code starts the basketballs moving

function onDocumentTouchStart( event ) {

  event.preventDefault();

  event.clientX = event.touches[0].clientX;
  event.clientY = event.touches[0].clientY;
  onDocumentMouseDown( event );

}

//this is suppose to be the clickable feature in the game.
function onDocumentMouseDown( event ) {

  var mouse3D = new THREE.Vector3( ( event.clientX / window.innerWidth ) * 2 - 1, -( event.clientY / window.innerHeight ) * 2 + 1,0.5 );
  var projector = new THREE.Projector();

  projector.unprojectVector(mouse3D, camera);
  mouse3D.sub( camera.position );
  mouse3D.normalize();

  var raycaster = new THREE.Raycaster( camera.position, mouse3D );
  var intersects = raycaster.intersectObjects(spheres);

  if (intersects.length > 0) { //object being clicked here; changing it to black
      intersects[0].object.material.color.setHex(Math.random() * 0xffffff);
    }
}

function render() {

	camera.position.x += ( mouseX - camera.position.x ) * .005;
	camera.position.y += (  mouseY - camera.position.y ) * .005;

	camera.lookAt(new THREE.Vector3(0, 0, 0));

  var timer = 0.0001 * Date.now();

	forShader.camX = camera.position.x;
	forShader.camY = camera.position.y;
	forShader.camZ = camera.position.z;

	//correction code
	forShader.camPos.value = camera.position;

	renderer.render(scene, camera);
	requestAnimationFrame(render);
	cube.rotation.y += 0.005;


//code researched from Three.js

  for (var i = 0, il = spheres.length; i < il; i ++) {

    var sphere = spheres[i];

    sphere.position.x = 5000 * Math.cos(timer + i);
    sphere.position.y = 5000 * Math.sin(timer + i * 1.1);

    sphere.rotation.y += 0.005;

  }

}
render();
