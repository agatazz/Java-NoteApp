package com.example.notesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

import java.util.HashMap;
import java.util.Map;


public class NewNoteActivity extends AppCompatActivity {
    private Button btnSave;
    private Button btnDelete;
    private EditText etTtile,etContent;
    private Toolbar ntoolbar;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference fnotesApp;
    private String Id;
    private Menu mainMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);

        try{
            Id=getIntent().getStringExtra("Id");
        }catch (Exception e ){
            e.printStackTrace();
        }

        btnSave=(Button) findViewById(R.id.button3);
        etTtile=(EditText) findViewById(R.id.title);
        etContent=(EditText) findViewById(R.id.editText2);
        ntoolbar=(Toolbar) findViewById(R.id.toolbar);
        btnDelete=(Button) findViewById(R.id.button4);

        setSupportActionBar(ntoolbar);
        firebaseAuth=FirebaseAuth.getInstance();
        fnotesApp= FirebaseDatabase.getInstance().getReference().child("Notes").child(firebaseAuth.getCurrentUser().getUid());

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title=etTtile.getText().toString().trim();
                String content=etContent.getText().toString().trim();


                if(!TextUtils.isEmpty(title) && !TextUtils.isEmpty(content)){
                    saveNote(title,content);
                }
                else {
                    Toast.makeText(NewNoteActivity.this,"Fill empty fields",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void saveNote(String title,String content){
        if(firebaseAuth.getCurrentUser() !=null){
            final DatabaseReference newNoteRef=fnotesApp.push();
            final Map noteMap=new HashMap();
            noteMap.put("title",title);
            noteMap.put("content",content);
            noteMap.put("timestamp", ServerValue.TIMESTAMP);

            Thread mainThread=new Thread(new Runnable() {
                @Override
                public void run() {
                    newNoteRef.setValue(noteMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(NewNoteActivity.this,"Note has been saved",Toast.LENGTH_SHORT).show();

                            }
                            else{
                                Toast.makeText(NewNoteActivity.this,"Could not save the note",Toast.LENGTH_SHORT).show();

                            }
                        }
                    });

                }
            });
            mainThread.start();



        }
        else{
            Toast.makeText(this,"User has to be signed in",Toast.LENGTH_SHORT).show();
        }


    }

    private void deleteNote(){
        fnotesApp.child(Id).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(NewNoteActivity.this,"Deleted",Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(NewNoteActivity.this,"Could not resolve",Toast.LENGTH_SHORT).show();

                }

            }
        });
    }

}
