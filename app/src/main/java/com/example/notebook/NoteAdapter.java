package com.example.notebook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class NoteAdapter extends ArrayAdapter<Note> {

    public static class ViewHolder {
        TextView title;
        TextView noteBody;
        ImageView noteIcon;
    }

    public NoteAdapter(Context context, ArrayList<Note> notes) {
        super(context, 0, notes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Note note = getItem(position);

        // create a new viewHolder
        ViewHolder viewHolder;

        if (convertView == null) {

            // to save our view references to
            viewHolder = new ViewHolder();

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_row, parent, false);

            viewHolder.title = (TextView) convertView.findViewById(R.id.list_item_title);
            viewHolder.noteBody = (TextView) convertView.findViewById(R.id.list_item_body);
            viewHolder.noteIcon = (ImageView) convertView.findViewById(R.id.list_item_note_img);

            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.title.setText(note.getTitle());
        viewHolder.noteBody.setText(note.getMessage());
        viewHolder.noteIcon.setImageResource(note.getAssociatedDrawable());

        return convertView;
    }

}
