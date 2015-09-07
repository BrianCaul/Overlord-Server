<html>
<head>
<link href="css/bootstrap-united.css" rel="stylesheet" />
<link href="css/bootstrap-responsive.css" rel="stylesheet" />
<style>
body {
	height: 100%;
	margin: 0;
	background: url(assets/img/books.jpg);
	background-size: 1440px 800px;
	background-repeat: no-repeat;
	display: compact;
}
</style>
</head>
<body>
	<div class="navbar navbar-default navbar-fixed-top">

		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-responsive-collapse">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
		</div>

		<div class="navbar-collapse collapse navbar-responsive-collapse">
			<ul class="nav navbar-nav">
				<li><a href="#Users">Users</a></li>
				<li><a href="#Companies">Companies</a></li>
				<li><a href="#Events">Events</a></li>
				<li><a href="#Venues">Venues</a></li>
				<li><a href="#Areas">Areas</a></li>
				<li><a href="#Positions">Positions</a></li>
			</ul>
		</div>
		<!-- /.nav-collapse -->
	</div>
	<div class="container">
		<br />
		<div class="jumbotron">
			<div>
				<h1>Welcome to the Overlord API!</h1>
				<p>This documentation is evolving as the overlord API is being
					built out. To start this documentation highlights the main
					operations for each type of entity. It provides an example request
					for each operation along with an example response.</p>

			</div>
		</div>

		<div class="row">
			<div class="col-lg-12">
				<h1 id="Users">Users</h1>
				<div class="jumbotron">
					<h2>rest/users</h2>
					<p>Get Request - List all user objects and their associated
						data</p>
					<p>Example Request:</p>
					<pre>No attributes</pre>
					<p>Example Response:</p>
					<pre>[{  
	"id":65,
	"userName":"briancaul",
	"name":"Brian Caul",
	"phone":"0857685431",
	"userType":"Attendant",
	"email":"caul1990@hotmail.com"
},
{  
	"id":66,
	"userName":"DavidMark",
	"name":"David Mark",
	"phone":"0857685431",
	"userType":"Supervisor",
	"email":"test1990@hotmail.com"
}]</pre>

					<p>
						<a class="btn btn-primary btn-lg" href="rest/users" role="button">Try
							it</a>
					</p>
				</div>
			</div>
		</div>
		
				<div class="row">
			<div class="col-lg-12">
				<p></p>
				<div class="jumbotron">
					<h2>rest/users/{id}</h2>
					<p>Get Request - List a single user object and its associated
						data</p>
					<p>Example Request:</p>
					<pre><strong>id</strong>: The users unique id

rest/users/65
					</pre>
					<p>Example Response:</p>
					<pre>{  
	"id":65,
	"userName":"briancaul",
	"name":"Brian Caul",
	"phone":"0857685431",
	"userType":"Attendant",
	"email":"caul1990@hotmail.com"
}</pre>

					<p>
						<a class="btn btn-primary btn-lg" href="rest/users/65" role="button">Try
							it</a>
					</p>
				</div>
			</div>
		</div>
		
				<div class="row">
			<div class="col-lg-12">
				<p></p>
				<div class="jumbotron">
					<h2>rest/users/{id}</h2>
					<p>Delete Request - Delete a user object and all associated data</p>
					<p>Example Request:</p>
<pre><strong>id</strong>: The users unique id

rest/users/66</pre>
					<p>Example Response:</p>
					<pre>Sucessfully Deleted ID: 66</pre>
				</div>
			</div>
		</div>
		
				<div class="row">
			<div class="col-lg-12">
				<p></p>
				<div class="jumbotron">
					<h2>rest/users</h2>
					<p>Post Request - Create a new user object</p>
					<p>Example Request:</p>
					<pre><strong>phone</strong>: The users phone number
<strong>email</strong>: The users email address
<strong>name</strong>: The users full name
<strong>userType</strong>: The users type as one of Attendant, Supervisor, Event Manager
<strong>username</strong>: The users username
<strong>password</strong>: The users password
<strong>companyId</strong>: The users company id

