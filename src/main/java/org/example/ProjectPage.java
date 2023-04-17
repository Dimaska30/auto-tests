package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import java.util.ArrayList;
import java.util.List;

public class ProjectPage {
    private WebDriver driver;
    public ProjectPage(WebDriver driver){
        this.driver=driver;
    }
    private By tableRows = By.cssSelector(".MuiTableRow-root.MuiTableRow-hover.TableProjects_tableRow__JJj-I.TableProjects_tableRowOnClick__Ypa5S.css-1wonh2x");

    public List<WebElement> getProjects(){
        return new ArrayList<WebElement>();
    }


}
