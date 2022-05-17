package com.example.lafamilledesanimaux.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lafamilledesanimaux.R;
import com.example.lafamilledesanimaux.models.Animal;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter {

    private ArrayList<Animal> animal;
    private LayoutInflater inflater;

    public ListAdapter(Context context, ArrayList<Animal> list){
        this.animal = list;
        this.inflater = LayoutInflater.from(context);
    }

    /**
     * retourne le nombre de ligne
     * @return
     */
    @Override
    public int getCount() {
        return animal.size();
    }

    /**
     * retourne l'item de la ligne actuelle
     * @param i
     * @return
     */
    @Override
    public Object getItem(int i) {
        return animal.get(i);
    }

    /**
     * retrourne un indice par rapport à la ligne actuelle
     * @param i
     * @return
     */
    @Override
    public long getItemId(int i) {
        return i;
    }

    /**
     * retourne la ligne formatée
     * @param i
     * @param view
     * @param viewGroup
     * @return
     */
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null){
            holder = new ViewHolder();

            // la ligne est construite avec un formatage relié à layout_list_animal
            view = inflater.inflate(R.layout.layout_list_animal, null);

            // chaque propriete du holder est reliee a une propriete graphique
            holder.textView4 = (TextView) view.findViewById(R.id.textView4);
            holder.imageView2 = (ImageView) view.findViewById(R.id.imageView2);

            // affecter le holder a la vue
            view.setTag(holder);
        }else{
            // recuperation du holder dans la vue
            holder = (ViewHolder) view.getTag();
        }

        // valorisation du contenu du holder (de la ligne)
        holder.textView4.setText(animal.get(i).getName().toString());
        holder.imageView2.setImageResource(Integer.parseInt(animal.get(i).getImg()));
        holder.textView4.setTag(i);
        return view;
    }

    private class ViewHolder{
        TextView textView4;
        ImageView imageView2;
    }
}
