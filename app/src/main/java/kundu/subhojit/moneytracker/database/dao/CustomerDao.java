package kundu.subhojit.moneytracker.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import kundu.subhojit.moneytracker.database.entity.CustomerEntity;

@Dao
public interface CustomerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCustomer(CustomerEntity customer);

    @Query("SELECT * FROM customer")
    List<CustomerEntity> getAllCustomers();
}
