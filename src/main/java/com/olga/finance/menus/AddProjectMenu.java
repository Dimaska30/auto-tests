package com.olga.finance.menus;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.olga.finance.pages.ProjectPage;

import java.util.List;

public class AddProjectMenu {
    private WebDriver driver;
    private ProjectPage parent;

    public AddProjectMenu(WebDriver driver, ProjectPage parent){
        this.driver=driver;
        this.parent=parent;
    }

    private By title = By.xpath("");

    private By projectName_field = By.xpath("");

    private By Client_field  = By.xpath("");
    private By Client_field_values = By.xpath("#menu- > div.MuiPaper-root.MuiPaper-elevation.MuiPaper-rounded.MuiPaper-elevation1.MuiPaper-root.MuiMenu-paper.MuiPaper-elevation.MuiPaper-rounded.MuiPaper-elevation8.MuiPopover-paper.css-177ic5c > ul > li");
    
    private By color_label_field = By.xpath("");
    private By color_label_field_values = By.xpath("");

    private By save_button = By.xpath("");

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
            }
        }
        colors.get(index).click();
        return true;
    }

    public ProjectPage clickSave(){
        driver.findElement(save_button).click();
        return parent;
    }
}
