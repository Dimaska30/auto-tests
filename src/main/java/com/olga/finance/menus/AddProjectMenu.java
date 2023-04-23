package com.olga.finance.menus;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.olga.finance.pages.ProjectPage;

import java.util.List;

public class AddProjectMenu {
    private WebDriver driver;

    public AddProjectMenu(WebDriver driver){
        this.driver=driver;
    }

    private By title = By.xpath("//div[@role=\"presentation\"]/div[3]/div/div/div/div[1]/div");

    private By projectName_field = By.xpath("//div[@role=\"presentation\"]/div[3]/div/div/div/div[2]/div[2]/div/div/input");

    private By Client_field  = By.xpath("//div[@id=\"standard-basic\"]");

    private By Client_field_values = By.xpath("//ul[@role=\"listbox\"]/li");
    
    private By color_label_field = By.xpath("//div[@role=\"presentation\"]/div[3]/div/div/div/div[4]/div[2]/div/div");
    private By color_label_field_values = By.xpath("//ul[@role=\"listbox\"]/li");

    private By save_button = By.cssSelector("button.AddProjectModal_save__Qm4oD");

    public String getTitle(){
        return driver.findElement(title).getText();
    }

    public boolean enterProjectName(String projectName){
        driver.findElement(projectName_field).sendKeys(projectName);
        return true;
    }

    public boolean enterClient(String client){
        driver.findElement(Client_field).click();
        List<WebElement> clients = driver.findElements(Client_field_values);
        int index = 0;
        for (int i=0; i<clients.size(); i++){
            if(clients.get(i).getText().equals(client)){
                index = i;
                break;
            }
        }
        clients.get(index).click();
        return true;
    }

    public boolean enterColor(String color){
        driver.findElement(color_label_field).click();
        List<WebElement> colors = driver.findElements(color_label_field_values);
        int index = 0;
        for(int i=0; i<colors.size(); i++) {
            if(colors.get(i).getText().equals(color)){
                index = i;
                break;
            }
        }
        colors.get(index).click();
        driver.findElement(title).click();
        return true;
    }

    public ProjectPage clickSave(){
        driver.findElement(title).click();
        driver.findElement(save_button).click();
        return new ProjectPage(driver);
    }
}
