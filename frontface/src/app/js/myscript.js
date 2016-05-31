/**
 * Created by admin on 5/24/2016.*/

angular
  .module("myModule", ['ngRoute'])
  .config(function ($routeProvider) {
    $routeProvider
      .when("/home", {
        templateUrl: "templates/home.html",
        controller: "homeController"
      })
      .when("/exam", {
        templateUrl: "templates/admin_page.html",
        controller: "adminController"
      })
      .when("/contact", {
        templateUrl: "templates/contact.html",
        controller: "contactController"
      })
      .when("/exam/addquestion", {
        templateUrl: "templates/add_questions.html",
        controller: "addQuestionController"
      })
      .when("/exam/viewexam", {
        templateUrl: "templates/viewexam.html",
        controller: "viewExamController"
      })

      .when("/about", {
        templateUrl: "templates/aboutus.html",
        controller: "aboutController"
      })
      .when("/career", {
        templateUrl: "templates/career.html",
        controller: "carrierController"
      })
  })
  .controller("homeController", function ($scope) {

  })
  .controller("addQuestionController", function ($scope, $http) {
    $scope.question = {};

    // $http.post('http://localhost:8080/api/questions',{"question":"How dodfsfadf you greet?","topic":"general","opa":"namasthe","opb":"hejsan","opc":"tjena","corr_op":"opa"})

    $scope.SubmitAddQuestionForm = function () {
      //posting data
      $http.post('http://localhost:8080/api/questions', $scope.question)


        .success(function (data) {
          if (data.errors) {
            $scope.iyyayyo = data.errors;
          }
          else $scope.question = null;
        })

    };


  })
  .controller("viewExamController", function ($scope, $http) {
    $scope.GetQuestionsFromDB = function () {
      //posting data
      $http.get('http://localhost:8080/api/questions')
        .then(function (response) {
          $scope.questionGot = response.data;
        })

    };

  })
  .controller("aboutController", function ($scope) {

  })
  .controller("carrierController", function ($scope) {

  })
  .controller("adminController", function ($scope) {

  })
  .controller("contactController", function ($scope) {

  });

