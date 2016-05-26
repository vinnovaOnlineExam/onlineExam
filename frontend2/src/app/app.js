
angular.module('frontend', [
  'ngRoute','ngResource',
  'frontend.todo'
])
.config(function ($routeProvider) {
  'use strict';
  $routeProvider

    .when('/todo', {
      controller: 'TodoCtrl',
      templateUrl: '/frontend/todo/login.html'
    })
    .when('/welcome',{
      controller:'data',
      templateUrl :'/frontend/todo/welcome.html'
      
    })
    .otherwise({
      redirectTo: '/todo'
    });
});
