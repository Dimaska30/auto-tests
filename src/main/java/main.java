import org.example.MainPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class main {
    public static void main(String[] args) {
            System.setProperty("webdriver.chromdriver", "D:\\tpo\\chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            WebDriver driver = new ChromeDriver(options);
            driver.get("https://olga-finance.effective.band/");
            MainPage page = new MainPage(driver);
    }
}
