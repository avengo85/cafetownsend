package cafe.Pages;

import cafe.framework.BasePage;
import cafe.framework.BaseTest;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class EmployeesPage extends BasePage {
    Actions actions = new Actions(driver);
    public ExtentTest test = BaseTest.test;

    private static final String CREATE_BUTTON_XPATH = "//a[@id='bAdd']";
    private static final String EDIT_BUTTON_XPATH = "//a[@id='bEdit']";
    private static final String DELETE_BUTTON_XPATH = "//a[@id='bDelete']";
    private static final String LOGOUT_BUTTON_XPATH = "//p[@ng-click='logout()']";
    private static final String GREETINGS_XPATH = "//p[@id='greetings']";

    @FindBy(xpath = CREATE_BUTTON_XPATH)
    private WebElement createButton;

    @FindBy(xpath = EDIT_BUTTON_XPATH)
    private WebElement editButton;

    @FindBy(xpath = DELETE_BUTTON_XPATH)
    private WebElement deleteButton;

    @FindBy(xpath = LOGOUT_BUTTON_XPATH)
    private WebElement logoutButton;

    @FindBy(xpath = GREETINGS_XPATH)
    private WebElement greetingsElement;


    public String getGreetingsText() {
        String text = greetingsElement.getText();
        test.log(LogStatus.INFO, "Greetings: " + text);
        return text;
    }

    public LoginPage loggingOut() {
        test.log(LogStatus.INFO, "Logging out...");
        actions.moveToElement(logoutButton).click().build().perform();
        return initPage(LoginPage.class);
    }

    private WebElement employee(String firstLast) {
        return driver.findElement(By.xpath("//body//li[contains(text(), '" + firstLast + "')]"));
    }

    public void selectEmployee(String firstLast) {
        test.log(LogStatus.INFO, "Selecting the employee " + firstLast + "...");
        employee(firstLast).click();
    }

    public CreateEmployeePage clickCreateButton() {
        test.log(LogStatus.INFO, "Clicking 'Create' button...");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", createButton);
        return initPage(CreateEmployeePage.class);
    }

    public void clickDeleteButtonAndAccept() {
        test.log(LogStatus.INFO, "Clicking 'Delete' button...");
        actions.moveToElement(deleteButton).click().build().perform();
        test.log(LogStatus.INFO, "Accepting alert...");
        driver.switchTo().alert().accept();
    }
}
