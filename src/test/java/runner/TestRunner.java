package runner;

import org.junit.runner.RunWith;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features ="src/main/resources/feature",
		glue= {"com.howoldareyou.step"},
		tags = ""
		)
public class TestRunner extends AbstractTestNGCucumberTests {
}