package gsalasfxml.PojoDao;

import gsalasfxml.conexao_banco.ConexaoSQLite;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class AlocacaoDAO {
    private Connection connection;
    private String url = "jdbc:sqlite:banco_de_dados/GerensiaSalasqlite.db";
    
    public AlocacaoDAO(){
        try{
            connection = DriverManager.getConnection(url);
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
    
    public void adicionar(Alocacao aloc){
        String sql = "INSERT INTO USUARIO(DESCRICAO, DATAaloc, HORAaloc, TEMPOaloc, idUser) values (?, ?, ?, ?, ?);";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, aloc.getDescricao());
            stmt.setString(2, aloc.getData()); // falta transaformar pra date
            stmt.setString(3, aloc.getHora()); // falta transforma pra time
            stmt.setString(4, aloc.getTempo()); // falta transforma pra date
            stmt.setInt(5, aloc.getIdUser());
            stmt.execute();
            stmt.close();
        }catch(SQLException u){
            throw new RuntimeException(u);
        }
    }
}
