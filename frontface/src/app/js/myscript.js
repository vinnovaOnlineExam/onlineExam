angular
  .module('myModule', ['ngRoute'])
  .directive('validPasswordC', function ($window) {
    return {
      require: 'ngModel',
      link: function ($scope, elm, attrs, ctrl) {
        ctrl.$parsers.unshift(function (viewValue, $scope) {
          var noMatch = viewValue != $scope.myForm.password.$viewValue
          ctrl.$setValidity('noMatch', !noMatch)


        })
      }
    }
  })


  .config(function ($routeProvider) {
    $routeProvider
      .when("/", {
        templateUrl: "templates/onloadcontent.html",
        controller: ""
      })
      .when("/register", {
        templateUrl: "templates/register.html",
        controller: "registerController"
      })
      .when("/mycarousel", {
        templateUrl: "templates/onloadcontent.html",
        controller: ""
      })
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
        controller: "createExamController"
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
        controller: "checkSubmitController"
      })

      .when("/takeExam/score", {
        templateUrl: "templates/score.html",
        controller: "scoreController"
      })
  })

  .controller("signInController",function ($scope) {
    $scope.sign={};
    $scope.signInForm = function () {
      $http.post('/api/Uvalidate/', $scope.sign)


        .success(function (data) {

          /*$location.path('takeExam');
          if (data.errors) {
            $scope.noExam = data.errors;
          }*/
        //  else $scope.sign = null;
        })    }
  })


  .controller("homeController", function ($scope) {

  })

  .controller("addQuestionController", function ($scope, $http) {
    $scope.question = {};

    $scope.SubmitAddQuestionForm = function () {
      //posting data
      $http.post('/api/questions', $scope.question)


        .success(function (data) {
          if (data.errors) {
            $scope.iyyayyo = data.errors;
          }
          else $scope.question = null;
        })

    };


  })
  .controller("createExamController", function ($scope, $location, $scope, $http, $window) {

    $scope.questionGotFirst = [];
    $scope.questionForExam = [];
    $scope.questionDetails =
      [
        {
          "topic": "General",
          "maxNo": null
        },
        {
          "topic": "HTML",
          "maxNo": null
        },
        {
          "topic": "CSS",
          "maxNo": null
        },
        {
          "topic": "AngularJS",
          "maxNo": null
        },
        {
          "topic": "Java",
          "maxNo": null
        },
        {
          "topic": "Python",
          "maxNo": null
        }
      ];
    $scope.noOfQuestions =
      [
        {
          "topic": "General",
          "size": null
        },
        {
          "topic": "HTML",
          "size": null
        },
        {
          "topic": "CSS",
          "size": null
        },
        {
          "topic": "AngularJS",
          "size": null
        },
        {
          "topic": "Java",
          "size": null
        },
        {
          "topic": "Python",
          "size": null
        }
      ];
    $scope.allQuestions = null;
    $scope.ids = [];
    $scope.selected = {};
    $scope.AbsUrl = null;
    $scope.newIds = "";
    $scope.Examlink = null;
    $scope.message = "examgenerated";

    $scope.questionsChose = [];

    $scope.range = function (min, max) {
      var res = [];
      for (var i = 0; i <= max; i++) {
        res.push(i);
      }
      return res;
    }

    $scope.questionsChose.examOpa = {};
    $http.get('/api/questions')
      .then(function (response) {
        $scope.questionGotFirst = response.data;
        $scope.processTopics();
      });

    $scope.processTopics = function () {
      for (i = 0; i < $scope.questionDetails.length; i++) {
        topicQuestions = 0;
        for (j = 0; j < $scope.questionGotFirst.length; j++) {
          if ($scope.questionGotFirst[j].topic == $scope.questionDetails[i].topic) {
            topicQuestions++;
          }
        }
        $scope.questionDetails[i].maxNo = topicQuestions;

      }

    }
    $scope.GetQuestionsFromDB = function () {
      //posting data
      $scope.questionGotSecond = angular.copy($scope.questionGotFirst);
      return $scope.questionGotSecond;

    }

    $scope.operateQuestions = function () {

      for (i = 0; i < $scope.noOfQuestions.length; i++) {
        if ($scope.allQuestions == null && $scope.noOfQuestions[i].size != null && $scope.noOfQuestions[i].size != 0) {
          $scope.allQuestions = "topic=" + $scope.noOfQuestions[i].topic + "&size=" + $scope.noOfQuestions[i].size;
        }
        else if ($scope.noOfQuestions[i].size != null && $scope.noOfQuestions[i].size != 0) {
          $scope.allQuestions += "&topic=" + $scope.noOfQuestions[i].topic + "&size=" + $scope.noOfQuestions[i].size;
        }
      }

      $scope.Examlink = $location.absUrl() + "/takeExam?" + $scope.allQuestions;

      $scope.allQuestions = null;
    }

    $scope.proceedToContinue = function () {
      //  $location.path($scope.Examlink);
      $location.path('takeExam/checkSubmit');
    }

    $scope.newIds = $location.search().ids;

    if ($scope.newIds != null && $scope.newIds.length > 0) {
      $http.get('/api/questions?ids=' + $scope.newIds)
        .then(function (response) {
          $scope.questionForExam = response.data;
        });

    }

  })
  .controller("checkSubmitController", function ($scope) {

  })
  .controller("aboutController", function ($location, $scope) {

  })


  .controller("careerController", function ($scope) {

  })
  .controller("adminController", function ($scope) {

  })
  .controller("contactController", function ($scope) {

  })
  /*  .controller("examInstructionsController", function ($scope, $location) {
   $scope.proceedToContinue = function () {
   $location.path($scope.Examlink);
   }
   })*/
  .controller("checkAndSubmitController", function ($scope, $http) {
    $http.get('/api/questions')
      .then(function (response) {
        $scope.questionGotExam = response.data;
      });

  })
  .controller("viewExamController", function ($scope, $rootScope) {
    //$scope.showQuestions = angular.copy($rootScope.questionsChose);
  })

  .controller("takeExamController", function ($scope, $location, $http) {

    $scope.questionForExam = [];
    $scope.selec = [];

    $scope.newURL = $location.url();
    $scope.newURL = $scope.newURL.substring($scope.newURL.indexOf("?") + 1);

    var shuffleArray = function (array) {
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
    $http.get('/api/questions?' + $scope.newURL)
      .then(function (response) {

        $scope.questionForExam = response.data;
        shuffleArray($scope.questionForExam);


        for (i = 0; i < $scope.questionForExam.length; i++) {

          $scope.selec.push({"question": $scope.questionForExam[i].id.toString(), "option": ""});

        }

      });
    $scope.SubmitTakeExamForm = function () {
      $http.post('/api/Qvalidate/', $scope.selec)


        .success(function (data) {
          $location.path('takeExam/score');
          if (data.errors) {
            $scope.noExam = data.errors;
          }
          //else $scope.selec = null;
        })
    };


  })

  .controller("scoreController", function ($scope, $http) {
    $http.get('/api/Qvalidate/')
      .then(function (response) {
        $scope.result = response.data;
      });
  })


  .controller("postExamController", function ($scope) {

  })
  .controller("registerController", function ($scope, $http) {
    $scope.user = {};
    $scope.UserRegister = function () {
      /*console.log($scope.user);
      if ($scope.non.password === $scope.non.password_c) {

        $scope.user.password = $scope.non.password;*/
        $http.post('/api/users', $scope.user)
          .success(function (data) {
            window.alert("Registration sucessful");

            if (data.errors) {
              $scope.noExam = data.errors;
            }
          })
     /* }
      else
      {
        $scope.non.nomatch = "passwords not matching";
      }*/
    }

  })



