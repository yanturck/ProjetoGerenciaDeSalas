package gsalasfxml.combox;

/**
 *
 * @author Yan Rocha
 */
public class Cadastrar {
    private String objt;
    
    public Cadastrar(String objt){
        super();
        this.objt = objt;
    }
    
    public String getObjt() {
        return objt;
    }

    public void setObjt(String objt) {
        this.objt = objt;
    }

    @Override
    public String toString() {
        return getObjt();
    }
    
}
