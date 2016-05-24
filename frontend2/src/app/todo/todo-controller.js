angular
  .module('frontend.todo',[])
  .controller('TodoCtrl', function ($scope, $window,$resource,$location) {
    var HelloWorld = $resource("http://localhost:8080/api/hello-world")
    $scope.greeting = HelloWorld.get();
    'use strict';
    $scope.todos = JSON.parse($window.localStorage.getItem('todos') || '[]');
    $scope.$watch('todos', function (newTodos, oldTodos) {
      if (newTodos !== oldTodos) {
        $window.localStorage.setItem('todos', JSON.stringify(angular.copy($scope.todos)));
      }
    }, true);
/**
    $scope.add = function () {
      var todo = {label: $scope.label, isDone: false};
      $scope.todos.push(todo);
      $window.localStorage.setItem('todos', JSON.stringify(angular.copy($scope.todos)));
      $scope.label = '';
    };


    $scope.check = function () {
      this.todo.isDone = !this.todo.isDone;
    };

 */

$scope.login=function () {
 var username=$scope.uname;
  var password=$scope.passwd;
  if($scope.uname== 'admin'&& $scope.passwd=='a') {
    $location.path('/welcome');
  }
}


  })

.controller('data', function ($scope,$http,$log) {

   $http.get('http://localhost:8080/api/users/1')
     .then(function (response) {
       $scope.email = response.data;
       $log.info(response);
     })
  $http.post("http://localhost:8080/api/users",{"name":"nene","email":"per@jaffa.co","password":"12345678"})


  $http.get('http://localhost:8080/api/users/')
    .then(function (response) {
      $scope.nene = response.data;
      $log.info(response);
    })

})
  
.controller('hard',function ($scope) {
  


})







/**
 var req = {
    method: 'POST',
    url: 'http://localhost:8080/api/users',
    headers: {
      'Content-Type': undefined
    },
    data: { "id":1024,"name":"srihas","email":"srihas@jaffa.co","password":"24323278" }
  }

 **/
/**  $http.post('http://localhost:8080/api/users/1',{"id":1024,"name":"srihas","email":"srihas@jaffa.co","password":"12345678"})
 .then(function (response) {


         })
 })**/



/**$scope.naresh=$http.get('http://localhost:8080/api/users/1');
 $scope.n=$http({
    url: 'http://localhost:8080/api/users',
    method: "GET",
    params: {id: 1}
  });
 **/







/**
 * Created by Naresh on 2016-05-16.
 */
