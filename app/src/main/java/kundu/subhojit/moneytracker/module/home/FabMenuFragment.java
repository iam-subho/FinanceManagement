package kundu.subhojit.moneytracker.module.home;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;

import kundu.subhojit.moneytracker.R;
import kundu.subhojit.moneytracker.database.DatabaseHelper;
import kundu.subhojit.moneytracker.database.entity.AccountEntity;

public class FabMenuFragment extends BottomSheetDialogFragment {

     Context mcontext;
    public static final String TAG="FabMenuFragment";
    public FabMenuFragment(Context context) {
        this.mcontext=context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.floating_button_menu, container, false);

        // Handle clicks on Add Income and Add Expense options
        view.findViewById(R.id.text_add_income).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Add Income action
                dismiss(); // Close the dialog
            }
        });

        view.findViewById(R.id.text_add_expense).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Add Expense action
                dismiss(); // Close the dialog
            }
        });

        view.findViewById(R.id.text_add_account).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 addaccounttodb();
                 dismiss();
            }
        });

        view.findViewById(R.id.text_add_category).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        return view;
    }

    private void addaccounttodb() {
        Dialog dialog=new Dialog(mcontext);
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

                DatabaseHelper databaseHelper=DatabaseHelper.getInstance(mcontext);

                databaseHelper.accountDao().insertAccount(new AccountEntity(accountName,balance,accountTypeid));

                Toast.makeText(mcontext,"Account Added",Toast.LENGTH_SHORT);
                dialog.dismiss();
            }
        });
    }
}
