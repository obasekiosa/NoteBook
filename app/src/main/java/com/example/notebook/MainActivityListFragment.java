package com.example.notebook;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainActivityListFragment extends ListFragment {

    private ArrayList<Note> notes;
    private NoteAdapter noteAdapter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        notes = new ArrayList<Note>();
        for( int i =0; i < 5; i++) {
            notes.add(new Note("Quantum Tunnelling",
                    "well this isn't an easy one to explain", Note.Category.TECHNICAL));

            notes.add(new Note("Quantum ",
                    "There are 6 images in it, when application start, it will show image1.png.", Note.Category.FINANCE));

            notes.add(new Note("Uquinola systems  bla balalaldjdjdjddjdjdjdduffffffffffffffffff",
                    "Systema systema When you click the image, it will show another image in order.", Note.Category.QUOTE));

            notes.add(new Note("Tunnelling",
                    "When you click the image, it will show another image in order.", Note.Category.PERSONAL));


        }
        noteAdapter = new NoteAdapter(getActivity(), notes);

        setListAdapter(noteAdapter);


    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        launchNoteDetailActivity(position);
    }

    private void launchNoteDetailActivity(int position) {

        // get note information concerning note item clicked on
        Note note = (Note) getListAdapter().getItem(position);

        // create new intent and populate with information needed by new activity
        Intent intent = new Intent(getActivity(), NoteDetailActivity.class);
        intent.putExtra(MainActivity.NOTE_TITLE_EXTRA, note.getTitle());
        intent.putExtra(MainActivity.NOTE_MESSAGE_EXTRA, note.getMessage());
        intent.putExtra(MainActivity.NOTE_CATEGORY_EXTRA, note.getCategory());
        intent.putExtra(MainActivity.NOTE_ID_EXTRA, note.getNoteId());

        startActivity(intent);
    }
}
