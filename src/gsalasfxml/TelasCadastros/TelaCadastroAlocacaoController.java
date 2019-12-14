package gsalasfxml.TelasCadastros;

import gsalasfxml.PojoDao.Alocacao;
import gsalasfxml.PojoDao.AlocacaoDAO;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class
 *
 * @author Yan Rocha
 */
public class TelaCadastroAlocacaoController implements Initializable {
    @FXML private Button btnLimpar;
    @FXML private Button btnSalvar;
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
    @FXML private RadioButton mesmoDia;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
        String numS = txtNumS.getText();
        
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
        mesmoDia.setSelected(false);
    }
    @FXML public void acaoSalvar(){
        if (alertaCampos() == false){
            String[] dataString = txtData.getText().split("/");
            String dateComeco = dataString[2] + "-" + dataString[1] + "-" + dataString[0];
            String dateFinal = dateComeco;
            if (mesmoDia.isSelected() == true){
                dateFinal = dateComeco;
            }else{
                String[] dataString1 = txtDuracao.getText().split("/");
                dateFinal = dataString1[2] + "-" + dataString1[1] + "-" + dataString1[0];
            }

            String horasString = txtHorario.getText();
            String[] horaSeparadas = horasString.split("-");
            int matriculaU = Integer.parseInt(txtMatU.getText());

            Alocacao aloc = new Alocacao(txtDescr.getText(), dateComeco, (horaSeparadas[0] + ":00"), (horaSeparadas[1] + ":00"), dateFinal, matriculaU, txtNumS.getText());
            AlocacaoDAO dao = new AlocacaoDAO();
            dao.adicionar(aloc);
            int id = dao.idAloc(aloc);
            labelAtualizacao.setText("Alocação Realizada!\nProtocolo de Alocação " + id);
            acaoLimpar();
        }else{
            labelAtualizacao.setText("Alocação não Realizada!\nInforme todos os CAMPOS!");
        }
    }
}
