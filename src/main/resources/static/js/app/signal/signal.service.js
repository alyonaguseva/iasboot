angular.
module('core.signal').
factory('Signal', ['$resource',
    function($resource) {
        return $resource('signals?page=:page&size=10', {}, {
            get: {
                method: 'GET',
                params: {page : 'page'}
            }
        });
    }
]).
factory('SignalCount', ['$resource',
    function($resource) {
        return $resource('signalsCount', {}, {
            get: {
                method: 'GET'
            }
        });
    }
]).
factory('ArchiveSignal', ['$resource',
    function($resource) {
        return $resource('signals?page=:page', {}, {
            query: {
                method: 'GET',
                params: {page : 'page'},
                isArray: true
            }
        });
    }
]).
factory('ArchiveSignalCount', ['$resource',
    function($resource) {
        return $resource('signalsCount', {}, {
            get: {
                method: 'GET'
            }
        });
    }
]);