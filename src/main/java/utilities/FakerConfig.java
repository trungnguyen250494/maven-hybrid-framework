package utilities;

import com.github.javafaker.Faker;

import java.util.Locale;

public class FakerConfig {
    Faker faker = new Faker(new Locale("en-US"));

    public static FakerConfig getFaker(){
        return new FakerConfig();
    }

    public String getFirstName(){
        return faker.address().firstName();
    }

    public String getLastName(){
        return faker.address().lastName();
    }

    public String getEmailAddress(){
        return faker.internet().emailAddress();
    }

    public String getPassword(){
        return faker.internet().password();
    }
}
