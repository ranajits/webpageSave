package webpage.ranjit.myapplication;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

public class OpenSavedWebpage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_saved_webpage);
        initUI();
    }

    WebView webView;

    private void initUI() {
        webView = (WebView) findViewById(R.id.webView);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT) {
            loadArchive();
        }else {
            webView.loadUrl(getIntent().getStringExtra("WebPageName"));
        }

    }

    public void loadArchive(View view){


    }

    private void loadArchive(){
        String rawData = null;
        try {
            rawData =   getStringFromFile(getIntent().getStringExtra("WebPageName"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        webView.loadDataWithBaseURL(null, rawData, "application/x-webarchive-xml", "UTF-8", null);
    }

    public String getStringFromFile (String filePath) throws Exception {
        File fl = new File(filePath);
        FileInputStream fin = new FileInputStream(fl);
        String ret = convertStreamToString(fin);
        //Make sure you close all streams.
        fin.close();
        return ret;
    }

    public  String convertStreamToString(InputStream is) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        reader.close();
        return sb.toString();
    }

}
