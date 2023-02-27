package es.codeurjc.ais.web;
import es.codeurjc.ais.Application;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;


import static org.junit.jupiter.api.Assertions.assertEquals;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class dramaTest {

    @LocalServerPort
    int port;

    WebDriver driver;

    @BeforeAll
    public static void setupClass() {
        WebDriverManager.edgedriver().setup();
    }

    @BeforeEach
    public void setup() {
        driver = new EdgeDriver();
    }

    @AfterEach
    public void teardown() {
        if(driver != null) {
            driver.quit();
        }
    }

    @Test
    public void test() throws InterruptedException {
        driver.get("http://localhost:"+this.port+"/");

        String s1 = "drama";

        WebElement searchInput = driver.findElement(By.name("topic"));

        //Thread.sleep(5000);

        searchInput.sendKeys(s1);

        //Thread.sleep(5000);

        searchInput.submit();

        WebElement book = driver.findElement(By.id("Pride and Prejudice"));

        //Thread.sleep(5000);

        book.click();

        //Thread.sleep(5000);

        WebElement tag = driver.findElement(By.id(s1));

        String s2 = tag.getText();

        assertEquals(s1, s2);
    }

}
