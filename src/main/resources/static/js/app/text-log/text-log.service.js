angular.
module('core.text-log').
factory('TextLog', ['$resource',
    function($resource) {
        return $resource('text-logs?page=:page&size=50&startDate=:startDate&endDate=:endDate&type=:type', {}, {
            get: {
                method: 'GET',
                params: {page : 'page',startDate:'startDate', endDate:'endDate', type:'type'}
            }
        });
    }
]);