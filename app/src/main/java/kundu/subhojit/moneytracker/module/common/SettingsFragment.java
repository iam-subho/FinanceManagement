package kundu.subhojit.moneytracker.module.common;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import kundu.subhojit.moneytracker.R;

public class SettingsFragment extends Fragment {

    public static final String TAG="SettingsFragment";
    public static final int PERMISSION_RESULT_CODE_FILECHOOSE=1011;

    EditText editTextPath;
    Button restore,choose;

    public SettingsFragment(){

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View settingsView =inflater.inflate(R.layout.fragment_settings, container, false);
        editTextPath=settingsView.findViewById(R.id.restoreBtn);
        choose=settingsView.findViewById(R.id.choose);
        restore=settingsView.findViewById(R.id.restore);

        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Restore Button Clicked",Toast.LENGTH_SHORT);
                Log.e(TAG+29,"Restore");
                  doBrowseFile();
            }
        });

        restore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restorethedb();
            }
        });

        settingsView.findViewById(R.id.restore).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Restore Button Clicked",Toast.LENGTH_SHORT);
                Log.e(TAG+29,"Restore");
            }
        });

        settingsView.findViewById(R.id.backupBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Backup Button Clicked",Toast.LENGTH_SHORT);
                Log.e(TAG+38,"Backup");
            }
        });


        return settingsView;
    }




    private void doBrowseFile() {
        Log.e(TAG+92,"function entry");
        Intent intent=new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent,PERMISSION_RESULT_CODE_FILECHOOSE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case PERMISSION_RESULT_CODE_FILECHOOSE:
                if(resultCode==Activity.RESULT_OK){
                    if(data!=null){
                        Uri fileuri=data.getData();
                        editTextPath.setText(fileuri.toString());
                        choose.setVisibility(View.INVISIBLE);
                        restore.setVisibility(View.VISIBLE);
                    }
                }
        }
    }

    public void restorethedb(){
        String path=editTextPath.getText().toString();

    }



}