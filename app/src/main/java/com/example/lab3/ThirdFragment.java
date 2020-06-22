package com.example.lab3;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class ThirdFragment extends Fragment implements View.OnClickListener {

    SQLiteDatabase db;
    DatabaseHelper databaseHelper;
    View view;
    Button button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.third_frag, container, false);

        databaseHelper = new DatabaseHelper(view.getContext());
        db = databaseHelper.getReadableDatabase();
        button = (Button)view.findViewById(R.id.deleteNote);
        button.setOnClickListener(this);
        return view;
    }

    static String getTitle(Context context, int position) {
        return "Страница № " + String.valueOf(position+1);
    }

    private void AddListeners(){


    }

    @Override
    public void onClick(View v) {
        String value = ((EditText)view.findViewById(R.id.deleteNoteID)).getText().toString();
        db.delete(DatabaseHelper.TABLE, "_id = ?", new String[]{value});
    }

    public static ThirdFragment newInstance(String text) {

        ThirdFragment f = new ThirdFragment();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);

        return f;
    }

    @Override
    public void onStop() {
        super.onStop();
        db.close();
    }
}
