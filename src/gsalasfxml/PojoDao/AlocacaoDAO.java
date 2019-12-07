package gsalasfxml.PojoDao;

import gsalasfxml.conexao_banco.ConexaoSQLite;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class AlocacaoDAO {
    private final ConexaoSQLite conexaoSQLite;
    
    public AlocacaoDAO(ConexaoSQLite aConexaoSQLite){
        this.conexaoSQLite = aConexaoSQLite;
    }
    
    public void criarTabelaAlocacao(){
        String sql = "CREATE TABLE IF NOT EXISTS ALOCACAO"
                + "("
                + "idALOCACAO int auto_increment primary key,"
                + "DATAaloc date not null,"
                + "HORAaloc time not null,"
                + "TEMPOaloc date not null,"
                + "idUser int not null,"
                + "foreign key (idUser) references USUARIO(idUSER)"
                +")";
    
        boolean conectou = this.conexaoSQLite.conectar(); // mensagem de erro na criacao da tabela
        Statement stmt = this.conexaoSQLite.criarStatement();
        if(conectou){
            this.conexaoSQLite.desconectar();
        }
    }
}
