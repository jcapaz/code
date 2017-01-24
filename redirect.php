<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Nom Nom</title>

    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script> 
	<script type="text/javascript" src="http://maps.google.com/maps/api/js?key=AIzaSyB5Q1xYeBo4NCBtuM1udB_Z7SWIh5cSeio"></script>  
	<script type="text/javascript" src="js/gmaps.js"></script>
  </head>
  <body style="padding-top: 70px;">
    <nav class="navbar navbar-inverse navbar-fixed-top" style="background-color: #BFB6AD;">
    <div class="container">
      <div class="navbar-header">
        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
          <span class="sr-only">Toggle navigation</span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="default.php">
          <img src="title-logo.png" width="234" height="32" alt="Nom Nom Yum Yum">
        </a>
      </div>
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
  <div id="container">
	<!--table-->
	<?php 
		$dbhost = 'localhost';
		$dbname = 'yelp';

		// Connect to test database
		$m = new MongoClient();

		$db = $m->$dbname;
		$name= "";
		$address ="";
		$lat = "";
		$lng = "";
		$hours = array();
		$stars = "";
		$price = 0;
		$alcohol = "none";
		$family = false;
		$romantic = false;
		$hipster = false;
		$categories = array();
			//check post values
		$id="";
		if(isset($_GET['donutStore'])){
			$id = $_GET['donutStore'];
		}

		//set query
		$businessQuery = array('business_id' => $id);

		$cursor = $db->business->find($businessQuery);
		$count = 0;
		$cursor -> limit(1);
		foreach($cursor as $item){
			$count++;
			$name = $item['name'];
			$address = $item['full_address'];
			$lat = $item['latitude'];
			$lng = $item['longitude'];
			$stars = $item['stars'];
			//attributes
			if(isset($item['attributes']['Price Range'])){
				$price = $item['attributes']['Price Range'];
			}
			if(isset($item['attributes']['Alcohol'])){
				$alcohol = $item['attributes']['Alcohol'];
			}
			if(isset($item['attributes']['Good for Kids'])){
				$family = $item['attributes']['Good for Kids'];
			}
			if(isset($item['attributes']['Ambience']['romantic'])){
				$romantic = $item['attributes']['Ambience']['romantic'];
			}
			if(isset($item['attributes']['Ambience']['hipster'])){
				$hipster = $item['attributes']['Ambience']['hipster'];
			}
			//hours
			if(isset($item['hours']['Friday'])){
				if(isset($item['hours']['Monday'])){
					$hours[0] = $item['hours']['Monday']['open']."-".$item['hours']['Monday']['close'];
				}
				else{
					$hours[0] = "Closed";
				}
				if(isset($item['hours']['Tuesday'])){
					$hours[1] = $item['hours']['Tuesday']['open']."-".$item['hours']['Tuesday']['close'];
				}
				else{
					$hours[1] = "Closed";
				}
				if(isset($item['hours']['Wednesday'])){
					$hours[2] = $item['hours']['Wednesday']['open']."-".$item['hours']['Wednesday']['close'];
				}
				else{
					$hours[2] = "Closed";
				}
				if(isset($item['hours']['Thursday'])){
					$hours[3] = $item['hours']['Thursday']['open']."-".$item['hours']['Thursday']['close'];
				}
				else{
					$hours[3] = "Closed";
				}
				if(isset($item['hours']['Friday'])){
					$hours[4] = $item['hours']['Friday']['open']."-".$item['hours']['Friday']['close'];
				}
				else{
					$hours[4] = "Closed";
				}
				if(isset($item['hours']['Saturday'])){
					$hours[5] = $item['hours']['Saturday']['open']."-".$item['hours']['Saturday']['close'];
				}
				else{
					$hours[5] = "Closed";
				}
				if(isset($item['hours']['Sunday'])){
					$hours[6] = $item['hours']['Sunday']['open']."-".$item['hours']['Sunday']['close'];
				}
				else{
					$hours[6] = "Closed";
				}
			}
			else{
				for($i=0; $i<7;$i++){
					$hours[$i] = "No data";
				}
			}
			$categories = $item['categories'];
			$lat = $item['latitude'];
			$lng = $item['longitude'];
		}
		$userids = array();
		$dates = array();
		$reviews = array();
		$ratings = array();
		$reviewQuery = array('business_id' => $id);

		$cursor = $db->review->find($reviewQuery);
		$cursor-> sort(array('date' => -1));
		foreach($cursor as $item){
			array_push($userids,$item['user_id']);
			array_push($dates,$item['date']);
			array_push($reviews,$item['text']);
			array_push($ratings,$item['stars']);
		}
		 
		//echo $name;
	?>
	<div id="name" class="col-md-6">
			<p style="color:#F5A9B0; font-size:50px; font-family:Kristen ITC"><?php echo $name ?><a href="http://www.twitter.com/search?q= <?php echo $name ?> "><img src="twitter.png" alt="Twitter" width="10%" height="10%"></a></p>
			
			
			<img src="star/<?php echo $stars ?>.png"  height="50px" width="214px" alt="4.5"></img>
			<div id="streetview1" style="width: 50%; min-height:300px"></div>
			<?php
				/*$lat = 0;
				$lng = 0;
				$addr = urlencode($address);
				$url ='http://maps.googleapis.com/maps/api/geocode/json?address='.$addr;
				$geocode = file_get_contents($url);
				$results = json_decode($geocode, true);
				if($results['status']=='OK'){
					$lat = $results['results'][0]['geometry']['location']['lat'];
					$lng = $results['results'][0]['geometry']['location']['lng'];
				}*/
			?>
				<script type="text/javascript">
					  $(document).ready(function(){
						var streetView1 = GMaps.createPanorama({
						  el: '#streetview1',
						  lat: <?php echo $lat ?>,
						  lng: <?php echo $lng ?>
						});
					  });
				</script>
			</br>
			<p>
			<?php
				if($price>3){
					echo "<img src=\"rich.png\" alt=\"Pricy\" width=\"10%\" height=\"10%\"></img>";
				}
			?>
			<?php
				if($romantic==true){
					echo "<img src=\"love.png\" alt=\"Romantic\" width=\"8%\" height=\"8%\"></img>";
				}
			?>
			<?php
				if($family==true){
					echo "<img src=\"family.png\" alt=\"Family friendly\" width=\"10%\" height=\"10%\"></img>";
				}
			?>
			<?php
				if($alcohol != "none"){
					echo "<img src=\"alcohol.png\" alt=\"alcohol\" width=\"8%\" height=\"8%\"></img>";
				}
			?>
			<?php
				if($hipster == true){
					echo "<img src=\"hipster.png\" alt=\"hipster chic\" width=\"10%\" height=\"10%\"></img>";
				}
			?>
			</p>
			<table class="table">
				<tr>
				<td>
					<label style="color:#8BDEE0">Categories</label>
					<p>
					<?php
						foreach($categories as $category){
							echo $category.", ";
						}
					?>
					</p>
				</td>
				<td>
					<label style="color:#EB5B76">Address</label>
					<p><?php echo $address; ?></p>
				</td>
			</table>
	</div>
	<div class="col-md-3"> 
		<!--Store hours-->
		</br>
		</br>
		<img src="hours-logo.png" alt="hours" class="center-block" width="200px" height="80px"></img>
		<table class="table">
			<tr>
				<td style="color:#F5A9B0; font-family:Kristen ITC">M</td>
				<td><?php echo $hours[0] ?></td>
			</tr>
			<tr>
				<td style="color:#F5A9B0; font-family:Kristen ITC">T</td>
				<td><?php echo $hours[1] ?></td>
			</tr>
			<tr>
				<td style="color:#F5A9B0; font-family:Kristen ITC">W</td>
				<td><?php echo $hours[2] ?></td>
			</tr>
			<tr>
				<td style="color:#F5A9B0; font-family:Kristen ITC">Th</td>
				<td><?php echo $hours[3] ?></td>
			</tr>
			<tr>
				<td style="color:#F5A9B0; font-family:Kristen ITC">F</td>
				<td><?php echo $hours[4] ?></td>
			</tr>
			<tr>
				<td style="color:#F5A9B0; font-family:Kristen ITC">S</td>
				<td><?php echo $hours[5] ?></td>
			</tr>
			<tr>
				<td style="color:#F5A9B0; font-family:Kristen ITC">Su</td>
				<td><?php echo $hours[6] ?></td>
			</tr>
		</table>
	</div>
	<div class="col-md-3">
		</br>
		</br>
		<img src="photos.png" class="center-block" width="100%" height="100%"></img>
		</br>
		<div id="myCarousel" class="carousel slide" >
				  <!-- Indicators -->
				 <ol class="carousel-indicators">
				    <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
				    <li data-target="#myCarousel" data-slide-to="1"></li>
				    <li data-target="#myCarousel" data-slide-to="2"></li>
				    <li data-target="#myCarousel" data-slide-to="3"></li>
				 </ol>

				 <!-- Wrapper for slides -->
				 <div class="carousel-inner" role="listbox">
				    <div class="item active">
						     <img src="logo.png" alt="logo">
				    </div>
				    <div class="item">
				        <img src="icon.png" alt="logo">
				    </div>
				 </div>

				 <!-- Left and right controls -->
				 <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
				    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
				    <span class="sr-only">Previous</span>
				 </a>
				 <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
				    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
				    <span class="sr-only">Next</span>
				 </a>
		</div>
	</div>
  </div>
	
	<div class="col-md-12">
	</br></br>
		<img src="reviews.png" class="center-block" alt="Reviews" width="20%" height="20%"></img>
		<table class="table table-hover">
		<thead>
		  <tr class="text-center" style="color:#8BDEE0">
			<th>Name</th>
			<th>Date</th>
			<th>Rating</th>
			<th>Review</th>
		  </tr>
		</thead>
		<tbody>
			
		  <?php
			//this is where the data will go
			$i=0;
			foreach($userids as $userid){
				echo "<tr>";
				$reviewQuery = array('user_id' => $userid);

				$cursor = $db->user->find($reviewQuery);
				$user = $cursor -> getnext();
				//print_r($user);
				
				echo "	<td><a href=\"user.php?donutEater=$userid\">".$user['name']."</a></td>";
				echo "	<td>".$dates[$i]."</td>";
				echo "	<td><img src=\"star/".$ratings[$i].".png\" height=\"20px\" width=\"85px\" alt=\"*****\"></img></td>";
				echo "	<td>".$reviews[$i]."</td>";
				echo "</tr>";
				$i++;
			}
		  ?>
		</tbody>
	 </table>
	</div>
	<div class="col-md-12">
		<p style="color:#F5A9B0; font-family:Kristen ITC">Made by: Alex Cook and Jeanne Capaz</p>
	</div>
	
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
  </body>
</html>
