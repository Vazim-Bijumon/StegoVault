
package stegovault.ui;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.ParallelTransition;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SplashScreen {

    public static void show(Stage stage, Runnable onFinished) {

        Image logoImage = new Image(
                SplashScreen.class.getResourceAsStream("/images/logo.png")
        );

        ImageView logo = new ImageView(logoImage);
        logo.setFitWidth(100);
        logo.setFitHeight(100);
        logo.setPreserveRatio(true);
        logo.setOpacity(0);
        logo.setScaleX(0.94);
        logo.setScaleY(0.94);

        Label title = new Label("StegoVault");
        title.setStyle(
                "-fx-font-size: 27px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #F1F5F9;"
        );
        title.setOpacity(0);

        VBox content = new VBox(14, logo, title);
        content.setAlignment(Pos.CENTER);
        content.setPrefSize(1200, 750);
        content.setStyle("-fx-background-color: #101827;");

        Scene splashScene = new Scene(content);
        splashScene.setFill(Color.web("#101827"));

        stage.setScene(splashScene);
        stage.setTitle("StegoVault");
        stage.setWidth(1200);
        stage.setHeight(750);
        stage.centerOnScreen();
        stage.show();

        FadeTransition logoFade =
                new FadeTransition(Duration.millis(550), logo);
        logoFade.setFromValue(0);
        logoFade.setToValue(1);

        ScaleTransition logoScale =
                new ScaleTransition(Duration.millis(650), logo);
        logoScale.setFromX(0.94);
        logoScale.setFromY(0.94);
        logoScale.setToX(1);
        logoScale.setToY(1);

        FadeTransition titleFade =
                new FadeTransition(Duration.millis(450), title);
        titleFade.setFromValue(0);
        titleFade.setToValue(1);
        titleFade.setDelay(Duration.millis(250));

        SequentialTransition animation =
                new SequentialTransition(
                        new PauseTransition(Duration.millis(100)),
                        new ParallelTransition(logoFade, logoScale),
                        titleFade,
                        new PauseTransition(Duration.millis(350))
                );

        animation.setOnFinished(event -> {
            FadeTransition fadeOut =
                    new FadeTransition(
                            Duration.millis(250), content
                    );

            fadeOut.setFromValue(1);
            fadeOut.setToValue(0);

            fadeOut.setOnFinished(e -> onFinished.run());
            fadeOut.play();
        });

        animation.play();
    }
}

