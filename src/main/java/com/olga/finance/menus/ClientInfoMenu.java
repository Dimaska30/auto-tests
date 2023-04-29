package com.olga.finance.menus;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.olga.finance.pages.TeamPage;

public class ClientInfoMenu {
    private WebDriver driver;

    public ClientInfoMenu(WebDriver driver) {
        this.driver = driver;
    }

    private By title = By.className(".AdminEditProfileModal_titleFont__oRnJC");
    private By editButton = By.xpath("/html/body/div[2]/div[3]/div/div/div[1]/div[3]/button");
    private By expertise_field = By
            .xpath("/html/body/div[2]/div[3]/div/div/div[2]/div/div[2]/div[4]/div[2]/div/div/select");
    private By saveButton = By.xpath("/html/body/div[2]/div[3]/div/div/div[3]/button[2]");

    public String getTitle() {
        return driver.findElement(title).getText();
    }

    public boolean selectExpertise(String expertise) {
        return selectSelectField(expertise_field, expertise);
    }

    public boolean clickEdit() {
        driver.findElement(editButton).click();
        return true;
    }

    public TeamPage clickSave() {
        driver.findElement(saveButton).click();
        return new TeamPage(driver);
    }

    private boolean selectSelectField(By path, String value) {
        WebElement selectElement = driver.findElement(path);
        Select select = new Select(selectElement);
        select.selectByVisibleText(value);
        return true;
    }
}