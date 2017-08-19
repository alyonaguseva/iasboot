var app = angular.module('aisApp', [
    'ngRoute', 'signalList', 'archiveSignalList', 'sensorList', 'pagination', 'logList'
]);

app.controller('HeaderController', function ($scope, $location)
{
    $scope.isActive = function (viewLocation) {
        return viewLocation === $location.path();
    };
});