rest/users</pre>
					<p>Example Response:</p>
					<pre>{  
	"id":65,
	"userName":"briancaul",
	"name":"Brian Caul",
	"phone":"0857685431",
	"userType":"Attendant",
	"email":"caul1990@hotmail.com"
}</pre>


				</div>
			</div>
		</div>
		
				<div class="row">
			<div class="col-lg-12">
				<p></p>
				<div class="jumbotron">
					<h2>rest/users/{id}</h2>
					<p>Post Request - Update an existing user object</p>
					<p>Example Request:</p>
					<pre><strong>phone</strong>: The users phone number
<strong>email</strong>: The users email address
<strong>name</strong>: The users full name
<strong>userType</strong>: The users type as one of Attendant, Supervisor, Event Manager
<strong>username</strong>: The users username
<strong>password</strong>: The users password
<strong>companyId</strong>: The users company id

rest/users/66
</pre>
					<p>Example Response:</p>
					<pre>{  
	"id":67,
	"userName":"briancaul",
	"name":"Brian Caul",
	"phone":"0857685431",
	"userType":"Attendant",
	"email":"caul1990@hotmail.com"
}</pre>

				</div>
			</div>
		</div>
		
				<div class="row">
			<div class="col-lg-12">
				<p></p>
				<div class="jumbotron">
					<h2>rest/users/signin</h2>
					<p>Post Request - Signin as an existing user</p>
					<p>Example Request:</p>
					<pre><strong>uname</strong>: The users username
<strong>pass</strong>: The users password

/rest/users/signin
</pre>
					<p>Example Response:</p>
					<pre>{  
	"id":65,
	"userName":"briancaul",
	"name":"Brian Caul",
	"phone":"0857685431",
	"userType":"Attendant",
	"email":"caul1990@hotmail.com"
}
					</pre>

				</div>
			</div>
		</div>
		
				<div class="row">
			<div class="col-lg-12">
				<p></p>
				<div class="jumbotron">
					<h2>/rest/users/{userid}/setposition</h2>
					<p>Post Request - Assign a position to a user</p>
					<p>Example Request:</p>
					<pre><strong>positionId</strong>: The position id to assign the user to</pre>
					<p>Example Response:</p>
					<pre>{  
	"id":65,
	"userName":"briancaul",
	"name":"Brian Caul",
	"phone":"0857685431",
	"userType":"Attendant",
	"email":"caul1990@hotmail.com"
}
					</pre>

				</div>
			</div>
		</div>
		
		<div class="row">
			<div class="col-lg-12">
				<h1 id="Companies">Companies</h1>
				<p></p>
				<div class="jumbotron">
					<h3>Further documentation coming soon</h3>
				</div>
			</div>
		</div>
		
				<div class="row">
			<div class="col-lg-12">
				<h1 id="Events">Events</h1>
				<p></p>
				<div class="jumbotron">
					<h3>Further documentation coming soon</h3>
				</div>
			</div>
		</div>
		
				<div class="row">
			<div class="col-lg-12">
				<h1 id="Venues">Venues</h1>
				<p></p>
				<div class="jumbotron">
					<h3>Further documentation coming soon</h3>
				</div>
			</div>
		</div>
		
		<div class="row">
			<div class="col-lg-12">
				<h1 id="Areas">Areas</h1>
				<p></p>
				<div class="jumbotron">
					<h3>Further documentation coming soon</h3>
				</div>
			</div>
		</div>
		
		<div class="row">
			<div class="col-lg-12">
				<h1 id="Positions">Positions</h1>
				<p></p>
				<div class="jumbotron">
					<h3>Further documentation coming soon</h3>
				</div>
			</div>
		</div>

	</div>
	<script src="js/jquery-1.8.3.js">
		
	</script>

	<script src="js/bootstrap.js">
		
	</script>

</body>
</html>
