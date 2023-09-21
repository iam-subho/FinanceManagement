package kundu.subhojit.moneytracker.module.home;

import android.Manifest;
import android.app.Dialog;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import kundu.subhojit.moneytracker.R;
import kundu.subhojit.moneytracker.database.DatabaseHelper;
import kundu.subhojit.moneytracker.database.entity.AccountEntity;
import kundu.subhojit.moneytracker.module.common.DueLoanFragment;
import kundu.subhojit.moneytracker.module.common.IncomeExpenseFragment;
import kundu.subhojit.moneytracker.module.common.SettingsFragment;
import kundu.subhojit.moneytracker.utility.Constants;
import kundu.subhojit.moneytracker.utility.Utility;

public class DashboardActivity extends AppCompatActivity {
    public static final String TAG="DashboardActivity";
    FloatingActionButton fab;
    Button homebtn,incomebtn,expensebtn,settingsbtn;
    private BottomNavigationView bottomNavigationView;
    ExtendedFloatingActionButton mAddFab;
    FloatingActionButton mAddIncomeExpense, mAddBank;
    TextView addaccountTV,addincomeexpenseTV;
    Boolean isAllFabsVisible;
    Utility utility;
    String[] permissionsRequired = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_dashboard); // Set your layout resource here
        //fab=findViewById(R.id.fabtn);
        utility=new Utility(DashboardActivity.this);
        setuppermission();

        /*SpeedDialView speedDialView=findViewById(R.id.floatingActionBtn);

        speedDialView.setOnActionSelectedListener(new SpeedDialView.OnActionSelectedListener() {
            @Override
            public boolean onActionSelected(SpeedDialActionItem actionItem) {
                int actiod=actionItem.getId();
                switch (actiod){
                    case R.id.bankadd:
                        Toast.makeText(DashboardActivity.this, "Bank Button Click", Toast.LENGTH_SHORT).show();
                    case R.id.transactionadd:
                        Toast.makeText(DashboardActivity.this, "Transaction Add Click", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });*/

        mAddFab = findViewById(R.id.add_fab);
        mAddIncomeExpense=findViewById(R.id.add_incomeexpense);
        mAddBank=findViewById(R.id.add_accounts);
        addaccountTV=findViewById(R.id.add_accounts_text);
        addincomeexpenseTV=findViewById(R.id.add_incomeexpense_text);

        mAddIncomeExpense.setVisibility(View.GONE);
        addincomeexpenseTV.setVisibility(View.GONE);
        mAddBank.setVisibility(View.GONE);
        addaccountTV.setVisibility(View.GONE);
        isAllFabsVisible = false;
        mAddFab.shrink();

        mAddFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isAllFabsVisible){
                    showallmenu();
                }else{
                    hideallmenu();
                }
            }
        });

        /*fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FabMenuFragment fabMenuFragment = new FabMenuFragment(DashboardActivity.this);
                fabMenuFragment.show(getSupportFragmentManager(), fabMenuFragment.getTag());
            }
        });*/

        mAddIncomeExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideallmenu();
                openincomeexpense();
            }
        });
        mAddBank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideallmenu();
                openaddbankaccount();
            }
        });

        bottomNavigationView=findViewById(R.id.bottom_navigation);
        fragmentopener(new HomeFragment(),true);

        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.home:
                        Log.e(TAG+49,"Home");
                        fragmentopener(new HomeFragment(),true);
                        return true;
                    case R.id.income:
                        Log.e(TAG+49,"Income");
                        fragmentopener(new IncomeExpenseFragment(),false);
                        return true;
                    case R.id.expense:
                        Log.e(TAG+49,"Expense");
                        fragmentopener(new DueLoanFragment(),false);
                        return true;
                    case R.id.settings:
                        Log.e(TAG+49,"Settings");
                        fragmentopener(new SettingsFragment(),false);
                        return true;
                }
                return true;
            }

        });

        //bottomNavigationView.setSelectedItemId(R.id.home);
    }



    private void openaddbankaccount() {
        Dialog dialog=new Dialog(DashboardActivity.this);
        dialog.setContentView(R.layout.dialog_add_account);
        // Set the width of the dialog to match the device's width
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(dialog.getWindow().getAttributes());
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;

        // Apply the width settings to the dialog
        dialog.getWindow().setAttributes(layoutParams);

        Button addButton = dialog.findViewById(R.id.addAccount);
        EditText accountNameEditText = dialog.findViewById(R.id.accountNameET);
        EditText balanceEditText = dialog.findViewById(R.id.balanceET);
        Spinner accountType =dialog.findViewById(R.id.accountType);

        dialog.show();

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String accountName=accountNameEditText.getText().toString();
                String balance=balanceEditText.getText().toString();
                int accountTypeidint=accountType.getSelectedItemPosition();
                String accountTypeid= String.valueOf(accountTypeidint);


                Log.e(TAG,"Data are "+accountName+" "+balance);

                DatabaseHelper databaseHelper=DatabaseHelper.getInstance(DashboardActivity.this);

                databaseHelper.accountDao().insertAccount(new AccountEntity(accountName,balance,accountTypeid));

                Toast.makeText(DashboardActivity.this,"Account Added",Toast.LENGTH_SHORT);
                dialog.dismiss();
            }
        });
    }

    private void openincomeexpense() {
    }

    public void fragmentopener(Fragment fragment, @NonNull Boolean flag){

        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =fragmentManager.beginTransaction();
        if(flag){
            fragmentTransaction.add(R.id.container,fragment);
        }else{
            fragmentTransaction.replace(R.id.container,fragment);
        }
        fragmentTransaction.commit();
    }

    public void setuppermission(){
        if(ActivityCompat.checkSelfPermission(DashboardActivity.this, permissionsRequired[0]) != PackageManager.PERMISSION_GRANTED
            || ActivityCompat.checkSelfPermission(DashboardActivity.this, permissionsRequired[1]) != PackageManager.PERMISSION_GRANTED){

            if(ActivityCompat.shouldShowRequestPermissionRationale(DashboardActivity.this,permissionsRequired[0])
                    || ActivityCompat.shouldShowRequestPermissionRationale(DashboardActivity.this,permissionsRequired[1])){

                ActivityCompat.requestPermissions(DashboardActivity.this,permissionsRequired,Constants.PERMISSION_CALLBACK_CONSTANT);
            }else{
                ActivityCompat.requestPermissions(DashboardActivity.this,permissionsRequired,Constants.PERMISSION_CALLBACK_CONSTANT);
            }
            utility.setPrefBoolean(Constants.permissionStatus, true);
        }
    }

    public void hideallmenu(){
        mAddIncomeExpense.setVisibility(View.GONE);
        addincomeexpenseTV.setVisibility(View.GONE);
        mAddBank.setVisibility(View.GONE);
        addaccountTV.setVisibility(View.GONE);
        isAllFabsVisible = false;
        mAddFab.shrink();
    }

    private void showallmenu() {
        mAddIncomeExpense.setVisibility(View.VISIBLE);
        addincomeexpenseTV.setVisibility(View.VISIBLE);
        mAddBank.setVisibility(View.VISIBLE);
        addaccountTV.setVisibility(View.VISIBLE);
        mAddFab.extend();
        isAllFabsVisible=true;
    }


}
