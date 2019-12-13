package gsalasfxml.PojoDao;

import gsalasfxml.conexao_banco.ConexaoSQLite;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class AlocacaoDAO {
    private Connection connection;
    private String url = "jdbc:sqlite:banco_de_dados/GerenciaSalasqlite.db";
    
    public AlocacaoDAO(){
        try{
            connection = DriverManager.getConnection(url);
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
    
    public void adicionar(Alocacao aloc){
        String sql = "INSERT INTO ALOCACAO(DESCRICAO, DATAaloc, HORAaloc, TEMPOaloc, DURACAO, idUser, idSala) values (?, ?, ?, ?, ?, ?, ?);";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, aloc.getDescricao());
            stmt.setString(2, aloc.getData()); 
            stmt.setString(3, aloc.getHora()); 
            stmt.setString(4, aloc.getTempo());
            stmt.setString(5, aloc.getDuracao());
            stmt.setInt(6, aloc.getIdUser());
            stmt.setString(7, aloc.getIdSala());
            stmt.execute();
            stmt.close();
        }catch(SQLException u){
            throw new RuntimeException(u);
        }
    }
    public int idAloc(Alocacao aloc){
        String sql = "SELECT idALOCACAO FROM ALOCACAO WHERE DESCRICAO = ? AND DATAaloc = ? AND HORAaloc = ? AND TEMPOaloc = ? AND DURACAO = ? AND idUser = ?;";
        int idAloc;
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, aloc.getDescricao());
            stmt.setString(2, aloc.getData()); 
            stmt.setString(3, aloc.getHora()); 
            stmt.setString(4, aloc.getTempo());
            stmt.setString(5, aloc.getDuracao());
            stmt.setInt(6, aloc.getIdUser());
            ResultSet rs = stmt.executeQuery();
            idAloc = rs.getInt("idALOCACAO");
            stmt.close();
        }catch(SQLException u){
            throw new RuntimeException(u);
        }
        return idAloc;
    }
    public List<Alocacao> buscarAloc(int idUser){
        String sql = "SELECT * FROM ALOCACAO WHERE idUser = ?;";
        List<Alocacao> alocs = new ArrayList<Alocacao>();
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idUser);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Alocacao aloc = new Alocacao(rs.getString("DESCRICAO"), rs.getString("DATAaloc"), rs.getString("HORAaloc"), rs.getString("TEMPOaloc"), rs.getString("DURACAO"), rs.getInt("idUser"), rs.getString("idSala"));
                alocs.add(aloc);
            }
            stmt.close();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return alocs;
    }
    public boolean existeBusca(int idUser){
        String sql = "SELECT * FROM ALOCACAO WHERE idUser = ?;";
        List<Alocacao> alocs = new ArrayList<Alocacao>();
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idUser);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Alocacao aloc = new Alocacao(rs.getString("DESCRICAO"), rs.getString("DATAaloc"), rs.getString("HORAaloc"), rs.getString("TEMPOaloc"), rs.getString("DURACAO"), rs.getInt("idUser"), rs.getString("idSala"));
                alocs.add(aloc);
            }
            stmt.close();
            return true;
        }catch(SQLException e){
            return false;
        }
    }
    public void excluir(int idAloc){
        String sql = "DELETE FROM ALOCACAO WHERE idALOCACAO = ?;";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idAloc);
            stmt.execute();
            stmt.close();
        }catch(SQLException u){
            throw new RuntimeException(u);
        }
    }
    
    public List<Alocacao> buscaGeral(){
        String sql = "SELECT * FROM ALOCACAO";
        //String idS = andar.substring(0,1) + i + aux.substring(0,1);
        List<Alocacao> alocs = new ArrayList<Alocacao>();
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
		Alocacao aloc = new Alocacao(rs.getString("DESCRICAO"), rs.getString("DATAaloc"), rs.getString("HORAaloc"), rs.getString("TEMPOaloc"), rs.getString("DURACAO"), rs.getInt("idUser"), rs.getString("idSala"));
		alocs.add(aloc);
            }
            stmt.close();
        }catch(SQLException u){
            throw new RuntimeException(u);
        }
        return alocs;
    }
}
