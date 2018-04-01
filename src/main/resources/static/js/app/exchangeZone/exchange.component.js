angular.
module('exchange').component(
    'exchange', {
        templateUrl: '/js/app/exchangeZone/exchange.template.html',
        controller: ['GetMeasures',
            function SensorsController(GetMeasures) {
                var vm = this;
                vm.sensors = null;
                vm.signals = null;
                vm.measures = null;
                vm.meas = null;
                vm.$onInit = function () {
                    vm.measures = GetMeasures.query();
                    // vm.sensors = GetSensors.query();
                    // vm.signals = GetSignals.query();

                };

                vm.setMeas = function (meas) {
                    vm.meas = {};
                    vm.meas.id = meas.id;
                    vm.meas.name = meas.name;
                    vm.meas.dataType = meas.dataType;
                };

                vm.add = function () {
                    vm.meas = {};
                };

                vm.save = function(meas) {
                    vm.measures = GetMeasures.query();
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