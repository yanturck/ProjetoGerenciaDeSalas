package gsalasfxml.PojoDao;

import java.sql.Time;
import java.sql.Date;

public class Alocacao {
    private String descricao;
    private Date data;
    private Date duracao;
    private Time hora;
    private Time tempo;
    private int idUser;

    public Alocacao(String des, Date dat, Time h, Time tmp, Date dur, int idUser){
        super();
        this.descricao = des;
        this.data = dat;
        this.duracao = dur;
        this.hora = h;
        this.tempo = tmp;
        this.idUser = idUser;
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

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public Time getTempo() {
        return tempo;
    }

    public void setTempo(Time Tempo) {
        this.tempo = tempo;
    }
    
    public Date getDuracao() {
        return duracao;
    }

    public void setDuracao(Date duracao) {
        this.duracao = duracao;
    }
    
}
