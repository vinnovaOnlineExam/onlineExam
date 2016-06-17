/**
 * Created by admin on 5/24/2016.*/

angular
  .module('myModule', ['ngRoute'])
  .config(function ($routeProvider) {
    $routeProvider
      .when("/", {
        templateUrl: "templates/onloadcontent.html",
        controller: "onloadController"
      })
      .when("/mycarousel", {
        templateUrl: "templates/onloadcontent.html",
        controller: "onloadController"
      })
      .when("/home", {
        templateUrl: "templates/home.html",
        controller: "homeController"
      })
      /*.when("/exam/postExam/takeExam", {
       templateUrl: "templates/exam_instructions.html",
       controller: "examTestController"
       })*/
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
      .when("/exam/createExam/takeExam", {
        templateUrl: "templates/takeExam.html",
        controller: "takeExamController"
      })
      .when("/exam/postExam", {
        templateUrl: "templates/postExam.html",
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
        controller: ""
      })

      .when("/takeExam/score", {
        templateUrl: "templates/score.html",
        controller: "scoreController"
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
  .controller("createExamController", function ($scope, $location, $rootScope, $http,$window) {

    $scope.questionGotFirst = [];
    $scope.questionForExam = [];
    $scope.noOfQuestions = null;
    $scope.ids = [];
    $scope.selected = {};
    $scope.AbsUrl = null;
    $scope.newIds = "";
    $rootScope.Examlink = null;
    $scope.message = "examgenerated";
//console.log($scope.questionsChose[0]);

    var nisse = {
      'key': []
    }

    nisse.key = "asdadsd";
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
      if ($scope.noOfQuestions <= $scope.questionGotFirst.length && $scope.noOfQuestions > 0) {
        for (var i = 0; i < numberEntered; i++) {

          // $scope.questionsChose{"examOpa"}.push($scope.questionGotFirst[i].opa);
          console.log($scope.questionGotFirst[i]);
          $scope.ids[i] = angular.copy($scope.questionGotFirst[i].id);
          //posting submittedquestions
        }

        $scope.AbsUrl = $location.absUrl() + "/takeExam?ids=" + $scope.ids.toString();
        $rootScope.Examlink = $location.absUrl() + "/takeExam?ids=" + $scope.ids.toString();
        //return $scope.AbsUrl;


        }
          else {
          $scope.p = 'please check the entered questions';
         window.alert($scope.p);
        console.log($scope.noOfQuestions);
        console.log($scope.questionGotFirst.length);
         }
      }

      // $rootScope.Examlink=angular.copy($scope.operateQuestions());
      //console.log($rootScope.Examlink);

      $scope.newIds = $location.search().ids;

      if ($scope.newIds != null && $scope.newIds.length > 0) {
        $http.get('http://localhost:8080/api/questions?ids=' + $scope.newIds)
          .then(function (response) {
            $scope.questionForExam = response.data;
            //console.log(questionForExam);
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

  .controller("takeExamController", function ($scope, $location, $http) {

    $scope.newIds = "";
    $scope.questionForExam = [];
    $scope.selec = [];


    $scope.newIds = $location.search().ids;
    var shuffleArray = function(array) {
      var m = array.length, t, i;

      // While there remain elements to shuffle
      while (m) {
        // Pick a remaining element…
        i = Math.floor(Math.random() * m--);

        // And swap it with the current element.
        t = array[m];
        array[m] = array[i];
        array[i] = t;
      }

      return array;
    }

    //if ($scope.newIds != null && $scope.newIds.length > 0) {
    $http.get('http://localhost:8080/api/questions?ids=' + $scope.newIds)
      .then(function (response) {
        /*$scope.random = function() {
         return 0.24 - Math.random();
         }*/
        $scope.questionForExam = response.data;
        shuffleArray($scope.questionForExam);
        console.log($scope.questionForExam);


        for (i=0;i<$scope.questionForExam.length;i++){

          //$scope.selec[i].question = $scope.questionForExam[i].id;
          $scope.selec.push({"question":$scope.questionForExam[i].id.toString(),"option":""});

        }
        /*angular.forEach($scope.questionForExam.id,function (value) {
          this.push(question+":"+value);
        },$scope.selec);*/
        console.log($scope.questionForExam);
        //shuffle question
        /*
         var shuffleArray = function(array) {
         var m = questionForExam.length, t, i;

         while (m) {
         i = Math.floor(Math.random() * m--);
         t = questionForExam[m];
         questionForExam[m] = questionForExam[i];
         questionForExam[i] = t;
         }

         return questionForExam;
         console.log(questionForExam);
         }
         */

      });
    $scope.SubmitTakeExamForm = function () {
      $http.post('http://localhost:8080/api/Qvalidate/', $scope.selec)


        .success(function (data) {
          $location.path('takeExam/score');
          if (data.errors) {
            $scope.noExam = data.errors;
          }
          //else $scope.selec = null;
        })
    };

    /*function doSomethingLater() {
     //THis will run when HTTP is done.
     if (angular.isObject($scope.questionForExam)) {
     var noOfIds = $scope.questionForExam.length;
     //window.alert("ids" + noOfIds);
     for (i = 0; i < noOfIds; i++) {
     console.log("from for", $scope.questionForExam[i]);
     //window.alert("from for", $scope.questionForExam[i]);
     }
     }
     }*/


    //}


  })

  .controller("scoreController", function ($scope,$http) {
        $http.get('http://localhost:8080/api/Qvalidate/')
          .then(function (response) {
            $scope.result = response.data;
            console.log($scope.result);
          });
  })


  /*if ($scope.selectedoption === $scope.q.corr_op) {
   $scope.score[i] = 1;
   console.log($scope.score[i])



   }
   else {
   $scope.score[i] = 0;
   console.log($scope.score[i])

   }
   */



  //var sum = $scope.score.reduce(function(a, b) { return a + b; }, 0);
  //console.log($scope.sum);

  /*
   for(i=0;i<newIds.length;i++)
   {
   if(selectedoption===corr_op) {
   $scope.score[i] = 1
   }
   else {
   $scope.score[i]=0
   }
   }
   */

  /*
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
   // For i=0 to noOfQues
   tions add $scope.questionGotFirst[previously randomised number] to new array and return
   return $scope.questionGotSecond;

   }

   $scope.shuffleArray = function(array) {
   $scope.m = $scope.questionForExam.length,
   $scope.t,
   $scope.i;

   while ($scope.m) {
   $scope.i = Math.floor(Math.random() * $scope.m--);
   $scope.t = $scope.questionForExam[m];
   $scope.questionForExam[m] = $scope.questionForExam[i];
   $scope.questionForExam[i] = t;
   }

   return $scope.questionForExam;
   console.log($scope.questionForExam);
   }

   for (i = 0; i < 4; i++) {
   if ($scope.selectedoption === $scope.questionShow..corr_op) {
   answer[i] = 1;
   }
   else {
   answer[i] = 0;
   }
   }
   */


  .controller("postExamController", function ($scope) {

  })


/*

 .controller("postExamController", function ($scope, $location, $rootScope, $http) {

 $scope.questionGotFirst = [];
 $scope.noOfQuestions = null;
 $scope.ids = [];
 $scope.AbsUrl = null;
 $scope.newIds = "";
 $scope.questionForExam = [];


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
 $scope.AbsUrl = $location.absUrl() + "/takeExam?ids=" + $scope.ids.toString();

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

 //$scope.proceedToContinue = function () {
 // $location.path('takeExam/checkSubmit');
 -->*/
