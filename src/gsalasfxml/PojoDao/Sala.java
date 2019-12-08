package gsalasfxml.PojoDao;

public class Sala {
    private int idSala;
    private String andar;
    private String tipoSala;
    private String asa;
    
    
    
    public Sala (int idSala, String andar, String asa, String tipoSala) {    
        super();
        this.idSala = idSala;
        this.andar = andar;
        this.tipoSala = tipoSala;
        this.asa = asa;
    }

    public int getIdSala() {
        return idSala;
    }
    public void setIdSala(int idSala) {
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

    public String getAsa() {
        return asa;
    }
    public void setAsa(String asa) {
        this.asa = asa;
    }
}
    
