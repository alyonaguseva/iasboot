
var context;

$(document).ready(function() {

    var sensors = $('.sensor');

    sensors.on('click', function() {
       selectSensor($(this));
    });
});

function selectSensor(sensor) {
    var id = sensor.attr('data-id');
    var container = $('.sensor-container');
    var table = container.find('table');
    table.find('tr').removeClass('info');
    var sen = table.find('.sen-'+id);
    sen.addClass('info');
    container.scrollTop(0);
    container.animate({
        scrollTop:  sen.offset().top - container.offset().top
    });
}



