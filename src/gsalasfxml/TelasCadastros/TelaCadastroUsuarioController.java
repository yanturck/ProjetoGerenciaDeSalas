package gsalasfxml.TelasCadastros;

import gsalasfxml.PojoDao.Usuario;
import gsalasfxml.PojoDao.UsuariosDAO;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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
    @FXML private Button btnAtualizar;
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
    @FXML private Label labelCamposO;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
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
             Alertas alerteC = new Alertas();
             aux = alerteC.camposFaltante();
         }
         return aux;
     }
     @FXML public void acaoSalvar(){
         boolean tmp = alertaCampos();
         
         if(tmp == false){
            int idUser = Integer.parseInt(txtId.getText());
            Usuario user = new Usuario(idUser, txtNome.getText(), txtTipoU.getText(), txtCurso.getText(), txtTelefone.getText());
            UsuariosDAO dao = new UsuariosDAO();
            if (dao.adicionar(user) == true){
                labelAtualizacao.setText("Salvo com Sucesso!!");
                acaoLimpar();
            }else{
                Alertas alert = new Alertas();
                alert.idExistente();
            }
         }
     }
     public boolean alertaCamposB(){
        boolean aux = false;
        String id = txtId.getText();
        
        if (id.length() != 0){
            return false;
        }else {
            Alertas alerte = new Alertas();
            return alerte.camposFaltante();
        }
    }
     @FXML public void acaoBuscar(){
         boolean busca = alertaCamposB();
         if(busca == false){
             UsuariosDAO dao = new UsuariosDAO();
             if (dao.conExiste(Integer.parseInt(txtId.getText())) == true){
                Usuario user = dao.buscar(Integer.parseInt(txtId.getText()));
                txtNome.setText(user.getNome());
                txtId.setText(Integer.toString(user.getIdUsuario()));
                txtTipoU.setText(user.getTipoUsuario());
                txtCurso.setText(user.getCurso());
                txtTelefone.setText(user.getTelefone());
                labelAtualizacao.setText("Busca realizada!");
             }else{
                 Alertas a = new Alertas();
                 a.idNExistente();
             }
         } else{
             labelAtualizacao.setText("Busca não Realizada, informe a MATRICULA!");
         }
         
     }
     @FXML public void acaoExcluir(){
         boolean tmp = alertaCampos();
         if(tmp == false){
            Alert confirmarExcluir = new Alert(Alert.AlertType.CONFIRMATION);
            ButtonType btnOk = new ButtonType("OK");
            ButtonType btnCan = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);
            
            confirmarExcluir.setTitle("CONFIRMAÇÃO");
            confirmarExcluir.setHeaderText("CONFIRMAR EXCLUSÃO");
            confirmarExcluir.setContentText("Tem certeza que deseja excluir?");
            
            confirmarExcluir.getButtonTypes().setAll(btnOk, btnCan);
            confirmarExcluir.showAndWait().ifPresent(b -> {
                if (b == btnOk){
                    int idUser = Integer.parseInt(txtId.getText());
                    UsuariosDAO dao = new UsuariosDAO();
                    dao.excluir(idUser);
                    labelAtualizacao.setText("Exclusão Realizada!");
                    acaoLimpar();
                }else{
                    labelAtualizacao.setText("Operação Cancelada");
                }
            });
         }
     }
     @FXML public void acaoAtualizar(){
         boolean tmp = alertaCampos();
         if(tmp == false){
            Alert confirmarExcluir = new Alert(Alert.AlertType.CONFIRMATION);
            ButtonType btnOk = new ButtonType("OK");
            ButtonType btnCan = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);
            
            confirmarExcluir.setTitle("CONFIRMAÇÃO");
            confirmarExcluir.setHeaderText("CONFIRMAR ALTERAÇÃO");
            confirmarExcluir.setContentText("Tem certeza que deseja alterar?");
            
            confirmarExcluir.getButtonTypes().setAll(btnOk, btnCan);
            confirmarExcluir.showAndWait().ifPresent(b -> {
                if (b == btnOk){
                    int idUsuario = Integer.parseInt(txtId.getText());
                    Usuario user = new Usuario(idUsuario, txtNome.getText(), txtTipoU.getText(), txtCurso.getText(), txtTelefone.getText());
                    UsuariosDAO dao = new UsuariosDAO();
                    dao.atualizar(idUsuario, user);
                    labelAtualizacao.setText("Alteração Realizada!");
                    acaoLimpar();
                }else{
                    labelAtualizacao.setText("Operação Cancelada");
                }
            });
         }
     }
}
