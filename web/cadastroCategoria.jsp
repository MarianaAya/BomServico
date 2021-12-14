<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Cadastro da categoria</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
        <script src="js/telaCategoriasController.js" type="text/javascript"></script>
        <script src="js/LoginController.js" type="text/javascript"></script>
    </head>
    <%!
        HttpSession session;
    %>
    <body onload="MostraCategorias();">
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
        <div class="py-5 h-100 bg-light align-items-center">
            <div class="container text-center">
                <h1>Gerenciamento de Categorias</h1>
                <form class="mt-4" name="dados" id="fdados">
                    <div class="form-row text-left">
                        <div class="col-md-2 mb-3">
                          <label for="validationDefault01">Código</label>
                          <input type="text" name="cod" class="form-control" id="validationDefault01" readonly value="0">
                        </div>
                        <div class="col-md-10 mb-3">
                          <label for="validationDefault02">Descrição</label>
                          <input type="text" name="desc" class="form-control" id="validationDefault02" placeholder="Digite a categoria" value="" required>
                        </div>
                    </div>
                    <div class="alert alert-danger alert-dismissible" id="erro" style="display:none">
                        <!--<button type="button" class="close" data-dismiss="alert">×</button>-->
                        <strong>Erro!</strong> <span id="erromsg"></span>
                    </div>
                    <button class="btn btn-success" type="button" onClick="GravaCategoria()">Salvar</button>
                </form>
                <form name="pesquisa" class="mt-5">
                    <div class="form-group-lg d-flex justify-content-center">
                        <input type="text" name="filtro" id="filtro" size="120" placeholder="Informe parte do nome" class="form-control  mb-2 mr-sm-2 w-50"/>
                        <input type="button"  onClick="MostraCategorias()" name="bpesq" value="Pesquisar" class="form-control  mb-2 mr-sm-2 btn btn-primary w-25"/>
                    </div>
                </form>
                <table class="table table-hover mt-4">
                    <thead>
                      <tr>
                        <th scope="col">Código</th>
                        <th scope="col">Descrição</th>
                        <th scope="col">Ação</th>
                      </tr>
                    </thead>
                    <tbody id="preview">
                      
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>

