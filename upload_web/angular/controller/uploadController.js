app.controller('uploadControler', [ '$scope', '$http', function($scope, $http) {
	$scope.upload = function() {
		var fd = new FormData();
		fd.append($scope.usuario, $scope.arquivo);
		$http.post("http://localhost:8081/upload_api/upload/", fd, {
			
			headers : {
				'Content-Type' : 'multipart/form-data',
			}
		}).success(function(data) {
			alert(data);
			$scope.lista();
		}).error(function(data) {
			alert(data);
			$scope.lista();
		});
	}

	$scope.lista = function() {
		$http.get("http://localhost:8081/upload_api/arquivos/").success(function(data) {
			$scope.arquivos = data;
		}).error(function(data) {
			alert(data);
		});
	}
	$scope.lista();
} ]);