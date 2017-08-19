angular.
module('logList').component(
    'logList', {
        templateUrl: '/js/app/log-list/log-list.template.html',
        bindings: {
            pageCount: '@',
            page: '@',
            pageClean: '@'
        },
        controller: ['Log', function LogListController(Log) {
            var vm = this;

            vm.$onInit = function () {
                vm.page = 1;
                vm.setPage(vm.page);

            };

            vm.setPage = function setPage(page) {
                Log.get({page: page - 1}, function (data) {
                    vm.logs = data.content;
                    vm.pageCount = data.totalPages;
                    vm.pageClean = false;
                });
            };
        }]
    }
);