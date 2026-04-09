package utils;

import com.github.javafaker.Faker;

import java.util.Locale;

public class RandomUtils {

    private Faker fakerRu = new Faker(new Locale("ru"));
    private Faker fakerEn = new Faker(new Locale("en"));

    public String getRandomFirstName() {
        return fakerRu.name().firstName();
    }

    public String getRandomLastName() {
        return fakerRu.name().lastName();
    }

    public String getRandomEmail() {
        return fakerEn.internet().emailAddress();
    }

    public String getRandomPhone() {
        return fakerRu.phoneNumber().subscriberNumber(10);
    }


    public String getRandomSubject() {
        String[] subjects = {"Поиск сотрудников", "Аудит", "Консультация", "Цены"};
        return subjects[fakerRu.random().nextInt(subjects.length)];
    }
}