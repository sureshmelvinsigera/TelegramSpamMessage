package org.telegram.service;

import org.telegram.event.Connect;

public class Service {

    Connect connect = new Connect();

    public void buildProject() throws InterruptedException {
        connect.loadProperties();
        connect.setDrivers();
        connect.onSite();
        connect.sendMessage();
    }
}
