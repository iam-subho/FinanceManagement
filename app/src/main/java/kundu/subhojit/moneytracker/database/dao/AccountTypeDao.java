package kundu.subhojit.moneytracker.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import kundu.subhojit.moneytracker.database.entity.AccountTypeEntity;

@Dao
public interface AccountTypeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAccountType(AccountTypeEntity accountType);

    @Query("SELECT * FROM account_type")
    List<AccountTypeEntity> getAllAccountTypes();
}
