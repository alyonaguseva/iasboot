angular.
module('core.log').
factory('Log', ['$resource',
    function($resource) {
        return $resource('logs?page=:page&size=50&startDate=:startDate&endDate=:endDate&success=:success', {}, {
            get: {
                method: 'GET',
                params: {page : 'page',startDate:'startDate', endDate:'endDate', success:'success'}
            }
        });
    }
]);