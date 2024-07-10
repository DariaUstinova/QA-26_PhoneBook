package tests;

import models.User;
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
    }

    @AfterMethod
    public void postcondition(){

    }
}
