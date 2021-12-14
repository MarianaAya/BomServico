function MostraCategorias()
{   //event.preventDefault(); // evita refresh da tela
    var filtro=document.getElementById("filtro").value; // verifica o filtro
    const URL_TO_FETCH='TelaCategorias?acao=consultar&filtro='+filtro;
    fetch(URL_TO_FETCH,{method:'get'/*opcional*/}).then(function(response)
    {
        response.text().then(function(result)  
        {

            obj=JSON.parse(result);
       
            var tabela="";
            for(var i=0; i<obj.length;i++)
                tabela+="<tr><td>"+obj[i].id+"</td>"+
                        "<td>"+obj[i].nome+"</td>"
                  + "<td>"+"<button type='button' onclick='ApagaAlteraCategoria(\"alterar\","+obj[i].id+")' class='btn btn-outline-secondary ml-2'>Alterar</button>"
                  + "<button type='button' onclick='ApagaAlteraCategoria(\"apagar\","+obj[i].id+")' class='btn btn-outline-danger ml-2'>Excluir</button></td>"
                  + "</tr>";
            
            
            document.getElementById('preview').innerHTML = tabela;
            
        });
    }).catch (function(err) {console.error(err);});
}



function ApagaAlteraCategoria(acao, cod)
{   //event.preventDefault(); // evita refresh da tela
    var url = "";
    switch (acao)
    {
        case "apagar":
            url = "TelaCategorias?acao=apagar&cod=" + cod;
            break;
        case "alterar":
            url = "TelaCategorias?acao=alterar&cod=" + cod;
            break;
    }
    
    fetch(url,{method:'get'/*opcional*/}).then(function(response)
    {
        response.text().then(function(result)  //response é um promisse
        {
            if (acao === 'apagar')
            {
                if(result.startsWith('Erro')){
                    document.getElementById("erromsg").innerHTML = result;
                    document.getElementById('erro').style.display = "block";
                }else{
                    document.getElementById("erromsg").innerHTML = result;
                    document.getElementById('erro').style.display = "none";
                }
                
                MostraCategorias();
            } else
            {
                var aux = result;
                var cat = aux.split(",");
                var form = document.forms["dados"];
                form.cod.value = cat[0];
                form.desc.value = cat[1];
            }
        });
    }).catch (function(err) {console.error(err);});

}

function GravaCategoria()
{
    //event.preventDefault(); // evita refresh da tela

    const URL_TO_FETCH = 'TelaCategorias';
    
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
        } else  // tudo OK, limpar o formulário
        {
            document.getElementById('erro').style.display = "none";
            document.getElementById('fdados').reset();            
            MostraCategorias();
        }
         
    }).catch(function (error) {
        console.error(error);
    });
      
}
function MostrarCategoriasFiltro(){
    var filtro=document.getElementById("filtro").value; // verifica o filtro
    const URL_TO_FETCH='TelaCategorias?acao=consultar&filtro='+filtro;
    fetch(URL_TO_FETCH,{method:'get'/*opcional*/}).then(function(response)
    {
        response.text().then(function(result)  //response é um promisse
        {
            obj=JSON.parse(result);
       
            var select="";
            for(var i=0; i<obj.length;i++)
                select+=`<option value=${obj[i].id}>${obj[i].nome}</option>`;
            
            
            document.getElementById('select-categoria').innerHTML += select;
            
        });
    }).catch (function(err) {console.error(err);});
}







