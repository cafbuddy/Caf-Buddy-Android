package edu.stolaf.codeday.caf_buddy_android;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.support.v4.app.FragmentActivity;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class Home extends FragmentActivity implements  AdapterView.OnItemSelectedListener {
    Spinner spinner;
    private EditText time_1;
    private EditText time_2;
    final Calendar c = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        spinner = (Spinner) findViewById(R.id.meals_spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,
                R.array.meals, android.R.layout.simple_spinner_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        TabHost tabHost = (TabHost) findViewById(R.id.tabHost);


        tabHost.setup();

        TabHost.TabSpec tabSpec = tabHost.newTabSpec("feed");
        tabSpec.setContent(R.id.feed);
        tabSpec.setIndicator("Feed");
        tabHost.addTab(tabSpec);

        TabHost.TabSpec tabSpec2 = tabHost.newTabSpec("create");
        tabSpec2.setContent(R.id.create);
        tabSpec2.setIndicator("Create");
        tabHost.addTab(tabSpec2);

        time_1 = (EditText)findViewById( R.id.starttime );
        time_2 = (EditText)findViewById(R.id.endtime);
        setCurrentDateOnView1();
        setCurrentDateOnView2();
    }

    public void setCurrentDateOnView1() {
        String timeFormat = "hh:mm a";
        SimpleDateFormat stf = new SimpleDateFormat( timeFormat, Locale.US );
        time_1.setText( stf.format( c.getTime() ) );
    }
    public void setCurrentDateOnView2() {
        String timeFormat = "hh:mm a";
        SimpleDateFormat stf = new SimpleDateFormat( timeFormat, Locale.US );
        time_2.setText( stf.format( c.getTime() ) );
    }
3

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
        TextView myText = (TextView) view;
        Toast.makeText(this, "You selected " + myText.getText(), Toast.LENGTH_SHORT).show();
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }
    public void create(View view){
        Toast.makeText(this, "You have created the entry! ", Toast.LENGTH_SHORT).show();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }
    TimePickerDialog.OnTimeSetListener time1 = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet( TimePicker view, int hourOfDay, int minute ) {
            c.set( Calendar.HOUR_OF_DAY, hourOfDay );
            c.set( Calendar.MINUTE, minute );
            setCurrentDateOnView1();
        }
    };

    public void timeOnClick1(View view){
        new TimePickerDialog(Home.this,time1,
                c.get(Calendar.HOUR),c.get(Calendar.MINUTE),false).show();
    }
    TimePickerDialog.OnTimeSetListener time2 = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet( TimePicker view, int hourOfDay, int minute ) {
            c.set( Calendar.HOUR_OF_DAY, hourOfDay );
            c.set( Calendar.MINUTE, minute );
            setCurrentDateOnView1();
        }
    };

    public void timeOnClick2(View view){
        new TimePickerDialog(Home.this,time2,
                c.get(Calendar.HOUR),c.get(Calendar.MINUTE),false).show();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
