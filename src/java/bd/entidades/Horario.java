
package bd.entidades;

import java.time.LocalDate;

public class Horario {
    private int id;
    private int anuncio;
    private String horaInicio;
    private String horaFim;
    private String diaSem;

    public Horario(int id,int anuncio,String horaInicio, String horaFim, String diaSem) {
        this.id = id;
        this.anuncio=anuncio;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.diaSem = diaSem;
    }
    
    public Horario(){
        this(0,0,null,null,"");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAnuncio() {
        return anuncio;
    }

    public void setAnuncio(int anuncio) {
        this.anuncio = anuncio;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(String horaFim) {
        this.horaFim = horaFim;
    }
    
    

    public String getDiaSem() {
        return diaSem;
    }

    public void setDiaSem(String diaSem) {
        this.diaSem = diaSem;
    }

    @Override
    public String toString() {
        return id + "," + anuncio + "," + horaInicio + "," + horaFim + "," + diaSem;
    }
    
    
    
    
}
