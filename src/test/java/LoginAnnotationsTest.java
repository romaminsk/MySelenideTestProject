import org.example.annotations.TestData;
import org.example.models.UserData;
import org.example.steps.LoginSteps;
import org.example.utils.DataProviders;
import org.testng.annotations.Test;

public class LoginAnnotationsTest extends BaseTest {

    LoginSteps loginSteps = new LoginSteps();

    @TestData(path = "src/test/resources/test-data/", modelType = "UserData")
    @Test(dataProviderClass = DataProviders.class, dataProvider = "getData")
    public void loginAnnotationsTest(UserData userData) {
        loginSteps.login(userData.getUsername(), userData.getPassword());
        loginSteps.verifyLogin("Products");
    }
}
