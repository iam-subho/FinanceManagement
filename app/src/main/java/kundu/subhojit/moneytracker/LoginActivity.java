package kundu.subhojit.moneytracker;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import kundu.subhojit.moneytracker.module.home.DashboardActivity;
import kundu.subhojit.moneytracker.utility.Constants;
import kundu.subhojit.moneytracker.utility.Utility;

public class LoginActivity extends AppCompatActivity {

    Button login,register;
    EditText loginpin;
    TextView usermsg;
    Utility utility;
    public static final String TAG="LoginActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login=findViewById(R.id.login);
        register=findViewById(R.id.register);
        loginpin=findViewById(R.id.pin);
        usermsg=findViewById(R.id.textView);
        utility = new Utility(getApplicationContext());

        Intent intent=getIntent();
        String msg=intent.getStringExtra("msg");



        if(!msg.isEmpty()){
            Toast.makeText(LoginActivity.this,msg,Toast.LENGTH_LONG);
        }



        boolean registered= utility.getPrefBoolean(Constants.registered);

        if(registered){
            utility.printAll();
            String username= utility.getPrefString(Constants.sname);
            //Log.e(TAG+53,String.valueOf(username));
            usermsg.setText("HI, "+username+" Please login to use");  // i have done it
        }


        
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              if(registered){
                  AlertDialog.Builder dialog=new AlertDialog.Builder(LoginActivity.this);
                  dialog.setTitle("Are your sure! you are already registered");
                  dialog.setMessage("Data will be lost");
                  dialog.setPositiveButton("Confirm", new DialogInterface.OnClickListener(){
                              @Override
                              public void onClick(DialogInterface dialog, int which) {
                                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                                dialog.dismiss();
                              }
                  });
                  dialog.setNegativeButton("Cancel",new DialogInterface.OnClickListener(){

                      @Override
                      public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                      }
                  });
                  //AlertDialog alertDialog=dialog.create();
                  dialog.show();
              }else{
                  Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                  startActivity(intent);
              }

            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pinvalue=loginpin.getText().toString();
                if(!pinvalue.isEmpty()){
                    loggedinfunction(pinvalue);
                }else{
                    Toast.makeText(LoginActivity.this,"Enter pin",Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private void loggedinfunction(String pin) {
        String pinfromdb= utility.getPrefString(Constants.loginpin);Log.e(TAG+63,pinfromdb);
        if(pinfromdb.equals(pin)){
        Intent dashboard=new Intent(this, DashboardActivity.class);
        startActivity(dashboard);
        }else{
            Toast.makeText(LoginActivity.this,"Enter a valid pin",Toast.LENGTH_LONG).show();
        }
    }


}