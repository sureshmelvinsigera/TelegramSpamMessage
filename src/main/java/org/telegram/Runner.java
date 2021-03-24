package org.telegram;

import org.telegram.service.Service;

public class Runner {
    public static void main(String[] args) {
        Service service = new Service();
        service.buildProject();
    }
}
