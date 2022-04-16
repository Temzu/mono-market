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
    .otherwise({
      redirectTo: '/'
    });
  }

  const contextPath = "http://localhost:8189/market/api/v1/";

  function run($rootScope, $http, $localStorage) {

  }
})();

angular.module('market-front').controller('indexController',
    function ($scope, $http, $localStorage, $location) {
      const contextPath = "http://localhost:8189/market/api/v1/";

    });