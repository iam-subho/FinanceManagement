/*
Copywrite Subhojit Kundu
* */

package kundu.subhojit.moneytracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import kundu.subhojit.moneytracker.database.DatabaseHelper;
import kundu.subhojit.moneytracker.database.dao.AccountTypeDao;
import kundu.subhojit.moneytracker.database.entity.AccountTypeEntity;
import kundu.subhojit.moneytracker.database.entity.CategoryEntity;
import kundu.subhojit.moneytracker.module.home.DashboardActivity;
import kundu.subhojit.moneytracker.utility.Constants;
import kundu.subhojit.moneytracker.utility.Utility;

public class SplashActivity extends AppCompatActivity {

    Handler handler;
    Utility utility;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        handler=new Handler();
        utility = new Utility(this);
        handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {

                                    boolean registered= utility.getPrefBoolean(Constants.registered);
                                    boolean initialEntry= utility.getPrefBoolean(Constants.initialEntryDone);
                                    if(registered){
                                        Intent intent = new Intent(SplashActivity.this,LoginActivity.class);
                                        intent.putExtra("msg","");
                                        startActivity(intent);
                                        if(!initialEntry){insertSampleData();}
                                        finish();
                                    }else{
                                        Intent intent = new Intent(SplashActivity.this, RegisterActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }



                                }
                            }

        ,100);
    }

    private void insertSampleData() {
        DatabaseHelper databaseHelper=DatabaseHelper.getInstance(getApplicationContext());
        databaseHelper.databaseWriteExecutor.execute(() -> {
            databaseHelper.accountTypeDao().insertAccountType(new AccountTypeEntity("Savings"));  //1
            databaseHelper.accountTypeDao().insertAccountType(new AccountTypeEntity("Credit"));   //2
            databaseHelper.accountTypeDao().insertAccountType(new AccountTypeEntity("Lending"));  //3
            utility.setPrefBoolean(Constants.initialEntryDone,true);
        });

    }

    @Override
    protected void onDestroy() {
        utility.setloggedinfalse();
        super.onDestroy();
    }
}