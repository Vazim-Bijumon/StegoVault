
package stegovault;

import stegovault.core.KeyManager;
import stegovault.core.SessionManager;
import stegovault.core.StegoVaultService;
import stegovault.ui.MainWindow;
import stegovault.ui.SplashScreen;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {

        stage.getIcons().add(
                new Image(
                        getClass().getResourceAsStream("/images/icon.png")
                )
        );

        SplashScreen.show(stage, () -> {

            var sessionKey = KeyManager.generateSessionKey();
            var sessionManager = new SessionManager(sessionKey);
            var service = new StegoVaultService(sessionManager);

            Scene mainScene = new MainWindow(service).createScene();

            stage.setScene(mainScene);
            stage.setTitle("StegoVault");
            stage.setMinWidth(1000);
            stage.setMinHeight(650);
            stage.setWidth(1200);
            stage.setHeight(750);
            stage.centerOnScreen();
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}

