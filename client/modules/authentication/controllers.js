'use strict';

angular.module('Authentication')

.controller('LoginController',
	['$scope', '$rootScope', '$location', 'AuthenticationService',
	function ($scope, $rootScope, $location, AuthenticationService) {
		AuthenticationService.ClearCredentials();

		$scope.login = function() {
			$scope.dataLoading = true;
			AuthenticationService.Login($scope.username, $scope.password, function(response) {
				if (response == "Success") {
					AuthenticationService.SetCredentials($scope.username, $scope.password);
					$location.path('/');
				} else {
					$scope.error = "Invalid username or password.";
					$scope.dataLoading = false;
				}
			});
		};
	}]);