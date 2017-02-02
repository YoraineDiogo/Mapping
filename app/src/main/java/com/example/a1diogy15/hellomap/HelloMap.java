package com.example.a1diogy15.hellomap;

import android.app.Activity;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.osmdroid.views.MapView;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.config.Configuration;

public class HelloMap extends Activity implements View.OnClickListener {

    MapView mv;

    public void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // this line tells OpenStreetMap about our app.
        // If you miss this out, you might get banned from OSM servers
        Configuration.getInstance().load
                (this, PreferenceManager.getDefaultSharedPreferences(this));

        mv = (MapView)findViewById(R.id.map1);

        mv.setBuiltInZoomControls(true);
        mv.getController().setZoom(14); //HIGHER numbers = more zoom in
        mv.getController().setCenter(new GeoPoint(40.1,22.5));

        Button button = (Button)findViewById(R.id.set_position);
        button.setOnClickListener(this);
    }

    public void onClick(View view)
    {
        EditText latitude = (EditText)findViewById(R.id.latitude);
        EditText longitude = (EditText)findViewById(R.id.longitude);

    }
}