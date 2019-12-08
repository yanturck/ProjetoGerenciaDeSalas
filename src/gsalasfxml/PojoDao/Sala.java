package gsalasfxml.PojoDao;

public class Sala {
    private String idSala;
    private String andar;
    private String tipoSala;
    
    
    
    public Sala (String idSala, String andar, String tipoSala) {    
        super();
        this.idSala = idSala;
        this.andar = andar;
        this.tipoSala = tipoSala;
    }

    public String getIdSala() {
        return idSala;
    }
    public void setIdSala(String idSala) {
        this.idSala = idSala;
    }

    public String getAndar() {
        return andar;
    }
    public void setAndar(String andar) {
        this.andar = andar;
    }
    
    public String getTipoSala() {
        return tipoSala;
    }
    public void setTipoSala(String tipoSala) {
        this.tipoSala = tipoSala;
    }
}
    
