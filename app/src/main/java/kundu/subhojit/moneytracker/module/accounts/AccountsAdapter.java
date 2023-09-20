package kundu.subhojit.moneytracker.module.accounts;

import androidx.annotation.NonNull;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import kundu.subhojit.moneytracker.R;
import kundu.subhojit.moneytracker.database.DatabaseHelper;
import kundu.subhojit.moneytracker.database.entity.AccountEntity;

public class AccountsAdapter extends ArrayAdapter<AccountEntity> {
    private LayoutInflater inflater;
    Context mcontext;
    private Activity mActivity;
    public static final String TAG="AccountsAdapter";

    public AccountsAdapter(Activity activity,@NonNull Context context, ArrayList<AccountEntity> list) {
        super(context,0, list);
        inflater = LayoutInflater.from(context);
        mcontext=context;
        mActivity=activity;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.adapter_accounts_single_item, parent, false);
        }

        AccountEntity item = getItem(position);

        // Populate the views in your custom layout with data from the CustomItem
        TextView nameTextView = convertView.findViewById(R.id.accountNameTV);
        TextView valueTextView = convertView.findViewById(R.id.accountBalanceTV);
        ImageView detailAccount=convertView.findViewById(R.id.detailAccount);
        ImageView editAccount=convertView.findViewById(R.id.editAccount);
        ImageView deleteAccount=convertView.findViewById(R.id.deleteAccount);

        editAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             editAccountfunction(item.getId(),item.getName(),item.getBalance(),item.getAccounttype_id());
            }
        });

        detailAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetailAccountfunction(item.getId(),item.getName(),item.getBalance(),item.getAccounttype_id());
            }
        });

        deleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteAccountfunction(item.getId());
            }
        });

        if (item.getName().length() > 8) {
            String shortenedText = item.getName().substring(0, 8) + "..";
            nameTextView.setText(shortenedText.toUpperCase());
        } else {
            nameTextView.setText(item.getName().toUpperCase());
        }
        valueTextView.setText("\u20B9 "+String.valueOf(item.getBalance()));

        return convertView;
    }

    private void DetailAccountfunction(int id, String name, String balance, String accounttypeId) {
        Dialog dialog=new Dialog(mcontext);
        dialog.setContentView(R.layout.dialog_add_account);
        // Set the width of the dialog to match the device's width
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(dialog.getWindow().getAttributes());
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes(layoutParams);

        Button addButton = dialog.findViewById(R.id.addAccount);
        EditText accountNameEditText = dialog.findViewById(R.id.accountNameET);
        EditText balanceEditText = dialog.findViewById(R.id.balanceET);
        Spinner accountType =dialog.findViewById(R.id.accountType);
        TextView header=dialog.findViewById(R.id.heading);
        LinearLayout curBalanceLinear=dialog.findViewById(R.id.curBalanceLinear);
        LinearLayout accountTypeTextLinear=dialog.findViewById(R.id.accountTypeTextLinear);
        LinearLayout accountTypeSpinner=dialog.findViewById(R.id.accountTypeSpinner);
        TextView accountTypeTextTV=dialog.findViewById(R.id.accountTypeTextTV);
        EditText balanceTotalET=dialog.findViewById(R.id.balanceTotalET);


        header.setText("Account Details");
        addButton.setVisibility(View.GONE);
        accountNameEditText.setFocusable(false);
        balanceEditText.setFocusable(false);
        accountType.setEnabled(false);
        curBalanceLinear.setVisibility(View.VISIBLE);
        balanceTotalET.setFocusable(false);
        accountTypeTextLinear.setVisibility(View.VISIBLE);
        accountTypeSpinner.setVisibility(View.GONE);


        //set Form
        accountType.setSelection(Integer.parseInt(accounttypeId));
        String selectedText=accountType.getSelectedItem().toString();
        accountTypeTextTV.setText("Account Type:"+selectedText);
        accountNameEditText.setText(name);
        balanceEditText.setText(balance);
        balanceTotalET.setText("0");

        dialog.show();
    }

    private void editAccountfunction(int id, String name, String balance, String accounttypeId) {
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
        TextView header=dialog.findViewById(R.id.heading);
        header.setText("Update Account");
        addButton.setText("Update");
        //set Form
        accountType.setSelection(Integer.parseInt(accounttypeId));Log.e(TAG,accounttypeId);
        accountNameEditText.setText(name);
        balanceEditText.setText(balance);

        dialog.show();

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String accountName=accountNameEditText.getText().toString();
                String balance=balanceEditText.getText().toString();
                int accountTypeidint=accountType.getSelectedItemPosition();
                String accountTypeid= String.valueOf(accountTypeidint);

                Log.e(TAG,"Data are "+accountName+" "+balance+" "+accountTypeidint);

                DatabaseHelper databaseHelper=DatabaseHelper.getInstance(mcontext);

                databaseHelper.accountDao().updateAccount(new AccountEntity(id,accountName,balance,accountTypeid));

                Toast.makeText(mcontext,"Account Updated",Toast.LENGTH_SHORT);
                dialog.dismiss();
            }
        });
    }

    private void deleteAccountfunction(int id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mcontext);
        builder.setTitle("Delete Account");
        builder.setMessage("Are you sure? ");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DatabaseHelper databaseHelper=DatabaseHelper.getInstance(mcontext);
                databaseHelper.accountDao().deleteAccountById(id);
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("No",null);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }
}