angular.
module('pagination').component(
    'pagination', {
        templateUrl: '/js/app/pagination/pagination.template.html',
        bindings:{
            pageCount : '<',
            page : '<',
            pageClean : '<',
            onPageChange: '&'
        },
        controller: ['$scope', function PaginationController() {
            var vm = this;
            vm.pages = [];

            // vm.$onInit = function () {
            //     buildPages();
            // };

            vm.$onChanges  = function (changes) {
                if (changes.pageClean != undefined) {
                    if (changes.pageClean.currentValue) {
                        vm.setPage(1);
                    } else if(changes.pageCount != undefined) {
                        buildPages();
                    }
                } else {
                    buildPages();
                }
            };

            vm.setPage = function setPage(page) {
                if (page == 0) {
                    vm.page = 1;
                } else if (page > vm.pageCount) {
                    vm.page = vm.pageCount;
                }
                vm.page = page;
                vm.onPageChange({$event: {page:vm.page}});
                buildPages();
            };

            vm.next = function next() {
                vm.setPage(Number(vm.page) + 1);
            };

            vm.prev = function prev() {
                vm.setPage(Number(vm.page) - 1);
            };

            vm.lastPage = function lastPage() {
                vm.setPage(Number(vm.pageCount));
            };

            vm.firstPage = function firstPage() {
                vm.setPage(1);
            };

            function buildPages() {
                var i;
                var pages = [];
                if (vm.pageCount < 10) {
                    for (i = 1; i <= vm.pageCount; i++) {
                        pages.push(i);
                    }
                } else {
                    if (vm.page <= 5) {
                        for (i = 1; i <= 7; i++) {
                            pages.push(i);
                        }

                        pages.push(0);

                        for (i = vm.pageCount - 1; i <= vm.pageCount; i++) {
                            pages.push(i);
                        }
                    } else if (vm.page >= vm.pageCount - 4) {
                        for (i = 1; i <= 2; i++) {
                            pages.push(i);
                        }

                        pages.push(0);

                        for (i = vm.pageCount - 6; i <= vm.pageCount; i++) {
                            pages.push(i);
                        }
                    } else {
                        for (i = 1; i <= 2; i++) {
                            pages.push(i);
                        }

                        pages.push(0);

                        for (i = vm.page - 2; i <= vm.page + 2; i++) {
                            pages.push(i);
                        }

                        pages.push(0);

                        for (i = vm.pageCount - 1; i <= vm.pageCount; i++) {
                            pages.push(i);
                        }
                    }
                }
                vm.pages = pages;
            }
        }]
    }
);