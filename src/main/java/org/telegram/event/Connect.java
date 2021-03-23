package org.telegram.event;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.ProfilesIni;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class Connect extends SetProperties {

    ProfilesIni profilesIni;
    FirefoxProfile firefoxProfile;
    FirefoxOptions firefoxOptions;
    WebDriver firefoxDriver;

    public void setDrivers() {
        profilesIni = new ProfilesIni();
        firefoxProfile = profilesIni.getProfile(user.getFireFoxProfile());
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
            for (int i = 0; i < user.getAccounts().size(); i++) {
                firefoxDriver.get("https://web.telegram.org/#/im?p=" + user.getAccounts().get(i));
                WebDriverWait wait = new WebDriverWait(firefoxDriver, user.getTimeout());
                try {
                    WebElement messageBlock = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class='composer_rich_textarea']")));
                    messageBlock.clear();
                    messageBlock.sendKeys(user.getMessage());
                    writeAcceptedAccounts(user.getAccounts().get(i));
                    try {
                        WebElement sendButton = firefoxDriver.findElement(By.cssSelector("button[class='btn btn-md im_submit im_submit_send']"));
                        sendButton.click();
                    } catch (Exception e) {
                        continue;
                    }
                } catch (Exception e) {
                    continue;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void writeAcceptedAccounts(String account) {
        try {
            PrintWriter printWriter = new PrintWriter(new File("acceptedAccounts"));
            printWriter.write(account + "/n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
