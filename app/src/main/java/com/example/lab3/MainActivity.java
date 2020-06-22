package com.example.lab3;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InitializeDB();
        setContentView(R.layout.activity_main);

        ViewPager pager = (ViewPager) findViewById(R.id.viewPager);
        pager.setAdapter(new MyPagerAdapter(this, getSupportFragmentManager()));
    }

    private void InitializeDB(){
//        SQLiteDatabase db = getBaseContext().openOrCreateDatabase("app.db", MODE_PRIVATE, null);
//        db.execSQL("CREATE TABLE IF NOT EXISTS notes (note TEXT)");
//        db.execSQL("INSERT INTO notes VALUES ('Tom Smith');");
//        db.execSQL("INSERT INTO notes VALUES ('John Dow');");
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {

        private Context context = null;

        public MyPagerAdapter(Context context, FragmentManager fm) {
            super(fm);
            this.context = context;
        }



        @Override
        public Fragment getItem(int pos) {
            switch(pos) {

                case 0: return FirstFragment.newInstance("FirstFragment, Instance 1");
                case 1: return SecondFragment.newInstance("SecondFragment, Instance 1");
                case 2: return ThirdFragment.newInstance("ThirdFragment, Instance 1");
                case 3: return FourthFragment.newInstance("ThirdFragment, Instance 2");
                default: return ThirdFragment.newInstance("ThirdFragment, Default");
            }
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public String getPageTitle(int position) {

            switch(position) {

                case 0: return FirstFragment.getTitle(context, position);
                case 1: return SecondFragment.getTitle(context, position);
                case 2: return ThirdFragment.getTitle(context, position);
                case 3: return FourthFragment.getTitle(context, position);

                default: return FirstFragment.getTitle(context, position);
            }
        }
    }
}