package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;

public class Main {

    public WebDriver driver;

    @BeforeSuite
    public static void setDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeTest
    public void setup() {
        driver = new ChromeDriver();
    }

    @AfterTest
    public void quit() {
        driver.quit();
    }

    @Test
    public void chromeTest() {

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

        String expectedTextButton = "ADD TO CART";
        String actualTextButton =
                driver.findElement(By.id("add-to-cart-sauce-labs-backpack"))
                        .getText()
                        .toUpperCase();;

       // System.out.println(actualTitle);
        WebElement clickOnButtonAddCart =
                driver.findElement(By.xpath("//*[@id=\"add-to-cart-sauce-labs-backpack\"]"));
        clickOnButtonAddCart.click();

        String expectedTextAfterClickOnButton = "REMOVE";
        String actualTextAfterClickOnButton =
                driver.findElement(By.id("remove-sauce-labs-backpack"))
                        .getText()
                        .toUpperCase();
        //System.out.println(actualTextAfterClickOnButton);

        Assert.assertEquals(expectedTitle, actualTitle);
        Assert.assertEquals(expectedTextButton, actualTextButton);
        Assert.assertEquals(expectedTextAfterClickOnButton, actualTextAfterClickOnButton);
        driver.close();
    }
}