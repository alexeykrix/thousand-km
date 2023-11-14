package com.krix.thousand;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class GameScreen extends ScreenAdapter {

    private Game game;
    private Stage stage;
    private Player player;

    public GameScreen(Game game) {
        this.game = game;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        // Initialize a player with some cards
        player = new Player();
        player.addCard(new Card(CardType.DISTANCE, 100, null, null));
//        player.addCard(new Card(CardType.TROUBLE, 0, "Engine", null));
//        player.addCard(new Card(CardType.HELP, 0, null, "Engine"));
//        player.addCard(new Card(CardType.TRUMP, 0, null, "Right of Way"));

        // Render the player's cards
        renderPlayerCards();
    }

    private void renderPlayerCards() {
        float cardSpacing = Gdx.graphics.getWidth() / 10f;
        float cardY = 0; // Bottom of the screen

        for (Card card : player.getCards()) {
            float cardX = cardSpacing * player.getCards().indexOf(card);
            card.setPosition(cardX, cardY);
            stage.addActor(card);
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
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
