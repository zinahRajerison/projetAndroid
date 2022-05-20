package com.example.lafamilledesanimaux.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lafamilledesanimaux.R;
import com.example.lafamilledesanimaux.models.CardGVAdapter;
import com.example.lafamilledesanimaux.models.CardModel;

import java.util.ArrayList;

public class Menu extends AppCompatActivity {


    GridView coursesGV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        coursesGV = findViewById(R.id.idGVcourses);

        ArrayList<CardModel> courseModelArrayList = new ArrayList<CardModel>();
        courseModelArrayList.add(new CardModel("Decouvre des animaux", R.drawable.liste));
        courseModelArrayList.add(new CardModel("Quizz", R.drawable.img18));
        courseModelArrayList.add(new CardModel("Preferences", R.drawable.parametre));

        CardGVAdapter adapter = new CardGVAdapter(this, courseModelArrayList);
        coursesGV.setAdapter(adapter);
        submitListener();
    }
    private void submitListener() {
        this.coursesGV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        Toast.makeText(Menu.this, "liste", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(Menu.this,List.class);
                        startActivity(intent);
                        break;
                    case 1:
                        Toast.makeText(Menu.this, "game", Toast.LENGTH_SHORT).show();
                        Intent intent1=new Intent(Menu.this,Fiche.class);
                        startActivity(intent1);
                        break;
                    case 2:
                        Toast.makeText(Menu.this, "parametre", Toast.LENGTH_SHORT).show();
                        Intent intent2=new Intent(Menu.this,PreferenceActivity.class);
                        startActivity(intent2);
                        break;
                }
            }
        });
    }
}