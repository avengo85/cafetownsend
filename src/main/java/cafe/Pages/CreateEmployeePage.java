package cafe.Pages;

import cafe.framework.BasePage;
import cafe.framework.BaseTest;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class CreateEmployeePage extends BasePage {

    public ExtentTest test = BaseTest.test;

    private static final String FIRST_NAME_TEXTBOX_XPATH = "//input[@ng-model='selectedEmployee.firstName']";
    private static final String LAST_NAME_TEXTBOX_XPATH = "//input[@ng-model='selectedEmployee.lastName']";
    private static final String START_DATE_TEXTBOX_XPATH = "//input[@ng-model='selectedEmployee.startDate']";
    private static final String EMAIL_TEXTBOX_XPATH = "//input[@ng-model='selectedEmployee.email']";
    private static final String SUBMIT_BUTTON_ENABLED_XPATH = "//button[@type='submit'][@ng-disabled='false'][contains(text(),'Add')]";


    @FindBy(xpath = FIRST_NAME_TEXTBOX_XPATH)
    private WebElement firstNameTextBox;

    @FindBy(xpath = LAST_NAME_TEXTBOX_XPATH)
    private WebElement lastNameTextBox;

    @FindBy(xpath = START_DATE_TEXTBOX_XPATH)
    private WebElement startDateTextBox;

    @FindBy(xpath = EMAIL_TEXTBOX_XPATH)
    private WebElement emailTextBox;

    @FindBy(xpath = SUBMIT_BUTTON_ENABLED_XPATH)
    private WebElement addButtonEnabled;

    public EmployeesPage createEmployee(String firstName, String lastName, String startDate, String email) {
        test.log(LogStatus.INFO, "Creating a new employee...");
        firstNameTextBox.sendKeys(firstName);
        lastNameTextBox.sendKeys(lastName);
        startDateTextBox.sendKeys(startDate);
        emailTextBox.sendKeys(email);
        addButtonEnabled.click();
        return initPage(EmployeesPage.class);
    }
}
