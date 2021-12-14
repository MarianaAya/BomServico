
package bd.entidades;

import java.time.LocalDate;

public class Usuario {
    private int id;
    private String nome; 
    private LocalDate data;
    private String cpf;
    private String email;
    private String senha;
    private char nivel;

    public Usuario(int id, String nome, LocalDate data, String cpf, String email, String senha, char nivel) {
        this.id = id;
        this.nome = nome;
        this.data = data;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        this.nivel = nivel;
    }

    public Usuario() {
        this(0,"",null,"","","",'*');
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

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public char getNivel() {
        return nivel;
    }

    public void setNivel(char nivel) {
        this.nivel = nivel;
    }

    @Override
    public String toString() {
        return id + "," + nome + "," + data + "," + cpf + "," + email + "," + senha + "," + nivel;
    }
    
    
    
}
