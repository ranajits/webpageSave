package webpage.ranjit.myapplication;

import android.Manifest;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    File dir;
    final  String TAG= "MainaCTIVITY";

    EditText edtUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        File sdCard = Environment.getExternalStorageDirectory();
         dir = new File (sdCard.getAbsolutePath() + "/WebView/");
        if(!dir.exists()){
            dir.mkdirs();
        }
        initUI();


    }



    WebView webView;

    private void initUI() {
        webView = (WebView) findViewById(R.id.webView);
        AndroidWebClient client = new AndroidWebClient();
        webView.setWebViewClient(client);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        edtUrl = (EditText) findViewById(R.id.edtUrl);

    }


    public void open(View view){

        if(edtUrl.getText().toString().startsWith("http")){
            webView.loadUrl(edtUrl.getText().toString());
        }else {
            Toast.makeText(MainActivity.this, "Enter Valid URL", Toast.LENGTH_SHORT).show();
        }


    }
    private class AndroidWebClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url,
                                  android.graphics.Bitmap favicon) {
            Log.d(TAG, " onPageStarted  ");

        }
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);


            view.saveWebArchive(dir.toString() + File.separator + "myArchive"+ getFilesCount(dir) +".mht");

            Log.d(TAG, " onPageFinished  "+  dir);
            // our webarchive wull be available now at the above provided location with name "myArchive"+".mht"

        }
        public void onLoadResource(WebView view, String url) {
            Log.d(TAG, " onLoadResource  ");
        }
    }




    public void SavePage(View view){
        startActivity(new Intent(MainActivity.this, SavedList.class));
    }


    /**
     * get count of files from " Quiky Media " folder
     *
     * @param file-- > File
     * @return --> int  Count
     */
    public static int getFilesCount(File file) {


        File[] files = file.listFiles();
        int count = 0;
        for (File f : files)
            if (f.isDirectory()){

            }
            else{
                count=count+1;
            }


        return count;

    }


}
