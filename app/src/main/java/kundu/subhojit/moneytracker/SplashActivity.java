/*
Copywrite Subhojit Kundu
* */

package kundu.subhojit.moneytracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

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
                                    if(registered){
                                        Intent intent = new Intent(SplashActivity.this,LoginActivity.class);
                                        intent.putExtra("msg","");
                                        startActivity(intent);
                                        finish();
                                    }else{
                                        Intent intent = new Intent(SplashActivity.this, RegisterActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }



                                }
                            }

        ,1000);
    }

    @Override
    protected void onDestroy() {
        utility.setloggedinfalse();
        super.onDestroy();
    }
}