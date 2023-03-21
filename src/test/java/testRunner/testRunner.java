package testRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "/Users/phamly/Documents/AutomationTesting/MyCucumberJavaFW/Features/BasisSearchFlow.feature",
        glue ="stepDefinitions"
)
public class testRunner {
}
