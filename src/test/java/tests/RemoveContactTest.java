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
    public void removeFirstContact(){
        int i = new Random().nextInt(1000)+1000;
        Contact contact = Contact.builder()
                .name("Jay"+i)
                .lastName("Lo")
                .phone("058111"+i)
                .email("jlo"+i+"@test.com")
                .address("Uta")
                .description("PM")
                .build();
        String phone = contact.getPhone();
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().saveContactForm();

//        List<WebElement> listBefore = wd.findElements(By.xpath("//div[@class='contact-item_card__2SOIM']"));
         List<WebElement> listBefore = wd.findElements(By.cssSelector("h3"));
        System.out.println("List size = " + listBefore.size());
            for(WebElement element : listBefore) {
                WebElement phoneData = element.findElement(By.xpath("//h3[text()='" + phone + "']"));
              if(phoneData.isDisplayed()){
                    element.click();
                    app.getHelperContact().click(By.xpath("//button[text()='Remove']"));
              }
            }
        List<WebElement> listAfter = wd.findElements(By.cssSelector(".contact-item_card__2SOIM"));
        Assert.assertNotEquals(listBefore.size(), listAfter.size());
        //Assert list.size less by 1
    }
    @Test
    public void removeAllContact(){
     List<WebElement> list = wd.findElements(By.xpath("//div[@class='contact-item_card__2SOIM']"));
       for(WebElement element : list) {
            WebElement elementToRemove = element.findElement(By.cssSelector("h3"));
            elementToRemove.click();
            app.getHelperContact().click(By.xpath("//button[text()='Remove']"));
            }
       Assert.assertTrue(list.isEmpty());
    }
}
