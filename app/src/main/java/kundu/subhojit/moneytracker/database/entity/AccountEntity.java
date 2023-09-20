package kundu.subhojit.moneytracker.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "accounts",indices = {@Index(value = "name", unique = true)})
public class AccountEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "balance")
    private String balance;

    @ColumnInfo(name = "accounttype_id")
    private String accounttype_id;

    public AccountEntity(int id, String name, String balance, String accounttype_id) {
        this.id = id;
        this.name = name;
        this.balance = balance;
        this.accounttype_id = accounttype_id;
    }

    @Ignore
    public AccountEntity(String name, String balance, String accounttype_id) {
        this.name = name;
        this.balance = balance;
        this.accounttype_id = accounttype_id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBalance() {
        return balance;
    }

    public String getAccounttype_id() {
        return accounttype_id;
    }
}
