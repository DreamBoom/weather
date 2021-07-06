package yfkj.weatherstation.db;

import java.util.ArrayList;

public class UpData {
    public String stationCode;
    public String dataTime;
    public String type;
    public ArrayList<MetaBean> weatherItems;

    public String getStationCode() {
        return stationCode;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
    }

    public String getDataTime() {
        return dataTime;
    }

    public void setDataTime(String dataTime) {
        this.dataTime = dataTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<MetaBean> getWeatherItems() {
        return weatherItems;
    }

    public void setWeatherItems(ArrayList<MetaBean> weatherItems) {
        this.weatherItems = weatherItems;
    }

    public static class MetaBean {
        private String acqItem;
        private String acqData;

        public String getAcqItem() {
            return acqItem;
        }

        public void setAcqItem(String acqItem) {
            this.acqItem = acqItem;
        }

        public String getAcqData() {
            return acqData;
        }

        public void setAcqData(String acqData) {
            this.acqData = acqData;
        }
    }

}
