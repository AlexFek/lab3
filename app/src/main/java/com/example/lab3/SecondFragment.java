package com.example.lab3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class SecondFragment extends Fragment {

    SQLiteDatabase db;
    DatabaseHelper databaseHelper;
    Cursor userCursor;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.second_frag, container, false);

        databaseHelper = new DatabaseHelper(view.getContext());
        db = databaseHelper.getReadableDatabase();
        AddListeners();
        return view;
    }

    static String getTitle(Context context, int position) {
        return "Страница № " + String.valueOf(position+1);
    }

    private void AddListeners(){
        Button button = view.findViewById(R.id.buttonAddNewNote);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = ((EditText)view.findViewById(R.id.addNewNote)).getText().toString();
                ContentValues cv = new ContentValues();
                cv.put(DatabaseHelper.COLUMN_NOTE, value);
                db.insert(DatabaseHelper.TABLE, null, cv);
            }
        });
    }

    public static SecondFragment newInstance(String text) {

        SecondFragment f = new SecondFragment();
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