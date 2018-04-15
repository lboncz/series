<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>Series Paradise</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
	<div class="main">
		<div class="header">
			<div class="header_resize">
				<div class="logo">
					<h1>Series Paradise</h1>
				</div>
				<div class="menu_nav">
					<ul>
						<li><a href="/">Home</a></li>
						<li><a href="series.html">Series</a></li>
						<li><a href="upload.html">Upload</a></li>
						<li class="active last"><a href="search.html">Search</a></li>
					</ul>
				</div>
			</div>
			<div class="content">
				<div class="content_resize">
					<div class="mainbar">
						<div class="article" align="center">
							<h2>Search</h2>
							<form method="get">
								<input type="text" name="title">
								<button type="submit">Search</button>
							</form>
							<p>
								<#list searchResult as s>
									<a href="${s.id}.html"><img src="${s.id}.jpg" alt="${s.title}" width="182" height="268"></a>
								</#list>
							</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
