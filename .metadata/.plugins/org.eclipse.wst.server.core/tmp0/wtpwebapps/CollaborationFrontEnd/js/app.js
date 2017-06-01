var app = angular.module("myApp",['ngRoute','ngCookies']);
app.config(function($routeProvider) {
	$routeProvider
	.when('/', {
		templateUrl : 'Common/Home.html'	
	})
	.when('/goRegister', {
		templateUrl : 'c_user/Register.html',
		controller : 'UserController'
	})
	.when('/goCreateBlog',{
		templateUrl : 'c_blog/CreateBlog.html',
		controller : 'BlogController'
	})
	/*.when('/goAdminHome',{
		templateUrl : 'c_admin/AdminHome.html',
		controller : 'UserController'
	})
	*//*.when('/goUserHome',{
		templateUrl : 'c_user/UserHome.html',
		controller : 'UserController'
	})*/
	.when('/goLogin',{
		templateUrl : 'c_user/login.html',
		controller : 'UserController'
	})
	.when('/goViewBlog',{
		templateUrl : 'c_blog/ViewBlog.html',
		controller : 'BlogController'
	})
	.when('/goCreateForum',{
		templateUrl : 'c_forum/CreateForum.html',
		controller : 'ForumController'
	})
	.when('/goEditBlog',{
		templateUrl : 'c_blog/EditBlog.html',
		controller : 'BlogController'
	})
	.when('/goDeleteBlog',{
		templateUrl : 'c_blog/DeleteBlog.html',
		controller : 'BlogController'
	})
	
	
	.otherwise({
		redirectTo : '/'
	});
});
app.run( function ($rootScope, $location,$cookieStore, $http) {
	
	$rootScope.currentUser = $cookieStore.get('currentUser') || {};
    if ($rootScope.currentUser) {
        $http.defaults.headers.common['Authorization'] = 'Basic' + $rootScope.currentUser; 
    }

	 $rootScope.$on('$locationChangeStart', function (event, next, current) {
		 //console.log("$locationChangeStart")
		 var userPages=['/goUserHome']
		 //var adminPages=['/goAdminHome']
		 var currentPage=$location.path()
		 var isUserPage=$.inArray(currentPage,userPages)
		 //var isAdminPage=$.inArray(currentPage,adminPages)
		 
		 var isLoggedIn = $rootScope.currentUser.userID;
		 
		 //console.log("isLoggedIn:" +isLoggedIn)
	     console.log("isUserPage:" +isUserPage)
	    // console.log("isAdminPage:" +isAdminPage)
	     if(!isLoggedIn)
	    	 {
	    	 $rootScope.notLogged =true 
	    	 $rootScope.isUser= false
	    	 $rootScope.isAdmin =false 
	    	 if(isUserPage===0){
	    		 alert("Login to proceed")
	    		 $location.path('/');
	    	 }
	    	 }
	     else{
	    	 var role= $rootScope.currentUser.userRole;
	    	 if(role=='ROLE_ADMIN')
	    		 {
	    		 console.log("Admin logged in")
	    		 $rootScope.isAdmin = true 
	    		 $rootScope.notLogged = false 
	    		 $rootScope.isUser= false
	    		 }
	    	 else{
	    		 console.log("User logged in")
	    		 $rootScope.notLogged =false 
	    		 $rootScope.isAdmin =false 
	    		 $rootScope.isUser= true
	    	 }
	    	 /*if(isAdminPage===0 && role!='ROLE_ADMIN' )
			 {
			  alert("You can not do this operation as you are logged as : " + role )
			   $location.path('/');			 
			 }*/
	     }
	 });
});