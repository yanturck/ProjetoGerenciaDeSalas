package gsalasfxml;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
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
    
    @FXML public void acaoBuscar(ActionEvent even){    
    }
    
    @FXML private Label labelAsa;
    @FXML private Label labelAndar;
    @FXML private ToggleGroup grupoAsa;
    @FXML private ToggleGroup grupoAndar;
    
    @FXML private Button btnS01;
    @FXML private Button btnS02;
    @FXML private Button btnS03;
    @FXML private Button btnS04;
    @FXML private Button btnS05;
    @FXML private Button btnS06;
    @FXML private Button btnS07;
    @FXML private Button btnS08;
    @FXML private Button btnS09;
    @FXML private Button btnS10;
    @FXML public void acaoBtnS01(ActionEvent even){
        
    }
    @FXML public void acaoBtnS02(ActionEvent even){
        
    }
    @FXML public void acaoBtnS03(ActionEvent even){
        
    }
    @FXML public void acaoBtnS04(ActionEvent even){
        
    }
    @FXML public void acaoBtnS05(ActionEvent even){
        
    }
    @FXML public void acaoBtnS06(ActionEvent even){
        
    }
    @FXML public void acaoBtnS07(ActionEvent even){
        
    }
    @FXML public void acaoBtnS08(ActionEvent even){
        
    }
    @FXML public void acaoBtnS09(ActionEvent even){
        
    }
    @FXML public void acaoBtnS10(ActionEvent even){
        
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
        cbCadastrar.getSelectionModel().clearSelection();
    }
    
    @FXML private TableColumn<?, ?> colunaInicio;
    @FXML private TableColumn<?, ?> colunaDescricao;
    @FXML private TableColumn<?, ?> colunaTermino;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       carregarComBox();
    }    
    
}
