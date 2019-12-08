package gsalasfxml.PojoDao;

import gsalasfxml.conexao_banco.ConexaoSQLite;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
    
    public void adicionar(Usuario user){
        String sql = "INSERT INTO USUARIO(idUSER, NOME, TIPOuser, CURSO, TELEFONE) values (?, ?, ?, ?);";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, user.getIdUsuario());
            stmt.setString(2, user.getNome());
            stmt.setString(3, user.getTipoUsuario());
            stmt.setString(4, user.getCurso());
            stmt.setString(5, user.getTelefone());
            stmt.execute();
            stmt.close();
        }catch(SQLException u){
            throw new RuntimeException(u);
        }
    }
}
