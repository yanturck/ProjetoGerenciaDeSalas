package gsalasfxml.TelasCadastros;

import javafx.scene.control.Alert;

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
    public void idNExistente(){
        Alert camposFalta = new Alert(Alert.AlertType.ERROR);
        camposFalta.setTitle("ERRO");
        camposFalta.setHeaderText("Matricula não existente");
        camposFalta.setContentText("Por favor digite uma Matricula que exista!");
        camposFalta.show();
    }
}