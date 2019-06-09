var app = angular.module('aisApp', [
    'ngRoute', 'signalList', 'archiveSignalList', 'sensorList',
    'pagination', 'logList', 'textLogList', 'exchange', 'signalValueExtList', 'interrogationSetting'
]);

app.controller('HeaderController', function ($scope, $location)
{
    $scope.isActive = function (viewLocation) {
        return viewLocation === $location.path();
    };
});