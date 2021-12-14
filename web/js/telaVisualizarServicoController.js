function MostrarAnuncio()
{
    var separar=window.location.href.split("anu_cod=");
    var dados = document.getElementById("infoCliente");
    var fotos=document.getElementById("fotos");
    var horarios=document.getElementById("horarios");
    var contato=document.getElementById("p-contatoAnuncio");
    console.log("cod"+separar[1]);
    fetch("TelaInfoAnuncio?codigo="+separar[1], {method: 'POST'})
    .then(function (response) {
        return response.text();})
    .then(function (text) {
        var json=JSON.parse(text);
        console.log(json);
        dados.innerHTML="<p><strong>Titulo:</strong>"+json.titulo+"</p>"+
                        "<p><strong>Valor:</strong> R$"+json.valor+"</p>"+
                        "<p><strong>Prestador: </strong>"+json.usuario.nome+"</p>"+
                        "<p><strong>Descrição: </strong>"+json.descricao+"</p>";
                        
        
        fotos.innerHTML="";
        if(json.foto1.length>0){
        fotos.innerHTML+="<div class='col-md-4'>"+
                            "<div class='card mb-4 box-shadow'>"+
                                "<img class='card-img-top' style='height: 225px;width: 100%;display: block' src='"+"fotos/anuncio"+json.id+"/"+"anuncio_foto1.jpg"+"'>"+
                            "</div>"+
                          "</div>";
        }
        if(json.foto2.length>0){
            fotos.innerHTML+="<div class='col-md-4'>"+
                            "<div class='card mb-4 box-shadow'>"+
                                "<img class='card-img-top' style='height: 225px;width: 100%;display: block' src='"+"fotos/anuncio"+json.id+"/"+"anuncio_foto2.jpg"+"'>"+
                            "</div>"+
                          "</div>";
        }
        if(json.foto3.length>0){
            fotos.innerHTML+="<div class='col-md-4'>"+
                            "<div class='card mb-4 box-shadow'>"+
                                "<img class='card-img-top' style='height: 225px;width: 100%;display: block' src='"+"fotos/anuncio"+json.id+"/"+"anuncio_foto3.jpg"+"'>"+
                            "</div>"+
                          "</div>";
        }
        for(let i=0;i<json.horarios.length;i++){
            horarios.innerHTML+="<p>"+json.horarios[i].diaSem+"  "+json.horarios[i].horaInicio+"  "+json.horarios[i].horaFim+"</p>";
        }
        contato.innerHTML="<p>"+json.contato+"</p>"
    }).catch(function (error) {
        console.error(error);
    });
}


