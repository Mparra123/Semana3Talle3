package com.example.semana3;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "main Activity log";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Log.i(TAG,"I'm onCreate");


        //Hot fix in order to run network operations on thread or as an asynchronous task.
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        setupUI();
    }

    /*
    @Override
    protected void onResume(){
        super.onResume();
        Log.i(TAG,"I'm onResume");
    }
    @Override
    protected void onStart(){
        super.onStart();
        Log.i(TAG,"I'm onStart");
    }
    @Override
    protected void onPause(){
        super.onPause();
        Log.i(TAG,"I'm onPause");
    }
    @Override
    protected void onStop(){
        Log.i(TAG,"I'm onStop");
        super.onStop();
    }
    @Override
    protected void onDestroy(){
        Log.i(TAG,"I'm onDestroy");
        super.onDestroy();
    }
    */

    private void setupUI(){

        final TextView mTextView = (TextView)findViewById(R.id.textView); //declaring the edit text variable with the name of the strings and ids

        final Button mButton =(Button)findViewById(R.id.btn_submit);

        final EditText mEdit_Text = (EditText)findViewById(R.id.editText);
        final Button mNewButton = (Button)findViewById(R.id.btn_New); // new syn button
        final Button mNewActivity = (Button)findViewById(R.id.btnNewActivity);


        //final Button mNewButton = (Button)findViewById(R.id.btn_new); new activity constant

       // final Intent intent2= new Intent(this,Main2Activity.class); // instance of the new activity.

        //final String[] message = {""};//= String.format("Holi"); //test send message to the other activity


        // new button synchronization

        mNewActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                goSecondActivity();

            }
        });


        mNewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                downloadContTask task = new downloadContTask();

                task.execute(new String[]{mEdit_Text.getText().toString()});

            }
        });

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //button do something


                if(hasInternetAccess()==false){ // validating internet connection permissions.

                    Toast.makeText(getApplicationContext(),"Not internet connection sorry :(",Toast.LENGTH_SHORT).show();

                }else{

                    Toast.makeText(getApplicationContext(),"Internet connection successfully accomplish",Toast.LENGTH_SHORT).show();

                    try {


                        //mEditContent.setText(downloadContent(mEdit_Text.toString())); // we set the value that will get the text
                        downloadContent(mEdit_Text.getText().toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(),"Error downloading",Toast.LENGTH_SHORT).show();

                    }
                }

                //message[0] = String.format(mTextView.getText().toString()); // message with the value of the textView
            }
        });

        /*
        mNewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //goSecondActivity(); //same method, other process
                intent2.putExtra("message", message[0]);
                startActivity(intent2);
            }
        });
*/
    }

    private void goSecondActivity(){

        Intent intent = new Intent(this,Main2Activity.class);
        startActivity(intent);
    }

    public boolean hasInternetAccess(){ //method to validate the connectivity.
        ConnectivityManager cm = (ConnectivityManager)getSystemService(getApplicationContext().CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();

        return netInfo != null &&  netInfo.isConnectedOrConnecting();

    }

    //method to download the content of the URL provided on the app
    private void downloadContent (String url) throws IOException {
        String response="";
        URL urlToFetch = new URL(url);
        HttpURLConnection urlConnection = (HttpURLConnection)urlToFetch.openConnection(); //opening the connection

        InputStream stream =urlConnection.getInputStream(); // will save the data
        response = readStrem(stream); //method that will read the data with a buffer
        urlConnection.disconnect(); //closing the connection

        EditText mEditContent = (EditText)findViewById(R.id.editContent);
        mEditContent.setText(response);
        // set the response and put to the edit tex content
    }

    //method that will buffer the data and response the string chain. "Convert the InputStrem to String"
    private String readStrem(InputStream stream) {

        Reader reader = new InputStreamReader(stream);
        BufferedReader buffer = new BufferedReader(reader);
        String response=""; // the response to show
        String chunkJustRead=""; // the data collected

        try{
            while((chunkJustRead = buffer.readLine()) != null){
                response += chunkJustRead;
            }

        }catch (IOException e){
            e.printStackTrace();
        }


        return response;
    }


    private class downloadContTask extends AsyncTask<String,Void,String>{

        private ProgressDialog mProgress;

        @Override
        protected void onPreExecute(){
            super.onPreExecute(); // main HILo
            mProgress = new ProgressDialog(MainActivity.this);
            mProgress.setMessage("Cargando contenido");
            mProgress.show();

        }

        @Override
        protected String doInBackground(String... url) {

            OkHttpClient client = new OkHttpClient();
            Request request = null;

            try {
                request = new Request.Builder().url(url[0]).build();
            }catch (Exception e){
                Log.i(TAG, "error in the URL");
                return "Download failed";
            }
            Response response = null;
            try {
                response = client.newCall(request).execute();
                if (response.isSuccessful()) {
                    return response.body().string();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "Download failed";
        }

        @Override
        protected void onPostExecute(String result){
            super.onPostExecute(result); //

            mProgress.dismiss();

            EditText mEditContent = (EditText)findViewById(R.id.editContent);
            mEditContent.setText(result);

        }
    }

}