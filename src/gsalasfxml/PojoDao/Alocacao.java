package gsalasfxml.PojoDao;

public class Alocacao {
    private String descricao;
    private String data;
    private String hora;
    private String tempo;
    private int idUser;

    public Alocacao(String des, int s, String dat, String h, String dur, int idUser){
        super();
        this.descricao = des;
        this.data = dat;
        this.hora = h;
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
