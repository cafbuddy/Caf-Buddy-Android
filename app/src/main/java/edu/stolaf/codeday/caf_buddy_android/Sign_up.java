package edu.stolaf.codeday.caf_buddy_android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;


public class Sign_up extends Activity {
    private EditText username=null;
    private EditText  password=null;
   private EditText  email=null;
    String usernametxt;
    String passwordtxt;
    String emailtxt;
    private Button signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        username = (EditText)findViewById(R.id.editText1);
        password = (EditText)findViewById(R.id.editText2);
        email = (EditText)findViewById(R.id.editText3);
    }
    public void Signup(View view){
        // Retrieve the text entered from the EditText
        usernametxt = username.getText().toString();
        passwordtxt = password.getText().toString();
        emailtxt = email.getText().toString();
        if (emailtxt.indexOf("@") != -1){
          if(emailtxt.regionMatches(emailtxt.indexOf("@"),"@stolaf.edu",0,11)== false){
              Toast.makeText(getApplicationContext(),
                      "Please enter a valid St. Olaf email 1",
                      Toast.LENGTH_LONG).show();
          }
        }else{
            Toast.makeText(getApplicationContext(),
                    "Please enter a valid St. Olaf email 2",
                    Toast.LENGTH_LONG).show();
        }
        // Force user to fill up the form
        if (usernametxt.equals("") || passwordtxt.equals("") || emailtxt.equals("") ) {
            Toast.makeText(getApplicationContext(),
                    "Please complete the sign up form",
                    Toast.LENGTH_LONG).show();

        } else if(emailtxt.length() <= 11 ){
            Toast.makeText(getApplicationContext(),
                    "Please enter a valid St. Olaf email",
                    Toast.LENGTH_LONG).show();
        }else if(passwordtxt.length() <8){

            Toast.makeText(getApplicationContext(),
                    "Password must be at least 8 characters",
                    Toast.LENGTH_LONG).show();

        }
        else {
            // Save new user data into Parse.com Data Storage
            ParseUser user = new ParseUser();
            user.setUsername(usernametxt);
            user.setPassword(passwordtxt);
            user.setEmail(emailtxt);
            user.signUpInBackground(new SignUpCallback() {
                public void done(ParseException e) {
                    if (e == null) {
                        // Show a simple Toast message upon successful registration
                        Toast.makeText(getApplicationContext(),
                                "Successfully Signed up, please log in.",
                                Toast.LENGTH_LONG).show();
                                 Intent intent = new Intent(getBaseContext(), MainActivity.class);
                                 intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                 startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(),
                                "Sign up Error", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sign_up, menu);
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
