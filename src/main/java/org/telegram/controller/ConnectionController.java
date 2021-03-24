package org.telegram.controller;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.ProfilesIni;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.telegram.model.ConnectionTG;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

public class ConnectionController {

    public ProfilesIni profilesIni;
    public FirefoxProfile firefoxProfile;
    public FirefoxOptions firefoxOptions;
    public WebDriver firefoxDriver;
    public ConnectionTG connectionTG;

    public void getConnectionTG() {
        connectionTG = new ConnectionTG();
        connectionTG.setUser(new UserController().initUser());
    }

    public void setDrivers() {
        profilesIni = new ProfilesIni();
        firefoxProfile = profilesIni.getProfile(connectionTG.getUser().getSettings().getFireFoxProfile());
        firefoxOptions = new FirefoxOptions();
        firefoxDriver = new FirefoxDriver(firefoxOptions.setProfile(firefoxProfile));
    }

    public void onSite() {
        try {
            firefoxDriver.get("https://web.telegram.org/");
        } catch (Exception e) {
            System.out.println("Connect exception, try later");
        }
    }

    public void sendMessage() {
        try {
            for (int i = 0; i < connectionTG.getUser().getSettings().getAccounts().size(); i++) {
                firefoxDriver.get("https://web.telegram.org/#/im?p=" + connectionTG.getUser().getSettings().getAccounts().get(i));
                if (i == 0) {
                    TimeUnit.SECONDS.sleep(3);
                }
                WebDriverWait wait = new WebDriverWait(firefoxDriver, connectionTG.getUser().getSettings().getTimeout());
                WebElement messageBlock = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class='composer_rich_textarea']")));
                try {
                    WebElement errorBlock = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class='error_modal_wrap md_simple_modal_wrap']")));
                    WebElement sendButton = firefoxDriver.findElement(By.cssSelector("button[class='btn btn-md btn-md-primary']"));
                    sendButton.click();
                } catch (TimeoutException e) {
                    messageBlock.clear();
                    messageBlock.sendKeys(connectionTG.getUser().getSettings().getMessage());
                    writeAcceptedAccounts(connectionTG.getUser().getSettings().getAccounts().get(i));
                    WebElement sendButton = firefoxDriver.findElement(By.cssSelector("button[class='btn btn-md im_submit im_submit_send']"));
                    sendButton.click();
                }
            }
            firefoxDriver.quit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void writeAcceptedAccounts(String account) {
        try (PrintWriter printWriter = new PrintWriter(new FileOutputStream("/home/wilyr/IdeaProjects/TelegramMessageSpammer/src/main/resources/acceptedAccounts", true))) {
            printWriter.write(account + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
