package org.example;

import org.example.enums.Users;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.example.config.Config.URL;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertTrue;

public class OrderPageObject {
    MainPageObject mainPageObject = new MainPageObject();
    WebDriver driver;
    String firstName;
    String lastName;
    String address;
    String phoneNumber;
    String date;
    String comment;

    public OrderPageObject(WebDriver driver, Users users) {
        this.driver = driver;
        driver.get(URL);
        this.firstName = users.getFirstName();
        this.lastName = users.getLastName();
        this.address = users.getAddress();
        this.phoneNumber = users.getPhoneNumber();
        this.date = users.getDate();
        this.comment = users.getComment();
    }

    private final By inputFirstName = By.cssSelector("div.Order_Form__17u6u > div:nth-child(1) > input");
    private final By inputLastName = By.cssSelector("div.Order_Form__17u6u > div:nth-child(2) > input");
    private final By inputAddress = By.cssSelector("div.Order_Form__17u6u > div:nth-child(3) > input");
    private final By inputPhoneNumber = By.cssSelector("div.Order_Form__17u6u > div:nth-child(5) > input");
    private final By metroDropDownInput = By.className("select-search__value");
    private final By dropDownListElement = By.xpath(".//li[@class='select-search__row']");
    private final By nextButton = By.cssSelector("div.Order_NextButton__1_rCA > button");
    private final By calendar = By.xpath(".//div[@class='react-datepicker__input-container']/input");
    private final By rentalPeriodInput = By.cssSelector(".Dropdown-control");
    private final By dropDownRentalPeriodMenu = By.xpath(".//div[@class='Dropdown-menu']/div[text()='сутки']");
    private final By checkBoxColor = By.xpath(".//input[@id='black']");
    private final By commentInput = By.cssSelector("div.Order_Form__17u6u > div.Input_InputContainer__3NykH > input");
    private final By createOrderButton = By.cssSelector("div.Order_Buttons__1xGrp > button:nth-child(2)");
    private final By yesButton = By.cssSelector("div.Order_Modal__YZ-d3 > div.Order_Buttons__1xGrp > button:nth-child(2)");
    private final By orderLabel = By.xpath(".//div[@class='Order_ModalHeader__3FDaJ' and text()='Заказ оформлен']");

    public OrderPageObject clickOrderButton(int numberOfButton) {
        switch (numberOfButton) {
            case 1: {
                driver.findElement(mainPageObject.getFirstOrderButton()).click();
                break;
            }
            case 2: {
                WebElement element = driver.findElement(mainPageObject.getSecondOrderButton());
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
                element.click();
                break;
            }
            default:
                driver.findElement(mainPageObject.getFirstOrderButton()).click();
        }
        return this;
    }

    public OrderPageObject fillingUserForm() {
        driver.findElement(inputFirstName).sendKeys(firstName);
        driver.findElement(inputLastName).sendKeys(lastName);
        driver.findElement(inputAddress).sendKeys(address);
        driver.findElement(inputPhoneNumber).sendKeys(phoneNumber);

        driver.findElement(metroDropDownInput).click();
        driver.findElement(dropDownListElement).click();
        driver.findElement(nextButton).click();
        return this;
    }

    public OrderPageObject fillingRentForm() {
        driver.findElement(rentalPeriodInput).click();
        driver.findElement(dropDownRentalPeriodMenu).click();

        driver.findElement(checkBoxColor).click();
        driver.findElement(commentInput).sendKeys(comment);
        driver.findElement(calendar).sendKeys(date);

        return this;
    }

    public OrderPageObject clickCreateOrderButton() {
        driver.findElement(createOrderButton).click();
        return this;
    }

    public OrderPageObject confirmationOrder() {
        driver.findElement(yesButton).click();
        return this;
    }

    public void createdOrderLabel() {
        boolean isDisplayed = driver.findElement(orderLabel).isDisplayed();
        assertTrue(isDisplayed);
        String labelText = driver.findElement(orderLabel).getText();
        MatcherAssert.assertThat("Заказ не создался", labelText, startsWith("Заказ оформлен"));
    }
}
