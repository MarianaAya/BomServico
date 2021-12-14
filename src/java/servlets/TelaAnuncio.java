package servlets;

import bd.dal.DALAnuncio;
import bd.entidades.Anuncio;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "TelaAnuncio", urlPatterns = {"/TelaAnuncio"})
public class TelaAnuncio extends HttpServlet {

    public String buscaAnuncios(String filtro) {
        String res = "";
        int j;
        ArrayList<Anuncio> anuncios = new DALAnuncio().getAnuncio(filtro);
      
        Gson gson=new Gson();
        res=gson.toJson(anuncios);
        
        
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

        Anuncio anu=null;
        DALAnuncio ctr = new DALAnuncio();
        HttpSession session = request.getSession(false);
        switch (acao.toLowerCase()) 
        {
            case "consultar":
                String filtro = request.getParameter("filtro");
                String cat_cod = request.getParameter("filtro-categoria");

                if (!filtro.isEmpty() && !cat_cod.isEmpty()) 
                    filtro = "upper(anu_titulo) like '%" + filtro.toUpperCase() + "%'"+" and cat_id="+Integer.parseInt(cat_cod);
                else{
                    if(!filtro.isEmpty())
                        filtro = "upper(anu_titulo) like '%" + filtro.toUpperCase() + "%'";
                    if(!cat_cod.isEmpty())
                        filtro =  "cat_id="+Integer.parseInt(cat_cod);
                }
                System.out.println(filtro);
                response.getWriter().print(buscaAnuncios(filtro));
                break;
            case "gravarcod":
                session.setAttribute("anu_id", cod);

                break;
            case "procurarcod":
                anu=ctr.getAnuncio(cod);
                response.getWriter().print(anu);

                break;
            case "apagar":
                if (!ctr.apagar(cod)){
                   erro = "Erro ao apagar a anuncio";
                }else{
                    if(session!=null){
                        if(session.getAttribute("anuncio_cod")!=null)
                            session.removeAttribute("anuncio_cod");
                    }
                }
                response.getWriter().print(erro);
                break;
            case "alterar":
                Anuncio a = ctr.getAnuncio(cod);

                response.getWriter().print(a); 
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
