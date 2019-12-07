package gsalasfxml.conexao_banco;

/**
 *
 * @author Yan Rocha
 */
public class CriaçãoDB {
    public static void main(String[] args) {
                
        ConexaoSQLite conexaoSQLite = new ConexaoSQLite();
        conexaoSQLite.conectar();
        conexaoSQLite.desconectar();
        
    }
}
