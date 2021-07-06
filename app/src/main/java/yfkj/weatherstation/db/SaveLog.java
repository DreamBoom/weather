package yfkj.weatherstation.db;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * @author wmx
 */
@Table(database = Database.class)
public class SaveLog extends BaseModel {
    @PrimaryKey(autoincrement = true)
    public long id;
    @Column
    public String num;
    @Column
    public String from;
    @Column
    public String time;
    @Column
    public String info;
    @Column
    public boolean success;
}
