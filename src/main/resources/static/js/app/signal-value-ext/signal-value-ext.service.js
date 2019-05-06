angular.
module('core.signalValueExt').
factory('SignalValueExt', ['$resource',
    function($resource) {
        return $resource('signalvalueext', {}, {
            query: {
                method: 'GET',
                isArray: true
            }
        });
    }
]);