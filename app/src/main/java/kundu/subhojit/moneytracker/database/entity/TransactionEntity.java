package kundu.subhojit.moneytracker.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "transactionlist")
public class TransactionEntity {
    @PrimaryKey
    @ColumnInfo(name="id")
    int id;
    @ColumnInfo(name="accountid")
    String accountid;
    @ColumnInfo(name="customerid")
    String customerid;
    @ColumnInfo(name="categoryid")
    String categoryid;
    @ColumnInfo(name="amount")
    String amount;
    @ColumnInfo(name="datetime")
    String datetime;

    public TransactionEntity(int id, String accountid, String customerid, String categoryid, String amount, String datetime) {
        this.id = id;
        this.accountid = accountid;
        this.customerid = customerid;
        this.categoryid = categoryid;
        this.amount = amount;
        this.datetime = datetime;
    }

    @Ignore
    public TransactionEntity(String accountid, String customerid, String categoryid, String amount, String datetime) {
        this.accountid = accountid;
        this.customerid = customerid;
        this.categoryid = categoryid;
        this.amount = amount;
        this.datetime = datetime;
    }

    public int getId() {
        return id;
    }

    public String getAccountid() {
        return accountid;
    }

    public String getCustomerid() {
        return customerid;
    }

    public String getCategoryid() {
        return categoryid;
    }

    public String getAmount() {
        return amount;
    }

    public String getDatetime() {
        return datetime;
    }
}
