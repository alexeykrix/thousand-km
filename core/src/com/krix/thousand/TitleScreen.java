package com.krix.thousand;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

public class TitleScreen extends ScreenAdapter {

    private Thousand game;
    private SpriteBatch batch;
    private BitmapFont font;
    private CharSequence text = "Play!";
    private float textX, textY;

    public TitleScreen(Thousand game) {
        this.game = game;
        batch = new SpriteBatch();
        font = new BitmapFont();

        font.getData().setScale(Gdx.graphics.getWidth() * .01f);

        // Calculate the position of the text
        GlyphLayout layout = new GlyphLayout(font, text);
        textX = (Gdx.graphics.getWidth() - layout.width) / 2;
        textY = (Gdx.graphics.getHeight() + layout.height) / 2;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        // Draw the text
        font.draw(batch, text, textX, textY);
        batch.end();

        if (Gdx.input.isTouched()) {
            float touchX = Gdx.input.getX();
            float touchY = Gdx.graphics.getHeight() - Gdx.input.getY(); // Invert Y-coordinate

            GlyphLayout layout = new GlyphLayout(font, text);

            if (touchX >= textX && touchX <= textX + layout.width &&
                    touchY >= textY - layout.height && touchY <= textY) {
                // Handle click on the text
                game.setScreen(new GameScreen(game));
            }
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}
