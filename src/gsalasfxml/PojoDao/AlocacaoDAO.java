package gsalasfxml.PojoDao;

import gsalasfxml.conexao_banco.ConexaoSQLite;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;

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
        String sql = "INSERT INTO ALOCACAO(DESCRICAO, DATAaloc, HORAaloc, TEMPOaloc, DURACAO, idUser) values (?, ?, ?, ?, ?, ?);";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, aloc.getDescricao());
            stmt.setDate(2, aloc.getData()); 
            stmt.setTime(3, aloc.getHora()); 
            stmt.setTime(4, aloc.getTempo());
            stmt.setDate(5, aloc.getDuracao());
            stmt.setInt(6, aloc.getIdUser());
            stmt.execute();
            stmt.close();
        }catch(SQLException u){
            throw new RuntimeException(u);
        }
    }
    /*public Alocacao buscarAloc(int idAloc){
        return aloc;
    }*/
    public void excluir(int idAloc){
        
    }
    public void atualizar(int idAloc){
        
    }
}
