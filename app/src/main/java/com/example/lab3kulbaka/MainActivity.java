package com.example.lab3kulbaka;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase db;
    long lastInsertedId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new ClassmatesDbHelper(this).getWritableDatabase();
        db.execSQL("DELETE FROM classmates");
        db.execSQL("INSERT INTO classmates (name) VALUES ('Tom Smith');");
        db.execSQL("INSERT INTO classmates (name) VALUES ('Nataly Tomson');");
        db.execSQL("INSERT INTO classmates (name) VALUES ('Adam Von');");
        db.execSQL("INSERT INTO classmates (name) VALUES ('Carla Adams');");
        ContentValues values = new ContentValues();
        values.put("name", "Miselina Blake");
        lastInsertedId = db.insert("classmates", "", values);
    }

    public void onShowBtnClick(View view) {
        final Intent intent = new Intent(this, ShowClassmatesActivity.class);
        startActivity(intent);
    }

    public void onAddBtnClick(View view) {
        TextInputEditText input = (TextInputEditText) findViewById(R.id.input);
        if (input.getText() != null && !input.getText().toString().isEmpty()) {
            ContentValues values = new ContentValues();
            values.put("name", input.getText().toString());
            lastInsertedId = db.insert("classmates", "", values);
            input.setText("");
            Toast.makeText(this, "Запит додано!", Toast.LENGTH_SHORT).show();
        }
    }

    public void onChangeBtnClick(View view) {
        final ContentValues cv = new ContentValues();
        cv.put("name", "Іванов Іван Іванович");
        db.update("classmates", cv, "id = ?", new String[]{String.valueOf(lastInsertedId)});
        Toast.makeText(this, "Змінено на Іванов Іван Іванович!", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        if (db != null) db.close();
        super.onDestroy();
    }
}