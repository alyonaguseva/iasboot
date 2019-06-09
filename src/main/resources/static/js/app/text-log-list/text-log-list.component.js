angular.
module('textLogList').component(
    'textLogList', {
        templateUrl: '/js/app/text-log-list/text-log-list.template.html',
        bindings: {
            pageCount: '@',
            page: '@',
            pageClean: '@'
        },
        controller: ['TextLog', function TextLogListController(TextLog) {
            var vm = this;
            vm.startDate = null;
            vm.endDate = null;
            vm.type = null;

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
                TextLog.get({page: page - 1, startDate : vm.startDate,
                    endDate : vm.endDate, type : vm.type}, function (data) {
                    vm.logs = data.content;
                    vm.pageCount = data.totalPages;
                    vm.pageClean = false;
                });
            };

            vm.setFilter = function () {
                vm.startDate = $('#startDate').val();
                vm.endDate = $('#endDate').val();
                vm.type = $('#type').val();

                vm.page = 1;
                vm.setPage(1);
            };

            vm.clearFilter = function () {
                $('#startDate').val(null);
                $('#endDate').val(null);
                $('#type').val(null);

                vm.setFilter();
            };

            vm.getText = function (type) {
                if (type == 'error') {
                    return 'Ошибка';
                } else if (type == 'warning') {
                    return 'Предупреждение';
                } else {
                    return 'Информация';
                }
            }

            vm.getClass = function (type) {
                if (type == 'error') {
                    return 'danger';
                } else if (type == 'warning') {
                    return 'warning';
                } else {
                    return 'info';
                }
            }
        }]
    }
);