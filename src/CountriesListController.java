import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import org.json.JSONArray;

import java.sql.SQLException;

public class CountriesListController {
    @FXML
    ComboBox countriesList;

    private ViewHandler vh;
    private CountryModel countryModel;

    public void init(ViewHandler vh, CountryModel countryModel) {
        this.vh = vh;
        this.countryModel = countryModel;

            JSONArray array = APIConnector.getInstance().getCountries();
            for (int i = 0; i < array.length(); i++) {
                countriesList.getItems().add(array.get(i));
            }

    }

    public void search() {
        countryModel.setCountry((String) countriesList.getSelectionModel().getSelectedItem());
        vh.openCountriesStatsScene();
    }
}
