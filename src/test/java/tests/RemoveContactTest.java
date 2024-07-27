package tests;

import models.Contact;
import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;

public class RemoveContactTest extends TestBase{
    WebDriver wd;
    @BeforeMethod
    public void precondition(){
        if(!app.getHelperUser().isLogged()){
            app.getHelperUser().login(new User().withEmail("testtest@test.com").withPassword("Codirovka84!"));
        }
        app.getHelperContact().provideContacts();//if list.size<3 contacts ->add 3 contacts

    }

    @Test
    public void removeOneContact(){
        int i = new Random().nextInt(1000)+1000;
        Contact contact = Contact.builder()
                .name("Jay"+i).lastName("Lo").phone("058111"+i).email("jlo"+i+"@test.com")
                .address("Uta").description("PM").build();
        String phone = contact.getPhone();
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().saveContactForm();

        List<WebElement> listBefore = wd.findElements(By.cssSelector("h3"));
        app.getHelperContact().click(By.xpath("//h3[text()='" + phone + "']"));
        app.getHelperContact().click(By.xpath("//button[text()='Remove']"));
        List<WebElement> listAfter = wd.findElements(By.cssSelector(".contact-item_card__2SOIM"));
        Assert.assertEquals(listBefore.size()-listAfter.size(),0);
    }
    @Test
    public void removeFirstContactMaria(){
        Assert.assertEquals(app.getHelperContact().removeOneContact(), 1);
    }
    @Test
    public void removeAllContact(){
app.getHelperContact().removeAllContacts();
        Assert.assertTrue(app.getHelperUser().isNoContactsHereDisplayed());
    }
}
