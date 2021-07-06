package yfkj.weatherstation.db;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * @author wmx
 */

@Table(database = Database.class)
public class Weather extends BaseModel {
    @PrimaryKey(autoincrement = true)
    public long id;
    @Column
    public String time;
    @Column
    public String wd;
    @Column
    public String sd;
    @Column
    public String ts;
    @Column
    public String tw;
    @Column
    public String gz;
    @Column
    public String qy;
    @Column
    public String yl;
    @Column
    public String zyl;
    @Column
    public String fs;
    @Column
    public String fx;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getWd() {
        return wd;
    }

    public void setWd(String wd) {
        this.wd = wd;
    }

    public String getSd() {
        return sd;
    }

    public void setSd(String sd) {
        this.sd = sd;
    }

    public String getTs() {
        return ts;
    }

    public void setTsd(String tsd) {
        this.ts = ts;
    }

    public String getTw() {
        return tw;
    }

    public void setTwd(String twd) {
        this.tw = tw;
    }

    public String getGz() {
        return gz;
    }

    public void setGz(String gz) {
        this.gz = gz;
    }

    public String getQy() {
        return qy;
    }

    public void setQy(String qy) {
        this.qy = qy;
    }

    public String getYl() {
        return yl;
    }

    public void setYl(String yl) {
        this.yl = yl;
    }

    public String getZyl() {
        return zyl;
    }

    public void setZyl(String zyl) {
        this.zyl = zyl;
    }

    public String getFs() {
        return fs;
    }

    public void setFs(String fs) {
        this.fs = fs;
    }

    public String getFx() {
        return fx;
    }

    public void setFx(String fx) {
        this.fx = fx;
    }
}
