var horarios=[];
var horariosExcluirBanco=[];
var cod=0;
var imagens=[];
var imagensExcluir=[];
function addHorario(){
    var form = document.forms["dados"];
    var erro=document.getElementById("erromsgH");
    if(form.horaInicio.value.length>0 && form.horaFim.value.length>0 && form.dia.value.length>0){
        erro.innerHTML="";
        document.getElementById('erroHorario').style.display = "none";
        console.log("campos corretos");
        var Hcod=0;
        if(horarios.length>0)
            Hcod=horarios[horarios.length-1][0]+1;
        horarios.push([Hcod,form.horaInicio.value,form.horaFim.value,form.dia.value,0]);
        document.getElementById("corpoTabela").innerHTML+="<tr>"+
        "<td>"+horarios[horarios.length-1][3]+"</td>"+
        "<td>"+horarios[horarios.length-1][1]+"</td>"+
        "<td>"+horarios[horarios.length-1][2]+"</td>"+
        "<td>"+
        "<button type='button' class='btn btn-outline-danger' onClick='removerHorario("+horarios[horarios.length-1][0]+")'>Excluir</button>"+
        "</td>"+
        "</tr>";
    }else{
        console.log("campode incorretos");
        erro.innerHTML="";
        console.log(form.horaInicio.value);
        if(form.horaInicio.value.length===0){
            console.log("Campo do horário de inicio vazio");
            erro.innerHTML+="<p>Campo do horário de inicio vazio</p>";
        }
        if(form.horaFim.value.length===0){
            console.log("Campo do fim de horário vazio");
            erro.innerHTML+="<p>Campo do fim de horário vazio</p>";
        }
        if(form.dia.value.length===0){
            console.log("Selecione um dia da semana");
            erro.innerHTML+="<p>Selecione um dia da semana</p>";
        }
        document.getElementById('erroHorario').style.display = "block";
    }
}
async function removerHorario(codH){
    console.log(codH);
    let i=0;
    while(i<horarios.length && horarios[i][0]!==codH){
        i++;
    }
    if(horarios[i][4]==1){
        horariosExcluirBanco.push(horarios[i]);
    }
    horarios=horarios.filter(horarios=>horarios[0]!==codH);
    
    MostrarHorarios();
}
function MostrarHorarios(){
    document.getElementById("corpoTabela").innerHTML="";
    for(var i=0;i<horarios.length;i++){
        document.getElementById("corpoTabela").innerHTML+="<tr>"+
        "<td>"+horarios[i][3]+"</td>"+
        "<td>"+horarios[i][1]+"</td>"+
        "<td>"+horarios[i][2]+"</td>"+
        "<td>"+
        "<button type='button' class='btn btn-outline-danger' onClick='removerHorario("+horarios[i][0]+")'>Excluir</button>"+
        "</td>"+
        "</tr>";
    }
            
}
function MostraCategorias()
{
    const URL_TO_FETCH='TelaFormularioAnuncio?acao=exibeCategorias';
    fetch(URL_TO_FETCH,{method:'get'/*opcional*/}).then(function(response)
    {
        response.text().then(function(result)  //response é um promisse
        {
            document.getElementById('categoria').innerHTML = result;
        });
    }).catch (function(err) {console.error(err);});
}
function BuscarHorarios(cod){
    const URL_TO_FETCH='TelaFormularioAnuncio?acao=exibeHorarios&cod='+cod;
    fetch(URL_TO_FETCH,{method:'get'/*opcional*/}).then(function(response)
    {
        response.text().then(function(result)  //response é um promisse
        {
            var aux=JSON.parse(result);
            for(var i=0;i<aux.length;i++){
                horarios.push([aux[i].id,aux[i].horaInicio,aux[i].horaFim,aux[i].diaSem,1]);
            }
            MostrarHorarios();
        });
    }).catch (function(err) {console.error(err);});
}
function removerImagem(numImg){
    var nomeImg="anuncio_foto"+numImg+".jpg";
    imagensExcluir.push(nomeImg);
    imagens=imagens.filter(imagens=>imagens!==nomeImg);
    CarregarImagens();
}
function CarregarImagens(){
    var fotos=document.getElementById('fotos');
    var numero;
    var partes;
    var aux;
    fotos.innerHTML="";
    for(var i=0;i<imagens.length;i++){
        aux=imagens[i];
        partes=aux.split(".");
        numero=partes[0][partes[0].length-1];
        fotos.innerHTML+="<div class='col-md-4'>"+
            "<div class='card mb-4 box-shadow'>"+
                "<img class='card-img-top' style='height: 225px;width: 100%;display: block' src='"+"fotos/anuncio"+cod+"/"+imagens[i]+"'>"+
                "<p>"+"Imagem "+numero+"</p>"+
                "<button type='button' class='btn btn-outline-danger' onClick='removerImagem("+numero+")'>Excluir</button>"+
            "</div>"+
          "</div>";
    }
}
function CarregarAnuncio(){
    const URL_TO_FETCH='TelaFormularioAnuncio?acao=alterar';
    var acao='alterar';
    fetch(URL_TO_FETCH,{method:'get'/*opcional*/}).then(function(response)
    {
        response.text().then(function(result)  //response é um promisse
        {

            var aux = result;
            var anuncio=JSON.parse(result);
            if(anuncio!==null){
                console.log(result);
                console.log("...................");
                
                
                cod=anuncio.id;
                var form = document.forms["dados"];
                form.titulo.value = anuncio.titulo;
                form.valor.value=anuncio.valor;
                form.descricao.value=anuncio.descricao;
                form.contato.value = anuncio.contato;
                form.categoria.value = anuncio.categoria.id;
                
                if(anuncio.foto1.length>0)
                    imagens.push(anuncio.foto1);
                if(anuncio.foto2.length>0)
                    imagens.push(anuncio.foto2);
                if(anuncio.foto3.length>0)
                    imagens.push(anuncio.foto3);
                CarregarImagens();
                BuscarHorarios(cod);
            }
            else
                cod=0;

        });
    }).catch (function(err) {console.error(err);});
    
}
function GravaAnuncio()
{
    var erro=document.getElementById("erromsg");
    var form = document.forms["dados"];
    if(form.titulo.value.length>0 && form.descricao.value.length>0 &&
            form.valor.value.length>0 && form.valor.value>0 && form.contato.value.length>0){
        document.getElementById('erro').style.display = "none";
        Gravar();
    }else{
        document.getElementById('erro').style.display = "block";
        erro.innerHTML="";
        if(form.titulo.value.length===0)
            erro.innerHTML+="<p>Campo do titulo vazio</p>";
        if(form.valor.value.length===0)
            erro.innerHTML+="<p>Campo do valor vazio</p>";
        else{
            if(form.valor.value<0)
                erro.innerHTML+="<p>Valor inválido</p>";
        }
        if(form.descricao.value.length===0)
            erro.innerHTML+="<p>Campo da descricao vazio</p>";
        if(form.contato.value.length===0)
            erro.innerHTML+="<p>Campo do contato vazio</p>";
    }
}
function Gravar(){
    //event.preventDefault(); // evita refresh da tela
    const URL_TO_FETCH = 'TelaFormularioAnuncio';
    var formData = new FormData(document.getElementById("fdados"));
    formData.append('horarios',horarios);
    formData.append('horariosExc',horariosExcluirBanco);
    formData.append('imagensExc',imagensExcluir);
    formData.append("cod",cod);
    formData.append('acao','confirmar');
    fetch(URL_TO_FETCH, { method: 'post', body: formData
    }).then(function (response) {
        return response.text();
    }).then(function (retorno) {
        // result recebe a resposta do módulo dinâmico
        if (retorno.startsWith('Erro')) // problemas ao alterar/gravar
        {
            document.getElementById('erromsg').innerHTML = retorno;
            document.getElementById('erro').style.display = "block";
        } else  // tudo OK, limpar o formulário
        {
            document.getElementById('erro').style.display = "none";
            document.getElementById('fdados').reset();            
            document.getElementById('fotos').innerHTML="";
            document.getElementById('corpoTabela').innerHTML="";
            window.location.href = "http://localhost:8084/BomServico/paginaPrestador.jsp";
        }
         
    }).catch(function (error) {
        console.error(error);
    });
}




