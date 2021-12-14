<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <title>Página do admnistrador</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="css/estilo.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
        <script src="js/LoginController.js" type="text/javascript"></script>
        <script src="js/telaAnuncioController.js" type="text/javascript"></script>
        <script src="js/telaCategoriasController.js" type="text/javascript"></script>
    </head>
    <%!
        HttpSession session;
    %>
    <body onload="MostraAnuncios();MostrarCategoriasFiltro();">
         <%
            session = request.getSession(false);
            if (session.getAttribute("usu_id") == null) {
                response.sendRedirect(".");
                session.invalidate();
            }
            else{
                if(session.getAttribute("usu_nivel").toString().charAt(0)=='P'){
                    response.sendRedirect(".");
                }
            }
        %>
        <header>
            <nav class="navbar navbar-expand-lg navbar-dark bg-dark justify-content-between">
                <div class="w-50 d-flex flex-row fles-wrap">
                    <a class="navbar-brand" href=".">BomServiço</a>
                    <div id="div-cadCategoria">
                        <ul class="navbar-nav mr-auto">
                            <li class="nav-item">
                              <a class="nav-link" href="cadastroCategoria.jsp">Cadastrar Categoria</a>
                              
                            </li>
                        </ul>
                    </div>
                    
                    <div id="div-cadCategoria">
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
                    </div>
                </div>
                <div class="dropdown">
                    <button class="btn btn-outline-light my-2 my-sm-0 pl-2 pr-2" type="button" onclick="ValidarLogin()">Logout</button>
                </div>
            </nav>
        </header>
        <div class="height-min bg-light align-items-center">
            <div class="container align-items-center">
                <form class="py-5">
                    <div class="form-row align-items-center">
                        <div class="col-sm-3 my-1">
                          <label class="sr-only" for="exampleFormControlInput1">Palavra-chave:</label>
                          <input type="text" class="form-control" id="filtro" placeholder="Digite uma palavra">
                        </div>
                        <div class="col-sm-3 my-1">
                            <label class="sr-only"  for="exampleFormControlSelect1">Categoria</label>
                            <select class="form-control" id="select-categoria">
                                <option value="">Selecione uma categoria</option>
                               
                            </select>
                        </div>
                        <div class="col-auto my-1">
                            <button type="button" class="btn btn-secondary" onclick="MostraAnuncios()">Pesquisar</button>
                        </div>
                    </div>
                </form>
                <div class="row mt-4" id="div-mostrarAnuncios">
                  
                   
                    
                </div>
            </div>
        </div>
    </body>
</html>


