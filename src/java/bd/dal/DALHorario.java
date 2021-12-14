
package bd.dal;


import bd.entidades.Horario;
import bd.util.Conexao;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;


public class DALHorario {
    public boolean salvar (Horario h)
    {
        String sql="insert into horario (anu_id,hor_horaini,hor_horafim,hor_dia) values ($1,'$2','$3','$4')";
        sql=sql.replace("$1",""+h.getAnuncio());
        sql=sql.replace("$2",h.getHoraInicio().toString());
        sql=sql.replace("$3",h.getHoraFim().toString());
        sql=sql.replace("$4",h.getDiaSem());
        Conexao con=new Conexao();
        boolean flag=con.manipular(sql);
        con.fecharConexao();
        return flag;                              
    }
    public boolean alterar (Horario h)
    {
         String sql="update horario hor_horaini='$1',hor_horafim='$2',hor_dia='$3' where anu_id=$4 and hor_id=$5";
        sql=sql.replace("$1",h.getHoraInicio().toString());
        sql=sql.replace("$2",h.getHoraFim().toString());
        sql=sql.replace("$3",h.getDiaSem());
        sql=sql.replace("$4",""+h.getAnuncio());
        sql=sql.replace("$3",""+h.getId());

        Conexao con=new Conexao();
        boolean flag=con.manipular(sql);
        con.fecharConexao();
        return flag;                       
    }
    public boolean apagar(int id)
    {
        Conexao con=new Conexao();
        boolean flag=con.manipular("delete from horario where hor_id="+id);
        con.fecharConexao();
        return flag;                      
    }
    public ArrayList <Horario> getHorario(String filtro)
    {   ArrayList <Horario> lista=new ArrayList();
        String sql="select * from horario";
        if (!filtro.isEmpty())
            sql+=" where "+filtro;
        sql+=" order by hor_id";
        Conexao con=new Conexao();
        ResultSet rs = con.consultar(sql);
        try
        {
          while(rs.next())
             lista.add(
          new Horario(rs.getInt("hor_id"),
                      rs.getInt("anu_id"),
                      rs.getString("hor_horaini"),
                      rs.getString("hor_horafim"),
                      rs.getString("hor_dia")

             
           ));
        }
        catch(Exception e){System.out.println(e);}
        con.fecharConexao();
        return lista;
    }
    public ArrayList <Horario> getHorarioData(String filtro)
    {   ArrayList <Horario> lista=new ArrayList();
        String sql="select * from horario";
        if (!filtro.isEmpty())
            sql+=" where "+filtro;
        sql+=" order by hor_horaini";
        Conexao con=new Conexao();
        ResultSet rs = con.consultar(sql);
        try
        {
          while(rs.next())
             lista.add(
          new Horario(rs.getInt("hor_id"),
                      rs.getInt("anu_id"),
                      rs.getString("hor_horaini"),
                      rs.getString("hor_horafim"),
                      rs.getString("hor_dia")

             
           ));
        }
        catch(Exception e){System.out.println(e);}
        con.fecharConexao();
        return lista;
    }
}
