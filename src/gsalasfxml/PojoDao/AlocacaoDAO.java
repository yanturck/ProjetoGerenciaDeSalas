package gsalasfxml.PojoDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
                String[] date = rs.getString("DATAaloc").split("-");
                String data = date[2] + "/" + date[1] + "/" + date[0];
                String[] dura = rs.getString("DURACAO").split("-");
                String duracao = dura[2] + "/" + dura[1] + "/" + dura[0];
		Alocacao aloc = new Alocacao(rs.getString("DESCRICAO"), data, rs.getString("HORAaloc"), rs.getString("TEMPOaloc"), duracao, rs.getInt("idUser"), rs.getString("idSala"));
                alocs.add(aloc);
            }
            stmt.close();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return alocs;
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
        List<Alocacao> alocs = new ArrayList<Alocacao>();
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String[] date = rs.getString("DATAaloc").split("-");
                String data = date[2] + "/" + date[1] + "/" + date[0];
                String[] dura = rs.getString("DURACAO").split("-");
                String duracao = dura[2] + "/" + dura[1] + "/" + dura[0];
		Alocacao aloc = new Alocacao(rs.getString("DESCRICAO"), data, rs.getString("HORAaloc"), rs.getString("TEMPOaloc"), duracao, rs.getInt("idUser"), rs.getString("idSala"));
		alocs.add(aloc);
            }
            stmt.close();
        }catch(SQLException u){
            throw new RuntimeException(u);
        }
        return alocs;
    }
}
