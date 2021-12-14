package restservices;

import bd.dal.DALAnuncio;
import bd.entidades.Anuncio;
import com.google.gson.Gson;
import java.util.ArrayList;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("services")
public class AnuncioResource {

    @Context
    private UriInfo context;

    public AnuncioResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAnuncios() {
        DALAnuncio ctr=new DALAnuncio();
        ArrayList<Anuncio> anuncios=ctr.getAnuncio("");
        String json;
        Gson gson=new Gson();
        json=gson.toJson(anuncios);
        return json;  
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public String getPiada(String id) {
        DALAnuncio ctr=new DALAnuncio();
        Anuncio anuncio=new Anuncio();
        System.out.println("id="+id);
        anuncio=ctr.getAnuncio(Integer.parseInt(id));
        Gson gson=new Gson();
        String json=gson.toJson(anuncio);
        return json;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/query/")
    public String getParamText(@QueryParam("titulo") String titulo,@QueryParam("cat_cod") String cat_cod) {
        // buscar a piada com o id
        //simulação:
        String filtro="";
        if (titulo.length()>0 && cat_cod.length()>0) 
            filtro = "upper(anu_titulo) like '%" + titulo.toUpperCase() + "%'"+" and cat_id="+Integer.parseInt(cat_cod);
        else{
            if(titulo.length()>0)
                filtro = "upper(anu_titulo) like '%" + titulo.toUpperCase() + "%'";
            if(cat_cod.length()>0)
                filtro =  "cat_id="+Integer.parseInt(cat_cod);
        }
        DALAnuncio ctr=new DALAnuncio();
        ArrayList<Anuncio> anuncios=ctr.getAnuncio(filtro);
        Gson gson=new Gson();
        String json=gson.toJson(anuncios);
        return json;
    }
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putXml(String content) {
    }
}
