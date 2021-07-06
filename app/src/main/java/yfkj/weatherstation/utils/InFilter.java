package yfkj.weatherstation.utils;

import android.app.Activity;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Jackie on 2016/1/30.
 * 过滤用户输入只能为金额格式
 */

public class InFilter implements InputFilter {
    Pattern mPattern;

    //输入的最大金额
    public static double MAX_VALUE = 50;
    public static double MIN_VALUE = 5;
    private final ActivityUtils utils;

    public InFilter(Activity act) {
        utils = new ActivityUtils(act);
        mPattern = Pattern.compile("([0-9]|\\.)*");
    }

    /**
     * @param source    新输入的字符串
     * @param start     新输入的字符串起始下标，一般为0
     * @param end       新输入的字符串终点下标，一般为source长度-1
     * @param dest      输入之前文本框内容
     * @param dstart    原内容起始坐标，一般为0
     * @param dend      原内容终点坐标，一般为dest长度-1
     * @return          输入内容
     */
    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        String s= source.toString();
        String destText = dest.toString();

        //验证删除等按键
        if (TextUtils.isEmpty(s)) {
            return "";
        }

        Matcher matcher = mPattern.matcher(source);
        if (!matcher.matches()) {
            return "";
        }
        //验证输入金额的大小
        double sumText = Double.parseDouble(destText + s);
        if (sumText > MAX_VALUE) {
            utils.showToast("已超出时长限制");
            return dest.subSequence(dstart, dend);
        }
        if (sumText < MIN_VALUE) {
            utils.showToast("最低时长五分钟");
        }
        return dest.subSequence(dstart, dend) + s;
    }
}
