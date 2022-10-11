google.charts.load('current', {'packages':['corechart', 'table']});
google.charts.setOnLoadCallback(graficoPrincipalFacturas);
google.charts.setOnLoadCallback(graficoPrincipal);
google.charts.setOnLoadCallback(graficoPrincipalReceptores);

google.charts.setOnLoadCallback(tablaReceptores);

function graficoPrincipalFacturas() {
  $.ajax({
    url: "/api/dashboard/totalinscripciones",
    dataType: "json",
  }).done(function (jsonData) {
    var data = new google.visualization.DataTable();
    data.addColumn('string', 'fecha_emision_to_month');
    data.addColumn('number', 'Inscripciones');
    

    jsonData.forEach(function (row) {
      data.addRow([
        row.fecha_emision_to_month,
        row.facturas
      ]);
    });

    var options = {
      title: 'Inscripciones por dia',
      height: 600
    };

    var formatter = new google.visualization.NumberFormat({fractionDigits: 2} );
    formatter.format(data, 1);

    var chart = new google.visualization.ColumnChart(document.getElementById('column_div'));
    chart.draw(data, options );
  }).fail(function (jq, text, err) {
    console.log(text + ' - ' + err);
  });
}


function graficoPrincipal() {
      $.ajax({
        url: "/api/dashboard/montototal",
        dataType: "json",
      }).done(function (jsonData) {
        var data = new google.visualization.DataTable();
        data.addColumn('string', 'fecha_emision_to_month');
        data.addColumn('number', 'montototal');
        

        jsonData.forEach(function (row) {
          data.addRow([
            row.fecha_emision_to_month,
            row.montototal
          ]);
        });

        var options = {
          title: 'Grafico de Facturas',
          height: 600
        };

        var formatter = new google.visualization.NumberFormat({fractionDigits: 2} );
        formatter.format(data, 1);

        var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
        chart.draw(data, options );
      }).fail(function (jq, text, err) {
        console.log(text + ' - ' + err);
      });
}


/* ESTA FUNCION NO NOS SIRVE*////
function graficoPrincipalReceptores() {
      $.ajax({
        url: "/api/dashboard/montototalinscripciones",
        dataType: "json",
      }).done(function (jsonData) {
        var data = new google.visualization.DataTable();
        data.addColumn('string', 'dniReceptor');
        jsonData.forEach(function (row) {
          data.addRow([
            row.dniReceptor,
            row.montototal
          ]);
        });
        var options = {
          title: 'Grafico de Receptores (DNI)',
          height: 600,
        };
        var formatter = new google.visualization.NumberFormat({fractionDigits: 2} );
        formatter.format(data, 1);

        var chart = new google.visualization.BarChart(document.getElementById('chart_div1'));
        chart.draw(data, options );
      }).fail(function (jq, text, err) {
        console.log(text + ' - ' + err);
      });
    
}

/**********************NO TOCAR YA FUNCIONA ESTE ******************** */
function tablaReceptores() {
  $.ajax({
    url: "/api/dashboard/montototalinscripciones",
    dataType: "json",
  }).done(function (jsonData) {
    var data = new google.visualization.DataTable();
    data.addColumn('string', 'DNI de INSCRITOS');
    
    jsonData.forEach(function (row) {
      data.addRow([
        row.dniReceptor
        
      ]);
    });
    var table = new google.visualization.Table(document.getElementById('table_div'));
    table.draw(data, {showRowNumber: true, width: '100%', height: '100%'});
  }).fail(function (jq, text, err) {
    console.log(text + ' - ' + err);
  });
  /*************************FIN TOCAR *********************/
}
