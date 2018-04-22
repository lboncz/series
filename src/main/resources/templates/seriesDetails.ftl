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
						<li class="last"><a href="search.html">Search</a></li>
					</ul>
				</div>
			</div>
			<div class="content">
				<div class="content_resize">
					<div class="mainbar">
						<div class="article" align="center">
							<h2>${series.title}</h2>
							<img src="images/${series.poster}">
						<h2>Comments</h2>
						<p>
						<table>
							<thead>
								<tr>
									<th>Author</th>
									<th>Comment</th>
								</tr>
							</thead>
							<tbody>
							<#list comments as c>
								<tr>
									<td>${c.author}</td>
									<td>${c.content}</td>
								</tr>
							</#list>
							</tbody>
						</table>
						</p>
						<p>
						<h2>Post Comment</h2>
						<form method="post">
							Name: <input type="text" name="author">
							<br><textarea rows="4" cols="50" name="content">Enter comment here...</textarea>
							<br><button type="submit">Send</button>
						</form>
						</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
