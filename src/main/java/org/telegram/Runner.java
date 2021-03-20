package org.telegram;

import org.telegram.service.Service;

import java.io.IOException;

public class Runner {
    public static void main(String[] args) throws IOException, InterruptedException {
        Service service = new Service();
        service.buildProject();
    }
}
