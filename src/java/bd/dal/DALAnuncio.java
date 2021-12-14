
package bd.dal;

import bd.entidades.Anuncio;
import bd.entidades.Horario;
import bd.util.Conexao;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;


public class DALAnuncio {
    public boolean salvar (Anuncio a)
    {
        System.out.println("entrei na DALAnuncio salvar");
        String sql="insert into anuncio (anu_titulo,anu_valor,anu_descricao,anu_contato,anu_foto1,anu_foto2,anu_foto3,cat_id,usu_id) "+
                " values ('$1',$2,'$3','$4','$5','$6','$7',$8,$9)";
        System.out.println("cat_id="+a.getCategoria()+"  usu_is="+a.getUsuario().getId());
        sql=sql.replace("$1",a.getTitulo());
        System.out.println("1--"+sql);
        sql=sql.replace("$2",""+a.getValor());
        System.out.println("1.2--"+sql);
        sql=sql.replace("$3",a.getDescricao());
        System.out.println("2--"+sql);
        sql=sql.replace("$4",a.getContato());
        System.out.println("SQL1="+sql);
        sql=sql.replace("$5",a.getFoto1());
        sql=sql.replace("$6",a.getFoto2());
        sql=sql.replace("$7",a.getFoto3());
        System.out.println("SQL1="+sql);
        sql=sql.replace("$8",""+a.getCategoria().getId());
        sql=sql.replace("$9",""+a.getUsuario().getId());
        System.out.println("cat_id="+a.getCategoria()+"  usu_is="+a.getUsuario().getId());
        System.out.println("SQL"+sql);
        Conexao con=new Conexao();
        boolean flag=con.manipular(sql);
        if(flag){
            a.setId(con.getMaxPK("anuncio", "anu_id"));
        }
        con.fecharConexao();
        return flag;                              
    }
    public boolean alterar (Anuncio a)
    {
         String sql="update anuncio set anu_titulo='$1',anu_valor=$2,anu_descricao='$3',anu_contato='$4',cat_id=$5, "+
                 "anu_foto1='$6',anu_foto2='$7',anu_foto3='$8' where anu_id="+a.getId();
        sql=sql.replace("$1",a.getTitulo());
        sql=sql.replace("$2",""+a.getValor());
        sql=sql.replace("$3",a.getDescricao());
        sql=sql.replace("$4",a.getContato());
        sql=sql.replace("$5",""+a.getCategoria().getId());
        sql=sql.replace("$6",a.getFoto1());
        sql=sql.replace("$7",a.getFoto2());
        sql=sql.replace("$8",a.getFoto3());
        System.out.println("SQL: "+sql);
        Conexao con=new Conexao();
        boolean flag=con.manipular(sql);
        con.fecharConexao();
        return flag;                       
    }
    public boolean apagar(int id)
    {
        Conexao con=new Conexao();
        boolean flag=con.manipular("delete from horario where anu_id="+id);
        flag=con.manipular("delete from anuncio where anu_id="+id);
        con.fecharConexao();
        return flag;                      
    }
    public ArrayList <Anuncio> getAnuncio(String filtro)
    {   ArrayList <Anuncio> lista=new ArrayList();
        String sql="select * from anuncio";
        if (!filtro.isEmpty())
            sql+=" where "+filtro;
        sql+=" order by anu_titulo";
        Conexao con=new Conexao();
        ResultSet rs = con.consultar(sql);
        try
        {
          while(rs.next())
             lista.add(
          new Anuncio(rs.getInt("anu_id"),
                      rs.getString("anu_titulo"),
                      rs.getFloat("anu_valor"),
                      rs.getString("anu_descricao"),
                      rs.getString("anu_contato"),
                      rs.getString("anu_foto1"),
                      rs.getString("anu_foto2"),
                      rs.getString("anu_foto3"),
                      new DALCategoria().getCategoria(rs.getInt("cat_id")),
                      new DALUsuario().getUsuario(rs.getInt("usu_id"))

             
           ));
        }
        catch(Exception e){System.out.println(e);}
        con.fecharConexao();
        return lista;
    }
    public Anuncio getAnuncio(int id){
        Anuncio a=null;
        Conexao con=new Conexao();
        String sql="select * from anuncio where anu_id="+id;
        ResultSet rs = con.consultar(sql);
        try
        {
          if(rs.next()){
             a=new Anuncio(rs.getInt("anu_id"),
                      rs.getString("anu_titulo"),
                      rs.getFloat("anu_valor"),
                      rs.getString("anu_descricao"),
                      rs.getString("anu_contato"),
                      rs.getString("anu_foto1"),
                      rs.getString("anu_foto2"),
                      rs.getString("anu_foto3"),
                      new DALCategoria().getCategoria(rs.getInt("cat_id")),
                      new DALUsuario().getUsuario(rs.getInt("usu_id")));
                        a.setHorarios(new DALHorario().getHorario("anu_id="+a.getId()));
          }
        }catch(Exception e){System.out.println(e);}
        con.fecharConexao();
        return a;
    }
    public Anuncio getAnuncioPrestador(int id){
        Anuncio a=null;
        System.out.println("entrei para pegar i anuncio");
        Conexao con=new Conexao();
        String sql="select * from anuncio where usu_id="+id;
        ResultSet rs = con.consultar(sql);
        try
        {
          if(rs.next()){
              System.out.println("entrei porque eu achei");
            a=new Anuncio(rs.getInt("anu_id"),
                      rs.getString("anu_titulo"),
                      rs.getFloat("anu_valor"),
                      rs.getString("anu_descricao"),
                      rs.getString("anu_contato"),
                      rs.getString("anu_foto1"),
                      rs.getString("anu_foto2"),
                      rs.getString("anu_foto3"),
                      new DALCategoria().getCategoria(rs.getInt("cat_id")),
                      new DALUsuario().getUsuario(rs.getInt("usu_id")));
          }else{
              System.out.println("entrei no else");
              a=null;
          }
        
        }catch(Exception e){System.out.println(e);}
        con.fecharConexao();
        return a;
    }
    public Anuncio getAnuncioOrd(int id){
        Anuncio a=null;
        Conexao con=new Conexao();
        String sql="select * from anuncio where anu_id="+id;
        ResultSet rs = con.consultar(sql);
        try
        {
          if(rs.next())
             a=new Anuncio(rs.getInt("anu_id"),
                      rs.getString("anu_titulo"),
                      rs.getFloat("anu_valor"),
                      rs.getString("anu_descricao"),
                      rs.getString("anu_contato"),
                      rs.getString("anu_foto1"),
                      rs.getString("anu_foto2"),
                      rs.getString("anu_foto3"),
                      new DALCategoria().getCategoria(rs.getInt("cat_id")),
                      new DALUsuario().getUsuario(rs.getInt("usu_id")));
            a.setHorarios(new DALHorario().getHorarioData("anu_id="+a.getId()));
        }catch(Exception e){System.out.println(e);}
        con.fecharConexao();
        return a;
    }
}
