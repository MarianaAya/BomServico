
package bd.entidades;

import java.util.ArrayList;
import java.util.List;

public class Anuncio {
    private int id;
    private String titulo;
    private float valor;
    private String descricao;
    private String contato;
    private String foto1;
    private String foto2;
    private String foto3;
    private Categoria categoria;
    private Usuario usuario;
    private List<Horario> horarios;
    public Anuncio(int id, String titulo, float valor, String descricao, String contato, String foto1, String foto2, String foto3, Categoria categoria, Usuario usuario) {
        this.id = id;
        this.titulo = titulo;
        this.valor = valor;
        this.descricao = descricao;
        this.contato = contato;
        this.foto1 = foto1;
        this.foto2 = foto2;
        this.foto3 = foto3;
        this.categoria = categoria;
        this.usuario = usuario;
        this.horarios=new ArrayList();
    }
    
    public Anuncio(){
        this(0,"",0,"","","","","",new Categoria(),new Usuario());
    }

 
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }



    public String getFoto1() {
        return foto1;
    }

    public void setFoto1(String foto1) {
        this.foto1 = foto1;
    }

    public String getFoto2() {
        return foto2;
    }

    public void setFoto2(String foto2) {
        this.foto2 = foto2;
    }

    public String getFoto3() {
        return foto3;
    }

    public void setFoto3(String foto3) {
        this.foto3 = foto3;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Horario> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<Horario> horarios) {
        this.horarios = horarios;
    }

    @Override
    public String toString() {
        return id+","+titulo+","+valor+","+descricao+","+contato+","+foto1+","+foto2+","+foto3+
                ","+categoria+","+usuario+","+ horarios;
    }
    
}
