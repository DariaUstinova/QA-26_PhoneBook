package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegistrationTests extends TestBase{

    @BeforeMethod
    public void precondition(){
        if(app.getHelperUser().isLogged()){
            app.getHelperUser().logout();
        }
    }
    @Test
    public void registrationSuccess(){
        int i = (int) ((System.currentTimeMillis()/1000)%3600);
        User user = new User().withEmail("test"+i+"@gmail.com").withPassword("Codirovka84!");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();
        Assert.assertTrue(app.getHelperUser().isLogged());
        Assert.assertTrue(app.getHelperUser().isNoContactsHereDisplayed());
    }

    @Test //(description="Bug report #123", enabled = false) //(description="Bug report #123" Fixed)
    public void registrationWrongEmail(){
        User user = new User().withEmail("testgmail.com").withPassword("Codirovka84!");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));

    }
    @Test
    public void registrationWrongPassword(){
        User user = new User().withEmail("test101gmail.com").withPassword("Cod");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));
    }
    @Test
    public void registrationExistsUser(){
        User user = new User().withEmail("test3@gmail.com").withPassword("Codirovka84!");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("User already exist"));
    }

    @AfterMethod
    public void postcondition(){

    }
}
