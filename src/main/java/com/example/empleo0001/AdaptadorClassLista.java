package com.example.empleo0001;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

class AdaptadorClassLista extends BaseAdapter {
    Context context;
    String puesto[];
    String contenido[];
    String fecha[];
    int bandera[];
    LayoutInflater inflater;
    public AdaptadorClassLista(Context applicatinContext, String[]puesto, String []contenido, int[] bandera, String[] fecha){
        this.context = context;
        this.puesto=puesto;
        this.contenido=contenido;
        this.bandera=bandera;
        this.fecha=fecha;
        inflater=(LayoutInflater.from(applicatinContext));
        }
    @Override
    public int getCount() {return puesto.length;}
    @Override
    public Object getItem(int i) {
        return null;
    }
    @Override
    public long getItemId(int i) {
        return 0;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view=inflater.inflate(R.layout.contenedor,null);
        TextView titulov = (TextView) view.findViewById(R.id.puesto);
        TextView contenidov = (TextView) view.findViewById(R.id.contenido);
        TextView fechav=(TextView)view.findViewById(R.id.fech_p);
        ImageView  imageViewRound=(ImageView)view.findViewById(R.id.image_circle);
        imageViewRound=(ImageView)view.findViewById(R.id.image_circle);
        Bitmap icon = BitmapFactory.decodeResource(view.getResources(),bandera[i]);
        imageViewRound.setImageBitmap(icon);
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        titulov.setText(puesto[i]);
        contenidov.setText(contenido[i]);
        fechav.setText(fecha[i]);
        return view;
    }

}