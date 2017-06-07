package com.example.aishwaryabommisetty.cibo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Aishwarya Bommisetty on 06-06-2017.
 */

public class BackgroundWorker extends AsyncTask<String,Void,String> {
    Context context;
    AlertDialog ad;
    BackgroundWorker (Context ctx)
    {
        context=ctx;
    }
    @Override
    protected String doInBackground(String... params) {
        String type=params[0];
        String username=params[1];
        String password=params[2];
        String login_url="https://dipsomaniac-structu.000webhostapp.com/login.php";
        if(type.equals("Login"))
        {
            try {
                Log.d("Tag","Entered IF");
                URL url=new URL(login_url);
                HttpURLConnection huc=(HttpURLConnection)url.openConnection();
                huc.setRequestMethod("POST");
                huc.setDoInput(true);
                huc.setDoInput(true);
                OutputStream os=huc.getOutputStream();
                BufferedWriter bf=new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
                String post_data= URLEncoder.encode("username","UTF-8")+"="+URLEncoder.encode(username,"UTF-8")+"&"+
                        URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
                bf.write(post_data);
                bf.flush();
                bf.close();
                os.close();
                InputStream is=huc.getInputStream();
                BufferedReader br=new BufferedReader(new InputStreamReader(is,"UTF-8"));
                String result="";
                String Line="";
                while((Line=br.readLine())!=null)
                {
                    result+=Line;
                    Log.d("Tag","Entered While");
                }
                br.close();
                is.close();
                huc.disconnect();
                return result;
            } catch (MalformedURLException e) {
                Log.d("Tag","MUE");
                e.printStackTrace();
            } catch (IOException e) {
                Log.d("Tag","IOE");
                e.printStackTrace();
            }
        }
        return "nope";
    }

    @Override
    protected void onPreExecute() {
        ad=new AlertDialog.Builder(context).create();
        ad.setTitle("Login Status");

    }

    @Override
    protected void onPostExecute(String s) {
        Log.d("Tag","Entered onPostExecute");
        ad.setMessage(s);
        ad.show();
        Log.d("Tag","Exit onPostExecute");
        // Toast.makeText(context,s,Toast.LENGTH_LONG).show();
        if(s.equals("login success"))
        {
            context.startActivity(new Intent(context,DashboardActivity.class));
        }
        else
        {
            Toast.makeText(context,s,Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        Log.d("Tag","Entered onProgressUpdate");
        super.onProgressUpdate(values);
    }


}
