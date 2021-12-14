
package servlets;


import bd.dal.DALAnuncio;
import bd.dal.DALUsuario;
import bd.entidades.Anuncio;
import bd.entidades.Usuario;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "TelaPrestador", urlPatterns = {"/TelaPrestador"})
public class TelaPrestador extends HttpServlet {

   
  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            response.setContentType("text/html;charset=UTF-8");
            String erro = "";
  
            String acao = request.getParameter("acao");
            int cod;
            try {
                cod = Integer.parseInt(request.getParameter("cod"));
            } catch (Exception e) {
                cod = 0;
            }
            HttpSession session = request.getSession(false);
            DALUsuario ctr = new DALUsuario();
            DALAnuncio ctrAnu = new DALAnuncio();
            Usuario u=null;
            Anuncio a=null;
            switch (acao.toLowerCase()) 
            {
                
                case "apagar":
                    if (!ctr.apagar(cod))
                       erro = "Erro ao apagar o usuario";
                    response.getWriter().print(erro);
                    break;
                case "alterar":
                    u = ctr.getUsuario(cod);
                    response.getWriter().print(u); 
                    break;
                case "buscarprestador":
                    if(session!=null){
                        if(session.getAttribute("usu_id")!=null){
                            cod=Integer.parseInt(session.getAttribute("usu_id").toString());
                            u = ctr.getUsuario(cod);
                        }else{
                            session.invalidate();
                        }
                    }
                    response.getWriter().print(u); 
                    break;
                case "buscaranuncio":
                    
                    cod=Integer.parseInt(session.getAttribute("usu_id").toString());
                    a=ctrAnu.getAnuncioPrestador(cod);

                    response.getWriter().print(a); 
                    break;
                case "confirmar": 
                    erro="ok";
                    
                    if(request.getParameter("nome").length()>0 && request.getParameter("dtNasc").length()>0 &&
                        request.getParameter("email").length()>0 && request.getParameter("senha").length()>0){
                        String nome = request.getParameter("nome");
                        String senha = request.getParameter("senha");
                        String senha2 = request.getParameter("senha2");
                        String email = request.getParameter("email");
                        String cpf = request.getParameter("cpf");
                        LocalDate data = LocalDate.parse(request.getParameter("dtNasc"));
                        System.out.println("Vou validar");
                        if(email.matches("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,4}") && senha.equals(senha2) &&
                           cpf.matches("[0-9]{3}+.[0-9]{3}+.[0-9]{3}+-[0-9]{2}")){
                          
                            Usuario usucpf=ctr.procurarPorCPF(cpf),usuemail=ctr.procurarPorEmail(email);
                            if(usucpf==null && usuemail==null){

                                Usuario usuario = new Usuario(cod,nome,data,cpf,email,senha,'P');
                                if (cod == 0) 
                                {   
                                    if (!ctr.salvar(usuario)) erro = "Erro ao gravar o usuario";}
                                else 
                                {   
                                    if (!ctr.alterar(usuario)) 
                                        erro = "Erro ao alterar o usuario";
                                    else
                                        erro = "Alterado com sucesso";
                                }
                            }
                            else{
                                if(cod!=0){
                                    Usuario usuario = new Usuario(cod,nome,data,cpf,email,senha,'P');
                                    if(usucpf!=null && usuemail!=null){
                                        if(usucpf.getId()==cod && usuemail.getId()==cod){
                                            
                                            if (!ctr.alterar(usuario)) 
                                                erro = "Erro ao alterar o usuario";
                                            else
                                                erro="Alterado com sucesso";
                                        }
                                        else{
                                            erro="Erro!";
                                            if(usucpf.getId()!=cod)
                                                erro+="<p>Existe usuário já cadastrado com esse CPF</p>";
                                            if(usuemail.getId()!=cod)
                                                erro+="<p>Existe usuário já cadastrado com esse email</p>";
                                        }
                                    }
                                    else{
                                        erro="Erro!";
                                        if(usucpf!=null){
                                            if(usucpf.getId()==cod){
                                                if (!ctr.alterar(usuario)) 
                                                    erro = "Erro ao alterar o usuario";
                                                else
                                                    erro="Alterado com sucesso";
                                            }
                                            else
                                                erro+="<p>Existe usuário já cadastrado com esse CPF</p>";
                                        }
                                        
                                        if(usuemail!=null){
                                            if(usuemail.getId()==cod){
                                                if (!ctr.alterar(usuario)) 
                                                    erro = "Erro ao alterar o usuario";
                                                else
                                                    erro="Alterado com sucesso";
                                            }
                                            else
                                            erro+="<p>Existe usuário já cadastrado com esse email</p>";
                                        }
                                    }
                                    
                                }
                                else{
                                    erro="Erro!";
                                    if(usucpf!=null)
                                        erro+="<p>Existe usuário já cadastrado com esse CPF</p>";
                                    if(usuemail!=null)
                                        erro+="<p>Existe usuário já cadastrado com esse email</p>";
                                }
                            }
                            
                            response.getWriter().print(erro);
                        }
                        else{
                            
                            erro="Erro!";
                            if(!email.matches("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,4}"))
                                erro+="<p>Email invalido</p>";
                            if(!senha.equals(senha2))
                                erro+="<p>Senhas diferentes</p>";
                            if(!cpf.matches("[0-9]{3}+.[0-9]{3}+.[0-9]{3}+-[0-9]{2}"))
                                erro+="<p>CPF inválido</p>";
  
                            response.getWriter().print(erro);
                        }
                            
                    }
                    else{
                        if(cod==0)
                            erro = "Erro ao gravar o usuario. Campos vazios";
                        else
                            erro="Erro ao alterar o usuario. Campos vazios";
                        
                        response.getWriter().print(erro);
                    }
                    break;
            }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
