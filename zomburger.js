
var width = window.innerWidth;
var height = window.innerHeight;

var killCount = 0;
var level = 1;
var gameState = false;
var ketchup = [];
var speed = 0;
var mouseX = 0, mouseY = 0;
var numZombies = 5;
var death = false;
var clock = new THREE.Clock();

var renderer = new THREE.WebGLRenderer({ antialias: true });
renderer.setSize(width, height);
document.body.appendChild(renderer.domElement);

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
cubeMap = THREE.ImageUtils.loadTextureCube( textureURLs ) ;

	var gui = new dat.GUI();

	var parameters =
	{
		a: "ZOMBURGER!", // string
		b: function () { alert ("Starting Game!  Get your gun!"); onStart() },
		e: function() { if(speed == 0) { speed = 5 + (level*10) } else {speed = 0} },
		f: "W, Arrow Up, Spacebar", //instructions
		g: "D & A",
    h: "Enter"
	};
	// gui.add( parameters )
	gui.add( parameters, 'a' ).name('Shoot to Kill!');
	gui.add( parameters, 'b' ).name('Start Game');
	gui.add( parameters, 'e' ).name('Pause');

	var folder1 = gui.addFolder('Controls');
	folder1.add( parameters, 'f' ).name('Use to Shoot Ketchup');
	folder1.add( parameters, 'g' ).name('Move left & right');
  folder1.add( parameters, 'h').name("Restart the Game");
	folder1.close();

	gui.open();


//adding sounds here
var audio = document.createElement('audio');
var source = document.createElement('source');
source.src = 'Squish.wav';
audio.appendChild(source);

//text
var levelText;
function createLevelText(){
	if(levelText != undefined){
		scene.remove(levelText);
	}
	var levelTextGeometry = new THREE.TextGeometry("Level" + " " + level, {
	size: 100,
	font: "helvetiker",
	weight: "normal",
	style: "normal"
	});

	 var levelTextMaterial = new THREE.MeshPhongMaterial( { color: 0xff0000, specular: 0xffffcc, shininess: 30, shading: THREE.SmoothShading } );
	 levelText = new THREE.Mesh(levelTextGeometry, levelTextMaterial);

	 levelText.position.x = 1500;
	 levelText.position.y = 400;
	 levelText.position.z = -1500;
	 levelText.rotation.y = 3.5;

	 scene.add(levelText);
}
createLevelText();

var countText;
function countKills(){
	if(countText != undefined){
		scene.remove(countText);
	}
	var countTextGeometry = new THREE.TextGeometry("Killed" + " " + killCount,{
	size: 100,
	font: "helvetiker",
	weight: "normal",
	style: "normal"
	});

	 var countTextMaterial = new THREE.MeshPhongMaterial( { color: 0xff0000, specular: 0xffffcc, shininess: 30, shading: THREE.SmoothShading } );
	 countText = new THREE.Mesh(countTextGeometry, countTextMaterial);

	 countText.position.x = 1500;
	 countText.position.y = 200;
	 countText.position.z = -1500;
	 countText.rotation.y = 3.5;

	 scene.add(countText);
}

//create the player we see in the road
var player;
var gun;
function createPlayer(){
	var playerImages = ["mposx.png", "mnegx.png", "mposy.png", "mnegy.png","mposz.png", "mnegz.png"];
	var materialArrayP = [];

	for (var i = 0; i < 6; i++)
		materialArrayP.push( new THREE.MeshBasicMaterial({
			map: THREE.ImageUtils.loadTexture( playerImages[i] )
	}));

	var playerMaterial = new THREE.MeshFaceMaterial(materialArrayP);
	var playerGeometry = new THREE.CubeGeometry(150,500,150);

	player = new THREE.Mesh(playerGeometry, playerMaterial);
	player.position.y = -300;
  player.position.z = -2300;


	scene.add(player);

	//create the gun
	var gunMaterial = new THREE.MeshPhongMaterial( {color: 0xff0000} );;
	var gunGeometry = new THREE.CylinderGeometry(20, 40, 160, 200, 200, false);

	gun = new THREE.Mesh(gunGeometry, gunMaterial);
	gun.position.x = -105;
	gun.position.y = -340;
	gun.position.z = -2200;
	gun.rotation.x = 190;

	scene.add(gun);
}

//create zombie burgers
var objects = [];
function createZombies(){

	var images  = ["zomburger.png"];
	var materialArray2 = [];

	for (var i = 0; i < 6; i++)
		materialArray2.push( new THREE.MeshBasicMaterial({
				map: THREE.ImageUtils.loadTexture( images[i] )
	}));
	var zombieMaterial = new THREE.MeshFaceMaterial(materialArray2);
	var zombieGeometry = new THREE.SphereGeometry( 150, 150, 150 );
		for ( var i = 0; i < numZombies; i++ ) {

			var object = new THREE.Mesh(zombieGeometry, zombieMaterial);
			object.position.x = Math.random() * 2400 - 1200;
			object.position.y = -400;
			object.position.z = 4000 + Math.random() * 2400 - 1200;
			object.rotation.y = -30;
			scene.add( object );
			objects.push( object );
		}
}

	// create perspective camera
	var camera = new THREE.PerspectiveCamera(45, width / height, 0.1, 10000);
	camera.position.y = -4999;
	camera.position.z = -3670;
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
	var pointLight1 = new THREE.PointLight(0xffffff); pointLight1.position.set(-500, 0, -2900);scene.add(pointLight1);
	var pointLight2 = new THREE.PointLight(0xffffff); pointLight2.position.set(0, 0, 2900);scene.add(pointLight2);
	var ambient = new THREE.AmbientLight( 0xffffff );
	scene.add(ambient);

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
//zombie target practice

