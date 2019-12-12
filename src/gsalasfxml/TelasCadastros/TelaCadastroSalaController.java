package gsalasfxml.TelasCadastros;

import gsalasfxml.PojoDao.Sala;
import gsalasfxml.PojoDao.SalasDAO;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;

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
    @FXML private Label labelCamposObrigatorios;
    @FXML private Label labelAtualizacao;
    @FXML private ToggleGroup grupoTipoS;
    @FXML private ToggleGroup grupoAsa;
    @FXML private RadioButton rbNorte;
    @FXML private RadioButton rbSul;
    @FXML private RadioButton rbComum;
    @FXML private RadioButton rbMnAud;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       /*btnLimpar.setOnMouseClicked((MouseEvent e)->{
       acaoLimpar();
    });
       btnSalvar.setOnMouseClicked((MouseEvent s)->{
       acaoSalvar();
       acaoLimpar();
    });
       btnExcluir.setOnMouseClicked((MouseEvent s)->{
       acaoExcluir();
       acaoLimpar();
    });*/
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
        boolean test1 = false;
        boolean test2 = false;
        if (rbNorte.isSelected() == false && rbSul.isSelected() == false){
            test1 = true;
        }
        if (rbComum.isSelected() == false && rbMnAud.isSelected() == false){
            test2 = true;
        }
        
        if (txtNumS.getText() != "" && test1 == false && test2 == false){
            tmp = false;
        }else{
            Alertas alerteC = new Alertas();
            tmp = alerteC.camposFaltante();
        }
        return tmp;
    }
    public Sala organizando(){
        String idSala;
        if(rbSul.isSelected() == true){
            idSala = txtNumS.getText() + "S";
        }else {
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
        if (alertas() == false){
            SalasDAO dao = new SalasDAO();
            Sala sala = organizando();
            if (dao.existe(sala.getIdSala()) == true){
                Alertas alert = new Alertas();
                alert.idExistente();
            }else{
                dao.adicionar(sala);
                labelAtualizacao.setText("Salvo com Sucesso!!");
                acaoLimpar();
            }
        }
    }
    @FXML public void acaoExcluir(){
        boolean aux1 = alertas();
        if (aux1 == false){
            RadioButton rAsa = (RadioButton) grupoAsa.getSelectedToggle();
            
            Alert confirmarExcluir = new Alert(Alert.AlertType.CONFIRMATION);
            ButtonType btnOk = new ButtonType("OK");
            ButtonType btnCan = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);
            
            confirmarExcluir.setTitle("CONFIRMAÇÃO");
            confirmarExcluir.setHeaderText("CONFIRMAR EXCLUSÃO");
            confirmarExcluir.setContentText("Tem certeza que deseja excluir?\n" + txtNumS.getText() + "-" + rAsa.getText());
            
            confirmarExcluir.getButtonTypes().setAll(btnOk, btnCan);
            confirmarExcluir.showAndWait().ifPresent(b -> {
                if (b == btnOk){
                    SalasDAO dao = new SalasDAO();
                    dao.excluir(organizando());
                    labelAtualizacao.setText("Exclusão Realizada!");
                    acaoLimpar();
                }else{
                    labelAtualizacao.setText("Operação Cancelada");
                }
            });
        }
    }
}