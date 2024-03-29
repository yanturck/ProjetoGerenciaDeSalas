package gsalasfxml.PojoDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SalasDAO {
    private Connection connection;
    private String url = "jdbc:sqlite:banco_de_dados/GerenciaSalasqlite.db";
    
    public SalasDAO(){
        try{
            connection = DriverManager.getConnection(url);
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
    
    public boolean adicionar(Sala sala){
        String sql = "INSERT INTO SALA(idSALA, ANDAR, TIPOsala) values (?, ?, ?);";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, sala.getIdSala());
            stmt.setInt(2, sala.getAndar());
            stmt.setString(3, sala.getTipoSala());
            stmt.execute();
            stmt.close();
            return true;
        }catch(SQLException u){
            return false;
        }
    }
   public void excluir(Sala sala){
       String sql = "DELETE FROM SALA WHERE idSALA = ? AND ANDAR = ? AND TIPOsala = ?;";
       try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, sala.getIdSala());
            stmt.setInt(2, sala.getAndar());
            stmt.setString(3, sala.getTipoSala());
            stmt.execute();
            stmt.close();
        }catch(SQLException u){
            throw new RuntimeException(u);
        }
   }
   public List<Sala> buscaGeral(){
        String sql = "SELECT * FROM SALA";
        List<Sala> salas = new ArrayList<Sala>();
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
		Sala sala = new Sala(rs.getString("idSala"), rs.getInt("ANDAR"), rs.getString("TIPOsala"));
		salas.add(sala);
            }
            stmt.close();
        }catch(SQLException u){
            throw new RuntimeException(u);
        }
        return salas;
    }
}
