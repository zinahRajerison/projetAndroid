package com.example.lafamilledesanimaux.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.lafamilledesanimaux.R;
import com.example.lafamilledesanimaux.models.Animal;

import java.util.ArrayList;

public class List extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
    }

    /**
     * creer la liste adapter
     */
    private void createList(){
        ArrayList<Animal> animallist = new Animal().getList();
        if(animallist != null){
            ListView list = (ListView) findViewById(R.id.listeview);
            ListAdapter listeadapter = new ListAdapter(this, animallist);
            list.setAdapter(listeadapter);

        }
    }

}