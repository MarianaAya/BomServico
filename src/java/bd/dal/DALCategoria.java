
package bd.dal;

import bd.entidades.Categoria;
import bd.util.Conexao;
import java.sql.ResultSet;
import java.util.ArrayList;


public class DALCategoria {
    public boolean salvar (Categoria c)
    {
        String sql="insert into categoria (cat_desc) values ('$1')";
        sql=sql.replace("$1",c.getNome());
        Conexao con=new Conexao();
        boolean flag=con.manipular(sql);
        con.fecharConexao();
        return flag;                              
    }
    public boolean alterar (Categoria c)
    {
        String sql="update categoria set cat_desc='$1'"+
                " where cat_id="+c.getId();
        sql=sql.replace("$1",c.getNome());

        Conexao con=new Conexao();
        boolean flag=con.manipular(sql);
        con.fecharConexao();
        return flag;                       
    }
    public boolean apagar(int id)
    {
        Conexao con=new Conexao();
        boolean flag=con.manipular("delete from categoria where cat_id="+id);
        con.fecharConexao();
        return flag;                      
    }
    public ArrayList <Categoria> getCategoria(String filtro)
    {   ArrayList <Categoria> lista=new ArrayList();
        String sql="select * from categoria";
        if (!filtro.isEmpty())
            sql+=" where "+filtro;
        sql+=" order by cat_id";
        Conexao con=new Conexao();
        ResultSet rs = con.consultar(sql);
        try
        {
          while(rs.next())
             lista.add(
          new Categoria(rs.getInt("cat_id"),rs.getString("cat_desc")));
        }
        catch(Exception e){System.out.println(e);}
        con.fecharConexao();
        return lista;
    }
    public Categoria getCategoria(int id){
        Categoria cat=null;
        Conexao con=new Conexao();
        String sql="select * from categoria where cat_id="+id;
        ResultSet rs = con.consultar(sql);
        try
        {
          if(rs.next())
             cat=new Categoria(rs.getInt("cat_id"),rs.getString("cat_desc"));
        
        }catch(Exception e){System.out.println(e);}
        con.fecharConexao();
        return cat;
    }
}
