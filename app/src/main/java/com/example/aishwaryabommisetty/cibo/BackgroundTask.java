package com.example.aishwaryabommisetty.cibo;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Aishwarya Bommisetty on 06-06-2017.
 */

public class BackgroundTask extends AsyncTask<String,Void,String> {
    Context ctx;
    BackgroundTask(Context ctx)
    {
        this.ctx=ctx;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        String reg_url="https://dipsomaniac-structu.000webhostapp.com/register.php";

        String method=params[0];
        if(method.equals("Register"))
        {
            String name= params[1];
            String user_name=params[2];
            String user_pass=params[3];
            try {
                Log.d("Tag","Entered IF");
                URL url=new URL(reg_url);
                HttpURLConnection huc=(HttpURLConnection)url.openConnection();
                Log.d("Tag","After openconn");
                huc.setRequestMethod("POST");
                Log.d("Tag","After req");
                huc.setDoInput(true);
                huc.setDoOutput(true);
                Log.d("Tag","After Do's");
                OutputStream OS=huc.getOutputStream();
                Log.d("Tag","Before Encoding"); //not working!!!!
                BufferedWriter bf=new BufferedWriter(new OutputStreamWriter(OS,"UTF-8"));
                String data= URLEncoder.encode("user","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+"&"+
                        URLEncoder.encode("user_name","UTF-8")+"="+URLEncoder.encode(user_name,"UTF-8")+"&"+
                        URLEncoder.encode("user_pass","UTF-8")+"="+URLEncoder.encode(user_pass,"UTF-8");
                Log.d("Tag",data);
                bf.write(data);
                bf.flush();
                bf.close();
                OS.close();
                InputStream is=huc.getInputStream();
                is.close();
                return "Registration success.";
            } catch (MalformedURLException e) {
                Log.d("Tag","URL!");

                e.printStackTrace();
            } catch (Exception e) {
                Log.d("Tag","IO!");

                e.printStackTrace();
            }
        }

        return "nope";
    }
    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
        Toast.makeText(ctx,result,Toast.LENGTH_LONG).show();
    }
}
