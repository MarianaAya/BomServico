<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <title>Cadastro Prestador</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
        <link href="css/estilo.css" rel="stylesheet" type="text/css"/>
        <link href="css/cadastroPrestador.css" rel="stylesheet" type="text/css"/>
        <script src="js/PrestadorController.js" type="text/javascript"></script>
        <script src="js/LoginController.js" type="text/javascript"></script>
    </head>
    
    <body onload="InserirDadosPrestador();CarregarHome()">
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
            <div class="background container d-flex justify-content-center">
                <div class="bg-white div-formularioPrestador mt-5 w-75">
                    <div class="p-5">
                        <form name="dados" id="fdados">
                            <div class="form-group form-group-lg d-flex flex-row flex-wrap ">
                                <div  style="display:none">
                                    <label class="w-25">Código: </label><input class="form-control w-75" type="text" name="cod" value="0" />
                                </div>
                                <div class="w-50 d-flex flex-row flex-wrap mt-2">
                                    <label class="w-25">Nome: </label><input class="form-control w-75" type="text" name="nome" value="" />
                                </div>
                                <div class="w-50 d-flex flex-row flex-wrap pl-2 mt-2">
                                    <label class="w-50"> Data de Nascimento: </label><input class="form-control w-50" type="date" name="dtNasc" value=""/>
                                </div>
                                <div class="w-50 d-flex flex-row flex-wrap mt-2">
                                    <label class="w-25">CPF: </label><input class="form-control w-50" type="text" name="cpf" value="" />
                                </div>
                                <div class="w-50 d-flex flex-row flex-wrap pl-2 mt-2">
                                    <label class="w-25">Email: </label><input class="form-control w-75" type="text" name="email" value="" />
                                 </div>
                                <div class="w-50 d-flex flex-row flex-wrap mt-2">
                                    <label class="w-25">Senha: </label><input class="form-control w-75" type="password" name="senha" value="" />
                                 </div>
                                <div class="w-50 d-flex flex-row flex-wrap mt-2 p-2">
                                    <label class="w-25">Confirmar senha: </label><input class="form-control w-75" type="password" name="senha2" value="" />
                                 </div>
                                <div class="alert alert-danger alert-dismissible" id="erro" style="display:none">
                                    <!--<button type="button" class="close" data-dismiss="alert">×</button>-->
                                    <strong>Erro!</strong> <span id="erromsg"></span>
                                </div>
                                <input type="button"  name="acao" onClick="GravaPrestador()" value="Confirmar" class="form-control  mb-2 mr-sm-2 btn btn-success mt-5"/>
                    
                            </div>  
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>

