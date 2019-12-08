package gsalasfxml.TelasCadastros;

import gsalasfxml.PojoDao.Alocacao;
import gsalasfxml.PojoDao.AlocacaoDAO;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    @FXML private ToggleGroup grupoMesmoD;
    @FXML private ToggleGroup grupoMaisS;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnLimpar.setOnMouseClicked((MouseEvent e)->{
       acaoLimpar(); 
    });
        btnSalvar.setOnMouseClicked((MouseEvent e)->{
            try {
                acaoSalvar();
            } catch (ParseException ex) {
                Logger.getLogger(TelaCadastroAlocacaoController.class.getName()).log(Level.SEVERE, null, ex);
            }
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
    @FXML public void acaoSalvar() throws ParseException{
        SimpleDateFormat formatador1 = new SimpleDateFormat("HH:mm:ss");
        String horario = txtHorario.getText();
        String inicio = horario.substring(0,5) + ":00";
        String termino = horario.substring(6,11) + ":00";
        Time time1 = (Time) formatador1.parse(inicio);
        Time time2 = (Time) formatador1.parse(termino);
        
        SimpleDateFormat formatador2 = new SimpleDateFormat("yyyy-mm-dd");
        String tmp = txtData.getText();
        String comeco = tmp.substring(6,11) + "-" + tmp.substring(3,5) + "-" + tmp.substring(0,3);
        Date dateComeco = (Date) formatador2.parse(comeco);
        
        Date dateFinal = dateComeco;
        if (txtDuracao.getText() != "" ){
            String aux = txtDuracao.getText();
            String data = aux.substring(6,11) + "-" + aux.substring(3,5) + "-" + aux.substring(0,3);
            dateFinal = (Date) formatador2.parse(data);
        }
        
        int matriculaU = Integer.parseInt(txtMatU.getText());
        
        Alocacao aloc = new Alocacao(txtDescr.getText(), dateComeco, time1, time2, dateFinal, matriculaU);
        AlocacaoDAO dao = new AlocacaoDAO();
        dao.adicionar(aloc);
    }
}
