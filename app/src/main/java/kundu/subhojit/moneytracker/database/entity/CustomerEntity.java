package kundu.subhojit.moneytracker.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "customer")
public class CustomerEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    int id;
    @ColumnInfo(name="name")
    String name;
    @ColumnInfo(name="mobileno")
    String mobileno;

    public CustomerEntity(int id, String name, String mobileno) {
        this.id = id;
        this.name = name;
        this.mobileno = mobileno;
    }

    @Ignore
    public CustomerEntity(String name, String mobileno) {
        this.name = name;
        this.mobileno = mobileno;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMobileno() {
        return mobileno;
    }
}
