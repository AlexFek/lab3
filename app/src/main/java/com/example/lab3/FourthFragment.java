package com.example.lab3;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

public class FourthFragment extends Fragment implements View.OnClickListener {

    SQLiteDatabase db;
    DatabaseHelper databaseHelper;
    View view;
    Button button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fourth_frag, container, false);
        databaseHelper = new DatabaseHelper(view.getContext());
        db = databaseHelper.getReadableDatabase();
        button = (Button)view.findViewById(R.id.updateNoteButton);
        button.setOnClickListener(this);
        return view;
    }

    static String getTitle(Context context, int position) {
        return "Страница № " + String.valueOf(position+1);
    }

    public static FourthFragment newInstance(String text) {

        FourthFragment f = new FourthFragment();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);

        return f;
    }

    @Override
    public void onClick(View view) {
        String id = ((EditText)this.view.findViewById(R.id.updateNoteID)).getText().toString();
        String value = ((EditText)this.view.findViewById(R.id.updateNoteText)).getText().toString();
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.COLUMN_NOTE, value);
        db.update(DatabaseHelper.TABLE, cv, DatabaseHelper.COLUMN_ID + "=" + id, null);
    }

    @Override
    public void onStop() {
        super.onStop();
        db.close();
    }
}
