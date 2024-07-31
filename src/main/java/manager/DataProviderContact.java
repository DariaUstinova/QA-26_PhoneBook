package manager;

import models.Contact;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviderContact {
    @DataProvider
    public Iterator<Object[]> example(){
        List<Object[]> list = new ArrayList<>();

        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> contactSuccess(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{Contact.builder()
                .name("Mia")
                .lastName("Si")
                .phone("05833322110")
                .email("mia@test.com")
                .address("Uta")
                 .description("PM")
                .build()});
        list.add(new Object[]{Contact.builder()
                .name("MiaD")
                .lastName("Sid")
                .phone("05833322118")
                .email("miad@test.com")
                .address("Uta")
                 .description("PM")
                .build()});

        return list.iterator();
    }
    @DataProvider
    public Iterator<Object[]> contactWrongPhone(){
        List<Object[]> list = new ArrayList<>();

        list.add(new Object[]{Contact.builder()
                .name("Jey")
                .lastName("Lo")
                .phone("123")
                .email("jlo@test.com")
                .address("Uta")
                .description("PM")
                .build()});
        list.add(new Object[]{Contact.builder()
                .name("Jey")
                .lastName("Lo")
                .phone("12345678912345676")
                .email("jleyo@test.com")
                .address("Uta")
                .description("PM")
                .build()});
        list.add(new Object[]{Contact.builder()
                .name("Jey")
                .lastName("Lo")
                .phone("wwwwwwwwwww")
                .email("jeylo@test.com")
                .address("Uta")
                .description("PM")
                .build()});
        list.add(new Object[]{Contact.builder()
                .name("Jey")
                .lastName("Lo")
                .phone("")
                .email("jleyo@test.com")
                .address("Uta")
                .description("PM")
                .build()});
        return list.iterator();
    }
}
