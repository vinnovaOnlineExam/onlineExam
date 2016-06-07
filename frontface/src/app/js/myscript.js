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
      .when("/takeExam", {
        templateUrl: "templates/exam_instructions.html",
        controller: "examInstructionsController"
      })
      .when("/contact", {
        templateUrl: "templates/contact.html",
        controller: "contactController"
      })
      .when("/exam/addquestion", {
        templateUrl: "templates/add_questions.html",
        controller: "addQuestionController"
      })
      .when("/exam/viewExam", {
        templateUrl: "templates/viewExam.html",
        controller: "viewExamController"
      })
      .when("/exam/createExam", {
        templateUrl: "templates/createExam.html",
        controller: "createExamController"
      })
      .when("/exam/postExam", {
        templateUrl: "templates/generate_exam.html",
        controller: "postExamController"
      })
      .when("/about", {
        templateUrl: "templates/aboutus.html",
        controller: "aboutController"
      })
      .when("/career", {
        templateUrl: "templates/career.html",
        controller: "careerController"
      })
      .when("/takeExam/checkSubmit", {
        templateUrl: "templates/check_and_submit.html",
        controller: "checkAndSubmitController"
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
  .controller("createExamController", function ($scope, $location, $rootScope, $http) {

    $scope.questionGotFirst = [];
    $scope.noOfQuestions = null;
    $scope.ids = [];
    $scope.AbsUrl = null;
    $scope.newIds = "";
    $scope.questionForExam = [];
    var nisse = {
      'key': []
    }

    nisse.key = "asdadsd"
    $scope.questionsChose = [];
    $scope.questionsChose.examOpa = {};
    $http.get('http://localhost:8080/api/questions')
      .then(function (response) {
        $scope.questionGotFirst = response.data;
      });
    $scope.GetQuestionsFromDB = function () {
      //posting data
      $scope.questionGotSecond = angular.copy($scope.questionGotFirst);
      //TODO: Math.rand() to get $scope.noOfQuestions many random variables between 0 and questionGotFirst.length
      // For i=0 to noOfQuestions add $scope.questionGotFirst[previously randomised number] to new array and return
      return $scope.questionGotSecond;

    }

    $scope.operateQuestions = function () {

      var numberEntered = $scope.noOfQuestions;
      //return $scope.numberEntered;
      //return $scope.noOfQuestions;

      for (var i = 0; i < numberEntered; i++) {

        // $scope.questionsChose{"examOpa"}.push($scope.questionGotFirst[i].opa);
        console.log($scope.questionGotFirst[i]);
        $scope.ids[i] = angular.copy($scope.questionGotFirst[i].id);
        //posting submittedquestions

      }
      $scope.AbsUrl = $location.absUrl() + "?ids=" + $scope.ids.toString();

      //console.log($rootScope.questionsChose[0]);
      return $scope.AbsUrl;


    }

    $scope.newIds = $location.search().ids;

    if ($scope.newIds != null && $scope.newIds.length > 0) {
      $http.get('http://localhost:8080/api/questions?ids=' + $scope.newIds)
        .then(function (response) {
          $scope.questionForExam = response.data;
        });

    }
  })

  .controller("aboutController", function ($location, $scope) {
    //$scope.naresh="nana";
    //$scope.AbsUrl = $location.absUrl();


  })
  .controller("careerController", function ($scope) {

  })
  .controller("adminController", function ($scope) {

  })
  .controller("contactController", function ($scope) {

  })
  .controller("examInstructionsController", function ($scope, $location) {
    $scope.proceedToContinue = function () {
      $location.path('takeExam/checkSubmit');
    }
  })
  .controller("checkAndSubmitController", function ($scope, $http) {
    $http.get('http://localhost:8080/api/questions')
      .then(function (response) {
        $scope.questionGotExam = response.data;
      });

  })
  .controller("viewExamController", function ($scope, $rootScope) {
    $scope.showQuestions = angular.copy($rootScope.questionsChose);
  })





