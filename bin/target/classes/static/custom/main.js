var app = angular.module('angularApp', ['ngResource', 'ngMaterial','ngRoute', 'ngTable', 'ngMessages']);

// configure our routes
app.config(function($routeProvider) {
	
	$routeProvider.when('/emp', {
			templateUrl : 'custom/controllers/employee/employee.html',
			controller  : 'EmployeeCtrl'
		}).when('/', {
			templateUrl : 'custom/controllers/home/home.html',
			controller  : 'homeCtrl'
		}).when('/home', {
			templateUrl : 'custom/controllers/home/home.html',
			controller  : 'homeCtrl'
		}).when('/search', {
			templateUrl : 'custom/controllers/search/search.html',
			controller  : 'searchCtrl'
		}).when('/book', {
      templateUrl : 'custom/controllers/book/book.html',
      controller  : 'bookCtrl'
    }).when('/checkoutByBorrower', {
      templateUrl : 'custom/controllers/checkoutByBorrower/checkout.html',
      controller  : 'checkoutByBorrowerCtrl'
    }).when('/checkoutByBook', {
      templateUrl : 'custom/controllers/checkoutByBook/checkout.html',
      controller  : 'checkoutByBookCtrl'
    }).when('/payDue', {
      templateUrl : 'custom/controllers/payDue/payDue.html',
      controller  : 'payDueCtrl'
    });

});