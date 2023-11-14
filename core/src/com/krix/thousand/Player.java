package com.krix.thousand;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private List<Card> cards;

    public Player() {
        cards = new ArrayList<>();
    }

    public List<Card> getCards() {
        return cards;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void removeCard(Card card) {
        cards.remove(card);
    }
}


//        addCard(new Card(CardType.DISTANCE, 100, null, null, new Texture("25-km.png")));
//        addCard(new Card(CardType.TROUBLE, 0, "Engine", null, new Texture("25-km.png")));
//        addCard(new Card(CardType.HELP, 0, null, "Engine", new Texture("25-km.png")));
//        addCard(new Card(CardType.TRUMP, 0, null, "Right of Way", new Texture("25-km.png")));