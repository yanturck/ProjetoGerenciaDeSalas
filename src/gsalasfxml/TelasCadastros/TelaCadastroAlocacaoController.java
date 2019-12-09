package gsalasfxml.TelasCadastros;

import gsalasfxml.PojoDao.Alocacao;
import gsalasfxml.PojoDao.AlocacaoDAO;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
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
    @FXML private Label labelAtualizacao;
    @FXML private TextField txtDescr;
    @FXML private TextField txtNumS;
    @FXML private TextField txtData;
    @FXML private TextField txtHorario;
    @FXML private TextField txtDuracao;
    @FXML private TextField txtMatU;
    @FXML private ToggleGroup grupoMesmoD;
    @FXML private ToggleGroup grupoMaisS;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       btnLimpar.setOnMouseClicked((MouseEvent e)->{
           acaoLimpar(); 
    });
        btnSalvar.setOnMouseClicked((MouseEvent e)->{
           acaoSalvar();
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
    @FXML public void acaoSalvar() {
        String dataString = txtData.getText();
        String[] dataSeparada = dataString.split("/");
        LocalDate dateC = LocalDate.of(Integer.parseInt(dataSeparada[2]),Integer.parseInt(dataSeparada[1]), Integer.parseInt(dataSeparada[0]));
        Date dateComeco = (Date) Date.from(dateC.atStartOfDay(ZoneId.systemDefault()).toInstant());
        
        Date dateFinal = dateComeco;
        if (txtDuracao.getText() != "" ){
            String auxString = txtDuracao.getText();
            String[] auxSeparada = auxString.split("/");
            LocalDate dateF = LocalDate.of(Integer.parseInt(auxSeparada[2]),Integer.parseInt(auxSeparada[1]), Integer.parseInt(auxSeparada[0]));
            dateFinal = (Date) Date.from(dateF.atStartOfDay(ZoneId.systemDefault()).toInstant());
        }
        
        String horasString = txtHorario.getText();
        String[] horaSeparadas = horasString.split("-");
        
        String horaComeco = horaSeparadas[0];
        String[] aux = horaComeco.split(":");
        LocalTime timeC = LocalTime.of(Integer.parseInt(aux[0]), Integer.parseInt(aux[1]), 00);
        Time timeComeco = Time.valueOf(timeC);
        
        String horaFinal = horaSeparadas[1];
        String[] tmp = horaFinal.split(":");
        LocalTime timeF = LocalTime.of(Integer.parseInt(tmp[0]), Integer.parseInt(tmp[1]), 00);
        Time timeFinal = Time.valueOf(timeF);
        
        int matriculaU = Integer.parseInt(txtMatU.getText());
        
        Alocacao aloc = new Alocacao(txtDescr.getText(), dateComeco, timeComeco, timeFinal, dateFinal, matriculaU);
        AlocacaoDAO dao = new AlocacaoDAO();
        labelAtualizacao.setText(dao.adicionar(aloc));
    }
}
