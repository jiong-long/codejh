<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>imageZoom</title>
<link type="text/css" rev="stylesheet" rel="stylesheet" href="css/reset.css">
<link type="text/css" rev="stylesheet" rel="stylesheet" href="css/zoom.css">
<script type="text/javascript" src="js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="js/jquery.SuperSlide.2.1.3.js"></script>
<script type="text/javascript" src="js/e-smart-zoom-jquery.min.js"></script>
<script type="text/javascript">
	function resize(){
		$("div.zoom,div.zoombtn").width($(window).width());
		$("div.zoom,div.zoomlist,.list,.imgContainer").height($(window).height());
		$("div.zoomlist,.tempWrap,.imgContainer").width($(window).width()-80);
	};
	$(window).resize(function(){resize()});
	$(function(){
		resize();
		jQuery(".zoom").slide({mainCell:".zoomlist .list",prevCell:".prev",nextCell:".next",effect:"fade",scroll:1,vis:1});
		$('img#imageFullScreen_1').smartZoom({'containerClass':'zoomableContainer'});
	});
</script>
</head>

<body>
     <div class="zoom">
        <div class="zoomlist of_h">
            <div class="list">
                <div class="zoombd">
                    <div class="imgContainer">
                        <img id="imageFullScreen_1" src="images/1.jpg"/>
                    </div>                 
                </div>
            </div>
        </div>
     </div>
</body>
</html>
