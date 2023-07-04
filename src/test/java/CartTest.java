import org.example.models.UserData;
import org.example.steps.LoginSteps;
import org.example.steps.ProductsSteps;
import org.example.utils.JsonReader;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {

    LoginSteps loginSteps = new LoginSteps();
    ProductsSteps productsSteps = new ProductsSteps();

    @Test(dataProvider = "userData", dataProviderClass = JsonReader.class)
    public void loginTest(UserData userData) {
        loginSteps.login(userData.getUsername(), userData.getPassword());
        loginSteps.verifyLogin("Products");
    }
}
