package tests;

import manager.DataProviderContact;
import models.Contact;
import models.User;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Random;

public class AddNewContactTests extends TestBase{

    @BeforeClass
    public void precondition(){
        if(!app.getHelperUser().isLogged()){
            app.getHelperUser().login(new User().withEmail("testtest@test.com").withPassword("Codirovka84!"));
        }
    }
    @Test(dataProvider = "contactSuccess", dataProviderClass = DataProviderContact.class)
    public void addNewContactPositiveAllFields(Contact contact){
       logger.info("Test run with data:---> "+contact.toString());
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().saveContactForm();
//        Assert.assertTrue(app.getHelperContact().isElementPresent(By.cssSelector(".contact-item_card__2SOIM>h2")));
        Assert.assertTrue(app.getHelperContact().isContactAddedByName(contact.getName()));
        Assert.assertTrue(app.getHelperContact().isContactAddedByPhone(contact.getPhone()));
    }
    @Test
    public void addNewContactPositiveReqFields(){
        int i = new Random().nextInt(1000)+1000;
        Contact contact = Contact.builder()
                .name("Mia"+i)
                .lastName("Si")
                .phone("058333"+i)
                .email("mia"+i+"@test.com")
                .address("Uta")
               // .description("")
                .build();
        logger.info(contact.toString());
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().getScreen("src/test/Screenshots/screen"+i+".png");
        app.getHelperContact().saveContactForm();
//        Assert.assertTrue(app.getHelperContact().isElementPresent(By.cssSelector(".contact-item_card__2SOIM>h2")));
        Assert.assertTrue(app.getHelperContact().isContactAddedByName(contact.getName()));
        Assert.assertTrue(app.getHelperContact().isContactAddedByPhone(contact.getPhone()));
    }
    @Test
    public void addNewContactWrongName(){
        Contact contact = Contact.builder()
                .name("")
                .lastName("Lo")
                .phone("05811100992")
                .email("jlo92@test.com")
                .address("Uta")
                .description("PM")
                .build();
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().saveContactForm();
        Assert.assertTrue(app.getHelperContact().isAddPageDisplayed());

    }
    @Test
    public void addNewContactWrongLastName(){
        Contact contact = Contact.builder()
                .name("Jays")
                .lastName("")
                .phone("05811111221")
                .email("jlo21@test.com")
                .address("Uta")
                .description("PM")
                .build();
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().saveContactForm();
        Assert.assertTrue(app.getHelperContact().isAddPageDisplayed());

    }
    @Test
    public void addNewContactWrongAddress(){
        Contact contact = Contact.builder()
                .name("Jay")
                .lastName("Lo")
                .phone("05811155555")
                .email("jlo55@test.com")
                .address("")
                .description("PM")
                .build();
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().saveContactForm();
        Assert.assertTrue(app.getHelperContact().isAddPageDisplayed());

    }
    @Test(dataProvider = "contactWrongPhone", dataProviderClass = DataProviderContact.class)
    public void addNewContactWrongPhone(Contact contact){
       logger.info("Tests run with data:--> "+contact.toString());
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().saveContactForm();
        Assert.assertTrue(app.getHelperContact().isAddPageDisplayed());
//        Assert.assertTrue(app.getHelperContact().isAlertPresent(" Phone not valid: Phone number must contain only digits! And length min 10, max 15!"));

        Assert.assertTrue(app.getHelperContact().isAlertPresent("Phone not valid:"));


    }
    @Test
    public void addNewContactWrongEmail(){
        Contact contact = Contact.builder()
                .name("Jane")
                .lastName("Ro")
                .phone("05811144444")
                .email("jlotest.com")
                .address("Uta")
                .description("PM")
                .build();
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().saveContactForm();
        Assert.assertTrue(app.getHelperContact().isAddPageDisplayed());
        Assert.assertTrue(app.getHelperContact().isAlertPresent("Email not valid:"));

    }
 }
