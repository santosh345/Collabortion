'use strict'
app.controller('UserController',['$scope','UserService','$location','$rootScope','$cookieStore','$http',
	function($scope, UserService, $location, $rootScope,$cookieStore, $http) {
	var self=this;
	console.log("Inside UserController")
	self.user={
		id : '',
		name : '',
		role : '',
		password:'',
		address : '',
		mobile:'',
		isOnline:'',
		email:''
	}
	
	//create user function
	self.createUser = function(user) {
		console.log("Inside createUser function of UserController");
		UserService.createUser(user)
		.then(
				function(d) {
					$location.path("/")
				},
				function(errResponse) {
					console.error('Error while creating user');
				});
	};
	
	// submit button function
	
	//reset button function
	self.reset = function() {
		self.user = {
				id : '',
				name : '',
				role : '',
				password:'',
				address : '',
				mobile:'',
				isOnline:'',
				email:''
	};
		$scope.myForm.$setPristine(); //reset form
	};
	self.login = function()
	{
		console.log("Login user");
		UserService.loginUser(self.user)
		.then(
				function(d) {
					self.user=d;
					if(self.user.errorCode=="404")
						{
						alert("Invalid Login")
						this.user.userID = "";
						}
					if(self.user.userRole=="ROLE_ADMIN")
					{
						console.log("Admin logged in")
						$cookieStore.put('currentUser',this.user);
						$http.defaults.headers.common['Authorization'] = 'Basic'+ $rootScope.currentUser;
						$location.path('/')						
					}
					if(self.user.userRole=="ROLE_USER")
						{
						console.log("User logged in")
					$cookieStore.put('currentUser',this.user);
					$http.defaults.headers.common['Authorization'] = 'Basic'+ $rootScope.currentUser;
					$location.path('/')
						}
				},
				function(errResponse) {
					console.error('Error while logging in user');
				});
	};
	self.submit = function() {
		{
		console.log("Submit user");
		self.createUser(self.user);
		}
		self.reset();
	};
	
}])