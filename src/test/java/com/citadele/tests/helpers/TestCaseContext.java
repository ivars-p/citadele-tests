package com.citadele.tests.helpers;

import com.citadele.tests.config.Config;
import com.citadele.tests.endPoints.DeckOfCardsEndpoint;
import com.citadele.tests.model.Deck;

public class TestCaseContext {

    private static Config CONFIG = Config.getInstance();

    private static ThreadLocal<DeckOfCardsEndpoint> DECK_OF_CARDS_ENDPOINT = new ThreadLocal<>();

    private static ThreadLocal<Deck> DECK = new ThreadLocal<>();

    public static DeckOfCardsEndpoint getDeckOfCardsEndpoint() {
        return DECK_OF_CARDS_ENDPOINT.get();
    }

    public static void setDeckOfCardsEndpoint(DeckOfCardsEndpoint endpoint) {
        DECK_OF_CARDS_ENDPOINT.set(endpoint);
    }

    public static Deck getDECK() {
        return DECK.get();
    }

    public static void setDECK(Deck deck) {
        DECK.set(deck);
    }

    public static Config getCONFIG() {
        return TestCaseContext.CONFIG;
    }
}