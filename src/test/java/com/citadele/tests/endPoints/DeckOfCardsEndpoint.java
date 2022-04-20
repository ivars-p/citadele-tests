package com.citadele.tests.endPoints;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class DeckOfCardsEndpoint {
    public static final int SUCCESS_STATUS_CODE = 200;

    public static final int GET_REQUEST = 0;

    public static final int POST_REQUEST = 1;

    public static final int DELETE_REQUEST = 2;

    public static final int PUT_REQUEST = 3;

    public static final String NEW_DECK = "api/deck/new/";

    public static final String DRAW_CARDS = "api/deck/%s/draw/?count=%s";

    public static final String CUSTOM_SET = "api/deck/new/shuffle/?cards=%s";

    public static final int FULL_DECK_CARD_COUNT = 52;

    public DeckOfCardsEndpoint() {}

    public Response sendRequest(int requestType, String url) {
        Response response;

        switch (requestType) {
            case GET_REQUEST:
                response = RestAssured.when().get(url);
                break;
            case POST_REQUEST:
                response = RestAssured.when().post(url);
                break;
            case DELETE_REQUEST:
                response = RestAssured.when().delete(url);
                break;
            case PUT_REQUEST:
                response = RestAssured.when().put(url);
                break;
            default:
                throw new AssertionError("Such option is not available!");
        }
        return response;
    }
}