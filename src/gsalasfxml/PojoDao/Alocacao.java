package gsalasfxml.PojoDao;

import java.sql.Date;
import java.sql.Time;

public class Alocacao {
    private String descricao;
    private String data;
    private String duracao;
    private String hora;
    private String tempo;
    private int idUser;
    private String idSala;

    public Alocacao(String des, String dat, String h, String tmp, String dur, int idUser, String idS){
        super();
        this.descricao = des;
        this.data = dat;
        this.duracao = dur;
        this.hora = h;
        this.tempo = tmp;
        this.idUser = idUser;
        this.idSala = idS;
    }

    public void setIdSala(String idSala) {
        this.idSala = idSala;
    }

    public String getIdSala() {
        return idSala;
    }

    public int getIdUser() {
        return idUser;
    }
    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
    
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getTempo() {
        return tempo;
    }

    public void setTempo(String Tempo) {
        this.tempo = tempo;
    }
    
    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }
    
}
