package com.krix.thousand;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import java.nio.charset.Charset;

import sun.awt.CharsetString;


public class Card extends WidgetGroup {

    private CardType cardType;
    private int distance; // For distance cards
    private String troubleType; // For trouble cards
    private String helpType; // For help cards

    private Image cardImage;

    public float cardHeight;
    public float cardWidth;

    public Card(CardType cardType, int distance, String troubleType, String helpType) {
        this.cardType = cardType;
        this.distance = distance;
        this.troubleType = troubleType;
        this.helpType = helpType;

        Texture cardTexture = loadTexture();

        cardImage = new Image(cardTexture);

        // Set up the layout
        addActor(cardImage);


        cardWidth = (Gdx.graphics.getWidth() - 30f) / 4f;
        cardHeight = cardWidth * (cardImage.getHeight() / cardImage.getWidth());


        cardImage.setSize(cardWidth, cardHeight);
    }

    private Texture loadTexture() {
        String path = "25-km.png";

        if (distance > 0) {
            path = distance + "-km.png";
        }

        return new Texture(Gdx.files.internal(path));
    }


}
