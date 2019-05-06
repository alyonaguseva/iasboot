var app = angular.module('aisApp', [
    'ngRoute', 'signalList', 'archiveSignalList', 'sensorList',
    'pagination', 'logList', 'exchange', 'signalValueExtList'
]);

app.controller('HeaderController', function ($scope, $location)
{
    $scope.isActive = function (viewLocation) {
        return viewLocation === $location.path();
    };
});