package gsalasfxml.PojoDao;

public class Sala {
    private int idSala;
    private String tipoSala;
    private String asa;
    
    
    
    public Sala (int idSala, String tipoSala, String asa) {    
        super();
        this.idSala = idSala;
        this.tipoSala = tipoSala;
        this.asa = asa;
    }

    public int getIdSala() {
        return idSala;
    }
    public void setIdSala(int idSala) {
        this.idSala = idSala;
    }
    
    public String getTipoSala() {
        return tipoSala;
    }
    public void setTipoSala(String tipoSala) {
        this.tipoSala = tipoSala;
    }

    
    public String getAsa() {
        return asa;
    }
    public void setAsa(String asa) {
        this.asa = asa;
    }
}
    
