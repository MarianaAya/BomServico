package servlets;

import bd.dal.DALAnuncio;
import bd.dal.DALCategoria;
import bd.dal.DALHorario;
import bd.dal.DALUsuario;
import bd.entidades.Anuncio;
import bd.entidades.Categoria;
import bd.entidades.Horario;
import bd.entidades.Usuario;
import com.google.gson.Gson;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;


@WebServlet(name = "TelaFormularioAnuncio", urlPatterns = {"/TelaFormularioAnuncio"})
@MultipartConfig(
    location="/", 
    fileSizeThreshold=1024*1024,    // 1MB *      
    maxFileSize=1024*1024*100,      // 100MB **
    maxRequestSize=1024*1024*10*10  // 100MB ***
)
public class TelaFormularioAnuncio extends HttpServlet {
    public void gravarImagem(Part foto,Anuncio anuncio,int numImagem) {
        try{
            if(foto.getSize()>0){
                byte[] imagem=new byte[(int)foto.getSize()];
                foto.getInputStream().read(imagem);
                // cria um arquivo com o mesmo nome da foto e grava o vetor como seu conteúdo                 + "/" +
                File file=new File(getServletContext().getRealPath("/")+"/fotos/"+"anuncio"+anuncio.getId());
                if(!file.exists()){
                    if(file.mkdir())
                    System.out.println("nao existe uma pasta para esse anuncio e criei");
                    else
                        System.out.println("nao consegui criar");
                }
                
                //Ver a imagem com esse nome já existe
                File fileImg=new File(getServletContext().getRealPath("/") + "/fotos/" + 
                        "/anuncio"+anuncio.getId()+"/"+"anuncio_foto"+numImagem+".jpg");
                if(fileImg.exists()){//se existe exclui
                    fileImg.delete();
                }
                
                FileOutputStream arquivo = new FileOutputStream(new File(getServletContext().getRealPath("/") + "/fotos/" + 
                        "/anuncio"+anuncio.getId()+"/"+"anuncio_foto"+numImagem+".jpg"));
                arquivo.write(imagem);
                arquivo.close();
            }
        }catch(Exception e){}
    }
    public void excluirImagem(Anuncio anuncio,int numImagem) {
        try{
            File file=new File(getServletContext().getRealPath("/")+"/fotos/"+"anuncio"+anuncio.getId());
            if(!file.exists()){
                if(file.mkdir())
                    System.out.println("nao existe uma pasta para esse anuncio e criei");
                else
                    System.out.println("nao consegui criar");
            }
            File fileImg=new File(getServletContext().getRealPath("/") + "/fotos/" + 
                    "/anuncio"+anuncio.getId()+"/"+"anuncio_foto"+numImagem+".jpg");
            if(fileImg.exists()){//se existe exclui
                fileImg.delete();
            }   
        }catch(Exception e){}
    }
    public String buscaCategorias() {
        int j;
        String res="";
        ArrayList<Categoria> categorias= new DALCategoria().getCategoria("");
        for (Categoria c : categorias) {
          res+=String.format("<option name='categoria' value=%s>%s</option>",""+c.getId(),c.getNome());
        }
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
            HttpSession session = request.getSession(false);
            DALHorario ctrH=new DALHorario();
            DALAnuncio ctr = new DALAnuncio();
            Gson gson = new Gson();
            switch (acao.toLowerCase()) 
            {
                case "consultar":
                    String filtro = request.getParameter("filtro");
                    if (!filtro.isEmpty()) filtro = "upper(cat_desc) like '%" + filtro.toUpperCase() + "%'";
                    //response.getWriter().print(buscaCategorias(filtro));
                    break;
                case "apagar":System.out.println("cod:"+cod);
                    if (!ctr.apagar(cod))
                       erro = "Erro ao apagar a categoria";
                    response.getWriter().print(erro);
                    break;
                case "alterar":
                    Anuncio c =null;
                    if(session!=null){
                        if(session.getAttribute("anuncio_cod")!=null){
                            c=ctr.getAnuncio(Integer.parseInt(session.getAttribute("anuncio_cod").toString()));
                        }
                    }
                    else
                        System.out.println("sessao =null");
                    
                    response.getWriter().print(gson.toJson(c)); // retorna todos os dados na forma de lista (,,,)
                    break;
                case "exibecategorias":
                    response.getWriter().print(buscaCategorias());
                    break;
                case "exibehorarios":
                    ArrayList<Horario> listaH=ctrH.getHorario("anu_id="+cod);
                    response.getWriter().print(gson.toJson(listaH));
                    break;
                case "confirmar": //novo e alteração
                    erro="ok";
                    String nomeFoto1="",nomeFoto2="",nomeFoto3="";

                    String titulo=request.getParameter("titulo");
                    float valor=Float.parseFloat(request.getParameter("valor"));
                    String desc = request.getParameter("descricao");
                    String contato=request.getParameter("contato");
                    int cat=Integer.parseInt(request.getParameter("categoria"));
                    Usuario usuario=new DALUsuario().getUsuario(Integer.parseInt(session.getAttribute("usu_id").toString()));
                    Categoria categoria=new DALCategoria().getCategoria(cat);

                    Part foto1 = request.getPart("foto1");
                    Part foto2 = request.getPart("foto2");
                    Part foto3 = request.getPart("foto3");
                    if(foto1.getSize()>0)
                        nomeFoto1="anuncio_foto1.jpg";
                    if(foto2.getSize()>0)
                        nomeFoto2="anuncio_foto2.jpg";
                    if(foto3.getSize()>0)
                        nomeFoto3="anuncio_foto3.jpg";

                    String horarios=request.getParameter("horarios");
                    String[] partes=horarios.split(",");
                    ArrayList<Horario> lista;
                    lista = new ArrayList();
                    if(partes.length>1){
                        for(int i=0;i<partes.length;i++){
                            if(Integer.parseInt(partes[i+4])==0)//apenas aqueles que ainda não estão no banco
                                lista.add(new Horario(Integer.parseInt(partes[i++]),cod,partes[i++],partes[i++],partes[i++]));
                            else
                                i+=4;
                        }
                    }
                
                    Anuncio anuncio =new Anuncio(cod,titulo,valor,desc,contato,
                            nomeFoto1,nomeFoto2,nomeFoto3,categoria,usuario);
                    anuncio.setHorarios(lista);
                    if (cod == 0) 
                    {   
                        if (!ctr.salvar(anuncio)){ 
                            erro = "Erro ao gravar o anúncio";
                        }
                        else{
                            for(int i=0;i<anuncio.getHorarios().size();i++){
                                anuncio.getHorarios().get(i).setAnuncio(anuncio.getId());
                                ctrH.salvar(anuncio.getHorarios().get(i));
                            }
                            gravarImagem(foto1,anuncio,1);
                            gravarImagem(foto2,anuncio,2);
                            gravarImagem(foto3,anuncio,3);
                            session.removeAttribute("anuncio_cod");
                        }
                    }
                    else{
                        Anuncio original=ctr.getAnuncio(cod);
                        if(anuncio.getFoto1().length()==0 && original.getFoto1().length()>0){
                           anuncio.setFoto1(original.getFoto1());
                        }
                        if(anuncio.getFoto2().length()==0 && original.getFoto2().length()>0){
                           anuncio.setFoto2(original.getFoto2());
                        }
                        if(anuncio.getFoto3().length()==0 && original.getFoto3().length()>0){
                           anuncio.setFoto3(original.getFoto3());
                        }

                        //Ver se há um horário que deve ser excluido
                        String horariosExc=request.getParameter("horariosExc");
                        String[] partesExc=horariosExc.split(",");
                        ArrayList<Horario> listaEx=new ArrayList();
                        if(partesExc.length>1){
                            for(int i=0;i<partesExc.length;i++){
                                System.out.println("vou criar um horario para apagar");
                                listaEx.add(new Horario(Integer.parseInt(partesExc[i++]),cod,partesExc[i++],partesExc[i++],partesExc[i++]));
                            }
                        }
                        
                        // Ver se há uma imagem a ser excluida
                        String imagensExc=request.getParameter("imagensExc");
                        String[] imgExc=imagensExc.split(",");
                        System.out.println(imgExc.length);
                        for(int i=0;i<imgExc.length;i++){
                            if(imgExc[i].equalsIgnoreCase("anuncio_foto1.jpg")){
                                if(!(foto1.getSize()>0))
                                    anuncio.setFoto1("");
                            }else{
                                if(imgExc[i].equalsIgnoreCase("anuncio_foto2.jpg")){
                                    if(!(foto2.getSize()>0))
                                        anuncio.setFoto2("");
                                }
                                else{
                                    if(imgExc[i].equalsIgnoreCase("anuncio_foto3.jpg")){
                                        if(!(foto3.getSize()>0))
                                        anuncio.setFoto3("");
                                    }
                                }
                            }
                            
                        }
                        if (!ctr.alterar(anuncio))
                        {
                            erro = "Erro ao alterar o anúncio";
                        }
                        else{
                            for(int i=0;i<listaEx.size();i++){
                                ctrH.apagar(listaEx.get(i).getId());
                            }
                            for(int i=0;i<lista.size();i++){
                                ctrH.salvar(lista.get(i));
                            }
                            gravarImagem(foto1,anuncio,1);
                            gravarImagem(foto2,anuncio,2);
                            gravarImagem(foto3,anuncio,3);
                            
                            if(foto1.getSize()==0 && anuncio.getFoto1().length()==0)
                                excluirImagem(anuncio,1);
                            if(foto2.getSize()==0 && anuncio.getFoto2().length()==0)
                                excluirImagem(anuncio,2);
                            if(foto3.getSize()==0 && anuncio.getFoto3().length()==0)
                                excluirImagem(anuncio,3);
                        }
                    }
                    response.getWriter().print(erro);

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
