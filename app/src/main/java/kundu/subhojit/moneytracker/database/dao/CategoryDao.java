package kundu.subhojit.moneytracker.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import kundu.subhojit.moneytracker.database.entity.CategoryEntity;

@Dao
public interface CategoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCategory(CategoryEntity category);

    @Query("SELECT * FROM category")
    List<CategoryEntity> getAllCategories();
}
