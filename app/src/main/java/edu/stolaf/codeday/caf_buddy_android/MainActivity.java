package edu.stolaf.codeday.caf_buddy_android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;

public class MainActivity extends Activity {
    private EditText username=null;
    private EditText  password=null;
    String usernametxt;
    String passwordtxt;
    private Button login;
    private Button signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = (EditText)findViewById(R.id.editText1);
        password = (EditText)findViewById(R.id.editText2);
        login = (Button)findViewById(R.id.login);
        signup = (Button) findViewById(R.id.signup);
        Parse.initialize(this, "svpARfo8bpZIQjUcEspJWfL75lKsUqSDO08DxpYD", "LPpzy5FqoX2SACc9jrL0cZi4CPaNulPmOelFhS2K");
        ParseObject test = new ParseObject("Test");
        test.put("asda","asdas");
        test.saveInBackground();
    }
    public void login(View view){
        // Retrieve the text entered from the EditText
        usernametxt = username.getText().toString();
        passwordtxt = password.getText().toString();

        // Send data to Parse.com for verification
        ParseUser.logInInBackground(usernametxt, passwordtxt,
                new LogInCallback() {
                    public void done(ParseUser user, ParseException e) {
                        if (user != null) {
                            // If user exist and authenticated, send user to Welcome.class
                            Intent intent = new Intent(MainActivity.this, Home.class);
                            startActivity(intent);
                            Toast.makeText(getApplicationContext(),
                                    "Successfully Logged in",
                                    Toast.LENGTH_LONG).show();
                            finish();
                        } else {
                            Toast.makeText(
                                    getApplicationContext(),
                                    "Invalid username or password",
                                    Toast.LENGTH_LONG).show();

                        }
                    }
                });
    }
   public void signup(View view){

       Intent intent = new Intent(this, Sign_up.class);
       startActivity(intent);
   }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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



