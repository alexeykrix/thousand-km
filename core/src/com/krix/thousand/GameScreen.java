package com.krix.thousand;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.List;

public class GameScreen extends ScreenAdapter {

    private Game game;
    private Stage stage;
    private Player player;
    private Player opponent;

    ClickListener cardClickListener;

    public GameScreen(Game game) {
        this.game = game;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        cardClickListener = new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Card clickedCard = (Card) event.getTarget();
                handleCardClick(clickedCard);
            }
        };

        // Add the cardClickListener to each card in the player's hand
//        for (Card card : player.getHand()) {
//            card.addListener(cardClickListener);
//        }

        player = new Player();
        opponent = new Player();


        drawInitialHand(player);
    }

    private void handleCardClick(Card clickedCard) {
        // Check if the clicked card can be placed based on game rules
        // For example, check if it's a distance card or a valid trouble/help card

        // Assuming you have a method to determine if the card can be placed
        if (canCardBePlaced(clickedCard)) {
            player.placeCard(clickedCard);
        }
    }

    private boolean canCardBePlaced(Card card) {
        return true;
    }


    private void drawInitialHand(Player player) {
        for (int i = 0; i < 4; i++) {
            Card randomCard = drawCardFromStack();
            player.addToHand(randomCard);
        }
    }

    private Card drawCardFromStack() {
//        CardType[] cardTypes = CardType.values();
//        CardType randomType = cardTypes[MathUtils.random(cardTypes.length - 1)];

        int[] distances = { 25, 50, 75, 100, 200 };
        int distance = distances[MathUtils.random((distances.length - 1))];

        return new Card(CardType.DISTANCE, distance, null, null);
    }

    private void renderPlayerHand(Player player) {
        float cardSpacing = 10f;
        float cardY = 0;

        for (Card card : player.getHand()) {
            int cardIndex = player.getHand().indexOf(card);

            float cardX = cardIndex * (card.cardWidth + cardSpacing );
            card.setPosition(cardX, cardY);
//            card.addListener(cardClickListener);
            stage.addActor(card);

        }


        float stackSpacing = Gdx.graphics.getWidth() / 6f;
        float stackY = Gdx.graphics.getHeight() * 0.75f;

        // Render distance stack
        float distanceStackX = stackSpacing;
        renderCardStack(player.getDistanceStack(), distanceStackX, stackY);

        // Render trouble/help stack using placedCards
        float placedStackX = stackSpacing * 4f;
        renderCardStack(player.getPlacedCards(), placedStackX, stackY);
    }

    private void renderCardStack(List<Card> stack, float stackX, float stackY) {
        float cardSpacing = Gdx.graphics.getWidth() / 20f;

        for (Card card : stack) {
            float cardX = stackX + cardSpacing * stack.indexOf(card);
            card.setPosition(cardX, stackY);
            stage.addActor(card);
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        renderPlayerHand(player);
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
