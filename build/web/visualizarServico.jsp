<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <title>Visualizar Serviço</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
        <link href="css/estilo.css" rel="stylesheet" type="text/css"/>
        <script src="js/telaVisualizarServicoController.js" type="text/javascript"></script>
        <script src="js/LoginController.js" type="text/javascript"></script>
        <script src="js/telaAnunciosController.js" type="text/javascript"></script>
    </head>
    <body onload="CarregarHome();MostrarAnuncio();">
        <header>
            <nav class="navbar navbar-expand-lg navbar-dark bg-dark justify-content-between">
                <div class="w-50 d-flex flex-row fles-wrap" id="div-logo">
                    <a class="navbar-brand" href=".">BomServiço</a>         
                </div>
                <div class="w-25 d-flex flex-row-reverse flex-wrap " id="div-controleLogin"> 
                </div>
            </nav>
        </header>
        <div class="py-5 height-min bg-light align-items-center">
            <div class="mt-5 mb-5 background container">
                <div class="div-paginaAnuncio">
                    <div class="p-5">
                        <div id="infoCliente">
                        </div>
                        <div class="d-flex justify-content-center mt-4" id="fotos">
                            
                        </div>
                        <div class="d-flex flex-wrap flex-row w-100">
                            <div class="text-center w-25">
                                <h1 class="titulo-horario">Contato</h1>
                                <p id="p-contatoAnuncio"></p>
                      
                            </div>
                            <div class="text-center w-75" id="horarios">
                                <h1 class="titulo-horario">Horário de Funcionamento</h1>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>

