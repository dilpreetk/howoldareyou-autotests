package com.howoldareyou.step;

import com.howoldareyou.core.step.AbstractStepDefinition;
import com.howoldareyou.page.MainPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;

import java.time.LocalDate;
import java.time.Period;
import java.time.chrono.IsoChronology;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class MainStepDefinition extends AbstractStepDefinition {

    private final MainPage homePage = new MainPage();

    @Given("^I am on the age determination website$")
    public void openHomePage() {
        homePage.open();
    }

    @When("^I type \"([^\"]*)\" into username field$")
    public void typeUserName(String username) {
        homePage.typeUsername(username);
    }

    @When("^I type \"([^\"]*)\" into birthday field$")
    public void typeBirthday(String birthday) {
        homePage.typeBirthday(birthday);
    }

    @When("^I type random user name")
    public void typeRandomUserName() {
        typeUserName(RandomStringUtils.randomAlphabetic(10));
    }

    @When("^I type random date of birth")
    public void typeRandomDateOfBirth() {
        typeBirthday(randomDateOfBirth);
    }

    @When("^I click submit button$")
    public void clickSubmitButton() {
        homePage.clickSubmitButton();
    }

    @Then("^I see \"(\\d+)\" result age$")
    public void verifyAge(int expectedAge) {
        Assert.assertEquals(homePage.getResultAgeText(), expectedAge);
    }

    @Then("^I see the correct age result$")
    public void verifyAge() {
        verifyAge(expectedAge);
    }
    
    @When("I type todays date and random year")
    public void todaysDateRandomYear() {
    	  typeBirthday(currentDateRandomYear);
    }
    
    @When("I type {string} in username")
    public void addUsername(String name) {
    	typeUserName(name);
    }
    
    @Then("^I see the correct age result for today date and random year")
    public void verifyAgeForOnlyRandomYear() {
        verifyAge(expectedAgeForOnlyRandomYear);
    }
    
    @Then("I see the correct {string}")
    public void verifyName(String name) {
    	Assert.assertEquals(name, homePage.getNametext());
    } 

    public String randomDateOfBirth = getRandomDateOfBirth();
    public int expectedAge = determineAge(randomDateOfBirth);
    public String currentDateRandomYear = getTodaysDateRandomMonth();
    public int expectedAgeForOnlyRandomYear=determineAge(currentDateRandomYear);

    public static String getRandomDateOfBirth() {
        Random random = new Random();
        int minYear = 1900;

        int currentYear = LocalDate.now().getYear();
        int randomYear = random.nextInt(currentYear - minYear + 1) + minYear;
        int randomDayOfYear = random.nextInt(IsoChronology.INSTANCE.isLeapYear(randomYear) ? 366 : 365) + 1;

        LocalDate randomDateOfBirth = LocalDate.ofYearDay(randomYear, randomDayOfYear);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");

        return randomDateOfBirth.format(formatter);
    }

    public static int determineAge(String dateOfBirth) {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
        LocalDate birthDate = LocalDate.parse(dateOfBirth, formatter);
        return Period.between(birthDate, currentDate).getYears();
    }
    
    public static String getTodaysDateRandomMonth()
    {
    	Random random = new Random();
        int minYear = 1900;
        int currentYear = LocalDate.now().getYear();
        int randomYear = random.nextInt(currentYear - minYear + 1) + minYear;
        LocalDate todayDate = LocalDate.now();
        LocalDate todayDateRandomYear = todayDate.withYear(randomYear);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
        return todayDateRandomYear.format(formatter);
    }

}
