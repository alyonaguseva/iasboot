$(document).ready(function() {

    $('#graphic-tab').on('shown.bs.tab', function(){
        drawGraphic();
    });

    $('.show-graphic').click(drawCustomGraphic);
});

function drawGraphic() {
    var graphic = $('.graphic');
    var body = $('body');
    var height = body.height() - 120;
    var width = body.width() - $('.col-left').width() - 100;
    graphic.height(height);
    graphic.width(width);

    var data = [];
    $(".sensor").each(function(){
        var me = $(this);
        var value = me.attr('data-value');
        var title = me.attr('data-title');
        data.push([title,value]);
    });
    var min = $('#minValue').val();
    var max = $('#maxValue').val();

    var minLength = $('#minSensorLength').val();
    var maxLength = $('#maxSensorLength').val();

    var graphicData = [{lines:{lineWidth:5}, color: 'red', label: 'Оперативные данные', data: data}];

    Flotr.draw(graphic[0], graphicData, {
        xaxis : {
            min: minLength,
            max: maxLength,
            title: "Длина, м",
            noTicks: 20
        },
        yaxis : {
            max : max,
            min : min,
            title : "Температура, C",
            titleAngle : 90,
            noTicks: 20
        }
    });
}

function drawCustomGraphic() {
    $('#storage-graphic-tab').tab('show');
    var graphic = $('.custom-graphic');
    var body = $('body');
    var height = body.height() - 120;
    var width = body.width() - $('.col-left').width() - 100;
    graphic.height(height);
    graphic.width(width);

    var list = '';
    var start = $('#startDate').val();
    var end = $('#endDate').val();

    $('.sen').each(function() {
       var me = $(this);
        var check = me.find('input').prop("checked");
        if (check) {
            if (list.length > 0) {
                list += ',';
            }
            list += me.find('.length').html();
        }
    });

    $.ajax({
        url: "/ajax/customGraphic",
        data: {
            start: start,
            end: end,
            list: list
        },
        dataType: "json"
    }).done(function (data) {
        Flotr.draw(graphic[0], JSON.parse(data.graphicData), {
            xaxis : {
                min: data.minTime,
                max: data.maxTime,
                title: "Дата время",
                noTicks: 10,
                mode: 'time',
                tickFormatter: numberToTime
            },
            yaxis : {
                max : data.max,
                min : data.min,
                title : "Температура, C",
                titleAngle : 90,
                noTicks: 20
            }
        });
    }).fail(function(jqXHR, textStatus, e ) {
        alert("Ошибка построения графика: " + textStatus);
    });
}

function numberToTime(fn) {
    var date = new Date(fn);
    return formatDateTime(date);
}

