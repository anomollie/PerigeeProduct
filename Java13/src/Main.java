import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    private double offsetX = 0;
    private double offsetY = 0;

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Parent root = new BaseWindow();
        primaryStage.setTitle("IOT Example");
        Scene primaryScene = new Scene(root, 1280, 720);
        primaryScene.getStylesheets().add("style.css");
        root.setOnMouseClicked(e -> {
            offsetX = e.getSceneX();
            offsetY = e.getSceneY();
        });
        root.setOnMouseDragged(e -> drag(primaryStage, e.getScreenX(), e.getScreenY()));
        primaryStage.setWidth(1280);
        primaryStage.setHeight(720);
        primaryStage.setScene(primaryScene);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryScene.setOnKeyPressed(e -> close(e.getCode(), primaryStage));
        primaryStage.show();
    }
    public void drag(Stage stage, double x, double y) {
        stage.setX(x - offsetX);
        stage.setY(y - offsetY);
    }
    public void close(KeyCode key, Stage primaryStage) {
        if (key == KeyCode.ESCAPE) {
            primaryStage.close();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void stop() throws Exception {
        super.stop();
       // ses.shutdownNow();
    }
}
