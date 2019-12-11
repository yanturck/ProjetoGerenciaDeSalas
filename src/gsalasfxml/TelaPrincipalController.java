package gsalasfxml;

import gsalasfxml.PojoDao.Alocacao;
import gsalasfxml.PojoDao.AlocacaoDAO;
import gsalasfxml.PojoDao.FrontAlocacao;
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
import javafx.scene.control.Button;
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
    }
    
    
    @FXML private ComboBox<Cadastrar> cbCadastrar;
    private List<Cadastrar> cadastros = new ArrayList<>();
    private ObservableList<Cadastrar> obsCadastro;
    
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
    }
    @FXML private TableView<FrontAlocacao> tableviewAlocacoes;
    @FXML private TableColumn<FrontAlocacao, String> colunaInicio;
    @FXML private TableColumn<FrontAlocacao, String> colunaDescricao;
    @FXML private TableColumn<FrontAlocacao, String> colunaTermino;
    
    private List<FrontAlocacao> listAlocs = new ArrayList();
    private ObservableList<FrontAlocacao> obsAlocs;
    
    public void carregarTable(){
        colunaInicio.setCellValueFactory(new PropertyValueFactory<>("data"));
        colunaDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        colunaTermino.setCellValueFactory(new PropertyValueFactory<>("dura"));
        
        /*RadioButton rAsa = (RadioButton) grupoAsa.getSelectedToggle();
        RadioButton rAndar = (RadioButton) grupoAndar.getSelectedToggle();
        String asa = rAsa.getText();
        String andar = rAndar.getText();*/
        AlocacaoDAO dao = new AlocacaoDAO();
        listAlocs = dao.telaPS();
        obsAlocs = FXCollections.observableArrayList(listAlocs);
        tableviewAlocacoes.setItems(obsAlocs);
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       carregarComBox();
       carregarTable();
       /*btnS01.setOnMouseClicked((MouseEvent e)->{
       carregarTable("01");
    });*/
    }    
    
}
