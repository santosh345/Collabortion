app.controller('BlogController',['$scope','BlogService','$location','$rootScope','$cookieStore','$http',
	function($scope, BlogService, $location, $rootScope,$cookieStore, $http)
	{
		console.log("Starting Of BlogController")
		this.blog = {
						id : '',
						title : '',
						description : '',
						user_id : '',
						comments : '',
						datecreated : '',
						status : '',
					};
		this.blogs=[];
		this.createBlog = function(blog) {
		console.log("createBlog!")
		BlogService.createBlog(blog)
		.then(
				function(a) 
				{
					this.blog=a;
					if(this.blog.ErrorCode==200)
					{
						return"Thank You Blog Created Successfully";
					}
					else if(this.blog.ErrorCode==400)
					{
						return"User Not Logged In Please Log In First To Create Blog";
					}
					else if(this.blog.ErrorCode==404)
					{
						return"Error Creating Blog Please Try Again";
					}
					$location.path("/")
				},
				function(errResponse) 
				{
						console.error('Error while creating Blog.');
			});
		};
		this.updateBlog = function(blog) {
			console.log("updateBlog!")
			this.blog.id=$rootScope.blog.id
			BlogService.updateBlog(blog)
			.then(
					function(a) 
					{
						this.blog=a;
						if(this.blog.ErrorCode==200)
						{
							return"Thank You Blog Updated Successfully";
							$location.path("/")
						}
						else if(this.blog.ErrorCode==400)
						{
							return"User Not Logged In Please Log In First To Update Blog";
							$location.path("/login")
						}
						else if(this.blog.ErrorCode==404)
						{
							return"Error Updating Blog Please Try Again";
							$location.path("/editBlog")
						}
						else if(this.blog.ErrorCode==500)
						{
							return"This Blog Is Not Created By You So You Cannot Update This Blog";
							$location.path("/")
						}
						
					},
					function(errResponse) 
					{
							console.error('Error while updating Blog.');
				});
			};
		this.fetchBlogById = function(blog) {
			console.log("fetchBlogById")
			BlogService.fetchBlogById(blog)
			.then(
					function(a)
					{
						this.blog=a;
						$rootScope.blog=a;
						console.log(this.blog)
						return"Thank You Blog Fetched Successfully";
						$location.path("/displayBlogById")
					},
					function(errResponse) 
					{
							console.error('Error while fetching Blog.');
				});
			};
		this.fetchAllBlogs = function() {
			console.log("fetchAllBlogs!")
			BlogService.fetchAllBlogs()
			.then(
					function(d) 
					{
						this.blogs=d;
						if(this.blogs.length==0)
						{
							return"There Are No Blogs To Display";
						}
						$rootScope.blogs=d;
						console.log(this.blogs)
						return"Thank You Blogs Fetched Successfully";
						$location.path('/displayBlog')
					},
					function(errResponse) 
					{
							console.error('Error while Fetching Blogs.');
				});
			};
			this.fetchAllPendingBlogs = function() {
				console.log("fetchAllPendingBlogs!")
				BlogService.fetchAllPendingBlogs()
				.then(
						function(d)
						{
							this.blogs=d;
							if(this.blogs.length==0)
							{
								return"There Are No Blogs To Display";
							}
							$rootScope.blogs=d;
							console.log(this.blogs)
							return"Thank You Blogs Fetched Successfully";
							$location.path('/adminManageBlogs')
						},
						function(errResponse) 
						{
								console.error('Error while Fetching Blogs.');
					});
				};
			this.display = function() {
				{
					console.log('Display All Blog');
					this.fetchAllBlogs();
				}
			};
			this.displayPendingBlogs = function() {
				{
					console.log('Display All Pending Blogs');
					this.fetchAllPendingBlogs();
				}
			};
			this.deleteBlog = function(blog) {
				console.log("deleteBlog!")
				BlogService.deleteBlog(blog)
				.then(
						function(d) 
						{
							this.blog=d;
							if(this.blog.errorCode==200)
							{
								return"Thank You Blog Deleted Successfully";
							}
							else if(this.blog.errorCode==400)
							{
								return"User Not Logged In Please Log In First To Delete Blog";
							}
							else if(this.blog.errorCode==404)
							{
								return"No Such Blog Exists";
							}
							else if(this.blog.errorCode==500)
							{
								return"This Blog Is Not Created By You So You Cannot Delete This Blog";
							}
							
						},
						function(errResponse)
						{
								console.error('Error while deleting Blog.');
					});
				};
		this.submit = function() {
			{
				console.log('Creating New Blog', this.blog);
				this.createBlog(this.blog);
			}
		};
		this.update = function(blog) {
				this.blog.id=blog;
				console.log('Update Blog', this.blog.id);
				BlogService.fetchBlogById(blog)
				.then(
					function(d) 
					{
						this.blog=d;
						$rootScope.blog=d;
						console.log(this.blog)
						$location.path('/editBlog')
					},
					function(errResponse) 
					{
							console.error('Error while Fetching Blogs.');
				});
			}
		this.approve = function(blog) {
			this.blog.id=blog;
			console.log('Update Blog Status To Approve', this.blog.id);
			BlogService.approveBlog(blog)
			.then(
				function(a) 
				{
					this.blog=a;
					if(this.blog.errorCode==200)
					{
						return"Thank You Blog Status Updated To Approved Successfully";
						$location.path('/admin')
					}
					else if(this.blog.errorCode==400)
					{
						return"User Not Logged In Please Log In First To Change Blog Status";
						$location.path('/login')
					}
					else if(this.blog.errorCode==404)
					{
						return"No Such Blog Exists";
						$location.path('/admin')
					}
					else if(this.blog.errorCode==500)
					{
						return"This Blog's Status Cannot Be Changed By You Because These Rights Are Given Only To Admin";
						$location.path('/')
					}
					$rootScope.blog=a;
					console.log(this.blog)
				},
				function(errResponse) 
				{
						console.error('Error while Approving Blog.');
			});
		}
		this.reject = function(blog) {
			this.blog.id=blog;
			console.log('Update Blog Status To Reject', this.blog.id);
			BlogService.rejectBlog(blog)
			.then(
				function(a) 
				{
					this.blog=a;
					if(this.blog.errorCode==200)
					{
						return"Thank You Blog Status Updated To Rejected Successfully";
						$location.path('/admin')
					}
					else if(this.blog.errorCode==400)
					{
						return"User Not Logged In Please Log In First To Change Blog Status";
						$location.path('/login')
					}
					else if(this.blog.errorCode==404)
					{
						return("No Such Blog Exists")
						$location.path('/admin')
					}
					else if(this.blog.errorCode==500)
					{
						return"This Blog's Status Cannot Be Changed By You Because These Rights Are Given Only To Admin";
						$location.path('/')
					}
					$rootScope.blog=a;
					console.log(this.blog)
				},
				function(errResponse) 
				{
						console.error('Error while Rejecting Blog.');
			});
		}
		this.edit = function(){
			console.log('Updating Blog', this.blog);
			this.updateBlog(this.blog)
		}
		this.displaybyid = function(blog) {
			{
				console.log('Display FetchBlogById',this.blog.id);
				this.fetchBlogById(this.blog.id);
			}
		};
		this.delete = function(blog) {
			{
				this.blog.id=blog;
				console.log('Deleting Blog', this.blog.id);
				this.deleteBlog(this.blog.id);
			}
		};
} ]);