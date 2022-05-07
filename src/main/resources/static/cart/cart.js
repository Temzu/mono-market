angular.module('market-front').controller('cartController',
    function ($scope, $http, $localStorage) {
      const contextPath = 'http://localhost:8189/market';

      $scope.loadCart = function () {
        $http({
          url: contextPath + '/api/v1/cart/' + $localStorage.guestCartUuid,
          method: 'GET'
        }).then(function (response) {
          $scope.cart = response.data;
        });
      }

      $scope.addToCart = function (productId) {
        $http({
          url: contextPath + '/api/v1/cart/' + $localStorage.guestCartUuid
              + '/add/' + productId,
          method: 'GET'
        }).then(function (response) {
          $scope.loadCart();
        }, function errorCallback(response) {
          console.log($localStorage.guestCartUuid);
        });
      }

      $scope.incrementCartPosition = function (productId) {
        $http({
          url: contextPath + '/api/v1/cart/' + $localStorage.guestCartUuid
              + '/add/' + productId,
          method: 'GET'
        }).then(function (response) {
          $scope.loadCart();
        });
      }

      $scope.decrementCartPosition = function (productId) {
        $http({
          url: contextPath + '/api/v1/cart/' + $localStorage.guestCartUuid
              + '/decrement/' + productId,
          method: 'GET'
        }).then(function (response) {
          $scope.loadCart();
        });
      }

      $scope.clearCart = function () {
        $http({
          url: contextPath + '/api/v1/cart/' + $localStorage.guestCartUuid
              + '/clear',
          method: 'GET'
        }).then(function (response) {
          $scope.cart = null;
        });
      }

      $scope.removeItemFromCart = function (productId) {
        $http({
          url: contextPath + '/api/v1/cart/' + $localStorage.guestCartUuid
              + '/remove/' + productId,
          method: 'GET'
        }).then(function (response) {
          $scope.loadCart();
        });
      }

      $scope.createOrder = function () {
        $http.post(contextPath + '/api/v1/orders', $scope.order_info)
        .then(function (response) {
          alert('Order created');
          $scope.loadCart();
        }, function errorCallback(response) {
          alert(response.data.messages);
        });
      }

      $scope.loadCart();
    });