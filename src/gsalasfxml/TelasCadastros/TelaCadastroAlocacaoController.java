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
    @FXML private Button btnAtualizar;
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
    @FXML private RadioButton maisSalas;
    @FXML private RadioButton mesmoDia;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       btnLimpar.setOnMouseClicked((MouseEvent e)->{
           acaoLimpar(); 
    });
        /*btnSalvar.setOnMouseClicked((MouseEvent e)->{
            acaoSalvar();
           acaoLimpar();
    });*/
    }    
    
    public boolean alertaCampos(){
        boolean aux = false;
        String descricao = txtDescr.getText();
        boolean mesDia = mesmoDia.isSelected();
        String numeroSala = txtNumS.getText();
        String data = txtData.getText();
        String horario = txtHorario.getText();
        String matriculaUser = txtMatU.getText();
        String duracao = txtDuracao.getText();
        
        if (mesDia== true || duracao.length() != 0){
            aux = true;
        }
        if ((descricao.length() != 0) && (numeroSala.length() != 0) && (data.length() != 0) && (horario.length() != 0) && (matriculaUser.length() != 0) && (aux == true)){
            return false;
        }else {
            Alertas alerte = new Alertas();
            return alerte.camposFaltante();
        }
    }
    public boolean alertaCamposB(){
        boolean aux = false;
        boolean mesDia = mesmoDia.isSelected();
        String data = txtData.getText();
        String matriculaUser = txtMatU.getText();
        String duracao = txtDuracao.getText();
        
        if (mesDia== true || duracao.length() != 0){
            aux = true;
        }
        if ((data.length() != 0) && (matriculaUser.length() != 0) && (aux == true)){
            return false;
        }else {
            Alertas alerte = new Alertas();
            return alerte.camposFaltante();
        }
    }
    @FXML public void acaoLimpar(){
        txtDescr.setText("");
        txtNumS.setText("");
        txtData.setText("");
        txtHorario.setText("");
        txtDuracao.setText("");
        txtMatU.setText("");
        maisSalas.setSelected(false);
        mesmoDia.setSelected(false);
    }
    @FXML public void acaoSalvar(){
        if (alertaCampos() == false){
            /*String[] dataString = txtData.getText().split("/");
            LocalDate dateC = LocalDate.of(Integer.parseInt(dataString[2]), Integer.parseInt(dataString[1]), Integer.parseInt(dataString[0]));
            Date dateComeco = (Date) Date.from(dateC.atStartOfDay(ZoneId.systemDefault()).toInstant());*/
            String[] dataString = txtData.getText().split("/");
            String dateComeco = dataString[2] + "-" + dataString[1] + "-" + dataString[0];
            String dateFinal = dateComeco;
            if (mesmoDia.isSelected() == true){
                dateFinal = dateComeco;
            }else{
                /*String[] duraString = txtDuracao.getText().split("/");
                LocalDate dateF = LocalDate.of(Integer.parseInt(duraString[2]), Integer.parseInt(duraString[1]), Integer.parseInt(duraString[0]));
                dateFinal = (Date) Date.from(dateF.atStartOfDay(ZoneId.systemDefault()).toInstant());*/
                String[] dataString1 = txtDuracao.getText().split("/");
                dateFinal = dataString1[2] + "-" + dataString1[1] + "-" + dataString1[0];
            }

            String horasString = txtHorario.getText();
            String[] horaSeparadas = horasString.split("-");

            /*String horaComeco = horaSeparadas[0];
            String[] aux = horaComeco.split(":");
            LocalTime timeC = LocalTime.of(Integer.parseInt(aux[0]), Integer.parseInt(aux[1]), 00);
            Time timeComeco = Time.valueOf(timeC);

            String horaFinal = horaSeparadas[1];
            String[] tmp = horaFinal.split(":");
            LocalTime timeF = LocalTime.of(Integer.parseInt(tmp[0]), Integer.parseInt(tmp[1]), 00);
            Time timeFinal = Time.valueOf(timeF);*/

            int matriculaU = Integer.parseInt(txtMatU.getText());

            Alocacao aloc = new Alocacao(txtDescr.getText(), dateComeco, (horaSeparadas[0] + ":00"), (horaSeparadas[1] + ":00"), dateFinal, matriculaU);
            AlocacaoDAO dao = new AlocacaoDAO();
            dao.adicionar(aloc);
            labelAtualizacao.setText("Alocação Realizada!");
        }
    }
    @FXML public void acaoBuscar(){
        if (alertaCamposB() == false){
            /*String[] dataString = txtData.getText().split("/");
            LocalDate dateC = LocalDate.of(Integer.parseInt(dataString[2]), Integer.parseInt(dataString[1]), Integer.parseInt(dataString[0]));
            Date dateComeco = (Date) Date.from(dateC.atStartOfDay(ZoneId.systemDefault()).toInstant());

            Date dateFinal = dateComeco;
            if (mesmoDia.isSelected() == true){
                dateFinal = dateComeco;
            }else{
                String[] duraString = txtDuracao.getText().split("/");
                LocalDate dateF = LocalDate.of(Integer.parseInt(duraString[2]), Integer.parseInt(duraString[1]), Integer.parseInt(duraString[0]));
                dateFinal = (Date) Date.from(dateF.atStartOfDay(ZoneId.systemDefault()).toInstant());
            }*/
            int idUser = Integer.parseInt(txtMatU.getText());
            AlocacaoDAO dao = new AlocacaoDAO();
            Alocacao aloc = dao.buscarAloc(txtData.getText(), txtDuracao.getText(), idUser);
            txtDescr.setText(aloc.getDescricao());
            //txtNumS.setText("");
            txtData.setText("" + aloc.getData());
            txtHorario.setText("" + aloc.getHora());
            txtDuracao.setText("" + aloc.getDuracao());
            txtMatU.setText("" + aloc.getIdUser());
            //maisSalas.setSelected(false);
            //mesmoDia.setSelected(false);
        }
    }
    @FXML public void acaoExcluir(){
        
    }
    @FXML public void acaoAtualizar(){
        
    }
}
