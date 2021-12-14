
package bd.entidades;

public class Categoria {
    private int id;
    private String nome;

    public Categoria() {
        this(0,"");
    }
    
    public Categoria(int id, String categoria) {
        this.id = id;
        this.nome = categoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String toString() {
        return id + "," + nome;
    }
    
}
