package gsalasfxml.TelasCadastros;

import gsalasfxml.TelasCadastros.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TelaCadastroUsuarioFXML extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("TelaCadastroUsuario.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setTitle("Cadastrar/Editar Usuários");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
