package org.telegram.service;

import org.telegram.controller.ConnectionController;

public class Service {

    public void buildProject() {
        ConnectionController connectionController = new ConnectionController();
        connectionController.getConnectionTG();
        connectionController.setDrivers();
        connectionController.onSite();
        connectionController.sendMessage();
    }

}
