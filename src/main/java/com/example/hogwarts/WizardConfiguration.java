package com.example.hogwarts;

import java.io.IOException;
import java.util.Properties;

public class WizardConfiguration {

    private static Properties props = new Properties();

    static{
        try {
            props.load(WizardConfiguration.class.getResourceAsStream("/application.properties"));
        } catch (IOException e) {
            // TODO: logging
            e.printStackTrace();
        }

    }

    public static Properties getProps() {
        return props;
    }

    public static String getValue(String key) {
        return props.getProperty(key);
    }
}
