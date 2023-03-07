package es.codeurjc.ais.web;

import es.codeurjc.ais.Application;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.github.bonigarcia.wdm.WebDriverManager;


@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SeleniumTests {

    @LocalServerPort
    int port;

    WebDriver driver;

    @BeforeAll
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
        driver.get("http://localhost:" + this.port + "/");
    }

    @AfterEach
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void test1() {
        WebElement searchInput = driver.findElement(By.name("topic"));
        String s1 = "drama";
        searchInput.sendKeys(s1);
        searchInput.submit();
        WebElement topic = driver.findElement(By.xpath("/html/body/div[2]/h1"));
        assertEquals("Books (topic=drama)", topic.getText());
        WebElement book = driver.findElement(By.xpath("/html/body/div[2]/div/a[1]"));
        book.click();
        WebElement tag = driver.findElement(By.id(s1));
        String s2 = tag.getText();
        assertEquals(s1, s2);
    }

    @Test
    public void test2() {
        WebElement searchInput = driver.findElement(By.name("topic"));
        searchInput.sendKeys("epic fantasy");
        searchInput.submit();
        WebElement book = driver.findElement(By.id("The Way of Kings"));
        book.click();
        WebElement nick = driver.findElement(By.name("nickname"));
        nick.sendKeys("text");
        WebElement cont = driver.findElement(By.name("content"));
        cont.sendKeys("my first review");
        WebElement button = driver.findElement(By.id("add-review"));
        button.click();
        String comment = driver.findElement(By.className("comment")).getText();
        assertTrue(comment.contains("my first review"));
    }

    @Test
    public void test3() {
        WebElement searchInput = driver.findElement(By.name("topic"));
        searchInput.sendKeys("epic fantasy");
        searchInput.submit();
        WebElement book = driver.findElement(By.id("Words of Radiance"));
        book.click();
        WebElement submitReview = driver.findElement(By.id("add-review"));
        submitReview.submit();
        WebElement error = driver.findElement(By.id("error-message"));
        String message = error.getText();
        assertEquals("Error at saving the review: empty fields", message);
    }
}
