package com.example.lab3;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.cursoradapter.widget.SimpleCursorAdapter;
import androidx.fragment.app.Fragment;

public class FirstFragment extends Fragment {


    String[] notes = { "Бразилия", "Аргентина", "Колумбия", "Чили", "Уругвай"};
    private View view;
    SQLiteDatabase db;
    DatabaseHelper databaseHelper;
    SimpleCursorAdapter userAdapter;
    Cursor userCursor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.first_frag, container, false);

        ListView noteList = (ListView) view.findViewById(R.id.noteList);

        // создаем адаптер
//        ArrayAdapter<String> adapter = new ArrayAdapter(view.getContext(),
//                android.R.layout.simple_list_item_1, notes);

        // устанавливаем для списка адаптер
//        countriesList.setAdapter(adapter);

        databaseHelper = new DatabaseHelper(view.getContext());
        db = databaseHelper.getReadableDatabase();
        userCursor =  db.rawQuery("select * from "+ DatabaseHelper.TABLE, null);
        if(userCursor.moveToFirst()){
            Log.i("NOTES", String.valueOf(userCursor.getCount()));
            String name = userCursor.getString(1);
            Log.i("NOTES", name);

            Toast.makeText(view.getContext(), name, Toast.LENGTH_LONG);
        }
        String[] headers = new String[] {DatabaseHelper.COLUMN_ID, DatabaseHelper.COLUMN_NOTE};
        userAdapter = new SimpleCursorAdapter(view.getContext(), android.R.layout.two_line_list_item,
                userCursor, headers, new int[]{android.R.id.text1, android.R.id.text2}, 0);
        noteList.setAdapter(userAdapter);

        return view;
    }

    static String getTitle(Context context, int position) {
        return "Страница № " + String.valueOf(position+1);
    }

    public static FirstFragment newInstance(String text) {

        FirstFragment f = new FirstFragment();
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