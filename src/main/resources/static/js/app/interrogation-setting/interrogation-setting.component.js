angular.
module('interrogationSetting').component(
    'interrogationSetting', {
        templateUrl: '/js/app/interrogation-setting/interrogation-setting.template.html',
        controller: ['GetInterrogationSetting','SaveInterrogationSetting','Bing3Exchange',
            function InterrogationSettingController(GetInterrogationSetting, SaveInterrogationSetting, Bing3Exchange) {
            var vm = this;
            this.setting = {};

            vm.$onInit = function () {
                vm.setting = GetInterrogationSetting.query();
            };

            vm.save = function() {
                SaveInterrogationSetting.save({}, this.setting).$promise.then(function(data) {
                    if (!data.result) {
                        vm.error = 'Ошибка сохранения настроек: ' + data.message;
                        vm.message = null;
                    } else {
                        vm.message = 'Настройки успешно сохранены';
                        vm.error = null;
                        vm.setting = GetInterrogationSetting.query();
                    }
                })
            };

            vm.bing3Exchange = function () {
                Bing3Exchange.save({}, {}).$promise.then(function (data) {
                    if (!data.result) {
                        vm.error = 'Ошибка взаимодействия с Bing3: ' + data.message;
                        vm.message = null;
                    } else {
                        vm.message = 'Взаимодействие выполнено';
                    }
                })
            };
        }]
    }
);