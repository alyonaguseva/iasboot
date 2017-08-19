angular.
module('core.dictionary').
factory('ControlObjects', ['$resource',
    function($resource) {
        return $resource('controlObjects', {}, {
            query: {
                method: 'GET',
                isArray: true
            }
        });
    }
]).
factory('ControlElements', ['$resource',
    function($resource) {
        return $resource('controlElements', {}, {
            query: {
                method: 'GET',
                isArray: true
            }
        });
    }
]).
factory('MeasurementTypes', ['$resource',
    function($resource) {
        return $resource('measurementTypes', {}, {
            query: {
                method: 'GET',
                isArray: true
            }
        });
    }
]).
factory('SensorGroups', ['$resource',
    function($resource) {
        return $resource('sensorGroups', {}, {
            query: {
                method: 'GET',
                isArray: true
            }
        });
    }
]).
factory('SensorTypes', ['$resource',
    function($resource) {
        return $resource('sensorTypes', {}, {
            query: {
                method: 'GET',
                isArray: true
            }
        });
    }
]);