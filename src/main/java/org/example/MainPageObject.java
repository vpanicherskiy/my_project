package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.enums.FaqList;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.example.config.Config.URL;
import static org.junit.Assert.assertTrue;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MainPageObject {
    WebDriver driver;
    String dropDownElement;
    String dropDownText;
    String expected;

    public MainPageObject(FaqList faqList, WebDriver driver) {
        this.driver = driver;
        driver.get(URL);
        this.dropDownElement = faqList.getButtonIndex();
        this.dropDownText = faqList.getElementIndex();
        this.expected = faqList.getExpected();
    }

    private By firstOrderButton = By.className("Button_Button__ra12g");
    private By secondOrderButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    public MainPageObject clickDropdownButton() {
        WebElement element = driver.findElement(By.xpath(".//*[@id='accordion__heading-" + dropDownElement + "']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        element.click();
        return this;
    }

    public void checkDropDownElementText() {
        boolean isDisplayed = driver.findElement(By.xpath(".//*[@id='accordion__panel-" + dropDownText + "']/p")).isDisplayed();
        assertTrue(isDisplayed);
        String elementText = driver.findElement(By.xpath(".//*[@id='accordion__panel-" + dropDownText + "']/p")).getText();
        Assert.assertEquals(expected, elementText);
    }
}
