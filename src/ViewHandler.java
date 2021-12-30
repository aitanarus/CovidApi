import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewHandler {
    private Scene countriesList;
    private Scene countriesStats;
    private Stage stage;

    private ModelFactory mf;

    public ViewHandler(ModelFactory mf){
        this.mf = mf;
    }

    public void start() {
        stage = new Stage();
        openCountriesListScene();
    }

    public void openCountriesListScene() {
        FXMLLoader loader = new FXMLLoader();
        if (countriesList == null) {

            Parent root = getRoot("CountriesList.fxml", loader);
            CountriesListController controller = loader.getController();
            controller.init(this, mf.getCountryModel());
            countriesList = new Scene(root);
        }
        stage.setTitle("Countries List");
        stage.setScene(countriesList);
        stage.show();
    }

    public void openCountriesStatsScene() {
        FXMLLoader loader = new FXMLLoader();
        if (countriesStats == null) {

            Parent root = getRoot("CountriesStats.fxml", loader);
            CountriesStatsController controller = loader.getController();
            controller.init(this, mf.getCountryModel());
            countriesStats = new Scene(root);
        }
        stage.setTitle("Countries Statistics");
        stage.setScene(countriesStats);
        stage.show();
    }


    private Parent getRoot(String s, FXMLLoader loader) {
        Parent root = null;
        loader.setLocation(getClass().getResource(s));
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return root;
    }

}
