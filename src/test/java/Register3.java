import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.time.Duration;

public class Register3 {
    int min = 1000000;
    int max = 9999999;
    int rndNum = min + (int) Math.round(Math.random() * (max - min));


    @Test
    public void positiveTest() {
        User x = new User("irenijus", "aviza", "" + rndNum + "@post.lt", "6" + rndNum, "Asd123456789");
        x.register();
        String text = "";
        try {
            text = User.driver.findElement(By.className("form-title")).getText();
        } catch (Exception e) {
        }
        Assert.assertEquals(text, "Patvirtinkite paskyrą");

    }

    @Test
    public void emptyName() {
        User x = new User("", "aviza", "" + rndNum + "@post.lt", "6" + rndNum, "Asd123456789");
        x.register();
        Assert.assertEquals(errorMsg("name"), "Ši reikšmė negali būti tuščia.");
    }

    @Test
    public void tooShortName() {
        User x = new User("rr", "aviza", "" + rndNum + "@post.lt", "6" + rndNum, "Asd123456789");
        x.register();
        Assert.assertEquals(errorMsg("name"), "Per mažas simbolių skaičius. Turi susidaryti iš 2 arba daugiau simbolių. Jūsų vardą gali sudaryti tik raidės ir „-“ simbolis");
    }


    @Ignore
    @Test
    public void oneSymbolName() {
        User x = new User("r", "aviza", "" + rndNum + "@post.lt", "6" + rndNum, "Asd123456789");
        x.register();
        Assert.assertEquals(errorMsg("name"), "Per mažas simbolių skaičius. Turi susidaryti iš 2 arba daugiau simbolių.");
    }

    @Test
    public void tooLongName() {
        User x = new User("irrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr", "aviza", "" + rndNum + "@post.lt", "6" + rndNum, "Asd123456789");
        x.register();
        Assert.assertEquals(errorMsg("name"), "Per didelis simbolių skaičius. Turi susidaryti iš 255 arba mažiau simbolių.");
    }

    @Test
    public void wrongSymbolName() {
        User x = new User("irenijus1989****", "aviza", "" + rndNum + "@post.lt", "6" + rndNum, "Asd123456789");
        x.register();
        Assert.assertEquals(errorMsg("name"), "Jūsų vardą gali sudaryti tik raidės ir „-“ simbolis");
    }

    @Test
    public void emptySurname() {
        User x = new User("irenijus", "", "" + rndNum + "@post.lt", "6" + rndNum, "Asd123456789");
        x.register();
        Assert.assertEquals(errorMsg("surname"), "Ši reikšmė negali būti tuščia.");

    }

    @Test
    public void tooShortSurname() {
        User x = new User("irenijus", "r", "" + rndNum + "@post.lt", "6" + rndNum, "Asd123456789");
        x.register();
        Assert.assertEquals(errorMsg("surname"), "Per mažas simbolių skaičius. Turi susidaryti iš 2 arba daugiau simbolių.");
    }

    @Test
    public void tooLongSurname() {
        User x = new User("irenijus", "irrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr", "" + rndNum + "@post.lt", "6" + rndNum, "Asd123456789");
        x.register();
        Assert.assertEquals(errorMsg("surname"), "Ši pavardė per ilga.");
    }

    @Test
    public void wrongSymbolSurname() {
        User x = new User("irenijus", "aviza1989****", "" + rndNum + "@post.lt", "6" + rndNum, "Asd123456789");
        x.register();
        Assert.assertEquals(errorMsg("surname"), "Jūsų pavardę gali sudaryti tik raidės ir „-“ simbolis");
    }

    @Test
    public void emptyPhoNo() {
        User x = new User("irenijus", "aviza", "" + rndNum + "@post.lt", "", "Asd123456789");
        x.register();
        Assert.assertEquals(errorMsg("phoNo"), "Telefono numeris turi būti nurodytas.");// meta Oops! An Error Occurred
    }

    @Test
    public void tooShortPhoNo() {
        User x = new User("irenijus", "aviza", "" + rndNum + "@post.lt", "1989", "Asd123456789");
        x.register();
        Assert.assertEquals(errorMsg("phoNo"), "Klaidingai įvestas tel. numeris");
    }

    @Test
    public void wrongSymbolPhoNo() {
        User x = new User("irenijus", "aviza", "" + rndNum + "@post.lt", "///***)))", "Asd123456789"); //meta Oops! An Error Occurred
        x.register();
        Assert.assertEquals(errorMsg("phoNo"), "Klaidingai įvestas tel. numeris");
    }

    @Test
    public void tooLongPhoNo() {
        User x = new User("irenijus", "aviza", "" + rndNum + "@post.lt", "6765229433", "Asd123456789"); //meta Oops! An Error Occurred
        x.register();
        Assert.assertEquals(errorMsg("phoNo"), "Klaidingai įvestas tel. numeris");
    }

    @Test
    public void emptyPassword() {
        User x = new User("", "aviza", "" + rndNum + "@post.lt", "6" + rndNum, "");
        x.register();
        Assert.assertEquals(errorMsg("password"), "Slaptažodį privalo sudaryti bent 8 simboliai, iš kurių bent viena didžioji raidė, mažoji raidė ir skaičius");
    }

    @Test
    public void tooLongPassword() {
        User x = new User("irenijus", "aviza", "" + rndNum + "@post.lt", "6" + rndNum, "Ad22222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222");
        x.register();
        Assert.assertEquals(errorMsg("password"), "Slaptažodis perilgas.");
    }


    @BeforeClass
    public void beforeClass() {
        User.driver = new ChromeDriver();
        User.driver.manage().window().maximize();
        User.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        acceptCookies();
    }

    public String errorMsg(String field) {
        String errorMsg = "";
        int position = 0;

        switch (field) {
            case "name":
                position = 0;
                break;
            case "surname":
                position = 1;
                break;
            case "email":
                position = 2;
                break;
            case "phoNo":
                position = 3;
                break;
            case "password":
                position = 4;
                break;
        }
        try {
            errorMsg = User.driver.
                    findElement(By.className("login-page-body--form")).
                    findElements(By.className("form-block")).get(position).
                    findElement(By.className("field-error")).getText();
        } catch (Exception e) {
        }
        return errorMsg;
    }

    public void acceptCookies() {
        User.driver.get("https://www.livinn.lt/register");
        User.driver.findElement(By.id("onetrust-button-group")).click();
    }

    @AfterClass
    public void afterClass() {
        //  driver.close();
    }
}


