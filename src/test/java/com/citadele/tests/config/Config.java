package com.citadele.tests.config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

    private static final String FILE_NAME = "default.properties";

    private static final String DEFAULT_DECK_OF_CARDS_API_URL = "default.deck.of.cards.api.url";

    private final Properties properties;

    private static Config CONFIG;

    private Config() throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(FILE_NAME);

        properties = new Properties();

        if (inputStream != null) {
            properties.load(inputStream);
        } else {
            throw new FileNotFoundException("property not found: " + FILE_NAME);
        }
    }

    public static Config getInstance() {
        if (CONFIG == null) {
            synchronized (Config.class) {
                try {
                    CONFIG = new Config();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return CONFIG;
    }

    public String getDefaultDeckOfCardsApiUrl() {
        return properties.getProperty(DEFAULT_DECK_OF_CARDS_API_URL);
    }
}