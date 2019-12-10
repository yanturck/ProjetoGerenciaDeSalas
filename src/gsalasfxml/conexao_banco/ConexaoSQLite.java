package gsalasfxml.conexao_banco;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Danniel Cristie ..
 */
public class ConexaoSQLite {

    private Connection conexao;

    /**
     * Conecta a um banco de dados (cria o banco se ele não existir)
     *
     * @return
     */
    public boolean conectar() {

        try {

            String url = "jdbc:sqlite:banco_de_dados/GerenciaSalasqlite.db";
            this.conexao = DriverManager.getConnection(url);

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }

        System.out.println("conectou!!!");
        return true;
    }

    public boolean desconectar() {

        try {
            if (this.conexao.isClosed() == false) {
                this.conexao.close();
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
        System.out.println("desconectou!!!");
        return true;
    }
    
    /**
     * Criar os Statements para nossas sqls serem execultadas
     * @return 
     */
    
    public Statement criarStatement(){
    try{
        return this.conexao.createStatement();
       }catch(SQLException e){
        return null;
       }
    }
    public PreparedStatement criarPreparedStatement(String sql){
    try{
        return this.conexao.prepareStatement(sql);
       }catch(SQLException e){
        return null;
       }
    }
    public Connection getConexao(){
        return conexao;
    }
}