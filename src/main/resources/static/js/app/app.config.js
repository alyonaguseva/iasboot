angular.
module('aisApp').
config(['$locationProvider', '$routeProvider',
    function config($locationProvider, $routeProvider) {
        $locationProvider.hashPrefix('!');

        $routeProvider.
        when('/journal', {
            template: '<signal-list></signal-list>'
        }).
        when('/archive', {
            template: '<archive-signal-list></archive-signal-list>'
        }).
        when('/sensors', {
            template: '<sensor-list></sensor-list>'
        }).
        when('/exchange', {
            template: '<exchange></exchange>'
        }).
        when('/logs', {
            template: '<log-list></log-list>'
        }).
        when('/signalvalues', {
            template: '<signal-value-ext-list></signal-value-ext-list>'
        }).
        when('/interrogation', {
            template: '<interrogation-setting></interrogation-setting>'
        }).
        otherwise('/logs');
    }
]);