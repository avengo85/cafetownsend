package cafe;

import cafe.Pages.*;
import cafe.framework.BasePage;
import cafe.framework.BaseTest;
import org.testng.Assert;
import org.testng.annotations.*;

public class WebTest extends BaseTest {

    private LoginPage loginPage;
    private EmployeesPage employeesPage;
    private static final String USERNAME = "Luke";
    private static final String PASSWORD = "Skywalker";
    private static final String FIRST_NAME = "Firstname";
    private static final String LAST_NAME = "Lastname";
    private static final String START_DATE = "2020-01-01";
    private static final String EMAIL = "test@test.com";


    @Test
    public void loginLogoutTest() {
        loginPage = BasePage.initPage(LoginPage.class);
        employeesPage = loginPage.loggingIn(USERNAME, PASSWORD);
        Assert.assertTrue(employeesPage.getGreetingsText().contains(USERNAME), "Greetings does not contain " + USERNAME);
        employeesPage.loggingOut();
    }

    @Test
    public void createDeleteEmployeeTest() {
        loginPage = BasePage.initPage(LoginPage.class);
        employeesPage = loginPage.loggingIn(USERNAME, PASSWORD);
        CreateEmployeePage createEmployeePage = employeesPage.clickCreateButton();
        employeesPage = createEmployeePage.createEmployee(FIRST_NAME, LAST_NAME, START_DATE, EMAIL);
        employeesPage.selectEmployee(FIRST_NAME + " " + LAST_NAME);
        employeesPage.clickDeleteButtonAndAccept();
    }
}


