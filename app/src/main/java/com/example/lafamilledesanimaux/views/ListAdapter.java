package com.example.lafamilledesanimaux.views;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
// import android.content.res.Resources;

import com.example.lafamilledesanimaux.R;
import com.example.lafamilledesanimaux.models.Animal;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter {

    private ArrayList<Animal> animal;
    private LayoutInflater inflater;
    private Context context;

    public ListAdapter(Context context, ArrayList<Animal> list){
        this.animal = list;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
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

        holder.textView4.setText(animal.get(i).getName().toString());

        String fnm = animal.get(i).getName().toLowerCase(); //  this is image file name
        String PACKAGE_NAME = context.getPackageName();
        int imgId = context.getResources().getIdentifier(PACKAGE_NAME+":drawable/"+fnm , null, null);
        holder.imageView2.setImageBitmap(BitmapFactory.decodeResource(context.getResources(),imgId));
        // displayVideo(fnm);

        holder.textView4.setTag(i);
        holder.textView4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                int position = (int) view.getTag();
                int id = animal.get((int) getItemId(position)).getId();
                ((List) context).setIndice(id);
            }
        });
        return view;
    }

    private class ViewHolder{
        TextView textView4;
        ImageView imageView2;
    }
}
