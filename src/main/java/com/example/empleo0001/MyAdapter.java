package com.example.empleo0001;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.Vibrator;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter {
    private Context context;
    private ArrayList<ItemContenedor> listItems;
    private final View.OnClickListener mOnClickListener=null;
    public MyAdapter(Context context,ArrayList<ItemContenedor> listItems) {
        this.context = context;
        this.listItems=listItems;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder( ViewGroup viewGroup, int i) {
        //crea la vista del item
       View conteView= LayoutInflater.from(context).inflate(R.layout.contenedor,null);
        viewGroup.setOnClickListener(mOnClickListener);
        System.out.println("se crea la vista :"+i);
        return new MyViewHolder(conteView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int i) {
        //asignamos datos delos items
        ItemContenedor item=listItems.get(i);//lalclase  igualams ala lista
        final    Vibrator vibra=(Vibrator)context.getSystemService(context.VIBRATOR_SERVICE);

        MyViewHolder ho=(MyViewHolder) viewHolder;
        Bitmap icon = BitmapFactory.decodeResource(ho.itemView.getResources(),item.getFoto());
        ho.foto.setImageBitmap(icon);
        ho.puestov.setText(item.getPuesto());
        ho.contenidov.setText(item.getDescripccion());
        ho.fechav.setText(item.getFecha());
        System.out.println("muestra item"+i);
        ho.foto.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"cRGANDO NUEVO ITEM"+i, Toast.LENGTH_LONG).show();
                httpConectionphp p=new httpConectionphp("http://192.168.0.10/dashboard/android/ConectionphpAndroid.php?sql=SELECT*%20FROM%20vacante");
                p.start();
                vibra.vibrate(50);


            }
        });
        ho.contenidov.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
         //       Toast.makeText(context,"texto:"+i,Toast.LENGTH_LONG).show();
                vibra.vibrate(50);
            }
        });
    }
    @Override
    public int getItemCount() {
        System.out.println("inicia priemro???");
//indica la cantidad de intems a crear en ell recicle list
        return listItems.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ///definir campo del item
        public ImageView foto;
        public TextView puestov;
        public TextView contenidov;
        public TextView fechav;
        public MyViewHolder(@NonNull final View itemView) {
            super(itemView);
            foto=(ImageView) itemView.findViewById(R.id.image_circle);
            foto=(ImageView)itemView.findViewById(R.id.image_circle);
            puestov=(TextView) itemView.findViewById(R.id.puesto);
            contenidov=(TextView) itemView.findViewById(R.id.contenido);
            fechav=(TextView)itemView.findViewById(R.id.fech_p);
        }
        }


private class httpConectionphp extends Thread{
    String urlL;
    public httpConectionphp(String urlL) {
        this.urlL = urlL;
    }
        @Override
    public void run(){
            try{

            URL link = new URL(urlL);
            HttpURLConnection http= (HttpURLConnection) link.openConnection();
            http.setRequestMethod("GET");
            http.setRequestProperty("Cnontent-length","0");
            http.setUseCaches(false);
            http.setAllowUserInteraction(false);
            http.setConnectTimeout(15000);
            http.setReadTimeout(15000);
            http.connect();
            int resp=http.getResponseCode();
            if(resp==HttpURLConnection.HTTP_OK){
                BufferedReader bfr=new BufferedReader(new InputStreamReader(http.getInputStream()));
                String line="";
                StringBuilder sb= new StringBuilder();
                while ((line=bfr.readLine())!=null){
                    sb.append(line+"\n");
                    System.out.println(line+"\n");
                    Thread.sleep(32);
                }
                bfr.close();
                http.disconnect();
                desZIpper(sb);
                Thread.sleep(10);
        }
            http.disconnect();
            }catch (MalformedURLException e){
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        public void desZIpper(StringBuilder sb){
            if(sb!=null){
                try {
                    JSONArray jsa = new JSONArray(sb.toString().trim());
                    listItems.clear();
                    for(int i=0;i<jsa.length();i++){
                        JSONObject jso=jsa.optJSONObject(i);
                        listItems.add(new ItemContenedor(R.drawable.mari,jso.getString("titulo"),jso.getString("contenido"),jso.getString("fecha_p")));
                        Thread.sleep(20);
                    }
//                    Intent in=new Intent(context,MainActivity.class);
//                    in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    //context.startActivity(in);
                }catch (JSONException e){
                    e.printStackTrace();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }

            }
        }
        }
}


