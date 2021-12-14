function MostraAnuncios()
{   //event.preventDefault(); // evita refresh da tela
    var filtro=document.getElementById("filtro").value; // verifica o filtro
    var filtro_categoria=document.getElementById("select-categoria").value; 
    
    const URL_TO_FETCH='http://localhost:8084/BomServico/webresources/services/query?titulo='+filtro+'&cat_cod='+filtro_categoria;
    fetch(URL_TO_FETCH,{method:'get'/*opcional*/}).then(function(response)
    {
        response.text().then(function(result)  //response é um promisse
        {
            obj=JSON.parse(result);
           obj=JSON.parse(result);
       
            var r="";
            let foto="";
            
            for(var i=0; i<obj.length;i++){
                if(obj[i].foto1.length>0)
                    foto="fotos/anuncio"+obj[i].id+"/"+obj[i].foto1;
                else if(obj[i].foto2.length>0)
                    foto="fotos/anuncio"+obj[i].id+"/"+obj[i].foto2;
                else if(obj[i].foto3.length>0)
                    foto="fotos/anuncio"+obj[i].id+"/"+obj[i].foto3;
          
                r+=
                    "<div class='col-md-4'>"+
                        "<div class='card mb-4 box-shadow'>"+
                            "<img class='card-img-top' style='height: 225px;width: 100%;display: block' src='"+foto+"'"+
                                 "data-holder-rendered='true'>"+
                            "<div class='card-body'>"+
                                "<p class='card-text'>"+obj[i].titulo+"</p>"+
                                "<div class='d-flex justify-content-between align-items-center'>"+
                                    "<div class='btn-group'>"+
                                        "<button type='button' class='btn btn-sm btn-outline-secondary' onclick='GravaCodigoAnuncio("+obj[i].id+")'>Visualizar Anúncio</button>"+
                                    "</div>";
                            
                                if(window.location.href=="http://localhost:8084/BomServico/paginaAdmin.jsp")
                                    r+="<div class='btn-group'>"+
                                        "<button type='button' class='btn btn-sm btn-outline-danger' onclick='ExcluirAnuncio("+obj[i].id+")'>Excluir Anúncio</button>"+
                                    "</div>";
                                r+="</div>"+
                            "</div>"+
                        "</div>"+
                    "</div>"
            }
            document.getElementById('div-mostrarAnuncios').innerHTML = r;
            
        });
    }).catch (function(err) {console.error(err);});
}
function ExcluirAnuncio(cod){
    const URL_TO_FETCH = 'TelaAnuncio';
    
    const data = new URLSearchParams();
   
    data.append('acao', 'apagar');
    data.append('cod', ''+cod);
    console.log("codigo para excluir="+cod);
    fetch(URL_TO_FETCH, { method: 'post', body: data 
    }).then(function (response) {
        return response.text();
    }).then(function (retorno) {
        // result recebe a resposta do módulo dinâmico
        MostraAnuncios();
         
    }).catch(function (error) {
        console.error(error);
    });
}
function GravaCodigoAnuncio(cod)
{
    window.location.href = "http://localhost:8084/BomServico/visualizarServico.jsp?anu_cod="+cod;
}
function VisualizarDadosAnuncio()
{
    //event.preventDefault(); // evita refresh da tela
    var separar=window.location.href.split("anu_cod=");

    const URL_TO_FETCH='TelaAnuncio?acao=procurarcod&cod='+separar[1];
    fetch(URL_TO_FETCH,{method:'get'/*opcional*/}).then(function(response)
    {
        response.text().then(function(result)  //response é um promisse
        {
            var aux = result;
            var anu = aux.split(",");
            console.log(anu);
            document.getElementById("p-nomePrestador").innerHTML+=anu[11];
            document.getElementById("p-valorAnuncio").innerHTML+=anu[2];
            document.getElementById("p-descAnuncio").innerHTML+=anu[3];
            document.getElementById("h1-tituloAnuncio").innerHTML+=anu[1];
            document.getElementById("p-contatoAnuncio").innerHTML+=anu[4];
        });
    }).catch (function(err) {console.error(err);});
    
      
}





