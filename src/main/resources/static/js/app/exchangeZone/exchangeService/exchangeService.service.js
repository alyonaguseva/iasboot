// angular.
//     module('core.exchange').
// factory('GetSensors', ['$resource',
//     function($resource) {
//         return $resource('exchange/sensors', {}, {
//             query: {
//                 method: 'GET',
//                 isArray: true
//             }
//         });
//     }
// ]);
// angular.
// module('core.exchange').
// factory('GetSignals', ['$resource',
//     function($resource) {
//         return $resource('exchange/signals', {}, {
//             query: {
//                 method: 'GET',
//                 isArray: true
//             }
//         });
//     }
// ]);

angular.
module('core.exchange').
factory('GetMeasures', ['$resource',
    function($resource) {
        return $resource('exchange/measures', {}, {
            query: {
                method: 'GET',
                isArray: true
}
});
}
]).
factory('GetComboMeasures', ['$resource',
    function($resource) {
        return $resource('exchange/combo/measures', {}, {
            query: {
                method: 'GET',
                isArray: true
            }
        });
    }
]).
factory('SaveMeasures', ['$resource',
    function($resource) {
        return $resource('exchange/measure/save');
    }
]).
factory('GetSensors', ['$resource',
    function($resource) {
        return $resource('exchange/sensors', {}, {
            query: {
                method: 'GET',
                isArray: true
            }
        });
    }
]).
factory('GetComboSensors', ['$resource',
    function($resource) {
        return $resource('exchange/combo/sensors', {}, {
            query: {
                method: 'GET',
                isArray: true
            }
        });
    }
]).
factory('SaveSensors', ['$resource',
    function($resource) {
        return $resource('exchange/sensor/save');
    }
]).
factory('GetSignals', ['$resource',
    function($resource) {
        return $resource('exchange/signals', {}, {
            query: {
                method: 'GET',
                isArray: true
            }
        });
    }
]).
factory('SaveSignals', ['$resource',
    function($resource) {
        return $resource('exchange/signal/save');
    }
]).
factory('GetPL302s', ['$resource',
    function($resource) {
        return $resource('exchange/pl302s', {}, {
            query: {
                method: 'GET',
                isArray: true
            }
        });
    }
]).
factory('SavePL302s', ['$resource',
    function($resource) {
        return $resource('exchange/pl302/save');
    }
]).
factory('GetComboSignalTypes', ['$resource',
    function($resource) {
        return $resource('exchange/combo/signalTypes', {}, {
            query: {
                method: 'GET',
                isArray: true
            }
        });
    }
]);