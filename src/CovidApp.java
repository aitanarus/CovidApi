import javafx.application.Application;
import javafx.stage.Stage;

public class CovidApp extends Application {
    @Override
    public void start(Stage stage){
        ModelFactory mf = new ModelFactory();
        ViewHandler vh = new ViewHandler(mf);
        vh.start();
    }
}
