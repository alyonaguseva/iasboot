angular.
module('signalList').component(
    'signalList', {
        templateUrl: '/js/app/signal-list/signal-list.template.html',
        bindings: {
            pageCount: '@',
            page: '@',
            pageClean: '@'
        },
        controller: ['Signal', 'SignalCount', function SignalListController(Signal, SignalCount) {
            var vm = this;

            vm.$onInit = function () {
                vm.page = 1;
                vm.setPage(vm.page);

                // SignalCount.get({}, function (val) {
                //     vm.pageCount = val.count;
                //     vm.page = 1;
                //     vm.setPage(1);
                // });

            };

            vm.setPage = function setPage(page) {
                Signal.get({page: page}, function (data) {
                    vm.signals = data.content;
                    vm.pageCount = data.totalPages;
                    vm.pageClean = false;
                });
            };
        }]
    }
);