package kundu.subhojit.moneytracker.database;

import android.content.Context;

import kundu.subhojit.moneytracker.database.entity.AccountTypeEntity;

public class CommonDB {

    Context mcontext;
    DatabaseHelper databaseHelper;

    CommonDB(Context context){
        mcontext=context;
        databaseHelper=DatabaseHelper.getInstance(mcontext);
    }

    void insertAccountType(AccountTypeEntity accountTypeEntity){
        databaseHelper.databaseWriteExecutor.execute(() -> {
            //mWordDao.insert(word);
        });
    }

}
