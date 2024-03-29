package gsalasfxml.PojoDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuariosDAO {
    private Connection connection;
    private String url = "jdbc:sqlite:banco_de_dados/GerenciaSalasqlite.db";
    
    public UsuariosDAO(){
        try{
            connection = DriverManager.getConnection(url);
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
    
    public boolean adicionar(Usuario user){
        String sql = "INSERT INTO USUARIO(idUSER, NOME, TIPOuser, CURSO, TELEFONE) values (?, ?, ?, ?, ?);";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, user.getIdUsuario());
            stmt.setString(2, user.getNome());
            stmt.setString(3, user.getTipoUsuario());
            stmt.setString(4, user.getCurso());
            stmt.setString(5, user.getTelefone());
            stmt.execute();
            stmt.close();
            return true;
        }catch(SQLException u){
            return false;
        }
    }
    public boolean conExiste(int idUser){
        String sql = "SELECT * FROM USUARIO WHERE idUSER = ?;";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idUser);
            ResultSet rs = stmt.executeQuery();
            Usuario user = new Usuario(rs.getInt("idUSER"), rs.getString("NOME"), rs.getString("TIPOuser"), rs.getString("CURSO"), rs.getString("TELEFONE"));
            stmt.close();
            return true;
        }catch(SQLException e){
            return false;
        }
    }
    public Usuario buscar(int idUser){
        String sql = "SELECT * FROM USUARIO WHERE idUSER = ?;";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idUser);
            ResultSet rs = stmt.executeQuery();
            Usuario user = new Usuario(rs.getInt("idUSER"), rs.getString("NOME"), rs.getString("TIPOuser"), rs.getString("CURSO"), rs.getString("TELEFONE"));
            stmt.close();
            return user;
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
    public void excluir(int user){
       String sql = "DELETE FROM USUARIO WHERE idUSER = ?;";
       try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, user);
            stmt.execute();
            stmt.close();
        }catch(SQLException u){
            throw new RuntimeException(u);
        }
   }
    public void atualizar(int idUser, Usuario user){
        String sql = "UPDATE USUARIO SET idUSER = ?, NOME = ?, TIPOuser = ?, CURSO = ?, TELEFONE = ? WHERE idUSER = ?;";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, user.getIdUsuario());
            stmt.setString(2, user.getNome());
            stmt.setString(3, user.getTipoUsuario());
            stmt.setString(4, user.getCurso());
            stmt.setString(5, user.getTelefone());
            stmt.setInt(6, idUser);
            stmt.execute();
            stmt.close();
        }catch(SQLException u){
            throw new RuntimeException(u);
        }
    }
    public List<Usuario> buscaGeral(){
        String sql = "SELECT * FROM USUARIO;";
        List<Usuario> users = new ArrayList<Usuario>();
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
		Usuario user = new Usuario(rs.getInt("idUSER"), rs.getString("NOME"), rs.getString("TIPOuser"), rs.getString("CURSO"), rs.getString("TELEFONE"));
		users.add(user);
            }
            stmt.close();
        }catch(SQLException u){
            throw new RuntimeException(u);
        }
        return users;
    }
}
