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
    public Alocacao buscarAloc(String dia, String dura, int idUser){
        String sql = "SELECT DESCRICAO, HORAaloc, TEMPOaloc, idSala FROM ALOCACAO WHERE DATAaloc = ? AND DURACAO = ? AND idUser = ?;";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, dia);
            stmt.setString(2, dura);
            stmt.setInt(3, idUser);
            ResultSet rs = stmt.executeQuery();
            Alocacao aloc = new Alocacao(rs.getString("DESCRICAO"), dia, rs.getString("HORAaloc"), rs.getString("TEMPOaloc"), dura, idUser, rs.getString("idSala"));
            stmt.close();
            return aloc;
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
    public void excluir(String dia, String dura, int idUser){
        String sql = "DELETE FROM ALOCACAO WHERE DATAaloc = ? AND DURACAO = ? AND idUser = ?;";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, dia);
            stmt.setString(2, dura);
            stmt.setInt(3, idUser);
            stmt.execute();
            stmt.close();
        }catch(SQLException u){
            throw new RuntimeException(u);
        }
    }
    
    public void atualizar(int idAloc, Alocacao aloc){
        String sql = "UPDATE ALOCACAO SET DESCRICAO = ?, DATAaloc = ?, HORAaloc = ?, TEMPOaloc = ?, DURACAO = ?, idUser = ?, idSala = ? WHERE idALOCACAO = ?;";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, aloc.getDescricao());
            stmt.setString(2, aloc.getData()); 
            stmt.setString(3, aloc.getHora()); 
            stmt.setString(4, aloc.getTempo());
            stmt.setString(5, aloc.getDuracao());
            stmt.setInt(6, aloc.getIdUser());
            stmt.setString(7, aloc.getIdSala());
            stmt.setInt(8, idAloc);
            stmt.execute();
            stmt.close();
        }catch(SQLException u){
            throw new RuntimeException(u);
        }
    }
    public List<FrontAlocacao> telaPS(){
        String sql = "SELECT * FROM ALOCACAO";
        //String idS = andar.substring(0,1) + i + aux.substring(0,1);
        List<FrontAlocacao> alocs = new ArrayList<FrontAlocacao>();
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
		FrontAlocacao aloc = new FrontAlocacao(rs.getString("DATAaloc"), rs.getString("DESCRICAO"), rs.getString("DURACAO"));
		alocs.add(aloc);
            }
            stmt.close();
        }catch(SQLException u){
            throw new RuntimeException(u);
        }
        return alocs;
    }
}
