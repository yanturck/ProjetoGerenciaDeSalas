package gsalasfxml.PojoDao;

/**
 *
 * @author Yan Rocha
 */
public class SalasAlocacoes {
    private String idSala;
    private int idAlocacao;
    
    public SalasAlocacoes(String idSala, int idAlocacao){
        super();
        this.idSala = idSala;
        this.idAlocacao = idAlocacao;
    }

    public String getIdSala() {
        return idSala;
    }

    public void setIdSala(String idSala) {
        this.idSala = idSala;
    }

    public int getIdAlocacao() {
        return idAlocacao;
    }

    public void setIdAlocacao(int idAlocacao) {
        this.idAlocacao = idAlocacao;
    }
    
}
