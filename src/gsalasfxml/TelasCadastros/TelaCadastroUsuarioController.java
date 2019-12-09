package gsalasfxml.TelasCadastros;

import gsalasfxml.PojoDao.Usuario;
import gsalasfxml.PojoDao.UsuariosDAO;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Yan Rocha
 */
public class TelaCadastroUsuarioController implements Initializable {
    
    @FXML private Button btnLimpar;
    @FXML private Button btnSalvar;
    @FXML private Button btnBuscar;
    @FXML private Button btnExcluir;
    @FXML private TextField txtNome;
    @FXML private TextField txtId;
    @FXML private TextField txtTipoU;
    @FXML private TextField txtCurso;
    @FXML private TextField txtTelefone;
    @FXML private Label labelNome;
    @FXML private Label labelId;
    @FXML private Label labelTipoU;
    @FXML private Label labelCurso;
    @FXML private Label labelTelefone;
    @FXML private Label labelAtualizacao;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       btnLimpar.setOnMouseClicked((MouseEvent e)->{
           acaoLimpar(); 
    });
       btnSalvar.setOnMouseClicked((MouseEvent e)->{
           acaoSalvar();
           acaoLimpar();
    });
       btnBuscar.setOnMouseClicked((MouseEvent e)->{
           acaoBuscar();
    });
    }    
     @FXML public void acaoLimpar(){
        txtNome.setText("");
        txtId.setText("");
        txtTipoU.setText("");
        txtCurso.setText("");
        txtTelefone.setText("");
    }
     @FXML public void acaoSalvar(){
         int idUser = Integer.parseInt(txtId.getText());
         Usuario user = new Usuario(idUser, txtNome.getText(), txtTipoU.getText(), txtCurso.getText(), txtTelefone.getText());
         UsuariosDAO dao = new UsuariosDAO();
         labelAtualizacao.setText(dao.adicionar(user));
     }
     
     @FXML public void acaoBuscar(){
         String tmp = txtId.getText();
         int busca = Integer.parseInt(txtId.getText());
         if(tmp==""){
             labelAtualizacao.setText("Busca não realizada, preencher a MATRICULA");
         } else{
             UsuariosDAO dao = new UsuariosDAO();
             Usuario user = dao.buscar(busca);
             txtNome.setText(user.getNome());
             txtId.setText(Integer.toString(user.getIdUsuario()));
             txtTipoU.setText(user.getTipoUsuario());
             txtCurso.setText(user.getCurso());
             txtTelefone.setText(user.getTelefone());
             labelAtualizacao.setText("Busca realizada!");
         }
         
     }
     @FXML public void acaoExcluir(){
     }
}
