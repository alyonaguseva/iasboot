angular.
module('core.log').
factory('Log', ['$resource',
    function($resource) {
        return $resource('logs?page=:page&size=10', {}, {
            get: {
                method: 'GET',
                params: {page : 'page'}
            }
        });
    }
]);