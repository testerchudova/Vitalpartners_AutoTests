package testdata;

import utils.RandomUtils;

public class TestData {
    RandomUtils random = new RandomUtils();

    public String
            fullName = random.getRandomFirstName() + " " + random.getRandomLastName(),
            company = "ООО " + random.getRandomLastName(),
            searchSubject = random.getRandomSubject(),
            email = random.getRandomEmail(),
            phone = random.getRandomPhone();
}
