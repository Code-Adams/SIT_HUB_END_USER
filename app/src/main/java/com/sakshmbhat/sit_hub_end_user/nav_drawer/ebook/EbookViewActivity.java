package com.sakshmbhat.sit_hub_end_user.nav_drawer.ebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.sakshmbhat.sit_hub_end_user.R;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class EbookViewActivity extends AppCompatActivity {

    private String ebookUrl;
    private PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ebook_view);

      initialize();

      new ebookDownloadAsyncTask().execute(ebookUrl);

    }

    private void initialize() {
        ebookUrl= getIntent().getStringExtra("ebookUrl");
        pdfView=findViewById(R.id.ebookViewer);
    }

    private class ebookDownloadAsyncTask extends AsyncTask<String,Void,InputStream>{


        @Override
        protected InputStream doInBackground(String... strings) {

            InputStream inputStream = null;

            try {
                //getting parsed url, as only one url hence give index as "0" here
                URL url = new URL(strings[0]);
                HttpURLConnection urlConnection= (HttpURLConnection) url.openConnection();
                if(urlConnection.getResponseCode()==200){
                    inputStream= new BufferedInputStream(urlConnection.getInputStream());
                }
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(EbookViewActivity.this, "Bad Ebook Url!", Toast.LENGTH_SHORT).show();
            }


            return inputStream;
        }
       //after async task has downloaded the ebook, we will display it in onPostExecute
        @Override
        protected void onPostExecute(InputStream inputStream) {
            //remove "super.onPostExecute(inputStream);"
            // Url for more customization options= https://github.com/barteksc/AndroidPdfViewer
            //super.onPostExecute(inputStream);

            pdfView.fromStream(inputStream)
                    .enableSwipe(true)
                    .enableDoubletap(true)
                    .load();

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(EbookViewActivity.this,EbookActivity.class));
        finish();
    }
}