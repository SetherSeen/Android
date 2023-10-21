package com.example.empleo0001;

import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList listIt=new ArrayList<ItemContenedor>();
   private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista);
        recyclerView = (RecyclerView) findViewById(R.id.lisaR);
        new HttpConnectionServerV().execute("http://192.168.0.10/dashboard/android/ConectionphpAndroid.php?sql=SELECT*%20FROM%20vacante");
    /*    listaE = (ListView) findViewById(R.id.listado);
        new HttpConnectionServerV().execute("http://192.168.0.10/dashboard/android/ConectionphpAndroid.php?sql=SELECT*%20FROM%20vacante");
*/
    }
    /*public   ArrayList<ItemContenedor> generateItems(){
        ArrayList listIt=new ArrayList();
        listIt.add(new ItemContenedor(R.drawable.mari,"dfdsfdsfds","54654","ewew"));
        return listIt;
    }*/

    public class HttpConnectionServerV extends AsyncTask<String, Void, StringBuilder> {

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        protected StringBuilder doInBackground(String... params) {
           HttpsConectionPhp p=new  HttpsConectionPhp();
          return p.ConectionPhp(params[0]);
        }

        @Override
        protected void onPostExecute(StringBuilder sb) {
            if(sb!=null){
                try {
                    JSONArray jsa = new JSONArray(sb.toString().trim());

                    for(int i=0;i<jsa.length();i++){
                        JSONObject jso=jsa.optJSONObject(i);
                        listIt.add(new ItemContenedor(R.drawable.mari,jso.getString("titulo"),jso.getString("contenido"),jso.getString("fecha_p")));
                    // AdaptadorClassLista adapta=new AdaptadorClassLista(getBaseContext(),)
                        Thread.sleep(20);
                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                recyclerView.setHasFixedSize(true);
                layoutManager = new LinearLayoutManager(getBaseContext());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.addItemDecoration(new DividerItemDecoration(getBaseContext(),LinearLayoutManager.VERTICAL));
                mAdapter = new MyAdapter(getBaseContext(),listIt);
                recyclerView.setAdapter(mAdapter);

            }
        }

    }



}



