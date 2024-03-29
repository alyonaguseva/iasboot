angular.
module('exchange').component(
    'exchange', {
        templateUrl: '/js/app/exchangeZone/exchange.template.html',
        controller: ['GetMeasures','GetComboMeasures','SaveMeasures',
                        'GetSensors','GetComboSensors','SaveSensors','GetSignals','SaveSignals', 'GetComboSignalTypes',
                        'GetPL302s','SavePL302s',
            function SensorsController(GetMeasures, GetComboMeasures, SaveMeasures,
                        GetSensors, GetComboSensors, SaveSensors, GetSignals, SaveSignals, GetComboSignalTypes,
                                       GetPL302s, SavePL302s) {
                var vm = this;
                vm.sensors = null;
                vm.comboSensors = null;
                vm.signals = null;
                vm.measures = null;
                vm.comboMeasures = null;
                vm.pl302s = null;
                vm.meas = null;
                vm.sensor = null;
                vm.signal = null;
                vm.pl302 = null;
                vm.selectedMeas = null;
                vm.selectedSensor = null;
                vm.selectedSignal = null;
                vm.selectedPL302 = null;
                vm.error = null;
                vm.errorSensor = null;
                vm.errorSignal = null;
                vm.errorPL302 = null;
                vm.comboSignalTypes = null;
                vm.$onInit = function () {
                    vm.pl302s = GetPL302s.query();
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
                    vm.signal.tagName = signal.tagName;
                    vm.signal.inTag = signal.inTag;
                    vm.signal.pl302 = signal.pl302;
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

                vm.setPL302 = function (pl302) {
                    vm.selectedPL302 = pl302;
                    vm.pl302 = {};
                    vm.pl302.id = pl302.id;
                    vm.pl302.name = pl302.name;
                    vm.pl302.url = pl302.url;
                    vm.pl302.password = pl302.password;
                };

                vm.addPL302 = function () {
                    vm.selectedPL302 = null;
                    vm.pl302 = {};
                };

                vm.savePL302 = function(pl302) {
                    SavePL302s.save({}, pl302).$promise.then(function(data) {
                        if (!data.result) {
                            vm.errorPL302 = 'Ошибка сохранения pl302: ' + data.message;
                        } else {
                            vm.errorPL302 = null;
                            vm.pl302s = GetPL302s.query();
                            vm.selectedPL302 = pl302;
                        }
                    })
                };
            }]
    }
);