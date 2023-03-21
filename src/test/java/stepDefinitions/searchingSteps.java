package stepDefinitions;

import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class searchingSteps {
    WebDriver driver;
    @When("Go to the MarsAir website")
    public void go_to_the_mars_air_website() {
    System.setProperty("webdriver.firefox.driver","drivers/geckodriver");
    driver = new FirefoxDriver();
    driver.get("https://marsair.recruiting.thoughtworks.net/LyPham");
    }
    @Then("There should be departure and return fields on a search form")
    public void there_should_be_departure_and_return_fields_on_a_search_form() {
        boolean departingElement = driver.findElement(By.id("departing")).isDisplayed();
        boolean returningElement = driver.findElement(By.id("returning")).isDisplayed();
        Assert.assertEquals(true,departingElement);
        Assert.assertEquals(true,returningElement);
    }
    @Then("Flights leave every six months, in July and December, both ways")
    public void flights_leave_every_six_months_in_july_and_december_both_ways() {
        List actualDepartingValues = new ArrayList();
        List actualReturningValues = new ArrayList();
        Select departingDropdown = new Select(driver.findElement(By.id("departing")));
        Select returningDropdown = new Select(driver.findElement(By.id("returning")));
        for (WebElement element: departingDropdown.getOptions()) {
            actualDepartingValues.add(element.getText());
        }
        for (WebElement element: returningDropdown.getOptions()) {
            actualReturningValues.add(element.getText());
        }
        List expectedTimeValues = new ArrayList();
        expectedTimeValues.add("Select...");
        expectedTimeValues.add("July");
        expectedTimeValues.add("December");
        expectedTimeValues.add("July (next year)");
        expectedTimeValues.add("December (next year)");
        expectedTimeValues.add("July (two years from now)");
        expectedTimeValues.add("December (two years from now)");
        for (int i = 0; i < actualDepartingValues.size(); i++) {
            Assert.assertTrue(actualDepartingValues.get(i).equals(expectedTimeValues.get(i)));
            Assert.assertTrue(actualReturningValues.get(i).equals(expectedTimeValues.get(i)));
        }
    }

    @When("Select a time for departing and returning")
    public void select_a_time_for_departing_and_returning() {
        WebElement departingDropdown =  driver.findElement(By.id("departing"));
        departingDropdown.click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        SelectItemSelectElement_func(departingDropdown,"July");

        WebElement returningDropdown = driver.findElement(By.id("returning"));
        returningDropdown.click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        SelectItemSelectElement_func(returningDropdown,"December (two years from now)");
    }

    protected void SelectItemSelectElement_func(WebElement select_element, String item_text) {
        try {
            List<WebElement> item_list = null;
            WebElement dropdown = select_element;
            Thread.sleep(1000);
            item_list = dropdown.findElements(By.tagName("option"));
            int rows_count = item_list.size();

            Boolean exist = false;
            for (WebElement cur : item_list) {
                WebElement child_span = cur;
                String str_tmp = child_span.getText();
                if (str_tmp.equals(item_text)) {
                    exist = true;
                    cur.click();
                    Thread.sleep(500);
                    break;
                }
            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    @When("Select {string} for {string}")
    public void select_for(String string, String string2) {
        Select aDropdown = null;
        if (string2 == "departing") {
            aDropdown = new Select(driver.findElement(By.id("departing")));
        } else if (string2 == "returning") {
            aDropdown = new Select(driver.findElement(By.id("returning")));
        }
        aDropdown.selectByValue(string);
    }

    @When("Click on {string} button")
    public void click_on_button(String string) {
        if(string.equals("Search")) {
            driver.findElement(By.xpath("//input[@type='submit']")).click();
        } else if (string.equals("Back")) {
            driver.findElement(By.xpath("//a[@value='Back']")).click();
        }
    }

    @Then("Verify that resulted searching should be displayed")
    public void verify_that_resulted_searching_should_be_displayed() {
        WebElement seatAvailable = driver.findElement(By.xpath("//div[@id='content']/p[1]"));
        WebElement call = driver.findElement(By.xpath("//div[@id='content']/p[2]"));

        String expectedMessage = "Seats available!Call now on 0800 MARSAIR to book!";
        String actualMessage = seatAvailable.getText()+call.getText();
        Assert.assertEquals(expectedMessage,actualMessage) ;
    }

}
