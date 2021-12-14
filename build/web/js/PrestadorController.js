function ApagaAlteraPrestador(acao, cod)
{   //event.preventDefault(); // evita refresh da tela
    var url = "";
    switch (acao)
    {
        case "apagar":
            url = "TelaPrestador?acao=apagar&cod=" + cod;
            break;
        case "alterar":
            url = "TelaPrestador?acao=alterar&cod=" + cod;
            break;
    }
    
    fetch(url,{method:'get'/*opcional*/}).then(function(response)
    {
        response.text().then(function(result)  //response é um promisse
        {
            if (acao === 'apagar')
            {
                document.getElementById("erromsg").innerHTML = result;
 
            } else
            {
                var aux = result;
                var usu = aux.split(",");
                var form = document.forms["dados"];
                console.log(usu);
                form.cod.value = usu[0];
                form.nome.value = usu[1];
                form.dtNasc.value = usu[2];
                form.cpf.value = usu[3];
                form.email.value = usu[4];
                form.senha.value = usu[5];
                form.senha2.value = usu[5];
            }
        });
    }).catch (function(err) {console.error(err);});

}

function GravaPrestador()
{
    //event.preventDefault(); // evita refresh da tela

    const URL_TO_FETCH = 'TelaPrestador';
    
    const data = new URLSearchParams();
    for (const pair of new FormData(document.getElementById('fdados'))) {
        data.append(pair[0], pair[1]);
    }
    data.append('acao', 'confirmar');
    fetch(URL_TO_FETCH, { method: 'post', body: data 
    }).then(function (response) {
        return response.text();
    }).then(function (retorno) {
        // result recebe a resposta do módulo dinâmico
        if (retorno.startsWith('Erro')) // problemas ao alterar/gravar
        {
            document.getElementById('erromsg').innerHTML = retorno;
            document.getElementById('erro').style.display = "block";
        }else  // tudo OK, limpar o formulário
        {
            if(retorno.startsWith('Alterado')){
                window.location.href = "http://localhost:8084/BomServico/paginaPrestador.jsp";
            }
            else{
                document.getElementById('erro').style.display = "none";
                document.getElementById('fdados').reset();   
            }
                     
   
        }
         
    }).catch(function (error) {
        console.error(error);
    });
      
}

function MostrarDadosPrestador()
{   //event.preventDefault(); // evita refresh da tela
    var url = "";
 
    url = "TelaPrestador?acao=buscarprestador";

    
    fetch(url,{method:'get'/*opcional*/}).then(function(response)
    {
        response.text().then(function(result)  //response é um promisse
        {
            
                var aux = result;
                var usu = aux.split(",");
       
                document.getElementById('p-nomePrestador').innerHTML+=usu[1];
                document.getElementById('p-dtNascPrestador').innerHTML+=mudarEstruturaData(usu[2]);
                document.getElementById('p-cpfPrestador').innerHTML+=usu[3];
                document.getElementById('p-emailPrestador').innerHTML+=usu[4];
        });
    }).catch (function(err) {console.error(err);});

}
function InserirDadosPrestador()
{   //event.preventDefault(); // evita refresh da tela
    var url = "";
 
    url = "TelaPrestador?acao=buscarprestador";

    
    fetch(url,{method:'get'/*opcional*/}).then(function(response)
    {
        response.text().then(function(result)  //response é um promisse
        {
                var aux = result;
     
               
                if(aux!="null"){
                    
                    var usu = aux.split(",");
                    var form = document.forms["dados"];

                    form.cod.value = usu[0];
                    form.nome.value = usu[1];
                    form.dtNasc.value = usu[2];
                    form.cpf.value = usu[3];
                    form.email.value = usu[4];
                    form.senha.value = usu[5];
                    form.senha2.value = usu[5];
                }
        });
    }).catch (function(err) {console.error(err);});

}
function MostrarDadosAnuncio()
{   //event.preventDefault(); // evita refresh da tela
    var url = "";
    var infoAnuncio=document.getElementById("divInfo");
    url = "TelaPrestador?acao=buscaranuncio";

    
    fetch(url,{method:'get'/*opcional*/}).then(function(response)
    {
        response.text().then(function(result)  //response é um promisse
        {
            
                var aux = result;
                console.log("aux"+aux);
                if(aux!=="null"){
                    var anu = aux.split(",");
                    infoAnuncio.innerHTML+="<div id='div-anuncioPrestador'>"+
                        "<div class='div-anuncioPrestador d-flex justify-content-center flex-column pl-5 pr-5 pb-5' >"+
                            "<div class='div-anuncio w-100 p-2'>"+
                                "<p id='p-tituloAnuncio'><strong>Titulo: </strong>"+anu[1]+"</p>"+
                                "<p id='p-descAnuncio'><strong>Descrição:</strong>"+anu[3]+"</p>"+
                                "<div class='div-button w-100 d-flex flex-wrap flex-row-reverse'>"+
                                    "<button type='button' class='btn btn-danger ml-2' onClick='excluirAnuncio("+anu[0]+")'>Excluir</button>"+
                                    "<button type='button' class='btn btn-secondary' onClick='alterarAnuncio("+anu[0]+")'>Alterar</button>"+
                                "</div>"+
                            "</div>"+
                        "</div>"+
                    "</div>";
                    document.getElementById("a-cadAnuncio").style.display="none";
                }       
        });
    }).catch (function(err) {console.error(err);});

}
function excluirAnuncio(cod){
    const URL_TO_FETCH = 'TelaAnuncio';
    
    const data = new URLSearchParams();
    var infoAnuncio=document.getElementById("divInfo");
    data.append('acao', 'apagar');
    data.append('cod', ''+cod);
    console.log("codigo para excluir="+cod);
    fetch(URL_TO_FETCH, { method: 'post', body: data 
    }).then(function (response) {
        return response.text();
    }).then(function (retorno) {
        // result recebe a resposta do módulo dinâmico
        infoAnuncio.innerHTML="";
        document.getElementById("a-cadAnuncio").style.display="block";
         
    }).catch(function (error) {
        console.error(error);
    });
}
function alterarAnuncio(cod){
    var url = "";
    console.log("vou guardar o anuncio: "+cod);
    url = "GuardarAnuncio?cod="+cod;
    fetch(url,{method:'get'/*opcional*/}).then(function(response)
    {
        response.text().then(function(result)  //response é um promisse
        {
            var aux = result;
            if(!aux.startsWith("Erro")){
                window.location.href = "http://localhost:8084/BomServico/cadastroAnuncio.jsp";
            }else{
                console.log("erro ao ir alterar anuncio");
            }
                
        });
    }).catch (function(err) {console.error(err);});
    
}


function mudarEstruturaData(valor){
        var date=new Date(valor);
        let dat="";
        if(date.getDate()<10)
            dat+='0';
        dat+=date.getDate()+"/";
        if(date.getMonth()+1<10)
            dat+='0';
        dat+=(date.getMonth()+1)+"/";
        dat+=date.getFullYear();
        
        
        return dat;
}








