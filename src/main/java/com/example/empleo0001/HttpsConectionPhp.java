package com.example.empleo0001;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpsConectionPhp {
    public StringBuilder ConectionPhp(String linUrl){
        try {
            URL link = new URL(linUrl);
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
                Thread.sleep(10);
                return sb;
            }
            http.disconnect();
        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
