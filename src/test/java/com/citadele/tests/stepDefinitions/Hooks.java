package com.citadele.tests.stepDefinitions;

import com.citadele.tests.endPoints.DeckOfCardsEndpoint;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.slf4j.Logger;

import static com.citadele.tests.helpers.TestCaseContext.setDeckOfCardsEndpoint;
import static org.slf4j.LoggerFactory.getLogger;

public class Hooks {

    private static final Logger LOGGER = getLogger(Hooks.class);

    @Before
    public void Before() {
        LOGGER.info("Adding deck of cards endpoint to test case TestCaseContext");
        DeckOfCardsEndpoint deckOfCardsEndpoint = new DeckOfCardsEndpoint();
        setDeckOfCardsEndpoint(deckOfCardsEndpoint);
    }

    @After
    public void After() {}
}