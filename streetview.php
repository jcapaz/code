<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>Street View</title>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script> 
	<script type="text/javascript" src="http://maps.google.com/maps/api/js?key=AIzaSyB5Q1xYeBo4NCBtuM1udB_Z7SWIh5cSeio"></script>  
	<script type="text/javascript" src="js/gmaps.js"></script>

</head>
<body>



  <script type="text/javascript">



  $(document).ready(function(){
    var streetView1 = GMaps.createPanorama({
      el: '#streetview1',
      lat: 30.8507026,
      lng: -83.2797442
    });

  });


  </script>




  <p> Street view panorama of the specified location  </p>

   <div id="streetview1" style="width: 300px; height: 200px"></div>


 </body>
 </html>
