package servlets;

import bd.dal.DALUsuario;
import bd.entidades.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "validarLogin", urlPatterns = {"/validarLogin"})
public class validarLogin extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String email,senha;
        String erro="";

        HttpSession session = request.getSession(false);
        Usuario usu=null;
       
        if(session==null){
            email=request.getParameter("email").toString();
            senha=request.getParameter("senha").toString();
            
            if(email.length()>0 && senha.length()>0){
                
            
                try
                {
                    DALUsuario ctr=new DALUsuario();
                    usu=ctr.getUsuario(email, senha);
                    if(usu==null){   
                        erro="Erro! Email e/ou senha incorretos";
                        response.getWriter().print(erro);
              
                    }
                    else{
                        
                        session = request.getSession(true);
                        session.setAttribute("usu_id", usu.getId());  
                        session.setAttribute("usu_nivel", usu.getNivel()); 
          
                    }
                }catch(Exception e)
                {
                    System.out.println("Erro");
                    response.sendRedirect("."); //voltar para index
                    
                }
            }
            else
            {
                erro="Erro! Campos Vazios";
                response.getWriter().print(erro);
            }
        }
        else{
            session.invalidate();
            System.out.println("Log out");
            erro="LoginOut";
            response.getWriter().print(erro);
           
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
