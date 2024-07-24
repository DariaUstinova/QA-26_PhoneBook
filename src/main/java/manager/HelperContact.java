package manager;

import models.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

public class HelperContact extends HelperBase {
    public HelperContact(WebDriver wd) {
        super(wd);
    }

    public void openContactForm() {
        pause(500);
        click(By.xpath("//*[text()='ADD']"));
//        click(By.cssSelector("a[href='/add']"));
    }

    public void fillContactForm(Contact contact) {

        type(By.xpath("//input[@placeholder='Name']"), contact.getName());
        type(By.xpath("//input[@placeholder='Last Name']"), contact.getLastName());
        type(By.xpath("//input[@placeholder='Phone']"), contact.getPhone());
        type(By.xpath("//input[@placeholder='email']"), contact.getEmail());
        type(By.xpath("//input[@placeholder='Address']"), contact.getAddress());
        type(By.xpath("//input[@placeholder='description']"), contact.getDescription());


    }

    public void saveContactForm() {
        click(By.xpath("//div[@class='add_form__2rsm2']//button" ));
//        click(By.xpath("//b[text()='Save']"));
    }

    public boolean isContactAddedByName(String name) {
        List<WebElement> list = wd.findElements(By.cssSelector("h2"));
        for (WebElement element : list){
            if(element.getText().equals(name)){
                return true;
            }
        }
        return false;
    }

    public boolean isContactAddedByPhone(String phone) {
        List<WebElement> list = wd.findElements(By.cssSelector("h3"));
        for (WebElement element : list){
            if(element.getText().equals(phone)){
                return true;
            }
        }
        return false;
    }

    public boolean isAddPageDisplayed() {
        return isElementPresent(By.cssSelector("a.active[href='/add']"));
    }

    public void provideContacts() {
        List<WebElement> list = wd.findElements(By.cssSelector(".contact-item_card__2SOIM"));
//        List<WebElement> list = wd.findElements(By.xpath("//div[@class='contact-item_card__2SOIM']"));
        if(list.size()<=3)
//        do
                    {addNewContact();
        }
//        while (list.size()<3);
    }
    public void addNewContact(){
        int i = new Random().nextInt(1000)+1000;
        Contact contact = Contact.builder()
                .name("Jay"+i)
                .lastName("Lo")
                .phone("058111"+i)
                .email("jlo"+i+"@test.com")
                .address("Uta")
                .description("PM")
                .build();
        openContactForm();
        fillContactForm(contact);
        saveContactForm();
    }
}
