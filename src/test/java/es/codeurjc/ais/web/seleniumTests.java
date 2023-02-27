package es.codeurjc.ais.web;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class seleniumTests {

    protected WebDriver driver;

    @BeforeAll
    public static void setupClass(){
        WebDriverManager.chromedriver().setup();
    }
    @BeforeEach
    public void setupTest(){
        driver=new ChromeDriver();
    }
    @AfterEach
    public void teardown() {
        if(driver!=null)
            driver.quit();
    }
    @Test
    public void rec_drama() {
        //Configurar el driver del navegador
       // System.setProperty("");
        //Abrir el navegador
        WebDriver driver=new EdgeDriver();
        driver.get("localhost:8080");
        WebElement webE1=driver.findElement(By.className("ui action input"));
        WebElement webE2=driver.findElement(By.id("search-button"));
        webE1.click();
        webE1.sendKeys("drama");
        webE2.click();
        WebElement webE3= driver.findElement(By.id("Pride and Prejudice"));
        webE3.click();
        String text=driver.findElement(By.id("drama")).getText();
        assertEquals(text,"drama");
    }
}
