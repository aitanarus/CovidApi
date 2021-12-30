
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;

public class CountriesStatsController {

    @FXML
    public Label country;
    @FXML
    public Label continent;
    @FXML
    private Label newCases;
    @FXML
    public Label recovered;
    @FXML
    public Label total;
    @FXML
    public Label critical;
    @FXML
    public Label active;

    @FXML
    private Label date;
    @FXML
    private Label update;

    private ViewHandler vh;
    private CountryModel countryModel;

    public void init(ViewHandler vh, CountryModel countryModel) {
        this.vh = vh;
        this.countryModel = countryModel;

        try {
            JSONObject stats = APIConnector.getInstance().getCountryStats(countryModel.getCountry());
            JSONObject cases = APIConnector.getInstance().getCases(countryModel.getCountry());
            System.out.println(cases);
            try {
                date.setText(stats.getString("day"));
                update.setText(stats.getString("time"));
                newCases.setText(cases.getString("new"));
                recovered.setText(String.valueOf(cases.getInt("recovered")));
                total.setText(String.valueOf(cases.getInt("total")));
                critical.setText(String.valueOf(cases.getInt("critical")));
                active.setText(String.valueOf(cases.getInt("active")));
                country.setText(stats.getString("country"));
                continent.setText(stats.getString("continent"));
            } catch (JSONException e){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Incomplete Information");
                alert.showAndWait();
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void back() {
        vh.openCountriesListScene();
    }

    public void refresh(ActionEvent actionEvent) {
        init(vh, countryModel);
    }
}
