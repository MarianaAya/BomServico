<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <title>Página Prestador</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
        <link href="css/estilo.css" rel="stylesheet" type="text/css"/>
        <link href="css/paginaPrestador.css" rel="stylesheet" type="text/css"/>
        <script src="js/PrestadorController.js" type="text/javascript"></script>
        <script src="js/LoginController.js" type="text/javascript"></script>
    </head>
    <%!
        HttpSession session;
    %>
    <body onload="MostrarDadosPrestador();MostrarDadosAnuncio();CarregarHome();">
        <%
            session = request.getSession(false);
            if (session.getAttribute("usu_id") == null) {
                response.sendRedirect(".");
                session.invalidate();
            }
            else{
                if(session.getAttribute("usu_nivel").toString().charAt(0)=='A'){
                    response.sendRedirect(".");
                }
            }
        %>
        <header>
            <nav class="navbar navbar-expand-lg navbar-dark bg-dark justify-content-between">
                <div class="w-50 d-flex flex-row fles-wrap" id="div-logo">
                    <a class="navbar-brand" href=".">BomServiço</a>         
                </div>
                <div class="w-25 d-flex flex-row-reverse flex-wrap " id="div-controleLogin"> 
                </div>
            </nav>
        </header>
        <div class="height-min py-5 h-100 bg-light align-items-center">
            <div class="background container  d-flex justify-content-center">
                <div class="div-paginaAnunciante w-75" >

                    <div class="p-5 d-flex flex-wrap flex-row">
                        <p class="w-100" id="p-nomePrestador"><strong>Nome: </strong></p>
                        <p class="w-50" id="p-cpfPrestador"><strong>CPF: </strong></p>
                        <p class="w-50" id="p-dtNascPrestador"><strong>Data de Nascimento: </strong></p>

                        <p id="p-emailPrestador"><strong>Email:</strong> </p>
                        <div class="d-flex flex-wrap w-100">
                            <a href="cadastroPrestador.jsp" class="btn btn-outline-secondary">Alterar</a>
                            <a href="cadastroAnuncio.jsp" class="btn btn-outline-secondary ml-2" id="a-cadAnuncio">Cadastrar Anuncio</a>
                        </div>
                    </div>
                    <div id="divInfo">
                        
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
