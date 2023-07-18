import org.example.models.UserData;
import org.example.steps.LoginCucumberSteps;
import org.example.utils.JsonReader;
import org.testng.annotations.Test;

public class LoginCucumberTest {

    LoginCucumberSteps loginCucumberSteps = new LoginCucumberSteps();

    @Test(dataProvider = "userData", dataProviderClass = JsonReader.class)
    public void loginTest(UserData userData) {
        loginCucumberSteps.navigateToLoginPage();
        loginCucumberSteps.enterUsernameAndPassword(userData.getUsername(), userData.getPassword());
        loginCucumberSteps.verifyProductsPageTitle("Products");
    }
}
