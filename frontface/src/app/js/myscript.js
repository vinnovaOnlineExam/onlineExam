/**
 * Created by admin on 5/24/2016.*/

             angular
                   .module("myModule",['ngRoute'])
                    .config(function ($routeProvider) {
        $routeProvider
            .when("/home", {
                templateUrl: "templates/home.html",
                controller: "homeController"
            })
            .when("/exam", {
                templateUrl: "add_questions.html",
                controller: "PostQuestionController"
            })
            .when("/about", {
                templateUrl: "templates/aboutus.html",
                controller: "aboutController"
            })
            .when("/career", {
                templateUrl: "templates/carrier.html",
                controller: "carrierController"
            })
    })
                 .controller("homeController",function ($scope) {

                   })
                 .controller("PostQuestionController",function ($scope, $http) {
                   $scope.question={};

                  // $http.post('http://localhost:8080/api/questions',{"question":"How dodfsfadf you greet?","topic":"general","opa":"namasthe","opb":"hejsan","opc":"tjena","corr_op":"opa"})

                   $scope.SubmitAddQuestionForm = function(){
                     //posting data
                     $http.post('http://localhost:8080/api/questions',$scope.question)


                       .success(function(data){
                         if(data.errors){
                           $scope.iyyayyo=data.errors;
                         }
                         else $scope.question = null;
                       })

                   };
                   $scope.GetQuestionsFromDB = function(){
                     //posting data
                     $http.get('http://localhost:8080/api/questions')
                       .then(function(response){
                         $scope.questionGot=response.data;
                       })

                   };


                 })
                 .controller("aboutController",function ($scope) {

                 })
                 .controller("carrierController",function ($scope) {

                 });

                    /*.config(function ($routeProvider) {

        $routeProvider
                        .when("#/",{
                            templateUrl: 'templates/home.html',
                            controller: 'homeController'
                        })
                        .when("#/career",{
                            templateUrl: 'templates/carrier.html',
                            controller: 'careerController'
                        })

                        .when("#/exam",{
                            templateUrl: 'templates/exam.html',
                            controller: 'examController'
                        })
                        .when("/aboutus",{
                            templateUrl: 'templates/aboutus.html',
                            controller: 'aboutusController'
                        })

    })
                    .controller("homeController", function ($scope) {
                        $scope.message="Homepage";})
                    .controller("careerController", function ($scope) {
                        $scope.message="Careerpage";})
                    .controller("examController", function ($scope) {
                        $scope.message="Exampage";})
                    .controller("aboutusController", function ($scope) {
                        $scope.message="Aboutuspage";});

*/
