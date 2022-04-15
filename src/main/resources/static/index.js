angular.module('market-front', []).controller("indexController",
    function ($scope, $http) {
      const contextPath = "http://localhost:8189/market/api/v1/";

      $http.get(contextPath + 'products').then(function (response) {
        $scope.products = response.data;
      });
    })