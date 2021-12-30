import java.rmi.RemoteException;

public class ModelFactory {

    private CountryModel countryModel;

    public ModelFactory() {
        countryModel = new CountryModel();
    }

    public CountryModel getCountryModel() {
        return countryModel;
    }
}
