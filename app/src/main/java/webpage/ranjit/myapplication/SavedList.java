package webpage.ranjit.myapplication;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;

public class SavedList extends AppCompatActivity {

    ListView simpleList;

    File dir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_list);

        File sdCard = Environment.getExternalStorageDirectory();
        dir = new File (sdCard.getAbsolutePath() + "/WebView/");
        if(!dir.exists()){
            dir.mkdirs();
        }

        simpleList = (ListView)findViewById(R.id.simpleListView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.textview, R.id.txt, getFilesCount(dir));
        simpleList.setAdapter(arrayAdapter);
        simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
            startActivity(new Intent(SavedList.this, OpenSavedWebpage.class).putExtra("WebPageName", ""+dir.toString() + File.separator + ""+ getFilesCount(dir).get(pos)));

            }
        });
    }



    /**
     * get count of files from " Quiky Media " folder
     *
     * @param file-- > File
     * @return --> int  Count
     */
    public static ArrayList<String> getFilesCount(File file) {

        ArrayList<String> SavedFiles= new ArrayList<>();
        File[] files = file.listFiles();
        int count = 0;
        for (File f : files)
            if (f.isDirectory()){

            }
            else{
                if(f.getName().endsWith(".mht")){
                    SavedFiles.add(f.getName());
                }
            }


        return SavedFiles;

    }
}
