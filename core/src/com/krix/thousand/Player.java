package com.krix.thousand;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private List<Card> cards;


    private List<Card> hand;

    private List<Card> distanceStack;
    private List<Card> placedCards;

    public Player() {
        hand = new ArrayList<>();
        distanceStack = new ArrayList<>();
        placedCards = new ArrayList<>();
    }
    public List<Card> getHand() {
        return hand;
    }

    public List<Card> getDistanceStack() {
        return distanceStack;
    }

    public List<Card> getPlacedCards() {
        return placedCards;
    }

    public void addToHand(Card card) {
        hand.add(card);
    }

    public void addToDistanceStack(Card card) {
        distanceStack.add(card);
    }

    public void removeFromHand(Card card) {
        hand.remove(card);
    }

    public void placeCard(Card card) {
        // Check if the player can place the card based on game rules
        // For example, check if a trouble card is already placed
        // If allowed, add to placed cards
        placedCards.add(card);

        // Remove the card from the hand or inventory based on where it came from
        removeFromHand(card);
    }
}