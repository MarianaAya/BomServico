
function ValidarLogin()
{
    //event.preventDefault(); // evita refresh da tela

    const URL_TO_FETCH = 'validarLogin';
    
    const data = new URLSearchParams();
    if(document.getElementById('fdadoslogin')!=null)
        for (const pair of new FormData(document.getElementById('fdadoslogin'))) {
            data.append(pair[0], pair[1]);
        }

    fetch(URL_TO_FETCH, { method: 'post', body: data 
    }).then(function (response) {
        return response.text();
    }).then(function (retorno) {
        // result recebe a resposta do módulo dinâmico
        if (retorno.startsWith('Erro')) // problemas ao alterar/gravar
        {
            document.getElementById('erromsgLogin').innerHTML = retorno;
            document.getElementById('erroLogin').style.display = "block";
        } else  // tudo OK, limpar o formulário
        {
            if(retorno.startsWith("LoginOut")){
                if(window.location.href!=="http://localhost:8084/BomServico/visualizarServico.jsp"){
                    window.location.href = "http://localhost:8084/BomServico/";
                }
                else{
                    window.location.href = "http://localhost:8084/BomServico/visualizarServico.jsp";
                }
  
            }
            else
            {
               
         
                document.getElementById('erroLogin').style.display = "none";
                document.getElementById('fdadoslogin').reset();
                    
                if(window.location.href!=="http://localhost:8084/BomServico/visualizarServico.jsp"){
                    window.location.href = "http://localhost:8084/BomServico/";
                }
                else{
                    window.location.href = "http://localhost:8084/BomServico/visualizarServico.jsp";
                }
            }
            
        }
         
    }).catch(function (error) {
        console.error(error);
    });
      
}

function GerarRelatorio(){
    var url = "";
    url = "GerarRelatorio";
    fetch(url,{method:'get'/*opcional*/}).then(response => response.blob())
    .then(data => window.open(URL.createObjectURL(data)))
}

function CarregarHome()
{   //event.preventDefault(); // evita refresh da tela
    var url = "";
 
    url = "TelaPrestador?acao=buscarprestador";
 
    fetch(url,{method:'get'/*opcional*/}).then(function(response)
    {
        response.text().then(function(result)  //response é um promisse
        {
            var aux = result;
            console.log(result);
            if(aux!=="null"){
         
                var usu = aux.split(",");
            
                
                if(usu[6]==="A"){
                    document.getElementById("div-logo").innerHTML+=`
                    <div id="div-cadCategoria">
                        <ul class="navbar-nav mr-auto">
                            <li class="nav-item">
                              <a class="nav-link" href="cadastroCategoria.jsp">Cadastrar Categoria</a>
                              
                            </li>
                        </ul>
                    </div>
                    
                    <div id="div-gerAnuncio" class="ml-2">
                        <ul class="navbar-nav mr-auto">
                            <li class="nav-item">
                              <a class="nav-link" href="paginaAdmin.jsp">Gerenciar Anúncios</a>
                              
                            </li>
                        </ul>
                    </div>
                    <div id="div-gerAnuncio" class="ml-2">
                        <ul class="navbar-nav mr-auto">
                            <li class="nav-item">
                              <button class="btn btn-outline-light my-2 my-sm-0 pl-2 pr-2" type="button" onclick="GerarRelatorio()">Gerar Relatório</button>
                              
                            </li>
                        </ul>
                    </div>`;
                    document.getElementById("div-controleLogin").innerHTML=`
                    <div id="div-perfil" class="w-75">
                        <div  class="w-100 d-flex flex-row-reverse flex-wrap">

                            <div class="dropdown">
                                <button class="btn btn-outline-light my-2 my-sm-0 pl-2 pr-2" type="button" onclick="ValidarLogin()">Logout</button>
                            </div>

                        </div>
                    </div>
                    `;
                }
                else{
                    document.getElementById("div-controleLogin").innerHTML=`
                    <div id="div-perfil" class="w-75">
                        <div  class="w-100 d-flex flex-row-reverse flex-wrap">

                            <div class="dropdown">
                                <button class="btn btn-outline-light my-2 my-sm-0 pl-2 pr-2" type="button" onclick="ValidarLogin()">Logout</button>
                            </div>
                            <a href="paginaPrestador.jsp" id="a-paginaPrestador" class="button-criarConta btn btn-outline-light my-2 my-sm-0 pl-2 pr-2 mr-2" >Meu Perfil</a>
                        </div>
                    </div>
                    `;
                  

                    
                }
               
              
            }
            else
            {
                document.getElementById("div-controleLogin").innerHTML=`
                <div class="dropdown" id="div-login">
                        <button id="dLabel" type="button" class="btn btn-outline-light" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                          Login
                        </button>
                        <div class="dropdown-menu bg-dark text-light">
                            <form class="px-4 py-3" name="dadoslogin" id="fdadoslogin">
                              <div class="form-group" >
                                <label>Email:</label>
                                <input type="email" class="form-control" name="email"  placeholder="email@example.com">
                              </div>
                              <div class="form-group">
                                <label>Senha</label>
                                <input type="password" class="form-control" name="senha"  placeholder="Password">
                              </div>
                                <div class="div-erroLogin" id="erroLogin" style="display:none">
                                  
                                    <span class="erromsgLogin" id="erromsgLogin"></span>
                                </div>
                              <button type="button" onclick="ValidarLogin()" class="btn btn-outline-light">Sign in</button>
                            </form>
                        </div>
                        <a href="cadastroPrestador.jsp" class="button-criarConta btn btn-outline-light my-2 my-sm-0 pl-2 pr-2 " >Crie uma conta</a>
                    </div>
                `

            }
                

                
                
        });
    }).catch (function(err) {console.error(err);});

}