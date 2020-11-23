import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;

public class Main {

    public static WebDriver driver = null;

    public static void main(String[] args) {
        System.out.println("Buying a product from Amazon India, Wouhou !");
        System.setProperty("webdriver.chrome.driver","driver/chromedriver.exe");
        driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver,30);
        goToAmazonPickProduct(wait);
        switchSecondWindow();
        driver.findElement(By.id("buy-now-button")).click();
        signInToAmazon(wait);

    }
    public static void goToAmazonPickProduct(WebDriverWait wait) {
        driver.navigate().to("https://www.amazon.in");
        WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
        searchBox.sendKeys("bag");
        driver.findElement(By.id("nav-search-submit-text")).click();
        System.out.println(driver.findElement(By.id("nav-search-submit-text")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div[2]/div[1]/div[2]/div/span[3]/div[2]/div[4]")));
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[1]/div[2]/div/span[3]/div[2]/div[4]")).click();
    }
    public static void switchSecondWindow() {
        System.out.println("All windows open " + driver.getWindowHandles());
        System.out.println("Current window " + driver.getWindowHandle());
        Set<String> allWindowHandles = driver.getWindowHandles();
        int index = 0;
        for(String handle : allWindowHandles) {
            if (index == 1){
                driver.switchTo().window(handle); //Switch to the second window
            }
            index++;
        }
        System.out.println("Current window after switch " + driver.getWindowHandle());
    }
    public static void signInToAmazon(WebDriverWait wait) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ap_email")));
        WebElement phoneNumber = driver.findElement(By.id("ap_email"));
        phoneNumber.sendKeys("24681998");
        driver.findElement(By.id("continue")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ap_password")));
        WebElement password = driver.findElement(By.id("ap_password"));
        password.sendKeys("nourkaroui");
        driver.findElement(By.id("signInSubmit")).click();
    }
}
