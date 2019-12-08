package gsalasfxml.conexao_banco;

/**
 *
 * @author Yan Rocha
 */
public class CriacaoDB {
    public static void main(String[] args) {
                
        ConexaoSQLite conexaoSQLite = new ConexaoSQLite();
        CriacaoTB criacaoTB = new CriacaoTB(conexaoSQLite);
        criacaoTB.criarTabelas();
    }
}
