angular.
module('archiveSignalList').component(
    'archiveSignalList', {
        templateUrl: '/js/app/archive-signal-list/archive-signal-list.template.html',
        bindings: {
            pageCount: '@',
            page: '@',
            pageClean: '@'
        },
        controller: ['ArchiveSignal', 'ArchiveSignalCount',
            function ArchiveSignalListController(ArchiveSignal, ArchiveSignalCount) {
            var vm = this;

            vm.$onInit = function () {
                ArchiveSignalCount.get({}, function (val) {
                    vm.pageCount = val.count;
                    vm.page = 1;
                    vm.setPage(1);
                });

            };

            vm.setPage = function setPage(page) {
                vm.signals = ArchiveSignal.query({page: page});
                vm.pageClean = false;
            };
        }]
    }
);