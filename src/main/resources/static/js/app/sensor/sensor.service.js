angular.
module('core.sensor').
factory('Sensor', ['$resource',
    function($resource) {
        return $resource('sensors', {}, {
            query: {
                method: 'GET',
                isArray: true
            }
        });
    }
]);