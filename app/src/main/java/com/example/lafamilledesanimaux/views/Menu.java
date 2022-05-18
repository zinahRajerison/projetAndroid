package com.example.lafamilledesanimaux.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

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
    }
}