package gsalasfxml.TelasCadastros;

import gsalasfxml.PojoDao.UsuariosDAO;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

/**
 *
 * @author Yan Rocha
 */
public class Alertas {

    public boolean camposFaltante(){
        Alert camposFalta = new Alert(Alert.AlertType.INFORMATION);
        camposFalta.setTitle("ATENÇÃO!!");
        camposFalta.setHeaderText("CAMPOS FALTANDO");
        camposFalta.setContentText("Por favor preencha todos os campos obrigatórios!");
        camposFalta.show();
        return true;
    }
    
    public void idExistente(){
        Alert camposFalta = new Alert(Alert.AlertType.ERROR);
        camposFalta.setTitle("ERRO");
        camposFalta.setHeaderText("ID já existente");
        camposFalta.setContentText("Por favor digite uma Matricula ou Sala que não exista!");
        camposFalta.show();
    }
}
