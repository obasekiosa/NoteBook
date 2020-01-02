package com.example.notebook;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class NoteEditFragment extends Fragment {


    public NoteEditFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate fragment edit layout
        View fragmentLayout = inflater.inflate(R.layout.fragment_note_edit, container, false);

        // grab widget references from layout
        EditText title = fragmentLayout.findViewById(R.id.edit_note_title);
        EditText body = fragmentLayout.findViewById(R.id.edit_note_message);
        ImageButton noteCatButton = fragmentLayout.findViewById(R.id.edit_note_button);
        Button saveButton = fragmentLayout.findViewById(R.id.save_note_button);

        // populate widget with note data
        Intent intent = getActivity().getIntent();
        title.setText(intent.getExtras().getString(MainActivity.NOTE_TITLE_EXTRA));
        body.setText(intent.getExtras().getString(MainActivity.NOTE_MESSAGE_EXTRA));

        Note.Category noteCat = (Note.Category) intent.getSerializableExtra(MainActivity.NOTE_CATEGORY_EXTRA);
        noteCatButton.setImageResource(Note.categoryToDrawable(noteCat));

        // return fragment layout
        return fragmentLayout;
    }

}
