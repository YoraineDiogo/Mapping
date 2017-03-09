package com.example.a1diogy15.hellomap;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.views.MapView;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.config.Configuration;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.view.View;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;


import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.TextView;
import android.widget.ListView;
import android.content.Context;

/*preferences*/
import android.preference.PreferenceActivity;
import android.os.Bundle;

public class HelloMap extends Activity implements View.OnClickListener {

    MapView mv;

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // this line tells OpenStreetMap about our app.
        // If you miss this out, you might get banned from OSM servers
        Configuration.getInstance().load
                (this, PreferenceManager.getDefaultSharedPreferences(this));

        mv = (MapView) findViewById(R.id.map1);

        mv.setBuiltInZoomControls(true);
        mv.getController().setZoom(14); //HIGHER numbers = more zoom in
        mv.getController().setCenter(new GeoPoint(40.1, 22.5));

        Button button = (Button) findViewById(R.id.set_position);
        button.setOnClickListener(this);
    }

    public void onClick(View view) {
        EditText latitude = (EditText) findViewById(R.id.latitude);
        EditText longitude = (EditText) findViewById(R.id.longitude);

    }

    /*Menus and Multiples Activities*/
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_hello_map, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.choosemap) {
            // react to the menu item being selected...
            Intent intent = new Intent(this, MapChooseActivity.class);
            startActivityForResult(intent,0);
            return true;
        }
        return false;
    }


    protected void onActivityResult(int requestCode,int resultCode,Intent intent)
    {

        if(requestCode==0)
        {

            if (resultCode==RESULT_OK)
            {
                Bundle extras=intent.getExtras();
                boolean cyclemap = extras.getBoolean("com.example.cyclemap");
                if(cyclemap==true)
                {
                    mv.setTileSource(TileSourceFactory.CYCLEMAP);
                }
                else
                {
                    mv.setTileSource(TileSourceFactory.MAPNIK);
                }
            }
        }
    }



/*ListViews abd ListActivities*/
    public class ExampleListActivity extends ListActivity
    {
        public void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
        }

        public void onListItemClick(ListView lv, View view, int index, long id)
        {
            // handle list item selection
        }
    }

    public class ExampleListActivity extends ListActivity
    {
        String[] data;

        public void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            data = new String[] { "Firefox", "Chrome", "Internet Explorer" };
            ArrayAdapter<String> adapter = new ArrayAdapter<String> (this, android.R.layout.simple_list_item_1, data);
            setListAdapter(adapter);
        }

        public void onListItemClick(ListView lv, View view, int index, long id)
        {
            // handle list item selection
        }
    }

    public class ExampleListActivity extends ListActivity
    {
        String[] names, details;

        public void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            names = new String[] { "The Crown", "The Cowherds", "The Two Brothers", "Piccolo Mondo" };
            details = new String[] { "pub, 2.5 miles north", "pub, 1.5 miles north",
                    "pub, 3.5 miles northeast" , "Italian restaurant, 0.5 miles west" };
            MyAdapter adapter = new MyAdapter();
            setListAdapter(adapter);
        }

        public void onListItemClick(ListView lv, View view, int index, long id)
        {
            // handle list item selection
        }


        public class MyAdapter extends ArrayAdapter<String>
        {
            public MyAdapter()
            {
                // We have to use ExampleListActivity.this to refer to the outer class (the activity)
                super(ExampleListActivity.this, android.R.layout.simple_list_item_1, names);
            }

            public View getView(int index, View convertView, ViewGroup parent)
            {
                LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View view = inflater.inflate(R.layout.poientry, parent, false);
                TextView title = (TextView)view.findViewById(R.id.poi_name), detail =
                        (TextView)view.findViewById(R.id.poi_desc);
                title.setText(names[index]);
                detail.setText(details[index]);
                return view;
            }
        }
    }

    public View getView(int index, View convertView, ViewGroup parent)
    {
        View view = convertView;
        if(view==null)
        {
            LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.poientry, parent, false);
        }
        TextView title = (TextView)view.findViewById(R.id.poi_name), detail =
                (TextView)view.findViewById(R.id.poi_desc);
        title.setText(titles[index]);
        detail.setText(details[index]);
        return view;
    }

    /*preferences*/
    public class MyPrefsActivity extends PreferenceActivity
    {
        public void onCreate (Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferences);
        }
    }

    public void onStart()
    {
        super.onStart();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        double lat = Double.parseDouble ( prefs.getString("lat", "50.9") );
        double lon = Double.parseDouble ( prefs.getString("lon", "-1.4") );
        boolean autodownload = prefs.getBoolean("autodownload", true);
        String pizzaCode = prefs.getString("pizza", "NONE");

        // do something with the preference data...
    }

    public void onDestroy()
    {
        super.onDestroy();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean ("isRecording", isRecording);
        editor.commit();
    }
}