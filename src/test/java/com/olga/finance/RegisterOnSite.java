package com.olga.finance;

import com.olga.finance.pages.LoginPage;
import com.olga.finance.pages.MainPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import com.olga.finance.pages.LoginPage;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class RegisterOnSite {
    static MainPage registrationOnSite(String email, String password){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--headless");
        options.addArguments("--window-size=950,900");

        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.get("https://olga-finance.effective.band/");
        LoginPage page_1 = new LoginPage(driver);

        page_1.writeEmail(email);
        page_1.writePassword(password);
        MainPage page_2 = page_1.clickSignInButton();

        return page_2;
    }
}
