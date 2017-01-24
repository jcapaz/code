<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Nom Nom</title>

    <!-- Bootstrap and datatable -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/jquery.dataTables.min.css" rel="stylesheet">
	
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
	<!--Script plugins-->
	<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.3.js"></script> 
	<script type="text/javascript" src="http://maps.google.com/maps/api/js?key=AIzaSyB5Q1xYeBo4NCBtuM1udB_Z7SWIh5cSeio"></script>  
	<script type="text/javascript" src="js/gmaps.js"></script>
	<script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="title-string.js"></script>

	<script>
		$(document).ready(function() {
			$('#table').DataTable({
				 columnDefs: [
				   { type: 'title-string', targets: 2}
				 ]
			  } );
		} );
		var info = table.page.info();
 
		$('#pagevalue').html(info.page+1);
	</script>
  </head>
  <body style="padding-top: 70px;">
	<!--Navigation bar -->
    <nav class="navbar navbar-inverse navbar-fixed-top" style="background-color: #BFB6AD;">
    <div class="container">
      <div class="navbar-header">
        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
          <span class="sr-only">Toggle navigation</span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </button>
		<!--Image icon-->
        <a class="navbar-brand" href="default.php">
          <img src="title-logo.png" width="234" height="32" alt="Nom Nom Yum Yum">
        </a>
      </div>
	  <!--Search form -->
      <div id="navbar" class="navbar-collapse collapse">
          <form class="navbar-form navbar-right" action="results.php">
            <div class="form-group">
              <input name="terms" type="text" placeholder="Search terms" class="form-control">
            </div>
            <div class="form-group">
              <input name="city" type="text" placeholder="City" class="form-control">
            </div>
            <button type="submit" class="btn btn-secondary">Search</button>
          </form>
        </div>
    </div>
  </nav>
  <div class="container">

  <div class="row">
  <div class="col-md-6">
  <!--Search results table -->
  <?php
	$names = array();
	$ids = array();
	$addresses = array();
	$stars = array();
	
	$terms="";
	if(isset($_GET['terms'])){
		$terms = $_GET['terms'];
	}
	$city ="New York";
	if(isset($_GET['city'])){
		$city = $_GET['city'];
	}
	$donuts = 0;
	if(isset($_POST['star'])){
		$donuts = $_POST['star'];
	}
	
	$count =0;
	$dbhost = 'localhost';
	$dbname = 'yelp';
	
	
	// Connect to test database
	$m = new MongoClient();

	$db = $m->$dbname;
	$businessQuery = array('categories' => new MongoRegex("/$terms/i"),'city' => new MongoRegex("/$city/i"),'stars' => array('$gt' => $donuts-.1 ));
	$cursor = $db->business->find($businessQuery);
	$count = $cursor -> count();
	$cursor-> sort(array('stars' => -1));
	foreach($cursor as $item){
		array_push($ids,$item['business_id']);
		array_push($names,$item['name']);
		array_push($addresses,$item['full_address']);
		array_push($stars,$item['stars']);
	}
  ?>
	<h1 class="text-center" style="color:#F5A9B0; font-family:Kristen ITC">Search Results</h1>
	<img class="center-block" alt="_____________" src="bar.png"></img>
		</br>
		<form class="form-inline" method="post">
		<div class=" col-md-7">
		</div>
		<div class="form-group col-md-5">

			<!-- Star search-->
			<label for="star">Donuts</label>
			<?php 
				$placeholder;
				if(isset($_POST['star'])){
					$placeholder = $_POST['star'];
				} 
				else{
					$placeholder = 1;
				}
			?>
			<input type="number" name="star" id="star" min="1" max="5" step=".5" placeholder="<?php echo"$placeholder" ?>"></input>
			<input type="submit" class="btn btn-secondary" value="Search"></input>
		</div>
		</form>
	  <table id="table" class="table table-hover">
		<thead>
		  <tr>
			<th class="font-center" style="font-family:Kristen ITC">Name</th>
			<th class="font-center" style="font-family:Kristen ITC">Address</th>
			<th class="font-center" style="font-family:Kristen ITC">Rating</th>
		  </tr>
		</thead>
		<tbody>
			<!--NEED loop for name address and rating-->
			<?php
				$i=0;
				foreach($names as $name){
					
					echo "<tr>";
					echo"<td><a href=\"redirect.php?donutStore=$ids[$i]\">$name</a></td>";
					echo"<td>$addresses[$i]</td>";
					echo"<td><span title=\"".$stars[$i]."\"><img src=\"star/".$stars[$i].".png\" height=\"25px\" width=\"107px\" alt=\"stars\"></img></span></td>";
					echo"</tr>";
					$i++;
				}
			?>
		</tbody>
	 </table>
	</div>
	<div class="col-md-6">
		<!--Geocoded map **Loop through resulting addresses from search**-->
		<div id="map" style="width: 100%; height: 500px; float:right; margin-left: 1px;" alt="Loading"></div>
		<p style="display:hidden;" id="pagevalue"></p>
		<script type="text/javascript">
				<?php
					$lat = 0;
					$lng = 0;
					$address = urlencode($addresses[0]);
					$url ='http://maps.googleapis.com/maps/api/geocode/json?address='.$address;
					
					$geocode = file_get_contents($url);
					$results = json_decode($geocode, true);
					if($results['status']=='OK'){
						$lat = $results['results'][0]['geometry']['location']['lat'];
						$lng = $results['results'][0]['geometry']['location']['lng'];
					}	
				?>
				$(document).ready(function(){
					map = new GMaps({
					div: '#map',
					lat: <?php echo "$lat" ?>,
					lng: <?php echo "$lng" ?>,
					zoom: 10,
				  });
				  
				  <?php
					$termArray = array("chinese", "donut", "japanese", "pharmacy","ice cream", "coffee","breakfast","pizza","mexican","burger","italian", "cake");
					$marker = "marker/arrow.png";
					if(in_array($terms,$termArray)){
						$marker = "marker/".$terms.".png";
					}
					$i = 0;
					$limit = 10;
					if(isset($_POST['limit'])){
						if($_POST['limit'] == "all"){
							$limit = count($names);
						}
						else{
							$limit = $_POST['limit'];
						}
					}
					if(count($names) < $limit){
						$limit = count($names);
					}
					for($i=0;$i<$limit;$i++){
						//calculate latitude and longitude based on address
						$lat = 0;
						$lng = 0;
						$address = urlencode($addresses[$i]);
						$url ='http://maps.googleapis.com/maps/api/geocode/json?address='.$address;
						$geocode = file_get_contents($url);
						$results = json_decode($geocode, true);
						if($results['status']=='OK'){
							$lat = $results['results'][0]['geometry']['location']['lat'];
							$lng = $results['results'][0]['geometry']['location']['lng'];
						}
						// add geo-marker
					   echo "map.addMarker({ \n";
					   echo "	lat: $lat,\n";
					   echo "	lng: $lng,\n";
					   echo "	icon: '$marker',";
					   echo "	title: "."\"".addslashes($names[$i])."\", \n";
					   echo "	infoWindow: {\n";
					   
					   //replaces line breaks in address **do not get rid of** 
					   $addr = 	str_replace("\n", " ", $addresses[$i]);
					   
					   echo "	content: '<p style=\"color:#E95272; font-family:Kristen ITC\"> Name : <td><a href=\"redirect.php?donutStore=$ids[$i]\">".addslashes($names[$i])."</a></td></br> Address: ". addslashes($addr)."</p>' }\n";
					   echo "});\n";
					   echo "\n\n";
					   
					}
					
				echo "});";
				
				 ?> 				 
		</script>
		<div style="float:right">
			<form method="post">
				<label style="color:#E95272; font-family:Kristen ITC">Limit to </label>
				<input type="submit" name="limit" class="btn btn-secondary" value="10"></input>
				<input type="submit" name="limit" class="btn btn-secondary" value="25"></input>
				<input type="submit" name="limit" class="btn btn-secondary" value="50"></input>
				<input type="submit" name="limit" class="btn btn-secondary" value="100"></input>
				<input type="submit" name="limit" class="btn btn-secondary" value="all"></input>
			</form>
		</div>
	</div>
</div>
  </div>
  </body>
</html>