//ketchup bullets

function collisionDetection(){
	var raycaster = new THREE.Raycaster();
	for(var i=0; i< ketchup.length; i++){
		var origin = new THREE.Vector3(ketchup[i].position.x,ketchup[i].position.y,ketchup[i].position.z);
		var dir = new THREE.Vector3(ketchup[i].position.x,ketchup[i].position.y, 2000);
		dir.normalize();

		raycaster.set(origin, dir);
		var intersects = raycaster.intersectObjects( objects );

			for ( var j = 0; j < intersects.length; j++ ) {
				scene.remove(intersects[ j ].object);
				var o = objects.indexOf(intersects[ j ].object);
				objects.splice(o, 1);
				scene.remove(ketchup[i]);
				ketchup.splice(i, 1);
				killCount++;
        countKills();
				break;
			}
	}
	if(objects.length == 0 && gameState){
		alert("Level Up!!");
    level++;
    createLevelText();
		numZombies += 3;
		speed += 3;
		createZombies();
	}
}//This is where the zombies will be moving toward you!

function moveZombies()
{
	for(var i = 0; i < objects.length; i++) {
		objects[i].position.z -= speed;

		if(objects[i].position.z <= -2300)
		  endGame();
	}
}

	var currentlyPressedKeys = [];
	window.addEventListener("keydown", function(event) {
		currentlyPressedKeys[event.keyCode] = true;
	});
	window.addEventListener("keyup", function(event) {
		currentlyPressedKeys[event.keyCode] = false;
	});

  window.addEventListener("click", restartGame);

	window.addEventListener("keypress", function(event) {
		if (event.keyCode == 38){
			shooting();
		 }
     if(event.keyCode == 13){
       if(gameState == false){
         onStart();
       }
       else if(gameState == true && death == true){
         location.reload();
       }
     }
	});

function handleInput(){
  if(currentlyPressedKeys[37] == true){//Left
		player.position.x -= speed;
		gun.position.x -= speed;
		if(player.position.x < -2400){
			player.position.x = -2400;
			gun.position.x = -2400;
		}
	}

	else if(currentlyPressedKeys[39] == true){//right
		player.position.x += speed;
		gun.position.x += speed;
		if(player.position.x > 2400){
			player.position.x = 2400;
			gun.position.x = 2400;
		}
	}
	else if(currentlyPressedKeys[65]==true) {// a
		player.position.x += speed;
		gun.position.x += speed;
		if(player.position.x < -2400){
			player.position.x = -2400;
			gun.position.x = -2400;
		}
	}

	else if(currentlyPressedKeys[68]==true) {// d
		player.position.x -= speed;
		gun.position.x -= speed;
		if(player.position.x > 2400){
			player.position.x = 2400;
			gun.position.x = 2400;
		}
  }
  else if(currentlyPressedKeys[87]==true) {// w
      shooting();
      }

  else if(currentlyPressedKeys[32]==true) {//spacebar
      shooting();
      }
	}

function shooting(){
	var bGeom = new THREE.SphereGeometry(25,25,25);
	var bMat = new THREE.MeshPhongMaterial( {color: 0xff0000});
	var bullet = new THREE.Mesh(bGeom, bMat);
	bullet.position.x = gun.position.x;
	bullet.position.y = gun.position.y;
	bullet.position.z = gun.position.z;

	//collision detect function
	ketchup.push(bullet);
	scene.add(ketchup[ketchup.length-1]);
	audio.play();
}

//start button
var loserimage = ["zom_death.png", "zom_death.png"];
var materialArrayL = [];

for (var i = 0; i < 6; i++)
  materialArrayL.push( new THREE.MeshBasicMaterial({
    map: THREE.ImageUtils.loadTexture( loserimage[i] )
}));

function createDeathBox() {
var losermaterial = new THREE.MeshFaceMaterial(materialArrayL);
var losergeometry = new THREE.BoxGeometry(10,800,800);

var loserBox = new THREE.Mesh(losergeometry, losermaterial);
loserBox.position.x = 100;
loserBox.position.y = 50;
loserBox.position.z = -2300;
loserBox.rotation.y += 800;

scene.add(loserBox);
}

function restartGame() {
  if(gameState == false){
    onStart();
  }
  else if(gameState == true && death == true){
    location.reload();
  }
}

function endGame() {
  createDeathBox();
  speed = 0;
  death = true;
}

function onStart() {
	createZombies();
	createPlayer();
	gameState = true;
	speed = 10;
}

function render() {
	collisionDetection();
	handleInput();
	moveZombies();

	for(var i=0; i < ketchup.length; i++){
		if(ketchup[i].position.z >= 2500){
			scene.remove(ketchup[i]);
			ketchup.splice(i, 1);
		}
		else {
			ketchup[i].position.z += 150;
		}
	}

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
