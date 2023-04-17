package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignUpPage {
    private WebDriver driver;

    public SignUpPage(WebDriver driver){
        this.driver=driver;
    }
    private By grey = By.cssSelector("");
//    private String getGreyText(){
//        return this.driver.findElement(grey);
//    }
}
