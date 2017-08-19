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
        when('/logs', {
            template: '<log-list></log-list>'
        }).
        otherwise('/journal');
    }
]);