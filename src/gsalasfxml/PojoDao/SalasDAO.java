package gsalasfxml.PojoDao;

import gsalasfxml.conexao_banco.ConexaoSQLite;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
    
    public void adicionar(Sala sala){
        String sql = "INSERT INTO SALA(idSALA, ANDAR, TIPOsala) values (?, ?, ?);";
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
   public boolean existe(String idSala){
       String sql = "SELECT * SALA WHERE idSALA = ?;";
       String tipo;
       try{
           PreparedStatement stmt = connection.prepareStatement(sql);
           stmt.setString(1, idSala);
           ResultSet rs = stmt.executeQuery();
           tipo = rs.getString("TIPOsala");
           stmt.close();
           return false;
       }catch(SQLException u){
            return true;
       }
   }
}
