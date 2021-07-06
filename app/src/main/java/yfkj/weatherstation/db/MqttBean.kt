package yfkj.weatherstation.db

class MqttBean {
    /**
     * name : 同步气象站名称及地址
     * id : cc9954aa6b874f02bb745983a677f204
     * content : {"stationCode":"13213","stationName":"气象站三号","type":"2","stationAddress":""}
     */
    var name: String? = null
    var id: String? = null
    var content: String? = null
}
internal class Content {
    /**
     * stationCode : 13213
     * stationName : 气象站三号
     * stationAddress :
     */
    var stationCode: String? = null
    var stationName: String? = null
    var type: String? = null
    var stationAddress: String? = null
    var stationLocation: StationLocation? = null
    var downloadUrl:String?=null
}
internal class ExBean {
    var stationCode: String? = null
    var type: String? = null
    var sensorCode:String?=null
    var alarmMessage:String?=null
}
internal class StationLocation {
    /**
     * stationCode : 13213
     * stationName : 气象站三号
     * type : 2
     * stationAddress :
     */
    var lng: String? = null
    var lat: String? = null
}

class upDown{
    var acqInterval: String? = null
    var stationCode: String? = null
    var type: String? = null
    var alarmData: List<AlarmDataBean>? = null

    class AlarmDataBean {
        /**
         * acqCode : WD
         * highAlarmValue : 30
         * lowAlarmValue : 20
         */
        var acqCode: String? = null
        var highAlarmValue: String? = null
        var lowAlarmValue: String? = null
    }
}