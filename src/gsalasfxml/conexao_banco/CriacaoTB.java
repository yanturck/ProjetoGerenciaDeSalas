package gsalasfxml.conexao_banco;

import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Yan Rocha
 */
public class CriacaoTB {
    private final ConexaoSQLite conexaoSQLite;
    
    public CriacaoTB(ConexaoSQLite aConexaoSQLite){
        this.conexaoSQLite = aConexaoSQLite;
    }
    
    public void criarTabelas(){
        String sql1 = "CREATE TABLE IF NOT EXISTS SALA"
                + "("
                + "idSala CHAR(4) PRIMARY KEY UNIQUE NOT NULL,"
                + "ANDAR INTEGER NOT NULL,"
                + "TIPOsala VARCHAR(15) NOT NULL"
                + ");";
        
        String sql2 = "CREATE TABLE IF NOT EXISTS USUARIO"
                + "("
                + "idUSER INTEGER PRIMARY KEY UNIQUE NOT NULL,"
                + "NOME VARCHAR(45) NOT NULL,"
                + "TIPOuser VARCHAR(45) NOT NULL,"
                + "CURSO VARCHAR(45),"
                + "TELEFONE CHAR(11)"
                +");";
        
        String sql3 = "CREATE TABLE IF NOT EXISTS ALOCACAO"
                + "("
                + "idALOCACAO INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "DESCRICAO VARCHAR(50) NOT NULL,"
                + "DATAaloc DATE NOT NULL,"
                + "HORAaloc TIME NOT NULL,"
                + "TEMPOaloc TIME NOT NULL,"
                + "DURACAO DATE NOT NULL,"
                + "idUser INTEGER NOT NULL,"
                + "foreign key (idUser) references USUARIO(idUSER)"
                + ");";
    
        String sql4 = "CREATE TABLE IF NOT EXISTS SALA_ALOCACAO"
                + "("
                + "idSALA INTEGER NOT NULL,"
                + "idALOCACAO INTEGER NOT NULL,"
                + "primary key(idSALA, idALOCACAO),"
                + "foreign key(idSALA) references SALA(idSALA),"
                + "foreign key(idALOCACAO) references ALOCACAO(idALOCACAO)"
                + ");";
        
        boolean conectou = false;
        try{
            conectou = this.conexaoSQLite.conectar();
            Statement stmt = this.conexaoSQLite.criarStatement();
            stmt.execute(sql1);
            stmt.execute(sql2);
            stmt.execute(sql3);
            stmt.execute(sql4);
        
            System.out.println("Tabelas criadas!!");
        } catch(SQLException e){
            // mensagem de erro na criacao da tabela
        } finally{
            if(conectou){
                this.conexaoSQLite.desconectar();
            }
        }
    }
}
