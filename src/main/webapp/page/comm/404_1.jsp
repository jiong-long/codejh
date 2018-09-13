<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Page Not Found</title>
<link rel="stylesheet" type="text/css" href="/jiong/page/comm/404_1.css" />
</head>
<body translate="no">
	<div class="content">
		<canvas class="snow" id="snow" width="1349" height="400"></canvas>
		<div class="main-text">
			<h1>
				天啊,那页失踪了。
			</h1>
		</div>
		<div class="ground">
			<div class="mound">
				<div class="mound_text">404</div>
				<div class="mound_spade"></div>
			</div>
		</div>
	</div>


	<script>
		(function() {
			function ready(fn) {
				if (document.readyState != 'loading') {
					fn();
				} else {
					document.addEventListener('DOMContentLoaded', fn);
				}
			}

			function makeSnow(el) {
				var ctx = el.getContext('2d');
				var width = 0;
				var height = 0;
				var particles = [];

				var Particle = function() {
					this.x = this.y = this.dx = this.dy = 0;
					this.reset();
				}

				Particle.prototype.reset = function() {
					this.y = Math.random() * height;
					this.x = Math.random() * width;
					this.dx = (Math.random() * 1) - 0.5;
					this.dy = (Math.random() * 0.5) + 0.5;
				}

				function createParticles(count) {
					if (count != particles.length) {
						particles = [];
						for (var i = 0; i < count; i++) {
							particles.push(new Particle());
						}
					}
				}

				function onResize() {
					width = window.innerWidth;
					height = window.innerHeight;
					el.width = width;
					el.height = height;

					createParticles((width * height) / 10000);
				}

				function updateParticles() {
					ctx.clearRect(0, 0, width, height);
					ctx.fillStyle = '#f6f9fa';

					particles.forEach(function(particle) {
						particle.y += particle.dy;
						particle.x += particle.dx;

						if (particle.y > height) {
							particle.y = 0;
						}

						if (particle.x > width) {
							particle.reset();
							particle.y = 0;
						}

						ctx.beginPath();
						ctx.arc(particle.x, particle.y, 5, 0, Math.PI * 2,
								false);
						ctx.fill();
					});

					window.requestAnimationFrame(updateParticles);
				}

				onResize();
				updateParticles();
			}

			ready(function() {
				var canvas = document.getElementById('snow');
				makeSnow(canvas);
			});
		})();
	</script>
</body>
</html>