package gsalasfxml.TelasCadastros;

import gsalasfxml.PojoDao.Usuario;
import gsalasfxml.PojoDao.UsuariosDAO;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
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
       btnExcluir.setOnMouseClicked((MouseEvent e)->{
           acaoExcluir();
           acaoLimpar();
    });
    }    
     @FXML public void acaoLimpar(){
        txtNome.setText("");
        txtId.setText("");
        txtTipoU.setText("");
        txtCurso.setText("");
        txtTelefone.setText("");
    }
     public boolean alertaCampos(){
         boolean aux = false;
         int nome = txtNome.getText().length();
         int id = txtId.getText().length();
         int tipoU = txtTipoU.getText().length();
         
         if ((nome != 0) && (id != 0) && (tipoU != 0)){
             aux = false;
         } else{
             aux = true;
             Alert camposFalta = new Alert(Alert.AlertType.INFORMATION);
             camposFalta.setTitle("ATENÇÃO!!");
             camposFalta.setHeaderText("CAMPOS FALTANDO");
             camposFalta.setContentText("Por favor preencha todos os campos!");
             camposFalta.show();
         }
         return aux;
     }
     @FXML public void acaoSalvar(){
         boolean tmp = alertaCampos();
         if(tmp == false){
            int idUser = Integer.parseInt(txtId.getText());
            Usuario user = new Usuario(idUser, txtNome.getText(), txtTipoU.getText(), txtCurso.getText(), txtTelefone.getText());
            UsuariosDAO dao = new UsuariosDAO();
            labelAtualizacao.setText(dao.adicionar(user));
         }
     }
     
     @FXML public void acaoBuscar(){
         int busca = Integer.parseInt(txtId.getText());
         if(busca!=0){
             UsuariosDAO dao = new UsuariosDAO();
             Usuario user = dao.buscar(busca);
             txtNome.setText(user.getNome());
             txtId.setText(Integer.toString(user.getIdUsuario()));
             txtTipoU.setText(user.getTipoUsuario());
             txtCurso.setText(user.getCurso());
             txtTelefone.setText(user.getTelefone());
             labelAtualizacao.setText("Busca realizada!");
         } else{
             labelAtualizacao.setText("Busca não Realizada, informe a MATRICULA!");
         }
         
     }
     @FXML public void acaoExcluir(){
         boolean tmp = alertaCampos();
         if(tmp == false){
            int idUser = Integer.parseInt(txtId.getText());
            Usuario user = new Usuario(idUser, txtNome.getText(), txtTipoU.getText(), txtCurso.getText(), txtTelefone.getText());
            UsuariosDAO dao = new UsuariosDAO();
            dao.excluir(user);
            labelAtualizacao.setText("Exclusão Realizada!");
         }
     }
}
