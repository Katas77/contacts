package org.example;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

@Getter
public class InitSavePaths {
    @Value("${app.path}")
    String pathInit;
    @Value("${app.pathSave}")
    String pathSave;
}
