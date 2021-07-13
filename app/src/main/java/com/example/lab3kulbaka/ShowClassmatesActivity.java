package com.example.lab3kulbaka;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;

import androidx.appcompat.app.AppCompatActivity;

public class ShowClassmatesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_classmates);
        SQLiteDatabase db = getBaseContext().openOrCreateDatabase("app.db", MODE_PRIVATE, null);
        Cursor query = db.rawQuery("SELECT * FROM classmates;", null);
        TextView textView = (TextView) findViewById(R.id.textView);
        while (query.moveToNext()) {
            int id = query.getInt(0);
            String name = query.getString(1);
            String date = query.getString(2);
            textView.append("Номер: " + id + "\nПІБ: " + name + "\nЧас: " + date + "\n");
            textView.append("-------------\n");
        }
        query.close();
        db.close();
    }
}