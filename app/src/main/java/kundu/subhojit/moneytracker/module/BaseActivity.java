package kundu.subhojit.moneytracker.module;

import android.os.Bundle;
import android.view.Menu;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import kundu.subhojit.moneytracker.R;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_main, menu); // You can create your own menu XML
//        return true;
//    }

    protected void setLayout(@LayoutRes int layoutResId) {
        setContentView(layoutResId);
        setupActionBar();
        setupBottomNavigation();
    }

    private void setupActionBar() {
        Toolbar toolbar = findViewById(R.id.toolbar); // Assuming you have a Toolbar in your layout
        setSupportActionBar(toolbar);
    }

    private void setupBottomNavigation() {
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation); // Assuming you have a BottomNavigationView in your layout
        bottomNav.setOnNavigationItemSelectedListener(item -> {
            // Handle bottom navigation item clicks here
            return true;
        });
    }
}
