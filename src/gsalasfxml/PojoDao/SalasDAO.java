package gsalasfxml.PojoDao;

import gsalasfxml.conexao_banco.ConexaoSQLite;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class SalasDAO {
    private Connection connection;
    private String url = "jdbc:sqlite:banco_de_dados/GerensiaSalasqlite.db";
    
    public SalasDAO(){
        try{
            connection = DriverManager.getConnection(url);
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
    
    public String adicionar(Sala sala){
        String sql = "INSERT INTO SALA(idSALA, ANDAR, TIPOsala) values (?, ?, ?);";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, sala.getIdSala());
            stmt.setInt(2, sala.getAndar());
            stmt.setString(3, sala.getTipoSala());
            stmt.execute();
            stmt.close();
            return "Salvo com Sucesso!";
        }catch(SQLException u){
            return "Salvo com Sucesso!";
        }
    }
}
