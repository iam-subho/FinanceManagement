package kundu.subhojit.moneytracker.module.accounts;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import kundu.subhojit.moneytracker.R;
import kundu.subhojit.moneytracker.database.DatabaseHelper;
import kundu.subhojit.moneytracker.database.entity.AccountEntity;


public class FragmentAccountList extends Fragment {

    Context mcontext;
    ArrayAdapter<AccountEntity> adapter;
    public static String TAG="FragmentAccountList";
    public FragmentAccountList(Context context) {
        // Required empty public constructor
        this.mcontext=context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View accountListView= inflater.inflate(R.layout.fragment_account_list, container, false);

        ListView listaccount=accountListView.findViewById(R.id.listacccount);
        DatabaseHelper databaseHelper=DatabaseHelper.getInstance(mcontext);
        ArrayList<AccountEntity> list=(ArrayList)databaseHelper.accountDao().getAllAccounts();
        adapter=new AccountsAdapter(requireActivity(),mcontext,list);
        listaccount.setAdapter(adapter);

        return accountListView;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e(TAG, "onResume called");
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }
}