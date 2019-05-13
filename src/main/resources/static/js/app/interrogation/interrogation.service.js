angular.
module('core.interrogation')
    .factory('SaveInterrogationSetting', ['$resource',
    function($resource) {
        return $resource('interrogation/setting/save');
    }
]).
factory('GetInterrogationSetting', ['$resource',
    function($resource) {
        return $resource('interrogation/setting/get', {}, {
            query: {
                method: 'GET'
            }
        });
    }
]);