package tests;

import models.Contact;
import models.User;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class AddNewContact extends TestBase{

    @BeforeMethod
    public void precondition(){
        if(!app.getHelperUser().isLogged()){
            app.getHelperUser().login(new User().withEmail("testtest@test.com").withPassword("Codirovka84!"));
        }
    }
    @Test
    public void addNewContactPositive(){
        int i = new Random().nextInt(1000)+1000;
        Contact contact = Contact.builder()
                .name("Jay")
                .lastName("Lo")
                .phone("058111"+i)
                .email("jlo"+i+"@test.com")
                .address("Uta")
                .description("PM")
                .build();
        app.getHelperContact().openAddNewContactForm();
        app.getHelperContact().fillAddNewContactForm(contact);
        app.getHelperContact().submitAddNewContactForm();
        Assert.assertTrue(app.getHelperContact().isElementPresent(By.cssSelector(".contact-item_card__2SOIM>h2")));
    }
    @Test
    public void addNewContactPositiveReqFields(){
        int i = new Random().nextInt(1000)+1000;
        Contact contact = Contact.builder()
                .name("Mia")
                .lastName("Si")
                .phone("058333"+i)
                .email("mia"+i+"@test.com")
                .address("Uta")
//                .description("PM")
                .build();
        app.getHelperContact().openAddNewContactForm();
        app.getHelperContact().fillAddNewContactForm(contact);
        app.getHelperContact().submitAddNewContactForm();
        Assert.assertTrue(app.getHelperContact().isElementPresent(By.cssSelector(".contact-item_card__2SOIM>h2")));
    }
}
