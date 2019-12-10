package gsalasfxml.PojoDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Yan Rocha
 */
public class SalasAlocacoesDAO {
    private Connection connection;
    private String url = "jdbc:sqlite:banco_de_dados/GerenciaSalasqlite.db";
    
    public SalasAlocacoesDAO(){
        try{
            connection = DriverManager.getConnection(url);
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
    
    public void adicionar(SalasAlocacoes sa){
        String sql = "INSERT INTO SALA_ALOCACAO(idSALA, idALOCACAO) values (?, ?);";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, sa.getIdSala());
            stmt.setInt(2, sa.getIdAlocacao());
            stmt.execute();
            stmt.close();
        }catch(SQLException u){
            throw new RuntimeException(u);
        }
    }
}
