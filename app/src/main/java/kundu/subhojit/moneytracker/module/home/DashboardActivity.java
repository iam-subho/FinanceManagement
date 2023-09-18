package kundu.subhojit.moneytracker.module.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;

import kundu.subhojit.moneytracker.R;
import kundu.subhojit.moneytracker.module.BaseActivity;
import kundu.subhojit.moneytracker.module.common.DueLoanFragment;
import kundu.subhojit.moneytracker.module.common.IncomeExpenseFragment;
import kundu.subhojit.moneytracker.module.common.SettingsFragment;

public class DashboardActivity extends AppCompatActivity {
    public static final String TAG="DashboardActivity";
    FloatingActionButton fab;
    Button homebtn,incomebtn,expensebtn,settingsbtn;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard); // Set your layout resource here
        fab=findViewById(R.id.fabtn);
        
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FabMenuFragment fabMenuFragment = new FabMenuFragment();
                fabMenuFragment.show(getSupportFragmentManager(), fabMenuFragment.getTag());
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

    public void fragmentopener(Fragment fragment,Boolean flag){

        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =fragmentManager.beginTransaction();
        if(flag){
            fragmentTransaction.add(R.id.container,fragment);
        }else{
            fragmentTransaction.replace(R.id.container,fragment);
        }

        fragmentTransaction.commit();


    }
}
