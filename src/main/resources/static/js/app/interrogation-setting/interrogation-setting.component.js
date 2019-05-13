angular.
module('interrogationSetting').component(
    'interrogationSetting', {
        templateUrl: '/js/app/interrogation-setting/interrogation-setting.template.html',
        controller: ['GetInterrogationSetting','SaveInterrogationSetting',
            function InterrogationSettingController(GetInterrogationSetting, SaveInterrogationSetting) {
            var vm = this;
            this.setting = {};

            vm.$onInit = function () {
                vm.setting = GetInterrogationSetting.query();
            };

            vm.save = function() {
                SaveInterrogationSetting.save({}, this.setting).$promise.then(function(data) {
                    if (!data.result) {
                        vm.error = 'Ошибка сохранения настроек: ' + data.message;
                    } else {
                        vm.error = null;
                        vm.setting = GetInterrogationSetting.query();
                    }
                })
            };
        }]
    }
);