package kundu.subhojit.moneytracker.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "category")
public class CategoryEntity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    int id;
    @ColumnInfo(name="name")
    String name;
    @ColumnInfo(name="categoryType")
    String categoryType;

    public CategoryEntity(int id, String name, String categoryType) {
        this.id = id;
        this.name = name;
        this.categoryType = categoryType;
    }

    @Ignore
    public CategoryEntity(String name, String categoryType) {
        this.name = name;
        this.categoryType = categoryType;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategoryType() {
        return categoryType;
    }
}
