package gsalasfxml.PojoDao;

/**
 *
 * @author Yan Rocha
 */
public class FrontAlocacao {
    private String data;
    private String descricao;
    private String dura;
    
    public FrontAlocacao(String data, String descricao, String dura){
        super();
        this.data = data;
        this.descricao = descricao;
        this.dura = dura;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDura() {
        return dura;
    }

    public void setDura(String dura) {
        this.dura = dura;
    }
}
