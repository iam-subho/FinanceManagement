package kundu.subhojit.moneytracker;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

import kundu.subhojit.moneytracker.utility.Constants;
import kundu.subhojit.moneytracker.utility.Utility;

public class RegisterActivity extends AppCompatActivity {

    EditText dobEditText,nameEditText,pinEditText,confirmpinEditText,mobEditText;
    DatePickerDialog datePickerDialog;
    String name,dob,pin,mob,confirmpin;
    Button btnRegister;
    Utility utility;
    TextView loginBtn;
    public static final String TAG="RegisterActivity";  //for Log and debug

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_register_layout);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        utility = new Utility(getApplicationContext());

        dobEditText  =findViewById(R.id.editTextDOB);
        nameEditText =findViewById(R.id.editTextName);
        pinEditText  =findViewById(R.id.editTextPIN);
        confirmpinEditText=findViewById(R.id.editTextPINConfirm);
        mobEditText   =findViewById(R.id.editTextMobileNo);
        btnRegister   =findViewById(R.id.registerbtn);
        loginBtn      =findViewById(R.id.loginTV);

        utility.clearAllPreferences();


        dobEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(RegisterActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                dobEditText.setText(dayOfMonth + "/"+ (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                completerregister();
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                intent.putExtra("msg","");
                startActivity(intent);
                finish();
            }
        });

    }

    private void completerregister() {
        name          =nameEditText.getText().toString();
        dob           =dobEditText.getText().toString();
        mob           =mobEditText.getText().toString();
        pin           =pinEditText.getText().toString();
        confirmpin    =confirmpinEditText.getText().toString();

        Log.e(TAG+93,name+" "+dob+" "+pin+" "+confirmpin+" "+mob);

        if (name.isEmpty() || dob.isEmpty() || mob.isEmpty() || pin.isEmpty() || confirmpin.isEmpty()) {
            Toast.makeText(RegisterActivity.this, "All fields are required", Toast.LENGTH_LONG).show();
        } else if (!pin.equals(confirmpin)) {
            Toast.makeText(RegisterActivity.this, "Pin & Confirm PIN do not match", Toast.LENGTH_LONG).show();
        }else{
            utility.setSharedPreference(Constants.sname,name);
            utility.setPrefString(Constants.dob,dob);
            utility.setPrefString(Constants.loginpin,pin);
            utility.setPrefString(Constants.mob,mob);
            utility.setPrefBoolean(Constants.registered,true);

            Log.e(TAG+107, String.valueOf(utility.containsKey(Constants.registered))+" "+"registered");


            Intent intent =new Intent(this,LoginActivity.class);
            intent.putExtra("msg","Registration Successfull! Please Login");
            startActivity(intent);

        }
    }


}