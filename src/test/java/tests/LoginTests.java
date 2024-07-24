package tests;

import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {

    @BeforeMethod
    public void precondition(){
        if(app.getHelperUser().isLogged()){
            app.getHelperUser().logout();
            logger.info("Before method logout finish");
        }
    }

    @Test
    public void  loginSuccess(){
        logger.info("Start test with name 'loginSuccess'");
        logger.info("test data begins-->email 'testtest@test.com' & PWD 'Codirovka84!'");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("testtest@test.com", "Codirovka84!");
        app.getHelperUser().submitLogin();

        Assert.assertTrue(app.getHelperUser().isLogged());
        logger.info("Assert check is element button 'Sign out' present");
    }
    @Test
    public void loginWrongEmail(){
        logger.info("test data begins-->email 'testtesttest.com' & PWD 'Codirovka84!'");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("testtesttest.com", "Codirovka84!");
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));
        logger.info("Assert check is alert present with error text 'Wrong email or passwor'");

    }
    @Test
    public void loginWrongPassword(){
        logger.info("test data begins-->email 'testtest@test.com' & PWD 'Cod'");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("testtest@test.com", "Cod");
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));
        logger.info("Assert check is alert present with error text 'Wrong email or passwor'");

    }
    @Test
    public void loginUnregisteredUser(){
        logger.info("test data begins-->email 'testt100@test.com' & PWD 'Codirovka84!'");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("testt100@test.com", "Codirovka84!");
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));
        logger.info("Assert check is alert present with error text 'Wrong email or passwor'");

    }

}
