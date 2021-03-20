package org.telegram.service;

import org.telegram.event.Connect;

import java.util.concurrent.TimeUnit;

public class Service {

    Connect connect = new Connect();

    public void buildProject() throws InterruptedException {
        connect.loadProperties();
        TimeUnit.SECONDS.sleep(3L);
        connect.setDrivers();
        TimeUnit.SECONDS.sleep(3L);
        connect.onSite();
        connect.sendMessage();
    }
}
