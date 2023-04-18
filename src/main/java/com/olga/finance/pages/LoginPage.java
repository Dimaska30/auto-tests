package com.olga.finance.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;
    public LoginPage(WebDriver driver){
        this.driver=driver;
    }
    private By signinButton = By.xpath("form/div/div[5]/button");

    private By EmailField = By.xpath("form/div/div[1]/div/div/input");

    private By passwordField = By.xpath("form/div/div[2]/div/div/input");

    public boolean writeEmail(String email) {
        driver.findElement(EmailField).sendKeys("value",email);
        return true;
    }
    public boolean writePassword(String password) {
        driver.findElement(passwordField).sendKeys("value",password);
        return true;
    }
    public MainPage clickSignInButton(){
        driver.findElement(signinButton).click();
        return new MainPage(driver);
    }
}
