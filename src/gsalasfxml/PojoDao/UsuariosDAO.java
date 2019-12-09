package gsalasfxml.PojoDao;

import gsalasfxml.conexao_banco.ConexaoSQLite;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UsuariosDAO {
    private Connection connection;
    private String url = "jdbc:sqlite:banco_de_dados/GerensiaSalasqlite.db";
    
    public UsuariosDAO(){
        try{
            connection = DriverManager.getConnection(url);
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
    
    public String adicionar(Usuario user){
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
            return "Salvo com Sucesso!";
        }catch(SQLException u){
            return "Salvo com Sucesso!";
        }
    }
    public Usuario buscar(int idUser){
        String sql = "SELECT * FROM USUARIO WHERE idUSER = ?;";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idUser);
            ResultSet rs = stmt.executeQuery();
            Usuario user = new Usuario(rs.getInt("idUSER"), rs.getString("NOME"), rs.getString("TIPOuser"), rs.getString("CURSO"), rs.getString("TELEFONE"));
            return user;
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
    
}
