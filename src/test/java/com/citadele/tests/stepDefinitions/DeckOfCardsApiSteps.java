package com.citadele.tests.stepDefinitions;

import com.citadele.tests.helpers.TestCaseContext;

import com.citadele.tests.models.Deck;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.slf4j.Logger;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.Random;

import static com.citadele.tests.endPoints.DeckOfCardsEndpoint.*;
import static com.fasterxml.jackson.databind.DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY;
import static java.lang.String.format;
import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertTrue;
import static org.slf4j.LoggerFactory.getLogger;
import static org.junit.Assert.assertThat;

public class DeckOfCardsApiSteps {

    private static final Logger LOGGER = getLogger(Hooks.class);

    private static final ObjectMapper objectMapper = new ObjectMapper()
            .configure(ACCEPT_SINGLE_VALUE_AS_ARRAY, true);

    private static final String DEFAULT_REQUEST_ERR_MSG = "Response returned status: %s with body %s";

    private static final List<String> listOfAces = asList("AS", "AH", "AD", "AC");

    @Given("User validates that deck of cards API is available")
    public void userValidatesThatDeckOfCardsAPIIsAvailable() {
        LOGGER.info("Validating that the endpoint is available.");
        TestCaseContext.getDeckOfCardsEndpoint()
                .sendRequest(GET_REQUEST, TestCaseContext.getCONFIG().getDefaultDeckOfCardsApiUrl())
                .then()
                .statusCode(SUCCESS_STATUS_CODE);
    }

    @Given("User opens a brand new deck of cards VIA API")
    public void openBrandNewDeck() throws IOException {
        String url = TestCaseContext.getCONFIG().getDefaultDeckOfCardsApiUrl() + NEW_DECK;

        LOGGER.info("Creating new deck in TestCaseContext.");
        Response response = TestCaseContext.getDeckOfCardsEndpoint().sendRequest(GET_REQUEST, url);

        setDeckInTestCaseContext(response);
    }

    @And("User draws {int} cards from the deck")
    public void userDrawsCardsFromTheDeck(int cardCount) throws IOException {
        String deckId = TestCaseContext.getDECK().getDeckId();
        String url = TestCaseContext.getCONFIG().getDefaultDeckOfCardsApiUrl() + format(DRAW_CARDS, deckId, cardCount);

        LOGGER.info(format("Draw %s cards from deck %s in TestCaseContext.", cardCount, deckId));
        Response response = TestCaseContext.getDeckOfCardsEndpoint().sendRequest(GET_REQUEST, url);

        setDeckInTestCaseContext(response);
    }

    @Then("User validates that {int} cards are left in the deck")
    public void userValidatesThatEXPECTED_CARD_COUNTCardsAreLeftInTheDeck(int remainingCardCount) {
        int actualCardCount = TestCaseContext.getDECK().getRemainingCardCount();
        LOGGER.info(format("Validate %s cards are left in the deck. (Actual: %s)", remainingCardCount, actualCardCount));
        assertThat(
                format("Remaining card count is incorrect - expected: %s, but was: %s", remainingCardCount, actualCardCount),
                actualCardCount,
                is(remainingCardCount)
        );
    }

    @When("User creates a Custom deck containing only Aces")
    public void userCreatesACustomDeckContainingOnlyAces() throws IOException {
        StringBuilder cardList = new StringBuilder();

        for (int i = 0; i < FULL_DECK_CARD_COUNT; i++) {
            cardList.append(listOfAces.get(new Random().nextInt(listOfAces.size()))).append(",");
        }

        LOGGER.info("Creating new custom deck in TestCaseContext.");
        String url = TestCaseContext.getCONFIG().getDefaultDeckOfCardsApiUrl() + format(CUSTOM_SET, cardList.toString());
        Response response = TestCaseContext.getDeckOfCardsEndpoint().sendRequest(GET_REQUEST, url);

        setDeckInTestCaseContext(response);
    }

    @Then("User validates that only Aces are present in the list")
    public void userValidatesThatOnlyAcesArePresentInTheList() {
        LOGGER.info("Validate that only cards in the deck are Aces");
        TestCaseContext.getDECK().getCardsList().stream().forEach(card ->
                assertTrue(format("Invalid card present: %s", card.getCode()), listOfAces.contains(card.getCode())));
    }

    private void setDeckInTestCaseContext(Response response) throws IOException {
        if (response.getStatusCode() == SUCCESS_STATUS_CODE) {
            Deck newDeck = objectMapper.readValue(new StringReader(response.getBody().asString()), Deck.class);
            TestCaseContext.setDECK(newDeck);
        } else {
            throw new AssertionError(
                    format(DEFAULT_REQUEST_ERR_MSG,
                            response.getStatusCode(),
                            response.getBody().asString())
            );
        }
    }
}