package manager;

import models.User;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviderUser {
    @DataProvider
    public Iterator<Object[]> example(){
        List<Object[]> list = new ArrayList<>();

        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> loginData(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"testtest@test.com", "Codirovka84!"});
        list.add(new Object[]{"testtest@test.com", "Codirovka84!"});
        list.add(new Object[]{"testtest@test.com", "Codirovka84!"});
        return list.iterator();
    }
    @DataProvider
    public Iterator<Object[]> loginModels(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{new User().withEmail("testtest@test.com").withPassword("Codirovka84!")});
        list.add(new Object[]{new User().withEmail("testtest@test.com").withPassword("Codirovka84!")});
        return list.iterator();
    }
}

