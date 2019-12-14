package gsalasfxml.TelasCadastros;

import gsalasfxml.PojoDao.Sala;
import gsalasfxml.PojoDao.SalasDAO;
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
public class ListaSalasController implements Initializable {
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
                Sala sala = tableviewSalas.getSelectionModel().getSelectedItem();
                tableviewSalas.getItems().remove(sala);
                SalasDAO dao = new SalasDAO();
                dao.excluir(sala);
                labelAtualizacao.setText("Item Excluido!");
            }
        });
    }
    @FXML private TableView<Sala> tableviewSalas;
    @FXML private TableColumn<Sala,String> colunaSala;
    @FXML private TableColumn<Sala, String> colunaTipoS;
    
    private List<Sala> listSalas = new ArrayList();
    private ObservableList<Sala> obsSalas;
    
    public void carregarTable(){
        colunaSala.setCellValueFactory(new PropertyValueFactory<>("idSala"));
        colunaTipoS.setCellValueFactory(new PropertyValueFactory<>("tipoSala"));
        
        SalasDAO dao = new SalasDAO();
        listSalas = dao.buscaGeral();
        obsSalas = FXCollections.observableArrayList(listSalas);
        tableviewSalas.setItems(obsSalas);
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarTable();
    }    
    
}
