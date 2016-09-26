var app = angular.module('upload', []);
app.directive('fileModel', ['$parse', function ($parse) {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {
            var model = $parse(attrs.fileModel);
            var modelSetter = model.assign;
            
            element.bind('change', function(){
                scope.$apply(function(){
                    modelSetter(scope, element[0].files[0]);
                });
            });
        }
    };
}]);
app.controller('uploadControler', [ '$scope', '$http', function($scope, $http) {
	$scope.upload = function() {
		var fd = new FormData();
		fd.append($scope.usuario, $scope.arquivo);
		$http.post("/upload_api/upload/", fd, {
			transformRequest : angular.identity,
			headers : {
				'Content-Type' : 'multipart/form-data'
			}
		}).success(function(data) {
			alert(data);
		}).error(function(data) {
			alert(data);
		});
	}
} ]);