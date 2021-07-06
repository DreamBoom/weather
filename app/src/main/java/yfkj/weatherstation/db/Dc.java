package yfkj.weatherstation.db;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * @author wmx
 */

@Table(database = Database.class)
public class Dc extends BaseModel {
    @PrimaryKey(autoincrement = true)
    public long id;
    @Column
    public String ts;
    @Column
    public String xzt;
    @Column
    public String czt;
    @Column
    public String fzt;
    @Column
    public String hwd;
    @Column
    public String gf;
    @Column
    public String cm;
    @Column
    public String gy;
    @Column
    public String gl;
    @Column
    public String dl;
    @Column
    public String kl;
    @Column
    public String yj;
    @Column
    public String cgw;
    @Column
    public String fgw;
    @Column
    public String xdNum;
    @Column
    public String xdy;
    @Column
    public String xdl;
    @Column
    public String xgl;
    @Column
    public String fdy;
    @Column
    public String fdl;
    @Column
    public String fzgl;
    @Column
    public String tdy;
    @Column
    public String tdl;
    @Column
    public String fdgl;
    @Column
    public String dayc;
    @Column
    public String allc;
    @Column
    public String dayy;
    @Column
    public String ally;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts;
    }

    public String getXzt() {
        return xzt;
    }

    public void setXzt(String xzt) {
        this.xzt = xzt;
    }

    public String getCzt() {
        return czt;
    }

    public void setCzt(String czt) {
        this.czt = czt;
    }

    public String getFzt() {
        return fzt;
    }

    public void setFzt(String fzt) {
        this.fzt = fzt;
    }

    public String getHwd() {
        return hwd;
    }

    public void setHwd(String hwd) {
        this.hwd = hwd;
    }

    public String getGf() {
        return gf;
    }

    public void setGf(String gf) {
        this.gf = gf;
    }

    public String getCm() {
        return cm;
    }

    public void setCm(String cm) {
        this.cm = cm;
    }

    public String getGy() {
        return gy;
    }

    public void setGy(String gy) {
        this.gy = gy;
    }

    public String getGl() {
        return gl;
    }

    public void setGl(String gl) {
        this.gl = gl;
    }

    public String getDl() {
        return dl;
    }

    public void setDl(String dl) {
        this.dl = dl;
    }

    public String getKl() {
        return kl;
    }

    public void setKl(String kl) {
        this.kl = kl;
    }

    public String getYj() {
        return yj;
    }

    public void setYj(String yj) {
        this.yj = yj;
    }

    public String getCgw() {
        return cgw;
    }

    public void setCgw(String cgw) {
        this.cgw = cgw;
    }

    public String getFgw() {
        return fgw;
    }

    public void setFgw(String fgw) {
        this.fgw = fgw;
    }

    public String getXdNum() {
        return xdNum;
    }

    public void setXdNum(String xdNum) {
        this.xdNum = xdNum;
    }

    public String getXdy() {
        return xdy;
    }

    public void setXdy(String xdy) {
        this.xdy = xdy;
    }

    public String getXdl() {
        return xdl;
    }

    public void setXdl(String xdl) {
        this.xdl = xdl;
    }

    public String getXgl() {
        return xgl;
    }

    public void setXgl(String xgl) {
        this.xgl = xgl;
    }

    public String getFdy() {
        return fdy;
    }

    public void setFdy(String fdy) {
        this.fdy = fdy;
    }

    public String getFdl() {
        return fdl;
    }

    public void setFdl(String fdl) {
        this.fdl = fdl;
    }

    public String getFzgl() {
        return fzgl;
    }

    public void setFzgl(String fzgl) {
        this.fzgl = fzgl;
    }

    public String getTdy() {
        return tdy;
    }

    public void setTdy(String tdy) {
        this.tdy = tdy;
    }

    public String getTdl() {
        return tdl;
    }

    public void setTdl(String tdl) {
        this.tdl = tdl;
    }

    public String getFdgl() {
        return fdgl;
    }

    public void setFdgl(String fdgl) {
        this.fdgl = fdgl;
    }

    public String getDayc() {
        return dayc;
    }

    public void setDayc(String dayc) {
        this.dayc = dayc;
    }

    public String getAllc() {
        return allc;
    }

    public void setAllc(String allc) {
        this.allc = allc;
    }

    public String getDayy() {
        return dayy;
    }

    public void setDayy(String dayy) {
        this.dayy = dayy;
    }

    public String getAlly() {
        return ally;
    }

    public void setAlly(String ally) {
        this.ally = ally;
    }
}
