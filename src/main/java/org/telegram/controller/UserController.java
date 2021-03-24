package org.telegram.controller;

import org.telegram.model.User;

import java.util.concurrent.ThreadLocalRandom;

public class UserController {

    User user = new User();
    SettingsController settingsController = new SettingsController();

    public User initUser() {
        user.setUserId(ThreadLocalRandom.current().nextInt(1, 1000 + 1));
        user.setSettings(settingsController.loadProperties());
        return user;
    }

}
