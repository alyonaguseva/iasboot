angular.
module('core.bing3')
    .factory('Bing3Exchange', ['$resource',
    function($resource) {
        return $resource('bing3/exchange');
    }
]);