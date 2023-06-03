package com.example.easytutonotes;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import io.realm.Realm;

public class AddNoteActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_note);

        EditText titleInput = findViewById(R.id.titleinput);
        EditText descriptionInput = findViewById(R.id.descriptioninput);
        Button saveBtn = findViewById(R.id.savebtn);

        Realm.init(getApplicationContext());
        Realm realm = Realm.getDefaultInstance();

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveBtn.setBackgroundColor(ContextCompat.getColor(
                        AddNoteActivity.this, R.color.design_default_color_primary_dark));

                String title = titleInput.getText().toString();
                String description = descriptionInput.getText().toString();
                long createdTime = System.currentTimeMillis();

                // save in db ... ->
                realm.beginTransaction();
                Note note = realm.createObject(Note.class);
                note.setTitle(title);
                note.setDescription(description);
                note.setCreatedTime(createdTime);
                realm.commitTransaction();

                Toast.makeText(getApplicationContext(), "Note saved", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }
}