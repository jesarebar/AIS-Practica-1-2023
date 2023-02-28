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
    }

    @AfterEach
    public void teardown() {
        if(driver != null) {
            driver.quit();
        }
    }

    @Test
    public void test1() throws InterruptedException {
        driver.get("http://localhost:"+this.port+"/");
        String s1 = "drama";
        WebElement searchInput = driver.findElement(By.name("topic"));
        searchInput.sendKeys(s1);
        searchInput.submit();
        WebElement book = driver.findElement(By.id("Pride and Prejudice"));
        book.click();
        WebElement tag = driver.findElement(By.id(s1));
        String s2 = tag.getText();
        assertEquals(s1, s2);
    }

    @Test
    public void test2() throws InterruptedException {
        driver.get("http://localhost:"+this.port+"/");
        String s = "epic fantasy";
        WebElement searchInput = driver.findElement(By.name("topic"));
        searchInput.sendKeys(s);
        searchInput.submit();
        WebElement book= driver.findElement(By.id("The Way of Kings"));
        book.click();
        WebElement nick=driver.findElement(By.name("nickname"));
        nick.sendKeys("text");
        WebElement cont=driver.findElement(By.name("content"));
        cont.sendKeys("my first review");
        WebElement button=driver.findElement(By.id("add-review"));
        button.click();
        String coment=driver.findElement(By.className("comment")).getText();
        assertTrue(coment.contains("my first review"));
        //Solo falla el equals porque devuelve más cosas en texta, devuelve un texto más largo
    }

    @Test
    public void test3() throws InterruptedException {
        driver.get("http://localhost:"+this.port+"/");

    }
}
