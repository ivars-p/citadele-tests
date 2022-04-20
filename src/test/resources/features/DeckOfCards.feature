Feature: Tests regards validating the functionality of http://deckofcardsapi.com/ api.

  @deckOfCards @api @l1 @DOC01TC01
  Scenario Outline: DOC01 TC01: Validate deck of cards api - card count - after drawing X cards
    Given User validates that deck of cards API is available
    When User opens a brand new deck of cards VIA API
    And User draws <RETRIEVED_CARD_AMOUNT> cards from the deck
    Then User validates that <EXPECTED_CARD_COUNT> cards are left in the deck

    Examples:
      | RETRIEVED_CARD_AMOUNT | EXPECTED_CARD_COUNT |
      | 3                     |  49                 |
      | 12                    |  40                 |
      | 0                     |  52                 |

  @deckOfCards @api @l3 @DOC01TC02
  Scenario: DOC01 TC02: Validate deck of cards api - creating custom deck
    Given User validates that deck of cards API is available
    When User creates a Custom deck containing only Aces
    And User draws 52 cards from the deck
    Then User validates that only Aces are present in the list