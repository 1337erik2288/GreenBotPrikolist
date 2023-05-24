package com.example.greenbotprikolist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;

public class NoteDetailsActivity extends AppCompatActivity {

    EditText nameDeckEditText, formatDeckEditText, creatureEditText, landEditText, sorceryEditText, artifactEditText;
    ImageButton saveDeckBtn;

    TextView pageTitleTextView;

    String nameDeck, formatDeck, creature, land, sorcery, artifact, winCount, loseCount, docId;
    boolean isEditMode = false;
    TextView deleteDeckTextViewBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_details);

        nameDeckEditText = findViewById(R.id.deck_name_text);
        formatDeckEditText = findViewById(R.id.deck_format_text);
        creatureEditText = findViewById(R.id.creature_text);
        landEditText = findViewById(R.id.land_text);
        sorceryEditText = findViewById(R.id.sorcery_text);
        artifactEditText = findViewById(R.id.artifact_text);
        saveDeckBtn = findViewById(R.id.save_deck_btn);
        pageTitleTextView = findViewById(R.id.page_title);
        deleteDeckTextViewBtn = findViewById(R.id.delete_note_text_btn);

        winCount = String.valueOf(0);
        loseCount = String.valueOf(0);
        nameDeck = getIntent().getStringExtra("nameDeck");
        formatDeck = getIntent().getStringExtra("formatDeck");
        creature = getIntent().getStringExtra("creature");
        land = getIntent().getStringExtra("land");
        sorcery = getIntent().getStringExtra("sorcery");
        artifact = getIntent().getStringExtra("artifact");
        docId = getIntent().getStringExtra("docId");

        if (docId!=null && !docId.isEmpty()){
            isEditMode = true;
        }

        nameDeckEditText.setText(nameDeck);
        formatDeckEditText.setText(formatDeck);
        creatureEditText.setText(creature);
        landEditText.setText(land);
        artifactEditText.setText(artifact);
        sorceryEditText.setText(sorcery);

        if (isEditMode){
            pageTitleTextView.setText("Edit your deck");
            deleteDeckTextViewBtn.setVisibility(View.VISIBLE);
        }


        saveDeckBtn.setOnClickListener((v)-> saveDeck());

        deleteDeckTextViewBtn.setOnClickListener((v)-> deleteDeckFromFirebase());
    }

    public void saveDeck(){
        String deckName = nameDeckEditText.getText().toString();
        String formatDeck = formatDeckEditText.getText().toString();
        String creature = creatureEditText.getText().toString();
        String land = landEditText.getText().toString();
        String sorcery = sorceryEditText.getText().toString();
        String artifact = artifactEditText.getText().toString();
        if(deckName==null || deckName.isEmpty()){
            nameDeckEditText.setError("Title is empty");
            return;
        }

        Note deck = new Note();

        deck.setDeckName(deckName);
        deck.setDeckFormat(formatDeck);
        deck.setCreature(creature);
        deck.setLand(land);
        deck.setSorcery(sorcery);
        deck.setArtifact(artifact);
        deck.setWinCount(winCount);
        deck.setLoseCount(loseCount);
        deck.setTimestamp(Timestamp.now());
        saveDeckToFirebase(deck);

    }

    public void saveDeckToFirebase(Note deck){
        DocumentReference documentReference;
        if (isEditMode){
            documentReference = Utility.getCollectionReferenceForNotes().document(docId);
        }else {
            documentReference = Utility.getCollectionReferenceForNotes().document();
        }


        documentReference.set(deck).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Utility.showToast(NoteDetailsActivity.this, "Deck added!");
                    finish();
                }else{
                    Utility.showToast(NoteDetailsActivity.this, "Failed...");
                }
            }
        });
    }

    public void deleteDeckFromFirebase(){
        DocumentReference documentReference;
        documentReference = Utility.getCollectionReferenceForNotes().document(docId);



        documentReference.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Utility.showToast(NoteDetailsActivity.this, "Deck deleted!");
                    finish();
                }else{
                    Utility.showToast(NoteDetailsActivity.this, "Failed...");
                }
            }
        });
    }
}