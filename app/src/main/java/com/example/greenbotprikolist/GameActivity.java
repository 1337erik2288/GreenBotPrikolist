package com.example.greenbotprikolist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    TextView deckNameEditText, deckFormatEditText, creatureCount, sorceryCount, landCount, artifactCount, creatureInformation, sorceryInformation, landInformation, artifactInformation, winInformation, loseInformation;

    ImageButton plusCreatureBtn, minusCreatureBtn, plusSorceryBtn, minusSorceryBtn, plusLandBtn, minusLandBtn, plusArtifactBtn, minusArtifactBtn, winBtn, loseBtn;
    String  nameDeck, formatDeck, creature, land, sorcery, artifact,win, lose, docId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        deckNameEditText = findViewById(R.id.game_deck_name_edit);
        deckFormatEditText = findViewById(R.id.game_deck_format_edit);
        creatureCount = findViewById(R.id.game_creature_edit);
        sorceryCount = findViewById(R.id.game_sorcery_edit);
        landCount = findViewById(R.id.game_land_edit);
        artifactCount = findViewById(R.id.game_artifact_edit);
        creatureInformation = findViewById(R.id.game_deck_creature_information);
        sorceryInformation = findViewById(R.id.game_deck_sorcery_information);
        landInformation = findViewById(R.id.game_deck_land_information);
        artifactInformation = findViewById(R.id.game_deck_artifact_information);
        winInformation = findViewById(R.id.game_deck_win_information);
        loseInformation = findViewById(R.id.game_deck_lose_information);
        plusCreatureBtn = findViewById(R.id.plus_creature_btn);
        minusCreatureBtn = findViewById(R.id.minus_creature_btn);
        plusSorceryBtn = findViewById(R.id.plus_sorcery_btn);
        minusSorceryBtn = findViewById(R.id.minus_sorcery_btn);
        plusLandBtn = findViewById(R.id.plus_land_btn);
        minusLandBtn = findViewById(R.id.minus_land_btn);
        plusArtifactBtn = findViewById(R.id.plus_artifact_btn);
        minusArtifactBtn = findViewById(R.id.minus_artifact_btn);
        winBtn = findViewById(R.id.add_win_btn);
        loseBtn = findViewById(R.id.add_lose_btn);

        nameDeck = getIntent().getStringExtra("nameDeck");
        formatDeck = getIntent().getStringExtra("formatDeck");
        creature = getIntent().getStringExtra("creature");
        land = getIntent().getStringExtra("land");
        sorcery = getIntent().getStringExtra("sorcery");
        artifact = getIntent().getStringExtra("artifact");
        win = getIntent().getStringExtra("win");
        lose = getIntent().getStringExtra("lose");
        docId = getIntent().getStringExtra("docId");

        deckNameEditText.setText(nameDeck);
        deckFormatEditText.setText(formatDeck);
        creatureCount.setText(creature);
        landCount.setText(land);
        artifactCount.setText(artifact);
        sorceryCount.setText(sorcery);
        winInformation.setText(win);
        loseInformation.setText(lose);
        creatureInformation.setText(creature);
        OnChange();
        plusCreatureBtn.setOnClickListener((v)-> {
            Float help = Float.parseFloat(String.valueOf(creatureCount.getText()));
            help++;
            creatureCount.setText(String.valueOf(help));
            OnChange();
        });
    }
    public void OnChange(){

        Float sum, helpCreature, helpSorcery, helpLand, helpArtifact;

        sum = Float.parseFloat(String.valueOf(creatureCount.getText())) + Float.parseFloat(String.valueOf(landCount.getText())) + Float.parseFloat(String.valueOf(artifactCount.getText())) + Float.parseFloat(String.valueOf(sorceryCount.getText()));
        helpCreature = Float.parseFloat(String.valueOf(creatureCount.getText()))/sum*100;
        helpSorcery = Float.parseFloat(sorcery)/sum*100;
        helpLand = Float.parseFloat(land)/sum*100;
        helpArtifact= Float.parseFloat(artifact)/sum*100;
        creatureInformation.setText(String.valueOf(helpCreature));
        sorceryInformation.setText(String.valueOf(helpSorcery));
        landInformation.setText(String.valueOf(helpLand));
        artifactInformation.setText(String.valueOf(helpArtifact));
    }
}