package com.citadele.tests.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Deck {

    @JsonProperty("success")
    private boolean successValidation;

    @JsonProperty("deck_id")
    private String deckId;

    @JsonProperty("remaining")
    private int remainingCardCount;

    @JsonProperty("shuffled")
    private boolean shuffled;

    @JsonProperty("cards")
    private List<Cards> cardsList;

    public String getDeckId() {
        return deckId;
    }

    public void setDeckId(String deckId) {
        this.deckId = deckId;
    }

    public int getRemainingCardCount() {
        return remainingCardCount;
    }

    public void setRemainingCardCount(int remainingCardCount) {
        this.remainingCardCount = remainingCardCount;
    }

    public boolean isShuffled() {
        return shuffled;
    }

    public void setShuffled(boolean shuffled) {
        this.shuffled = shuffled;
    }

    public List<Cards> getCardsList() {
        return cardsList;
    }

    public void setCardsList(List<Cards> cardsList) {
        this.cardsList = cardsList;
    }
}