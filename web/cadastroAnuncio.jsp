<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <title>Cadastro Anúncio</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
        <link href="css/cadastroAnuncio.css" rel="stylesheet" type="text/css"/>
        <link href="css/estilo.css" rel="stylesheet" type="text/css"/>
        <script src="js/telaFormularioAnuncioController.js" type="text/javascript"></script>
        <script src="js/LoginController.js" type="text/javascript"></script>
    </head>
    <%!
        HttpSession session;
    %>
    <body onload="MostraCategorias();CarregarAnuncio();">
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
                <a class="navbar-brand" href=".">BomServiço</a>
                <div class="w-25 d-flex flex-row-reverse flex-wrap ">
                    <div id="div-perfil" class="w-75 d-flex flex-row-reverse flex-wrap">

                        <div class="dropdown">
                            <button class="btn btn-outline-light my-2 my-sm-0 pl-2 pr-2" type="button" onclick="ValidarLogin()">Logout</button>
                        </div>
                        <a href="paginaPrestador.jsp" class="button-criarConta btn btn-outline-light my-2 my-sm-0 pl-2 pr-2 mr-2" >Meu Perfil</a>
                    </div>
                </div>
            </nav>
        </header>
        <div class="py-5 bg-light align-items-center">
            <div class="background container">
                <div class="div-formularioAnuncio">
                    <div class="p-5">
                        <form name="dados" method="POST" id="fdados" enctype="multipart/form-data" >
                            <div class="form-group form-group-lg">
                                <div class="w-100 d-flex flex-row flex-wrap">
                                    <label class="w-20">Titulo: </label><input class="form-control w-80" type="text" name="titulo" value="" />
                                </div>
                                <div class="w-100 d-flex flex-row flex-wrap mt-2">
                                    <label class="w-20"  for="exampleFormControlSelect1">Categoria</label>
                                    <select class="form-control w-25" name="categoria" id="categoria">
                                    </select>
                                </div>
                                <div class="w-100 d-flex flex-row flex-wrap mt-2">
                                    <label class="w-20">Valor: </label><input class="form-control w-25" type="number" step="0.1" name="valor"/>
                                </div>
                                <div class="w-100 d-flex flex-row flex-wrap mt-2">
                                    <label class="w-20">Descrição: </label><textarea class="form-control w-80" name="descricao" rows="3"></textarea>
                                </div>
                                <div class="w-100 d-flex flex-row flex-wrap mt-2">
                                    <label class="w-20">Contato: </label><input class="form-control w-80" type="text" name="contato" value="" />
                                </div>
                                <div class="d-flex flex-row w-100 mt-2">
                                    <label class="w-20">Imagem 1: </label><input type="file" name="foto1" class="form-control-file border w-50">
                                </div>
                                <div class="d-flex flex-row w-100 mt-2">
                                    <label class="w-20">Imagem 2: </label><input type="file" name="foto2" class="form-control-file border w-50">
                                </div>
                                <div class="d-flex flex-row w-100 mt-2">
                                    <label class="w-20">Imagem 3: </label><input type="file" name="foto3" class="form-control-file border w-50">
                                </div>
                                <div class="d-flex justify-content-center mt-4" id="fotos">
                                    
                                </div>
                                <div class="d-flex flex-row flex-wrap">
                                    <p class="w-100 text-center p-tituloHorarios mt-2"><strong>Horários de Atendimento</strong></p>
                                    <div class="d-flex flex-row w-25">
                                        <label>Horário de Inicio: </label><input class="form-control w-50 ml-2" type="time" name="horaInicio" value="" />
                                    </div>
                                    <div class="d-flex flex-row w-25">
                                        <label class="ml-2">Horário de Fim: </label><input class="form-control w-50 ml-2" type="time" name="horaFim" value="" />
                                    </div>
                                    <div class="d-flex flex-row w-50">
                                        <label class="ml-2 w-25">Dia da Semana:</label>
                                        <select name="dia" id="dia" class="form-control input-diaSemana ml-2 w-75">
                                            <option value="">Selecione um dia</option>
                                            <option value="Segunda">Segunda</option>
                                            <option value="Terca">Terça</option>
                                            <option value="Quarta">Quarta</option>
                                            <option value="Quinta">Quinta</option>
                                            <option value="Sexta">Sexta</option>
                                            <option value="Sabado">Sábado</option>
                                            <option value="Domingo">Domingo</option>
                                        </select>
                                    </div>
                                    <div class="d-flex flex-row w-100 mt-2 justify-content-center">
                                        <input type="button"  name="acao" value="Adicionar horário" onClick="addHorario()" class="form-control btn btn-secondary w-50"/>
                                    </div>
                                    <div class="alert alert-danger alert-dismissible mt-4" id="erroHorario" style="display:none">
                                        <strong>Erro!</strong> <span id="erromsgH"></span>
                                    </div>
                                    <table class="table table-hover mt-4">
                                        <thead>
                                          <tr>
                                            <th>Dia da Semana</th>
                                            <th>Horário de Inicio</th>
                                            <th>Horário de Fim</th>
                                            <th>Exclusão</th>
                                          </tr>
                                        </thead>
                                        <tbody id="corpoTabela">
                                          
                                        </tbody>
                                    </table>
                                    
                                </div>
                                <div class="alert alert-danger alert-dismissible" id="erro" style="display:none">
                                    <strong>Erro!</strong> <span id="erromsg"></span>
                                </div>
                                <input type="button"  name="acao" value="Confirmar" onClick="GravaAnuncio()" class="form-control  mb-2 mr-sm-2 btn btn-success mt-5"/>

                            </div>  
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>

