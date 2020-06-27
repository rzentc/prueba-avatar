
var url_servicio_pelicula='http://localhost:8080/peliculas';
var url_servicio_actor='http://localhost:8080/actores?ids=';
var peliculas;

function showPage() {
  document.getElementById("loader").style.display = "none";
  document.getElementById("myDiv").style.display = "block";
}

function hidePage() {
  document.getElementById("loader").style.display = "block";
  document.getElementById("myDiv").style.display = "none";
}

function cargarActoresPelicula(actores) {
    $.ajax({
        url: url_servicio_actor + actores, 
        success: function(rpta){
            
            let tabla = '<table class="table table-striped">' +
                    '<thead>' +
                      '<tr>' +
                        '<th></th>' +
                        '<th></th>' +
                      '</tr>' +
                    '</thead>' +
                    '<tbody>';
            
            for (var i=0; i<rpta.actores.length; ++i)
                tabla += '<tr>' +
                            '<td>'+rpta.actores[i]+'</td>' +
                         '</tr>';
            
            tabla +='</tbody>' +
                  '</table>';
            
            let div = $("#div_actores_pelicula");
            div.html(tabla);
            showPage();
        },
        error: function(error) {
            showPage();
            alert(error);
        }
    });
}

function cargarDetallesPelicula(identificador) {
    let listaActores = '';
    for (var i=0; i<peliculas.length; ++i) {
        if (peliculas[i].url == identificador) {
            for (var j=0; j<peliculas[i].characters.length; ++j)
                if (j==0) listaActores += peliculas[i].characters[j].split("/")[5];
                else listaActores += ('-' + peliculas[i].characters[j].split("/")[5]);
        
            let tabla = '<table class="table table-striped">' +
                    '<thead>' +
                      '<tr>' +
                        '<th></th>' +
                        '<th></th>' +
                      '</tr>' +
                    '</thead>' +
                    '<tbody>'+
                      '<tr>' +
                        '<td>Director</td>' +
                        '<td>'+peliculas[i].director+'</td>' +
                      '</tr>' +
                      '<tr>' +
                        '<td>Productor</td>' +
                        '<td>'+peliculas[i].producer+'</td>' +
                      '</tr>' +
                      '<tr>' +
                        '<td>Fecha de Publicacion</td>' +
                        '<td>'+peliculas[i].release_date+'</td>' +
                      '</tr>' +
                      '<tr>' +
                        '<td>Reseña</td>' +
                        '<td>'+peliculas[i].opening_crawl+'</td>' +
                      '</tr>' +
                    '</tbody>' +
                  '</table>';
            
            let div = $("#div_detalle_pelicula");
            div.html(tabla);
            
            break;
        }
    }

    cargarActoresPelicula(listaActores) ;
}

$(document).ready(function(){

    $.ajax({
        url: url_servicio_pelicula, 
        success: function(rpta){
            peliculas=rpta.results;
            let combo = "<select class='form-control' id='combo_pelicula'>" +
                         "<option value='0'>Selecione...</option>";
            for (var i=0; i<rpta.results.length; ++i)
                combo += "<option value='"+ rpta.results[i].url +"'>" + rpta.results[i].title + "</option>";
            combo += "</select>"
                
            let div = $("#div_combo_pelicula");
            div.html(combo);
            
            $('#combo_pelicula').on('change', function() {
               hidePage();
               cargarDetallesPelicula(this.value);
            });
            showPage();
        },
        error: function(error) {
            showPage();
            alert(error);
        }
    });
});
