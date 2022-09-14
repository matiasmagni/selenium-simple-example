package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.testng.Assert;

public class Login {
    public WebDriver driver;

    @BeforeSuite
    public static void setDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeTest
    public void setup() {
        ChromeOptions chromeOptions = new ChromeOptions();
        driver = new ChromeDriver(chromeOptions);
    }

    @AfterTest
    public void quit() {
        driver.quit();
    }

    @Test
    public void positiveLogin() {

        driver.get("https://www.saucedemo.com");
        driver.manage().window().maximize();

        WebElement userInput = driver.findElement(By.xpath("//*[@id=\"user-name\"]"));
        userInput.sendKeys("standard_user");

        WebElement passwordInput = driver.findElement(By.xpath("//*[@id=\"password\"]"));
        passwordInput.sendKeys("secret_sauce");

        WebElement buttonSubmit = driver.findElement(By.xpath(("//*[@id=\"login-button\"]")));
        buttonSubmit.click();

        String expectedTitle = "PRODUCTS";
        String actualTitle =
                driver.findElement(By.className("header_secondary_container"))
                        .findElement(By.className("title"))
                        .getText()
                        .toUpperCase();

        Assert.assertEquals(expectedTitle, actualTitle);
        driver.close();
    }

    @Test
    public void negativeLogin() {

        driver.get("https://www.saucedemo.com");
        driver.manage().window().maximize();

        WebElement userInput = driver.findElement(By.xpath("//*[@id=\"user-name\"]"));
        userInput.sendKeys("standard_user");

        WebElement passwordInput = driver.findElement(By.xpath("//*[@id=\"password\"]"));
        passwordInput.sendKeys(" ");

        WebElement buttonSubmit = driver.findElement(By.xpath(("//*[@id=\"login-button\"]")));
        buttonSubmit.click();

        String expectedTitle = "Epic sadface: Username and password do not match any user in this service";
        String actualTitle =
                driver.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3"))
                        .getText();

        Assert.assertEquals(expectedTitle, actualTitle);
        driver.close();
    }
}
