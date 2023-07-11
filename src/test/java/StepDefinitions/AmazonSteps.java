package StepDefinitions;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.junit.Assert;

import org.openqa.selenium.WebDriver;

import java.util.Set;


public class AmazonSteps {
     WebDriver driver;

    @Given("I am on the Amazon homepage")
    public void i_am_on_the_amazon_homepage() {
        // Write code here that turns the phrase above into concrete actions
        System.setProperty("web-driver.chrome.driver", "C://Users//Sravani//Downloads//chromedriver_win32//chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.amazon.in");
        driver.manage().window().maximize();
    }

    @When("I search for {string}")
    public void i_search_for(String searchTerm) {
        // Write code here that turns the phrase above into concrete actions
        WebElement searchBox = driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
        searchBox.click();
        searchBox.sendKeys(searchTerm);
        searchBox.submit();
    }

    @When("I click on a product")
    public void i_click_on_a_product() {
        // Write code here that turns the phrase above into concrete actions
        WebElement firstProduct = driver.findElement(By.xpath("(//div[@data-component-type='s-search-result'])[1]//h2/a"));
        firstProduct.click();

    }

    @Then("I verify the product details")
    public void i_verify_the_product_details() {
        // Write code here that turns the phrase above into concrete actions
        String parentHandle = driver.getWindowHandle();
        Set<String> handles = driver.getWindowHandles();
        for (String handle : handles) {
            if (!handle.equals(parentHandle)) {
                driver.switchTo().window(handle);
                break;
            }
        }

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 500)");
        String color = driver.findElement(By.xpath("//body/div[@id='a-page']/div[@id='dp']/div[@id='dp-container']/div[@id='centerCol']/div[@id='twister_feature_div']/div[@id='twisterContainer']/div[1]/form[1]/div[1]/div[1]/span[1]")).getText();
        String size = driver.findElement(By.xpath("//body/div[@id='a-page']/div[@id='dp']/div[@id='dp-container']/div[@id='centerCol']/div[@id='twister_feature_div']/div[@id='twisterContainer']/div[1]/form[1]/div[2]/div[1]/span[1]")).getText();
        String brand = driver.findElement(By.xpath("//body[1]/div[4]/div[2]/div[3]/div[11]/div[49]/div[1]/table[1]/tbody[1]/tr[1]/td[2]/span[1]")).getText();

        Assert.assertEquals("128 GB", size);
        Assert.assertEquals("Blue", color);
        Assert.assertEquals("Apple", brand);

        driver.quit();
        }
}
