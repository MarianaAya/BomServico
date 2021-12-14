
package bd.dal;

import bd.entidades.Usuario;
import bd.util.Conexao;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;


public class DALUsuario {
    public boolean salvar (Usuario u)
    {
        String sql="insert into usuario (usu_nome,usu_dtNasc,usu_cpf,usu_email,usu_senha,usu_nivel) values ('$1','$2','$3','$4','$5','$6')";
        sql=sql.replace("$1",u.getNome());
        sql=sql.replace("$2",u.getData().toString());
        sql=sql.replace("$3",u.getCpf());
        sql=sql.replace("$4",u.getEmail());
        sql=sql.replace("$5",u.getSenha());
        sql=sql.replace("$6",""+u.getNivel());
        Conexao con=new Conexao();
        boolean flag=con.manipular(sql);
        con.fecharConexao();
        return flag;                              
    }
    public boolean alterar (Usuario u)
    {
        String sql="update usuario set usu_nome='$1',usu_dtnasc='$2',usu_cpf='$3',usu_email='$4',usu_senha='$5',usu_nivel='$6' "+
                " where usu_id="+u.getId();
        sql=sql.replace("$1",u.getNome());
        sql=sql.replace("$2",u.getData().toString());
        sql=sql.replace("$3",u.getCpf());
        sql=sql.replace("$4",u.getEmail());
        sql=sql.replace("$5",u.getSenha());
        sql=sql.replace("$6",""+u.getNivel());

        System.out.println("SQL Alterar="+sql);
        Conexao con=new Conexao();
        boolean flag=con.manipular(sql);
        con.fecharConexao();
        return flag;                       
    }
    public boolean apagar(int id)
    {
        Conexao con=new Conexao();
        boolean flag=con.manipular("delete from usuario where usu_id="+id);
        con.fecharConexao();
        return flag;                      
    }
    public ArrayList <Usuario> getUsario(String filtro)
    {   ArrayList <Usuario> lista=new ArrayList();
        String sql="select * from usuario";
        if (!filtro.isEmpty())
            sql+=" where "+filtro;
        sql+=" order by usu_nome";
        Conexao con=new Conexao();
        ResultSet rs = con.consultar(sql);
        try
        {
          while(rs.next())
             lista.add(
          new Usuario(rs.getInt("usu_id"),
                      rs.getString("usu_nome"),
                      LocalDate.parse(rs.getString("usu_dtnasc")),
                      rs.getString("usu_cpf"),
                      rs.getString("usu_email"),
                      rs.getString("usu_senha"),
                      rs.getString("usu_nivel").charAt(0))
             
           );
        }
        catch(Exception e){System.out.println(e);}
        con.fecharConexao();
        return lista;
    }
    public Usuario getUsuario(int id){
        Usuario usu=null;
        Conexao con=new Conexao();
        String sql="select * from usuario where usu_id="+id;
        ResultSet rs = con.consultar(sql);
        try
        {
          if(rs.next())
             usu=new Usuario(rs.getInt("usu_id"),
                      rs.getString("usu_nome"),
                      LocalDate.parse(rs.getString("usu_dtnasc")),
                      rs.getString("usu_cpf"),
                      rs.getString("usu_email"),
                      rs.getString("usu_senha"),
                      rs.getString("usu_nivel").charAt(0));
        
        }catch(Exception e){System.out.println(e);}
        con.fecharConexao();
        return usu;
    }
    public Usuario getUsuario(String email,String senha){
        Usuario usu=null;
        Conexao con=new Conexao();
        String sql="select * from usuario where usu_email='$1' and usu_senha='$2'";
        sql=sql.replace("$1",email);
        sql=sql.replace("$2",senha);
        ResultSet rs = con.consultar(sql);
        try
        {
          if(rs.next())
             usu=new Usuario(rs.getInt("usu_id"),
                      rs.getString("usu_nome"),
                      LocalDate.parse(rs.getString("usu_dtnasc")),
                      rs.getString("usu_cpf"),
                      rs.getString("usu_email"),
                      rs.getString("usu_senha"),
                      rs.getString("usu_nivel").charAt(0));
        
        }catch(Exception e){System.out.println(e);}
        con.fecharConexao();
        return usu;
    }
    public Usuario procurarPorCPF(String cpf){
        Usuario usu=null;
        Conexao con=new Conexao();
        String sql="select * from usuario where usu_cpf like '$1'";
        sql=sql.replace("$1",cpf);

        ResultSet rs = con.consultar(sql);
        try
        {
          if(rs.next())
             usu=new Usuario(rs.getInt("usu_id"),
                      rs.getString("usu_nome"),
                      LocalDate.parse(rs.getString("usu_dtnasc")),
                      rs.getString("usu_cpf"),
                      rs.getString("usu_email"),
                      rs.getString("usu_senha"),
                      rs.getString("usu_nivel").charAt(0));
        
        }catch(Exception e){System.out.println(e);}
        con.fecharConexao();
        return usu;
    }
    public Usuario procurarPorEmail(String email){
        Usuario usu=null;
        Conexao con=new Conexao();
        String sql="select * from usuario where usu_email like '$1'";
        sql=sql.replace("$1",email);

        ResultSet rs = con.consultar(sql);
        try
        {
          if(rs.next())
             usu=new Usuario(rs.getInt("usu_id"),
                      rs.getString("usu_nome"),
                      LocalDate.parse(rs.getString("usu_dtnasc")),
                      rs.getString("usu_cpf"),
                      rs.getString("usu_email"),
                      rs.getString("usu_senha"),
                      rs.getString("usu_nivel").charAt(0));
        
        }catch(Exception e){System.out.println(e);}
        con.fecharConexao();
        return usu;
    }
}
