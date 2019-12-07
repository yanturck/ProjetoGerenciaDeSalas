package gsalasfxml.PojoDao;

public class Alocacao {
    private String descricao;
    private int sala;
    private String data;
    private String hora;
    private String duracao;

    public Alocacao(String des, int s, String dat, String h, String dur){
        super();
        this.descricao = des;
        this.sala = s;
        this.data = dat;
        this.hora = h;
        this.duracao = dur;
    }
    
    public int getSala() {
        return sala;
    }
    public void setSala(int sala) {
        this.sala = sala;
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

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }
}
