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
                templateUrl: "templates/exam.html",
                controller: "examController"
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
                 .controller("examController",function ($scope) {

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