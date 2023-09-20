package kundu.subhojit.moneytracker.database;

import android.content.ContentResolver;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.room.migration.Migration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import kotlin.jvm.Throws;
import kundu.subhojit.moneytracker.database.dao.AccountDao;
import kundu.subhojit.moneytracker.database.dao.AccountTypeDao;
import kundu.subhojit.moneytracker.database.dao.CategoryDao;
import kundu.subhojit.moneytracker.database.dao.CustomerDao;
import kundu.subhojit.moneytracker.database.dao.TransactionDao;
import kundu.subhojit.moneytracker.database.entity.AccountEntity;
import kundu.subhojit.moneytracker.database.entity.AccountTypeEntity;
import kundu.subhojit.moneytracker.database.entity.CategoryEntity;
import kundu.subhojit.moneytracker.database.entity.CustomerEntity;
import kundu.subhojit.moneytracker.database.entity.TransactionEntity;
import kundu.subhojit.moneytracker.utility.Constants;

@Database(entities = {AccountTypeEntity.class, AccountEntity.class, CategoryEntity.class, CustomerEntity.class, TransactionEntity.class}, version = 1)
public abstract class DatabaseHelper extends RoomDatabase {

    public abstract AccountTypeDao accountTypeDao();
    public abstract AccountDao accountDao();
    public abstract CategoryDao categoryDao();
    public abstract CustomerDao customerDao();
    public abstract TransactionDao transactionDao();

    private static DatabaseHelper instance;
    public static String TAG="DatabaseHelper";
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static DatabaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),DatabaseHelper.class, Constants.databasename)
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return instance;
    }


    public static void roomImportDatabaseFromFile(String uri, Context context){
        Log.e(TAG,uri);
        File dbFile = context.getApplicationContext().getDatabasePath(Constants.databasename);
        instance=Room.databaseBuilder(context.getApplicationContext(), DatabaseHelper.class, Constants.databasename).build();
        if(checkRoomExist(context.getApplicationContext())){
            try{

                File sourceFile = new File(uri);
                if(sourceFile.isFile() && sourceFile.exists()){
                    copyFileForMe(sourceFile,dbFile);
                }

            }catch (Exception e){
                Log.e(TAG,e.toString());
            }
        }

    }


    public static boolean checkRoomExist(Context context) {
        final File dbFile = context.getDatabasePath(Constants.databasename);
        Log.e(TAG,"Check");
        if (dbFile.exists()) {
           return true;
        }

        return false;

    }

    private static void copyFileForMe(File sourceFile, File destFile) throws IOException {
        FileInputStream inputStream = null;
        FileOutputStream outputStream = null;

        try {
            inputStream = new FileInputStream(sourceFile);
            outputStream = new FileOutputStream(destFile);

            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }



}
