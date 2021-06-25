package com.sakshmbhat.sit_hub_end_user.nav_drawer.ebook;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.github.barteksc.pdfviewer.PDFView;
import com.sakshmbhat.sit_hub_end_user.R;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class EbookViewActivity extends AppCompatActivity {

    private String ebookUrl;
    private PDFView pdfView;
    private ShimmerFrameLayout shimmerFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ebook_view);

      initialize();

      if(checkConnectivity()){

          try{
              new ebookDownloadAsyncTask().execute(ebookUrl);
          }catch (Exception e){

              AlertDialog.Builder builder = new AlertDialog.Builder(
                      EbookViewActivity.this);
              builder.setTitle("Oops!");
              builder.setCancelable(false);
              builder.setMessage("Ebook cannot be displayed right now. Download it instead");
              builder.setPositiveButton("OK",
                      new DialogInterface.OnClickListener() {
                          public void onClick(DialogInterface dialog,
                                              int which) {
                              finish();
                          }
                      });
              builder.show();

          }

      }else{

          AlertDialog.Builder builder = new AlertDialog.Builder(
                  EbookViewActivity.this);
          builder.setTitle("No connection!");
          builder.setCancelable(false);
          builder.setMessage("Check your Internet and try again!");
          builder.setPositiveButton("OK",
                  new DialogInterface.OnClickListener() {
                      public void onClick(DialogInterface dialog,
                                          int which) {
                          finish();
                      }
                  });
          builder.show();


      }
    }

    private void initialize() {
        ebookUrl= getIntent().getStringExtra("ebookUrl");
        pdfView=findViewById(R.id.ebookViewer);
        shimmerFrameLayout=findViewById(R.id.shimmer_view_container);
        shimmerFrameLayout.setVisibility(View.VISIBLE);
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
                shimmerFrameLayout.stopShimmer();
                shimmerFrameLayout.setVisibility(View.GONE);
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
            shimmerFrameLayout.stopShimmer();
            shimmerFrameLayout.setVisibility(View.GONE);

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(EbookViewActivity.this,EbookActivity.class));
        finish();
    }

    public boolean checkConnectivity() {
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        //we are connected to a network
        connected = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED;
        return connected;
    }
    @Override
    protected void onPause() {
        shimmerFrameLayout.stopShimmer();
        super.onPause();
    }

    @Override
    protected void onResume() {
        shimmerFrameLayout.startShimmer();
        super.onResume();
    }

}