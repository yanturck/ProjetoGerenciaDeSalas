package gsalasfxml.TelasCadastros;

import gsalasfxml.PojoDao.Alocacao;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Yan Rocha
 */
public class TelaCadastroAlocacaoController implements Initializable {
    @FXML private Button btnLimpar;
    @FXML private Button btnSalvar;
    @FXML private Button btnBuscar;
    @FXML private Button btnExcluir;
    @FXML private Label labelDescr;
    @FXML private Label labelNumS;
    @FXML private Label labelData;
    @FXML private Label labelHorario;
    @FXML private Label labelDuracao;
    @FXML private Label labelMatU;
    @FXML private Label labelAndar;
    @FXML private TextField txtDescr;
    @FXML private TextField txtNumS;
    @FXML private TextField txtData;
    @FXML private TextField txtHorario;
    @FXML private TextField txtDuracao;
    @FXML private TextField txtMatU;
    @FXML private ToggleGroup grupoAsa;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnLimpar.setOnMouseClicked((MouseEvent e)->{
       acaoLimpar(); 
    });
    }    
    @FXML public void acaoLimpar(){
        txtDescr.setText("");
        txtNumS.setText("");
        txtData.setText("");
        txtHorario.setText("");
        txtDuracao.setText("");
        txtMatU.setText("");
    }
    @FXML public void acaoSalvar(){
        
       // Alocacao aloc = new Alocacao(txtDescr.getText(), );
        
    }
}
