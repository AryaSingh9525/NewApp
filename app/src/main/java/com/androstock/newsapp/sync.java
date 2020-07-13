package com.androstock.newsapp;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

public class sync extends AsyncTask<Void,Integer,String> {

    Context ctx;  // define a context to avoid error
    ProgressDialog pd;

    sync(Context c)
    {
        ctx = c;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd = new ProgressDialog(ctx);
        pd.setMessage("downloading...");
        pd.setMax(100);
        pd.setCancelable(true);
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd.setIcon(R.mipmap.ic_launcher);
        pd.show();
    }

    // this is what happens in the background
    //background computation

    @Override
    protected String doInBackground(Void... voids) {

        try {
            for (int i = 1; i <= 100; i++) {
                publishProgress(i);
                Thread.sleep(50);  // if error occurs avoid it through try catch

            }

            return " Download completed";

        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

    //parameters passes to this method
    //invoked on the UI thread after a call to publishProgress(Progress...).
    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        pd.setProgress(values[0]);
    }

    //invoked on the UI thread after the background computation finishes.
    //The result of the background computation is passed to this step as a parameter.
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        AlertDialog.Builder builder= new AlertDialog.Builder(ctx);
        builder.setTitle("Alert")
                .setMessage("Download completed")
                .setMessage("Do you want to Save?")
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(ctx, "Saved Successfully", Toast.LENGTH_SHORT).show();


                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ctx.startActivity(new Intent(ctx,Function.class));

                    }
                });
        builder.show();
        Toast.makeText(ctx, s, Toast.LENGTH_SHORT).show();
        pd.hide();
    }
}

