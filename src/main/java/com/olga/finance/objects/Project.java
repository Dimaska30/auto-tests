package com.olga.finance.objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Project {
    private WebElement element;

    public Project(WebElement element) {
        this.element = element;
    }

    public String getProjectId() {
        return element.findElement(By.xpath(".//td[0]")).getText();
    }

    public String getProjectName() {
        return element.findElement(By.xpath(".//td[1]")).getText();
    }

    public String getClient() {
        return element.findElement(By.xpath(".//td[2]")).getText();
    }

    public String getStartDate() {
        return element.findElement(By.xpath(".//td[3]")).getText();
    }

    public String getEndDate() {
        return element.findElement(By.xpath(".//td[4]")).getText();
    }

    public String getManager() {
        return element.findElement(By.xpath(".//td[5]")).getText();
    }

    public String getContractStatus() {
        return element.findElement(By.xpath(".//td[6]")).getText();
    }

    public String getcolumn(int i) throws IllegalArgumentException {
        if(i >= 0 && i <7){
            return element.findElement(By.xpath(".//td["+i+"]")).getText();
        } else {
            throw new IllegalArgumentException("Wrong column number");
        }
    }
}