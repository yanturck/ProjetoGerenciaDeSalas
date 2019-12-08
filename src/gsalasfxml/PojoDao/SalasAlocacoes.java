package gsalasfxml.PojoDao;

/**
 *
 * @author Yan Rocha
 */
public class SalasAlocacoes {
    private int idSala;
    private int idAlocacao;
    
    public SalasAlocacoes(int idSala, int idAlocacao){
        super();
        this.idSala = idSala;
        this.idAlocacao = idAlocacao;
    }

    public int getIdSala() {
        return idSala;
    }

    public void setIdSala(int idSala) {
        this.idSala = idSala;
    }

    public int getIdAlocacao() {
        return idAlocacao;
    }

    public void setIdAlocacao(int idAlocacao) {
        this.idAlocacao = idAlocacao;
    }
    
}
