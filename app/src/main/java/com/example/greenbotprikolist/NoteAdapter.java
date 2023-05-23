package com.example.greenbotprikolist;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class NoteAdapter extends FirestoreRecyclerAdapter<Note, NoteAdapter.DeckViewHolder> {

    MainActivity context;



    public NoteAdapter(@NonNull FirestoreRecyclerOptions<Note> options, MainActivity context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull DeckViewHolder holder, int position, @NonNull Note deck) {
        holder.deckNameTextView.setText(deck.getDeckName());
        holder.deckFormatTextView.setText(deck.getDeckFormat());
        holder.creatureTextView.setText(deck.getCreature());
        holder.landTextView.setText(deck.getLand());
        holder.sorceryTextView.setText(deck.getSorcery());
        holder.artifactTextView.setText(deck.getArtifact());
        holder.timestampTextView.setText(Utility.timestampToString(deck.getTimestamp()));


        holder.itemView.setOnClickListener((v)-> {
            Intent intent = new Intent(context,GameActivity.class);
            intent.putExtra("creature", deck.getCreature());
            intent.putExtra("land", deck.getLand());
            intent.putExtra("artifact", deck.getArtifact());
            intent.putExtra("sorcery", deck.getSorcery());
            intent.putExtra("win", deck.getWinCount());
            intent.putExtra("lose", deck.getLoseCount());
            String docId = this.getSnapshots().getSnapshot(position).getId();
            intent.putExtra("docId", docId);
            context.startActivity(intent);
        });
    }

    @NonNull
    @Override
    public DeckViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_note_item,parent, false);
        return new DeckViewHolder(view);
    }

    class DeckViewHolder extends RecyclerView.ViewHolder {

        TextView deckNameTextView, deckFormatTextView, creatureTextView, landTextView, sorceryTextView, artifactTextView, timestampTextView;

        public DeckViewHolder(@NonNull View itemView) {
            super(itemView);
            deckNameTextView = itemView.findViewById(R.id.deck_name_text_view);
            deckFormatTextView = itemView.findViewById(R.id.deck_format_text_view);
            creatureTextView = itemView.findViewById(R.id.creature_text_view);
            landTextView = itemView.findViewById(R.id.land_text_view);
            sorceryTextView = itemView.findViewById(R.id.sorcery_text_view);
            artifactTextView = itemView.findViewById(R.id.artifact_text_view);
            timestampTextView = itemView.findViewById(R.id.timestamp_note_text_view);
        }
    }
}
