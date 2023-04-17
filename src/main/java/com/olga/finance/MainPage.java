package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage {
    private WebDriver driver;

    public  MainPage(WebDriver driver){
        this.driver = driver;
    }

    private By signInButton = By.cssSelector(".HeaderMenu-link--sign-in");

    private By signUpButton = By.xpath("/html/body/div[2]/div[1]/header/div/div[2]/div/div/a");

    public LoginPage clickSignInButton(){
        driver.findElement( signInButton).click();
        return new LoginPage(driver);
    }

    public SignUpPage clickSignUpButton() {
        driver.findElement(signUpButton).click();
        return new SignUpPage(driver);
    }
}
