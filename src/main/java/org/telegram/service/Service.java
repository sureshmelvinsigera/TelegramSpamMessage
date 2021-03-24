package org.telegram.service;

import org.telegram.event.Connect;

public class Service {

    public void buildProject() throws InterruptedException {
            Connect connect = new Connect();
            connect.loadProperties();
            connect.setDrivers();
            connect.onSite();
            connect.sendMessage();
    }


}
