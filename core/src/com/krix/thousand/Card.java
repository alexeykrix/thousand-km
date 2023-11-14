package com.krix.thousand;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
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
    private Table statsTable;


    public Card(CardType cardType, int distance, String troubleType, String helpType) {
        this.cardType = cardType;
        this.distance = distance;
        this.troubleType = troubleType;
        this.helpType = helpType;

        Texture cardTexture = loadTexture();

        cardImage = new Image(cardTexture);
        statsTable = new Table();

        // Set up the layout
        addActor(cardImage);
        addActor(statsTable);

        // Adjust the size and position of the card elements
        setSize(cardImage.getWidth(), cardImage.getHeight() + statsTable.getHeight());
        statsTable.setPosition(0, -statsTable.getHeight());

        // Populate the statistics table based on the card type
        populateStatsTable();
    }

    private Texture loadTexture() {
        String path = "25-km.png";

        if (distance > 0) {
            path = distance + "-km.png";
        }

        return new Texture(Gdx.files.internal(path));
    }

    private void populateStatsTable() {
        switch (cardType) {
            case DISTANCE:
                statsTable.add(createStatLabel("Distance: " + distance));
                break;
            case TROUBLE:
                statsTable.add(createStatLabel("Trouble: " + troubleType));
                break;
            case HELP:
                statsTable.add(createStatLabel("Help: " + helpType));
                break;
            case TRUMP:
                statsTable.add(createStatLabel("Trump: " + helpType));
                break;
        }
    }

    private Label createStatLabel(String text) {
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = new BitmapFont(); // Provide the desired font

        Label label = new Label(text, labelStyle);
        return label;
    }
}
