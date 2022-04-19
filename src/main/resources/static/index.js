(function ($localStorage) {
  'use strict';

  angular
  .module('market-front', ['ngRoute', 'ngStorage'])
  .config(config)
  .run(run);

  function config($routeProvider, $httpProvider) {
    $routeProvider
    .when('/', {
      templateUrl: 'home/home.html',
      controller: 'homeController'
    })
    .when('/products', {
      templateUrl: 'products/products.html',
      controller: 'productsController'
    })
    .when('/registration', {
      templateUrl: 'registration/registration.html',
      controller: 'registrationController'
    })
    .otherwise({
      redirectTo: '/'
    });
  }

  const contextPath = "http://localhost:8189/market";

  function run($rootScope, $http, $localStorage) {
    if ($localStorage.currentUser) {
      $http.defaults.headers.common.Authorization = 'Bearer '
          + $localStorage.currentUser.token;
    }

    $http.post(contextPath + '/api/v1/cart')
    .then(function successCallback(response) {
      $localStorage.happyCartUuid = response.data;
    });
  }
})();

angular.module('market-front').controller('indexController',
    function ($scope, $http, $localStorage, $location) {

      const contextPath = "http://localhost:8189/market";

      $scope.tryToAuth = function () {

        $http.post(contextPath + '/api/v1/auth/login', $scope.user)
        .then(function successCallback(response) {
          if (response.data.token) {
            $http.defaults.headers.common.Authorization = 'Bearer '
                + response.data.token;
            $localStorage.currentUser = {
              login: $scope.user.login,
              token: response.data.token
            };

            $scope.currentUserName = $scope.user.login;

            $scope.user.login = null;
            $scope.user.password = null;
          }
        }, function errorCallback(response) {
          alert(response.data.messages);
        });

        // $http.post(contextPath + '/api/v1/cart')
        // .then(function successCallback(response) {
        //   $localStorage.happyCartUuid = response.data;
        // });
      };

      $scope.tryToLogout = function () {
        $scope.clearUser();

        // $http.post(contextPath + '/api/v1/cart')
        // .then(function successCallback(response) {
        //   $localStorage.happyCartUuid = response.data;
        // });

        $location.path('/');
        if ($scope.user.login) {
          $scope.user.login = null;
        }
        if ($scope.user.password) {
          $scope.user.password = null;
        }
      };

      $scope.clearUser = function () {
        delete $localStorage.currentUser;
        $http.defaults.headers.common.Authorization = '';
      };

      $scope.isUserLoggedIn = function () {
        if ($localStorage.currentUser) {
          return true;
        } else {
          return false;
        }
      };
    });