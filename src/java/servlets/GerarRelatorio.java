package servlets;

import bd.util.Conexao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;


@WebServlet(name = "GerarRelatorio", urlPatterns = {"/GerarRelatorio"})
public class GerarRelatorio extends HttpServlet {
    private byte[] gerarRelatorioPDF(String sql, String relat)
    {   byte[] pdf;
        try { //sql para obter os dados para o relatorio
            JasperPrint jasperprint=null;
            Conexao con=new Conexao();
            ResultSet rs =con.consultar(sql);
            System.out.println(""+rs);
            con.fecharConexao();
            JRResultSetDataSource jrRS = new JRResultSetDataSource(rs);
            System.out.println(""+jrRS.toString());
            jasperprint = JasperFillManager.fillReport(relat, null, jrRS);
            pdf=JasperExportManager.exportReportToPdf(jasperprint);

        } catch (JRException erro) {
            pdf=null;
            System.out.println("Um erro ocorreu ao gerar o pdf");
        }
        return pdf;
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/pdf");
        byte[] pdf=gerarRelatorioPDF("select * from anuncio as a,usuario as s, categoria as c where a.usu_id=s.usu_id and a.cat_id=c.cat_id order by cat_desc",
                                 getServletContext().getRealPath("")+"/relatorios/rel_anuncios.jasper");
        System.out.println("pdf="+pdf.length);
        response.getOutputStream().write(pdf,0,pdf.length);
        response.getOutputStream().flush();
        response.getOutputStream().close();
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
