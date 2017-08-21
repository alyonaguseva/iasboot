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
            vm.startDate = null;
            vm.endDate = null;
            vm.success = null;

            vm.$onInit = function () {
                vm.page = 1;
                vm.setPage(vm.page);

                $(".datepicker").datepicker({
                    firstDay: 1,
                    dateFormat: "dd.mm.yy",
                    dayNames: [ "Воскресение", "Понедельник", "Вторник", "Среда", "Четверг", "Пятница", "Суббота" ],
                    dayNamesMin: [ "Вс", "Пн", "Вт", "Ср", "Чт", "Пт", "Сб" ],
                    dayNamesShort: [ "Воск", "Пон", "Втор", "Сред", "Четв", "Пят", "Суб" ],
                    monthNames: [ "Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь" ],
                    monthNamesShort: [ "Янв", "Фев", "Март", "Апр", "Май", "Июнь", "Июль", "Авг", "Сен", "Окт", "Нояб", "Дек" ]
                });
            };

            vm.setPage = function setPage(page) {
                Log.get({page: page - 1, startDate : vm.startDate,
                    endDate : vm.endDate, success : vm.success}, function (data) {
                    vm.logs = data.content;
                    vm.pageCount = data.totalPages;
                    vm.pageClean = false;
                });
            };

            vm.setFilter = function () {
                vm.startDate = $('#startDate').val();
                vm.endDate = $('#endDate').val();
                vm.success = $('#success').val();

                vm.page = 1;
                vm.setPage(1);
            };

            vm.clearFilter = function () {
                $('#startDate').val(null);
                $('#endDate').val(null);
                $('#success').val(null);

                vm.setFilter();
            };
        }]
    }
);