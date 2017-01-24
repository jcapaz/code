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
	<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.3.js"></script> 
	<script type="text/javascript" src="http://maps.google.com/maps/api/js?key=AIzaSyB5Q1xYeBo4NCBtuM1udB_Z7SWIh5cSeio"></script>  
	<script type="text/javascript" src="js/gmaps.js"></script>
	<script type="text/javascript" src="js/jquery.dataTables.min.js"></script>

	<script>
		$(document).ready(function() {
			$('#friend').DataTable();
		} );
	</script>
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
		$rtgSnc;
		$fans = 0;
		$id = "";
		$star = 0;
		$friends = array();
		$compliments = array("none" => "none");
		if(isset($_GET['donutEater'])){
			$id = $_GET['donutEater'];
		}
		//set query
		$userQuery = array('user_id' => $id);

		$cursor = $db->user->find($userQuery);

		$cursor -> limit(1);
		foreach($cursor as $item){
			$name = $item['name'];
			$rtgSnc = $item['yelping_since'];
			$fans = $item['fans'];
			$stars = round($item['average_stars'],0);
			$friends = $item['friends'];
			$compliments = $item['compliments'];
		}
		$dates = array();
		$reviews = array();
		$ratings = array();
		$businessId = array();
		$reviewQuery = array('user_id' => $id);

		$cursor = $db->review->find($reviewQuery);
		$cursor-> sort(array('date' => -1));
		foreach($cursor as $item){
			array_push($businessId,$item['business_id']);
			array_push($dates,$item['date']);
			array_push($reviews,$item['text']);
			array_push($ratings,$item['stars']);
		}
		 
		//echo $name;
	?>
	<div id="name" class="col-md-6">
			<p style="color:#F5A9B0; font-size:50px; font-family:Kristen ITC"><?php echo $name ?></p>
			
			
			<img src="star/<?php echo $stars ?>.png"  height="50px" width="214px" alt="4.5"></img>
			</br>
			<img src="cat.png" alt="" height="30%" width="30%"></img>
			<table class="table">
				<tr>
				<td>
					<label style="color:#8BDEE0">Rating since</label>
					<p><?php echo "$rtgSnc"?></p>
				</td>
				<td>
					<label style="color:#EB5B76">Fans</label>
					<p><?php echo $fans; ?></p>
				</td>
			</table>
	</div>
	<div class="col-md-3">
	<img src="unicorn.png" class="center-block" alt="Reviews" width="90%" height="90%"></img>
	    <table  class="table table-hover">
			<thead>
				<th>Compliment</th>
				<th>Votes</th>
			</thead>
			<tbody>
			<?php
				foreach($compliments as $compliment => $compliment_value) {
					echo "<tr>";
					echo "<td> $compliment</td>";
					echo "<td> $compliment_value</td>";
					echo "</tr>";
				}
					
			?>
			</tbody>
		</table>
	</div>
	<div class="col-md-3"> 
		<img src="friends.png" class="center-block" alt="Reviews" width="70%" height="70%"></img>
		<table id="friend" class="table table-hover">
			<thead>
				<th>Name</th>
			</thead>
			<tbody>
				<?php
					foreach($friends as $friend){
						echo "<tr>";
						$reviewQuery = array('user_id' => $friend);

						$cursor = $db->user->find($reviewQuery);
						$user = $cursor -> getnext();
						//print_r($user);
						
						echo "	<td><a href=\"user.php?donutEater=$friend\">".$user['name']."</a></td>";
						echo "</tr>";
					}
				?>
			</tbody>
		</table>
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
			foreach($dates as $date){
				echo "<tr>";
				//print_r($user);
				$reviewQuery = array('business_id' => $businessId[$i]);

				$cursor = $db->business->find($reviewQuery);
				$user = $cursor -> getnext();
				$business = $user['name'];
				echo "	<td><a href=\"redirect.php?donutStore=$businessId[$i]\">".$business."</a></td>";
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
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
  </body>
</html>
