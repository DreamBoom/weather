package yfkj.weatherstation.utils

import com.chibatching.kotpref.KotprefModel
import org.jetbrains.annotations.Nullable
import java.util.*


/**
 * @author Created by Dream
 */
object UserInfo : KotprefModel() {
    //sp的文件名
    val RESULTCODE_SUCCESS = 200
    val RESULTCODE_setting = 101
    val RESULTCODE_device = 102
    var live = 0
    var name by nullableStringPref("气象站")
    var id by nullableStringPref("")
    var time by intPref( 5)
    var address by nullableStringPref("")

    var lat by floatPref()
    var lng by floatPref()

    var wdUp by nullableStringPref("")
    var sdUp by nullableStringPref("")
    var twUp by nullableStringPref("")
    var tsUp by nullableStringPref("")
    var gzUp by nullableStringPref("")
    var qyUp by nullableStringPref("")
    var ylUp by nullableStringPref("")
    var fsUp by nullableStringPref("")

    var wdDown by nullableStringPref("")
    var sdDown by nullableStringPref("")
    var twDown by nullableStringPref("")
    var tsDown by nullableStringPref("")
    var gzDown by nullableStringPref("")
    var qyDown by nullableStringPref("")
    var ylDown by nullableStringPref("")
    var fsDown by nullableStringPref("")
    var dlDown by nullableStringPref("20")

    var mName by nullableStringPref("")
    var mPass by nullableStringPref("")
    var topicUp by nullableStringPref("")
    var topicDown by nullableStringPref("")
    var ip by nullableStringPref("")
    var client by nullableStringPref("")
}