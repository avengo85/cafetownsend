package cafe.Pages;

import cafe.framework.BasePage;
import cafe.framework.BaseTest;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    public ExtentTest test = BaseTest.test;

    private static final String USERNAME_TEXTBOX_XPATH = "//input[@ng-model='user.name']";
    private static final String PASSWORD_TEXTBOX_XPATH = "//input[@ng-model='user.password']";
    private static final String LOGIN_BUTTON_XPATH = "//button[@type='submit']";

    @FindBy(xpath = USERNAME_TEXTBOX_XPATH)
    private WebElement usernameTextbox;

    @FindBy(xpath = PASSWORD_TEXTBOX_XPATH)
    private WebElement passwordTextbox;

    @FindBy(xpath = LOGIN_BUTTON_XPATH)
    private WebElement loginButton;

    public EmployeesPage loggingIn(String username, String password) {
        test.log(LogStatus.INFO, "Logging is as " + username + " ...");
        usernameTextbox.sendKeys(username);
        passwordTextbox.sendKeys(password);
        loginButton.click();
        return initPage(EmployeesPage.class);
    }
}
