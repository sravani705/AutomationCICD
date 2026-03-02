package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/java/cucumber",glue="sravaniprojects.stepDefinitions",
monochrome=true,tags="@ErrorValidation",plugin={"html:target/cucumber.html"})

//monochrome=true -to print the results in readable format
public class TestNGTestRunner extends AbstractTestNGCucumberTests {

}
