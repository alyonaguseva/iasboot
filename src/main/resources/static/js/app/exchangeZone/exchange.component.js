angular.
module('exchange').component(
    'exchange', {
        templateUrl: '/js/app/exchangeZone/exchange.template.html',
        controller: ['GetMeasures','GetComboMeasures','SaveMeasures',
                        'GetSensors','GetComboSensors','SaveSensors','GetSignals','SaveSignals', 'GetComboSignalTypes',
            function SensorsController(GetMeasures, GetComboMeasures, SaveMeasures,
                        GetSensors, GetComboSensors, SaveSensors, GetSignals, SaveSignals, GetComboSignalTypes) {
                var vm = this;
                vm.sensors = null;
                vm.comboSensors = null;
                vm.signals = null;
                vm.measures = null;
                vm.comboMeasures = null;
                vm.meas = null;
                vm.sensor = null;
                vm.signal = null;
                vm.selectedMeas = null;
                vm.selectedSensor = null;
                vm.selectedSignal = null;
                vm.error = null;
                vm.errorSensor = null;
                vm.errorSignal = null;
                vm.comboSignalTypes = null;
                vm.$onInit = function () {
                    vm.measures = GetMeasures.query();
                    vm.sensors = GetSensors.query();
                    vm.signals = GetSignals.query();
                    vm.comboSensors = GetComboSensors.query();
                    vm.comboMeasures = GetComboMeasures.query();
                    vm.comboSignalTypes = GetComboSignalTypes.query();
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
                            vm.comboSensors = GetComboMeasures.query();
                            vm.selectedMeas = meas;
                        }
                    })
                };

                vm.setSensor = function (sensor) {
                    vm.selectedSensor = sensor;
                    vm.sensor = {};
                    vm.sensor.id = sensor.id;
                    vm.sensor.name = sensor.name;
                    vm.sensor.type = sensor.type;
                    vm.sensor.objMonitor = sensor.objMonitor;
                    vm.sensor.tagName = sensor.tagName;
                };

                vm.addSensor = function () {
                    vm.selectedSensor = null;
                    vm.sensor = {};
                };

                vm.saveSensor = function(sensor) {
                    SaveSensors.save({}, sensor).$promise.then(function(data) {
                        if (!data.result) {
                            vm.errorSensor = 'Ошибка сохранения датчика: ' + data.message;
                        } else {
                            vm.errorSensor = null;
                            vm.sensors = GetSensors.query();
                            vm.comboMeasures = GetComboSensors.query();
                            vm.selectedSensor = sensor;
                        }
                    })
                };

                vm.setSignal = function (signal) {
                    vm.selectedSignal = signal;
                    vm.signal = {};
                    vm.signal.id = signal.id;
                    vm.signal.type = signal.type;
                    vm.signal.sensor = signal.sensor;
                    vm.signal.measureParam = signal.measureParam;
                };

                vm.addSignal = function () {
                    vm.selectedSignal = null;
                    vm.signal = {};
                };

                vm.saveSignal = function(signal) {
                    SaveSignals.save({}, signal).$promise.then(function(data) {
                        if (!data.result) {
                            vm.errorSignal = 'Ошибка сохранения сигнала: ' + data.message;
                        } else {
                            vm.errorSignal = null;
                            vm.signals = GetSignals.query();
                            vm.selectedSignal = signal;
                        }
                    })
                };
            }]
    }
);