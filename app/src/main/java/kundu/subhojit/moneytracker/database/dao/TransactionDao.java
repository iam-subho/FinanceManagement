package kundu.subhojit.moneytracker.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import kundu.subhojit.moneytracker.database.entity.TransactionEntity;

@Dao
public interface TransactionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTransaction(TransactionEntity transaction);

    @Query("SELECT * FROM transactionlist")
    List<TransactionEntity> getAllTransactions();
}
