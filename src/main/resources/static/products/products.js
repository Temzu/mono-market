angular.module('market-front').controller("productsController",
    function ($scope, $http) {
      const contextPath = "http://localhost:8189/market";

      $scope.loadPage = function (pageIndex = 1) {
        $http({
          url: contextPath + '/api/v1/products',
          method: 'GET',
          params: {
            p: pageIndex,
            title: $scope.filter ? $scope.filter.title : null,
            min_price: $scope.filter ? $scope.filter.min_price : null,
            max_price: $scope.filter ? $scope.filter.max_price : null
          }
        }).then(function (response) {
          $scope.productsPage = response.data;
          $scope.navList = $scope.generatePagesIndexes(1, $scope.productsPage.totalPages);
          console.log(response.data);
        });
      };

      $scope.showProductsPage = function (pageIndex = 1) {
        $http({
          url: contextPath + '/api/v1/products',
          method: 'GET',
          params: {
            title: $scope.filter ? $scope.filter.title : null,
            min_price: $scope.filter ? $scope.filter.min_price : null,
            max_price: $scope.filter ? $scope.filter.max_price : null,
            page: pageIndex
          }
        }).then(function (response) {
          $scope.productsPage = response.data;

          let minPageIndex = pageIndex - 2;
          if (minPageIndex < 1) {
            minPageIndex = 1;
          }

          let maxPageIndex = pageIndex + 2;
          if (maxPageIndex > $scope.productsPage.totalPages) {
            maxPageIndex = $scope.productsPage.totalPages;
          }

          $scope.PaginationArray = $scope.generatePagesIndexes(minPageIndex, maxPageIndex);
          console.log(response.data);
        });
      };

      $scope.generatePagesIndexes = function (startPage, endPage) {
        let arr = [];
        for (let i = startPage; i < endPage + 1; i++) {
          arr.push(i);
        }
        return arr;
      }

      // $scope.addToCart = function (productId) {
      //     $http.get(contextPath + '/api/v1/cart/add/' + productId)
      //         .then(function (response) {
      //         });
      // }

      // $scope.addToCart = function (productId) {
      //   $http({
      //     url: contextPath + '/api/v1/cart/add',
      //     method: 'POST',
      //     params: {
      //       product_id: productId,
      //       uuid: $localStorage.happyCartUuid
      //     }
      //   }).then(function (response) {
      //     console.log("OK");
      //   });
      // }

      // $scope.createOrder = function () {
      //   $http.get(contextPath + '/api/v1/orders/create')
      //   .then(function (response) {
      //   });
      // }

      $scope.showProductsPage();

    })