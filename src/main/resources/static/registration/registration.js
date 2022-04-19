angular.module('market-front').controller("registrationController",
    function ($scope, $http, $localStorage, $location) {
      const contextPath = "http://localhost:8189/market";

      $scope.tryToSignUp = function () {
        console.log(123);
        $http.post(contextPath + '/api/v1/auth/signup', $scope.user)
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
            $scope.user.email = null;

            $location.path('/');
          }
        }, function errorCallback(response) {
          alert(response.data.messages);
        });
      };

    })