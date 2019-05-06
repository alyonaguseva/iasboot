angular.
module('signalValueExtList').component(
    'signalValueExtList', {
        templateUrl: '/js/app/signal-value-ext-list/signal-value-ext-list.template.html',
        controller: ['SignalValueExt', function SignalValueExtListController(SignalValueExt) {
            var vm = this;

            vm.$onInit = function () {
                vm.signalValueExts = SignalValueExt.query();
            };
        }]
    }
);