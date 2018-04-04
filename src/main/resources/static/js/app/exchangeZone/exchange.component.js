angular.
module('exchange').component(
    'exchange', {
        templateUrl: '/js/app/exchangeZone/exchange.template.html',
        controller: ['GetMeasures','SaveMeasures',
            function SensorsController(GetMeasures, SaveMeasures) {
                var vm = this;
                vm.sensors = null;
                vm.signals = null;
                vm.measures = null;
                vm.meas = null;
                vm.selectedMeas = null;
                vm.error = null;
                vm.$onInit = function () {
                    vm.measures = GetMeasures.query();
                    // vm.sensors = GetSensors.query();
                    // vm.signals = GetSignals.query();

                };

                vm.setMeas = function (meas) {
                    vm.selectedMeas = meas;
                    vm.meas = {};
                    vm.meas.id = meas.id;
                    vm.meas.name = meas.name;
                    vm.meas.dataType = meas.dataType;
                };

                vm.add = function () {
                    vm.selectedMeas = null;
                    vm.meas = {};
                };

                vm.save = function(meas) {
                    SaveMeasures.save({}, meas).$promise.then(function(data) {
                        if (!data.result) {
                            vm.error = 'Ошибка сохранения измеряемого параметра: ' + data.message;
                        } else {
                            vm.error = null;
                            vm.measures = GetMeasures.query();
                            vm.selectedMeas = meas;
                        }
                    })
                };

                vm.setSensor = function (sensor) {
                    vm.sensor = sensor;
                };
                vm.setSignal = function (signal) {
                    vm.signal = signal;
                };
                vm.setMeasure = function (measure) {
                    vm.measure = measure;
                };
            }]
    }
);