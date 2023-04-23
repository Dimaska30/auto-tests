package com.olga.finance.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {
    private WebDriver driver;

    public  MainPage(WebDriver driver){
        this.driver = driver;
    }

    private By projectsButtons = By.xpath("/html/body/div/div/div[1]/div/ul/div[5]/div");

    private By teamButtons = By.xpath("/html/body/div/div/div[1]/div/ul/div[6]/div");

    public ProjectPage clickProject() {
        driver.findElement(projectsButtons).click();
        return new ProjectPage(driver);
    }

    public TeamPage clickTeam() {
        driver.findElement(teamButtons).click();
        return new TeamPage(driver);
    }

    public WebDriver getDriver() {
        return driver;
    }
}
