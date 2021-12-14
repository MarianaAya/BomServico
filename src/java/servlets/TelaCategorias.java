
package servlets;

import bd.dal.DALCategoria;
import bd.entidades.Categoria;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "TelaCategorias", urlPatterns = {"/TelaCategorias"})
@MultipartConfig(
    location="/", 
    fileSizeThreshold=1024*1024,    // 1MB *      
    maxFileSize=1024*1024*100,      // 100MB **
    maxRequestSize=1024*1024*10*10  // 100MB ***
)
public class TelaCategorias extends HttpServlet {

    
    public String buscaCategorias(String filtro) {
        String res = "";
        int j;
        ArrayList<Categoria> categorias = new DALCategoria().getCategoria(filtro);
        Gson gson=new Gson();
        res=gson.toJson(categorias);
        
        
        return res;
    }
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
            DALCategoria ctr = new DALCategoria();
            
            switch (acao.toLowerCase()) 
            {
                case "consultar":
                    String filtro = request.getParameter("filtro");
                    if (!filtro.isEmpty()) filtro = "upper(cat_desc) like '%" + filtro.toUpperCase() + "%'";
                    response.getWriter().print(buscaCategorias(filtro));
                    break;
                    
                case "apagar":System.out.println("cod:"+cod);
                    if (!ctr.apagar(cod)){
                       erro = "Erro ao apagar a categoria";
                    }
                    response.getWriter().print(erro);
                    break;
                case "alterar":
                    Categoria c = ctr.getCategoria(cod);
                    System.out.println(c.getNome());
                    response.getWriter().print(c); // retorna todos os dados na forma de lista (,,,)
                    break;
             
                case "confirmar": //novo e alteração
                    erro="ok";
                    if(request.getParameter("desc").length()>0){
                        String desc = request.getParameter("desc");
                        Categoria categoria = new Categoria(cod,desc);
                        if (cod == 0) 
                        {   
                            if (!ctr.salvar(categoria)) erro = "Erro ao gravar a categoria";}
                        else 
                        {   if (!ctr.alterar(categoria)) erro = "Erro ao alterar a categoria";}
                        response.getWriter().print(erro);
                    }
                    else{
                        if(cod==0)
                            erro = "Erro ao gravar a categoria. Campos vazios";
                        else
                            erro="Erro ao alterar a categoria. Campos vazios";
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
