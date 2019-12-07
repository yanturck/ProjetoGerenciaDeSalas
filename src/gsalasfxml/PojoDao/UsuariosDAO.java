package gsalasfxml.PojoDao;

import gsalasfxml.conexao_banco.ConexaoSQLite;
import java.sql.Statement;

public class UsuariosDAO {
    private final ConexaoSQLite conexaoSQLite;
    
    public UsuariosDAO(ConexaoSQLite aConexaoSQLite){
        this.conexaoSQLite = aConexaoSQLite;
    }
    
    public void criarTabelaUsuario(){
        String sql = "CREATE TABLE IF NOT EXISTS USUARIO"
                + "("
                + "idUSER int primary key,"
                + "NOME varchar(45) not null,"
                + "TIPOuser varchar(45) not null,"
                + "CURSO varchar(30),"
                + "TELEFONE char(11),"
                +")";
    
        boolean conectou = this.conexaoSQLite.conectar(); // mensagem de erro na criacao da tabela
        Statement stmt = this.conexaoSQLite.criarStatement();
        if(conectou){
            this.conexaoSQLite.desconectar();
        }
    }
}
