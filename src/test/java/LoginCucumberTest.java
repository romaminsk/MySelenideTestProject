import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/main/java/org/example/features/Login.feature",
        glue = "org.example.steps",
        tags = "@SmokeTest",
        plugin = "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
)
public class LoginCucumberTest extends AbstractTestNGCucumberTests {
}
