angular.
module('sensorList').component(
    'sensorList', {
        templateUrl: '/js/app/sensor-list/sensor-list.template.html',
        controller: ['Sensor', 'ControlObjects', 'ControlElements', 'MeasurementTypes',
            'SensorGroups','SensorTypes',
            function SensorListController(Sensor,  ControlObjects, ControlElements, MeasurementTypes,
            SensorGroups, SensorTypes) {
            var vm = this;
            vm.sensor = null;

            vm.$onInit = function () {
                vm.sensors = Sensor.query();
                vm.controlObjects = ControlObjects.query();
                vm.controlElements = ControlElements.query();
                vm.measurementTypes = MeasurementTypes.query();
                vm.sensorGroups = SensorGroups.query();
                vm.sensorTypes = SensorTypes.query();
            };

            vm.setSensor = function (sensor) {
                vm.sensor = sensor;
            };

            vm.clearFilter = function (filter) {
                filter.controlObject = null;
                filter.controlElement = null;
                filter.measurementType = null;
                filter.sensorGroup = null;
                filter.sensorType = null;
            };

            vm.sensorOn = function (sensor) {
                sensor.on = true;
            };

            vm.sensorOff = function (sensor) {
                sensor.on = false;
            };
        }]
    }
);