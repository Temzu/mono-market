angular.module('market-front', []).controller("indexController",
    function ($scope, $http) {
      const contextPath = "http://localhost:8189/market/api/v1/";

      $scope.loadProducts = function (pageIndex = 1) {
        $http({
          url: contextPath + 'products',
          method: 'GET',
          params: {
            page: pageIndex
          }
        }).then(function (response) {
          $scope.productsPage = response.data;
          console.log(response)
        });
      }

      $scope.loadProducts(1);

    })