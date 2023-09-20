package kundu.subhojit.moneytracker.database;

import android.content.ContentResolver;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.room.migration.Migration;

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

    private static DatabaseHelper INSTANCE;

    public static synchronized DatabaseHelper getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),DatabaseHelper.class, Constants.databasename)
                    .addMigrations(MIGRATION_1_2) // Add migrations if needed
                    .createFromAsset(Constants.databasename)
                    .build();
        }
        return INSTANCE;
    }

    // Define migrations if needed
    // Example Migration:
    private static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            // Add migration logic here
        }
    };

    public static boolean isValidDatabaseFile(Context context, Uri databaseUri) {
        // Check if the scheme is "file" and the file extension is ".db"
        if (ContentResolver.SCHEME_FILE.equals(databaseUri.getScheme()) && databaseUri.getPath().endsWith(".db")) {
            try {
                // Try to open the file as an SQLite database
                SQLiteDatabase.openDatabase(databaseUri.getPath(), null, SQLiteDatabase.OPEN_READWRITE);
                return true; // If successful, it's a valid SQLite database file
            } catch (SQLiteException e) {
                e.printStackTrace();
            }
        }
        return false; // Not a valid database file
    }
}
