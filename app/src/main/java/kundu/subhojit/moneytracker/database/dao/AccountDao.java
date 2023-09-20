package kundu.subhojit.moneytracker.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import kundu.subhojit.moneytracker.database.entity.AccountEntity;

@Dao
public interface AccountDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAccount(AccountEntity account);

    @Query("SELECT * FROM accounts")
    List<AccountEntity> getAllAccounts();

    @Query("DELETE FROM accounts WHERE id = :accountId")
    void deleteAccountById(int accountId);

    @Update
    void updateAccount(AccountEntity account);
}
