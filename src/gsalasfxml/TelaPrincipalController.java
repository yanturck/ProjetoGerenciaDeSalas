package gsalasfxml;

import gsalasfxml.PojoDao.Alocacao;
import gsalasfxml.PojoDao.AlocacaoDAO;
import gsalasfxml.PojoDao.UsuariosDAO;
import gsalasfxml.TelasCadastros.Alertas;
import gsalasfxml.TelasCadastros.TelaCadastroAlocacaoFXML;
import gsalasfxml.TelasCadastros.TelaCadastroSalaFXML;
import gsalasfxml.TelasCadastros.TelaCadastroUsuarioFXML;
import gsalasfxml.combox.Cadastrar;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.xml.ws.Action;

/**
 * FXML Controller class
 *
 * @author Yan Rocha
 */
public class TelaPrincipalController implements Initializable {
    @FXML private TextField txtBusca;
    @FXML private Button btnBusca;
    @FXML private Label labelPesquisar;
    
    @FXML public void acaoBuscar(){
        colunaInicio.setCellValueFactory(new PropertyValueFactory<>("data"));
        colunaDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        colunaTermino.setCellValueFactory(new PropertyValueFactory<>("tempo"));
        colunaHorario.setCellValueFactory(new PropertyValueFactory<>("hora"));
        colunaSala.setCellValueFactory(new PropertyValueFactory<>("idSala"));
        colunaDuracao.setCellValueFactory(new PropertyValueFactory<>("duracao"));
        AlocacaoDAO dao = new AlocacaoDAO();
        UsuariosDAO daoU = new UsuariosDAO();
        if (txtBusca.getText().length() != 0){
            if (daoU.conExiste(Integer.parseInt(txtBusca.getText())) == true){
                listAlocs = dao.buscarAloc(Integer.parseInt(txtBusca.getText()));
                obsAlocs = FXCollections.observableArrayList(listAlocs);
                tableviewAlocacoes.setItems(obsAlocs);
                labelAtualizacao.setText("Busca Realizada!");
            }else{
                Alertas a = new Alertas();
                a.idNExistente();
                labelAtualizacao.setText("Busca não Realizada!");
            }
        }else {
            listAlocs = dao.buscaGeral();
            obsAlocs = FXCollections.observableArrayList(listAlocs);
            tableviewAlocacoes.setItems(obsAlocs);
            labelAtualizacao.setText("Busca Realizada!");
        }
    }
    
    
    @FXML private ComboBox<Cadastrar> cbCadastrar;
    private List<Cadastrar> cadastros = new ArrayList<>();
    private ObservableList<Cadastrar> obsCadastro;
    
    @FXML private Button btnExcluir;
    
    public void carregarComBox(){
        Cadastrar cadastro1 = new Cadastrar("Sala");
        Cadastrar cadastro2 = new Cadastrar("Usuário");
        Cadastrar cadastro3 = new Cadastrar("Alocação");
        
        
        cadastros.add(cadastro1);
        cadastros.add(cadastro2);
        cadastros.add(cadastro3);
        obsCadastro = FXCollections.observableArrayList(cadastros);
        cbCadastrar.setItems(obsCadastro);
    }
    @FXML public void pegar(){
        Cadastrar cadastrar = cbCadastrar.getSelectionModel().getSelectedItem();
        if (cadastrar.getObjt().equals("Sala")){
            TelaCadastroSalaFXML s = new TelaCadastroSalaFXML();
            try {
                s.start(new Stage());
            } catch (Exception ex) {
                Logger.getLogger(TelaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if (cadastrar.getObjt().equals("Usuário")){
            TelaCadastroUsuarioFXML u = new TelaCadastroUsuarioFXML();
            try {
                u.start(new Stage());
            } catch (Exception ex) {
                Logger.getLogger(TelaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if (cadastrar.getObjt().equals("Alocação")){
            TelaCadastroAlocacaoFXML a = new TelaCadastroAlocacaoFXML();
            try {
                a.start(new Stage());
            } catch (Exception ex) {
                Logger.getLogger(TelaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        cbCadastrar.getSelectionModel().clearSelection();
    }
    @FXML private TableView<Alocacao> tableviewAlocacoes;
    @FXML private TableColumn<Alocacao, String> colunaInicio;
    @FXML private TableColumn<Alocacao, String> colunaDescricao;
    @FXML private TableColumn<Alocacao, String> colunaTermino;
    @FXML private TableColumn<Alocacao, String> colunaHorario;
    @FXML private TableColumn<Alocacao, String> colunaSala;
    @FXML private TableColumn<Alocacao, String> colunaDuracao;
    @FXML private Label labelAtualizacao;
    
    private List<Alocacao> listAlocs = new ArrayList();
    private ObservableList<Alocacao> obsAlocs;
    
    public void carregarTable(){
        colunaInicio.setCellValueFactory(new PropertyValueFactory<>("data"));
        colunaDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        colunaTermino.setCellValueFactory(new PropertyValueFactory<>("tempo"));
        colunaHorario.setCellValueFactory(new PropertyValueFactory<>("hora"));
        colunaSala.setCellValueFactory(new PropertyValueFactory<>("idSala"));
        colunaDuracao.setCellValueFactory(new PropertyValueFactory<>("duracao"));
        
        AlocacaoDAO dao = new AlocacaoDAO();
        listAlocs = dao.buscaGeral();
        obsAlocs = FXCollections.observableArrayList(listAlocs);
        tableviewAlocacoes.setItems(obsAlocs);
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       carregarComBox();
       carregarTable();
    }    
    @FXML  public void acaoExcluir(){
        Alert confirmarExcluir = new Alert(Alert.AlertType.CONFIRMATION);
        ButtonType btnOk = new ButtonType("OK");
        ButtonType btnCan = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);
            
        confirmarExcluir.setTitle("CONFIRMAÇÃO");
        confirmarExcluir.setHeaderText("CONFIRMAR EXCLUSÃO");
        confirmarExcluir.setContentText("Tem certeza que deseja excluir?");
            
        confirmarExcluir.getButtonTypes().setAll(btnOk, btnCan);
        confirmarExcluir.showAndWait().ifPresent(b -> {
            if (b == btnOk){
                Alocacao aloc = tableviewAlocacoes.getSelectionModel().getSelectedItem();
                tableviewAlocacoes.getItems().remove(aloc);
                AlocacaoDAO dao = new AlocacaoDAO();
                int id = dao.idAloc(aloc);
                dao.excluir(id);
                labelAtualizacao.setText("Item Excluido!");
            }
        });
    }
}
