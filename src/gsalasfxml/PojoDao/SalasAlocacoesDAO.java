package gsalasfxml.PojoDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    public String buscarSala(int idAloc){
        String sql = "SELECT idSALA FROM SALA_ALOCACAO WHERE = ?";
        List<String> salas = new ArrayList<String>();
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idAloc);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                SalasAlocacoes sa = new SalasAlocacoes(rs.getString("idSALA"), idAloc);
                salas.add(sa.getIdSala());
            }
            stmt.close();
        }catch(SQLException u){
            throw new RuntimeException(u);
        }
        String finalStr = "";
        for (String str : salas) {
            if (finalStr.trim().isEmpty()) {finalStr = str;}
            else {finalStr = finalStr + "," + str;}
	}
        return finalStr;
    }
}
