package gsalasfxml.PojoDao;

public class Alocacao {
    private String descricao;
    private String data;
    private String hora;
    private String tempo;

    public Alocacao(String des, int s, String dat, String h, String dur){
        super();
        this.descricao = des;
        this.data = dat;
        this.hora = h;
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
}
