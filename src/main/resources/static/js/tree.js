/**
 * Created by nikolay on 03.10.15.
 */

$(document).ready(function() {
    initTree();
});

function initTree() {
    $('#tree').on('changed.jstree', function (e, data) {
        if (data.selected.length > 0) {
            var id = data.selected[0];
            id = id.replace("tree-sensor-", "");
            clickSensor(id, false);
        }

    }).on('before_open.jstree', function (e, data) {
        updateSensorTreeChildren();
    }).jstree();
}

function updateSensorTreeChildren ()  {
    var tree = $('#tree');
    for (var i=0; i<sensors.length; i++) {
        var sensor = sensors[i];
        var treeSensor = tree.find('#tree-sensor-' + sensor.id);
        treeSensor.data('type', sensor.type);
        tree.jstree('set_icon', treeSensor, typeImg + sensor.type + '.png');
    }
}

function updateSensorTree () {
    var tree = $('#tree');
    updateSensorTreeChildren();
    tree.find('li').each(function(index, elem) {
        var root = $(elem);
        if (root.data('litype') == 'root') {
            var type = 1;
            var name = root.data('id');
            for (var i=0; i<sensors.length; i++) {
                var sensor = sensors[i];
                if (sensor.objMonitor == name && sensor.type > type && sensor.type != 4) {
                    type = sensor.type;
                }
            }

            root.data('type', type);
            tree.jstree('set_icon', root, typeImg + type + '.png');
        }
    });
}