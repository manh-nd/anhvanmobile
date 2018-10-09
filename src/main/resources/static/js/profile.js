var app = angular.module("profileApp", []);

app.filter('formatPrice', function() {
	return function(x) {
		return x.toLocaleString('vi-VN');
	};
});

app.controller("profileController", function($scope, $http) {
	$('#myList [href="#profile"]').tab("show");

	$('#myList [href="#profile"]').on('click', function(e) {
		e.preventDefault()
		$(this).tab('show');
	});
	$('#myList [href="#change-password"]').on('click', function(e) {
		e.preventDefault()
		$(this).tab('show');
	});
	$('#myList [href="#order-history"]').on('click', function(e) {
		e.preventDefault()
		$(this).tab('show');
		$http({
			method : "GET",
			url : "/profile/order/history"
		}).then(function onSuccess(response) {
			$scope.orders = response.data;
		}, function onError(response) {
			alert(response.statusText);
		});
	});

	$scope.viewOrder = function(x) {
		$scope.order = x;
		$("#orderDetailModal").modal("show");
	}

	$http({
		method : "GET",
		url : "/profile/order/total-order"
	}).then(function onSuccess(response) {
		$scope.totalOrder = response.data;
	}, function onError(response) {
		alert(response.statusText);
	});

	$http({
		method : "GET",
		url : "/profile/user-info"
	}).then(function onSuccess(response) {
		$scope.user = response.data;
	}, function onError(response) {
		alert(response.statusText);
	});

});