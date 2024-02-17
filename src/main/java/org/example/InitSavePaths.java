package org.example;

import org.springframework.beans.factory.annotation.Value;

public class InitSavePaths {
    @Value("${app.path}")
    String pathInit;
    @Value("${app.pathSave}")
    String pathSave;

    public String getPathInit() {
        return pathInit;
    }

    public String getPathSave() {
        return pathSave;
    }
}
