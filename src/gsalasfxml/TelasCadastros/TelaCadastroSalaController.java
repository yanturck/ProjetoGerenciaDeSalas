package gsalasfxml.TelasCadastros;

import gsalasfxml.PojoDao.Sala;
import gsalasfxml.PojoDao.SalasDAO;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Yan Rocha
 */
public class TelaCadastroSalaController implements Initializable {
    
    @FXML private Button btnLimpar;
    @FXML private Button btnSalvar;
    @FXML private Button btnExcluir;
    @FXML private TextField txtNumS;
    @FXML private Label labelNumS;
    @FXML private Label labelTipoS;
    @FXML private Label labelAsa;
    @FXML private Label labelAtualizacao;
    @FXML private ToggleGroup grupoTipoS;
    @FXML private ToggleGroup grupoAsa;
    @FXML private RadioButton rbNorte;
    @FXML private RadioButton rbSul;
    @FXML private RadioButton rbComum;
    @FXML private RadioButton rbMnAud;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       btnLimpar.setOnMouseClicked((MouseEvent e)->{
       acaoLimpar();
    });
       btnSalvar.setOnMouseClicked((MouseEvent s)->{
       acaoSalvar();
       acaoLimpar();
    });
       btnExcluir.setOnMouseClicked((MouseEvent s)->{
       acaoExcluir();
       acaoLimpar();
    });
    }    
    @FXML public void acaoLimpar(){
        txtNumS.setText("");
        rbNorte.setSelected(false);
        rbSul.setSelected(false);
        rbComum.setSelected(false);
        rbMnAud.setSelected(false);
    }
    
    public boolean alertas(){
        boolean tmp = false;
        RadioButton asa = (RadioButton) grupoAsa.getSelectedToggle();
        RadioButton tipo = (RadioButton) grupoTipoS.getSelectedToggle();
        if (txtNumS.getText() == "" || asa.getText() == "" || tipo.getText() == ""){
            tmp = true;
            Alert camposFalta = new Alert(Alert.AlertType.INFORMATION);
            camposFalta.setTitle("ATENÇÃO!!");
            camposFalta.setHeaderText("CAMPOS FALTANDO");
            camposFalta.setContentText("Por favor preencha todos os campos!");
        }
        return tmp;
    }
    public Sala organizando(){
        String idSala;
        RadioButton rAsa = (RadioButton) grupoAsa.getSelectedToggle();
        if(rAsa.getText() == "Sul"){
            idSala = txtNumS.getText() + "S";
        }else{
            idSala = txtNumS.getText() + "N";
        }
        String tmp = txtNumS.getText();
        int andar = Integer.parseInt(tmp.substring(0,1));
        
        RadioButton rTipo = (RadioButton) grupoTipoS.getSelectedToggle();
        String tipo = rTipo.getText();
        
        Sala sala = new Sala(idSala, andar, tipo);
        return sala;
    }
    
    @FXML public void acaoSalvar(){
        boolean aux = alertas();
        if (aux == false){
            SalasDAO dao = new SalasDAO();
            labelAtualizacao.setText(dao.adicionar(organizando()));
        }
    }
    @FXML public void acaoExcluir(){
        boolean aux1 = alertas();
        if (aux1 == false){
            SalasDAO dao = new SalasDAO();
            labelAtualizacao.setText(dao.excluir(organizando()));
        }
    }
}