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
]);