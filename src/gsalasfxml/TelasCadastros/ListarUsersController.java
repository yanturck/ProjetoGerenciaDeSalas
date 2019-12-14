package gsalasfxml.TelasCadastros;

import gsalasfxml.PojoDao.Usuario;
import gsalasfxml.PojoDao.UsuariosDAO;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Yan Rocha
 */
public class ListarUsersController implements Initializable {
    @FXML private Button btnExcluir;
    @FXML private Label labelAtualizacao;
    @FXML public void acaoExcluir(){
        Alert confirmarExcluir = new Alert(Alert.AlertType.CONFIRMATION);
        ButtonType btnOk = new ButtonType("OK");
        ButtonType btnCan = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);
            
        confirmarExcluir.setTitle("CONFIRMAÇÃO");
        confirmarExcluir.setHeaderText("CONFIRMAR EXCLUSÃO");
        confirmarExcluir.setContentText("Tem certeza que deseja excluir?");
            
        confirmarExcluir.getButtonTypes().setAll(btnOk, btnCan);
        confirmarExcluir.showAndWait().ifPresent(b -> {
            if (b == btnOk){
                Usuario user = tableviewUsers.getSelectionModel().getSelectedItem();
                tableviewUsers.getItems().remove(user);
                UsuariosDAO dao = new UsuariosDAO();
                dao.excluir(user.getIdUsuario());
                labelAtualizacao.setText("Item Excluido!");
            }
        });
    }
    @FXML private TableView<Usuario> tableviewUsers;
    @FXML private TableColumn<Usuario, Integer> colunaMat;
    @FXML private TableColumn<Usuario, String> colunaNome;
    @FXML private TableColumn<Usuario, String> colunaTipo;
    @FXML private TableColumn<Usuario, String> colunaCurso;
    @FXML private TableColumn<Usuario, String> colunaFone;
    
    private List<Usuario> listUsers = new ArrayList();
    private ObservableList<Usuario> obsUsers;
    
    public void carregarTable(){
        colunaMat.setCellValueFactory(new PropertyValueFactory<>("idUsuario"));
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaTipo.setCellValueFactory(new PropertyValueFactory<>("tipoUsuario"));
        colunaCurso.setCellValueFactory(new PropertyValueFactory<>("curso"));
        colunaFone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        
        UsuariosDAO dao = new UsuariosDAO();
        listUsers = dao.buscaGeral();
        obsUsers = FXCollections.observableArrayList(listUsers);
        tableviewUsers.setItems(obsUsers);
        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarTable();
    }    
    
}
