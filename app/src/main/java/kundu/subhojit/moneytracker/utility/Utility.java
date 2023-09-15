package kundu.subhojit.moneytracker.utility;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import java.util.Map;

public class Utility {

    public static  Context appContext;
    public static final String TAG="Utility";
    private static final String PREFERENCE="myMoneyTrackerApp";
    private SharedPreferences preferences;

    public Utility(Context context) {
        appContext = context;
        preferences = context.getSharedPreferences(PREFERENCE,Context.MODE_PRIVATE);
    }

    public void setSharedPreference(String name, String value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(name, value);
        editor.apply();
    }

     public void setPrefBoolean( String name,boolean value){
         SharedPreferences.Editor editor = preferences.edit();
         editor.putBoolean(name, value);
         editor.apply();
     }

    public void setPrefString( String name,String value){
        Log.e(TAG+36,"key "+name+" "+value);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(name, value);
        editor.apply();
    }

    public void setPrefInteger(String name,int value){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(name, value);
        editor.apply(); // Use apply() for asynchronous saving
    }

    public boolean getPrefBoolean(String name){
        return preferences.getBoolean(name,false);
    }

    public String getPrefString(String name){
        return preferences.getString(name,"");
    }

    public Integer getPrefInteger(String name){
        return preferences.getInt(name,0);
    }



    public boolean containsKey(String key) {
        //Log.e(key, String.valueOf(preferences.getBoolean(key,false)));
        return preferences.contains(key);
    }

    public void clearAllPreferences(){
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
    }

    public void printAll() {
        Map<String, ?> allEntries = preferences.getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            // Print or log the key and value
            Log.e("SharedPreferences", "Key: " + key + " Value: " + value.toString());
        }
    }
    public void setloggedinfalse(){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(Constants.loggedin,false);
        editor.apply();

    }

}
