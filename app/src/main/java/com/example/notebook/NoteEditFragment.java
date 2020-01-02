package com.example.notebook;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
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

    private ImageButton noteCatButton;
    private Note.Category savedButtonCategory;
    private AlertDialog categoryDialogObject, confirmDialogObject;
    private EditText title, body;

    public NoteEditFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate fragment edit layout
        View fragmentLayout = inflater.inflate(R.layout.fragment_note_edit, container, false);

        // grab widget references from layout
        title = fragmentLayout.findViewById(R.id.edit_note_title);
        body = fragmentLayout.findViewById(R.id.edit_note_message);
        noteCatButton = fragmentLayout.findViewById(R.id.edit_note_button);
        Button saveButton = fragmentLayout.findViewById(R.id.save_note_button);

        // populate widget with note data
        Intent intent = getActivity().getIntent();
        title.setText(intent.getExtras().getString(MainActivity.NOTE_TITLE_EXTRA));
        body.setText(intent.getExtras().getString(MainActivity.NOTE_MESSAGE_EXTRA));

        Note.Category noteCat = (Note.Category) intent.getSerializableExtra(MainActivity.NOTE_CATEGORY_EXTRA);
        savedButtonCategory = noteCat;
        noteCatButton.setImageResource(Note.categoryToDrawable(noteCat));

        buildCategoryDialog();
        buildConfirmDialog();


        noteCatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryDialogObject.show();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialogObject.show();
            }
        });

        // return fragment layout
        return fragmentLayout;
    }

    private void buildCategoryDialog() {
        final String[] categories = new String[]{"Personal", "Technical", "Quote", "Finance"};
        AlertDialog.Builder categoryBuilder = new AlertDialog.Builder(getActivity());
        categoryBuilder.setTitle("Choose Note Type");

        categoryBuilder.setSingleChoiceItems(categories, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                // dismisses our dialog window
                categoryDialogObject.cancel();

                switch (item) {
                    case 0:
                        savedButtonCategory = Note.Category.PERSONAL;
                        noteCatButton.setImageResource(R.drawable.a);
                        break;
                    case 1:
                        savedButtonCategory = Note.Category.TECHNICAL;
                        noteCatButton.setImageResource(R.drawable.b);
                        break;
                    case 2:
                        savedButtonCategory = Note.Category.QUOTE;
                        noteCatButton.setImageResource(R.drawable.c);
                        break;
                    case 3:
                        savedButtonCategory = Note.Category.FINANCE;
                        noteCatButton.setImageResource(R.drawable.bg);
                        break;

                }
            }
        });

        categoryDialogObject = categoryBuilder.create();

    }

    private void buildConfirmDialog() {
        AlertDialog.Builder confrimBuilder = new AlertDialog.Builder(getActivity());
        confrimBuilder.setTitle("Are you sure");
        confrimBuilder.setMessage("Are you sure you want to save the note");

        confrimBuilder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.d("Save note", "Note title: " + title.getText() + "Note Message: "
                        + body.getText() + " Note category: " + savedButtonCategory);

                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });

        confrimBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // do nothing here
            }
        });

        confirmDialogObject = confrimBuilder.create();
    }

}
