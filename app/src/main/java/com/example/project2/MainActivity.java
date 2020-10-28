package com.example.project2;
//These are the libraries used in the program
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
//created the MainActivity fields to Android.Widget classes
    private TextView mTitleMessage;
    private EditText mEmail;
    private EditText mPassword;
    private TextView mRegister;
//created String variables to contain the text from user input
    static String email;
    private String password;
    private String mainMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//findViewById returns a reference to a view by using the view's id as an argument
//It is casted to the specific Widget class to make sure there is no problems
        mTitleMessage=(TextView) findViewById((R.id.login_message)) ;
        mEmail= (EditText) findViewById(R.id.editText_Email);
        mPassword= (EditText) findViewById(R.id.editText_password);
        //mLogin=(Button)findViewById(R.id.login_button);
        mRegister=(TextView)findViewById(R.id.here_text_button);
//setOnClickListener creates a class onClick with a View as a parameter. In this case is a textView
//When the textView is clicked, the code will execute
       mRegister.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
//intentRegister is an Intent variable(object) initialized to call Register activity
               Intent intentRegister=new Intent(MainActivity.this,RegisterActivity.class);
//The method startActivity() starts specified activity inside intentRegister
               startActivity(intentRegister);
           }
       });
        }


//Function is called when the button is pressed
    public void MainProcessClick(View view) {
//getText().toString extracts a string from the View
        email=mEmail.getText().toString();
        password=mPassword.getText().toString();
//ValidateData() is called to check if all the data entered by the user is correct
//if it's not, the app will print out a message else, the user will enter a new activity
        if(validateData()){
            mTitleMessage.setText(mainMessage);
        }
        else {
            Intent intentMain = new Intent(MainActivity.this, WelcomeActivity.class);
            startActivity(intentMain);
        }
    }


//validateData() returns a boolean and checks if the data is correct
    public boolean validateData(){
//Pattern is a class under the java.util.regex library and checks if a string pattern is found another string
//Matcher matches the two strings to be  compared
        Pattern pattern=Pattern.compile("@farmingdale.edu");
        Matcher findEmail=pattern.matcher(email);
//Because the email is a String and a reference variable, it has to be compared using the .equals() function
//returns a boolean, and the if will return true if either one of the widgets were left empty by the user
//and will send a message "try again"
        if (email.equals("")||password.equals("")) {
            mainMessage="Try Again";
            return true;
//find() is also a function under the regex library, return a boolean if a match is found
        }else if(!(findEmail.find())){
            mainMessage="Invalid Email";
            return true;
        }
//the .length function returns an int. Here only passwords larger than 5 characters and less than 15 are accepted
//else it will return true and the app will not accept the user input
        else if(password.length()< 5|| password.length()> 15) {
            mainMessage="Invalid Password";
            return true;
        }
//if the program reaches here, it will return false, to mainProcessClick and will allow the program to jump to
//the welcome activity
        else{
            return false;
        }
    }


//getEmail is a static type of function that returns a string. This is used to welcome the user in the welcome activity
    public static String getEmail()
    {
        return email;
    }

}