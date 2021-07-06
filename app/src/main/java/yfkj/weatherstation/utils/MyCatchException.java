package yfkj.weatherstation.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import android.widget.Toast;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Objects;

import yfkj.weatherstation.App;

/**
 * Created by Linfeng on 2019/05/14.
 */
//全部错误捕捉器
public class MyCatchException implements Thread.UncaughtExceptionHandler {
    //本类实例
    private static MyCatchException mInstance;
    //系统默认的uncatchException
    private Thread.UncaughtExceptionHandler mDefaultException;

    private Context mContext;

    //保证只有一个实例
    public MyCatchException() {
    }

    //单例模式
    public static MyCatchException getInstance() {
        if (mInstance == null) {
            mInstance = new MyCatchException();
        }
        return mInstance;
    }

    //获取系统默认的异常处理器,并且设置本类为系统默认处理器
    public void init(Context ctx) {
        this.mContext = ctx;
        mDefaultException = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    //自定义错误处理器
    private boolean handlerException(Throwable ex) {
        if (ex == null) {  //如果已经处理过这个Exception,则让系统处理器进行后续关闭处理
            return false;
        }

        //获取错误原因
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);
        Throwable cause = ex.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        printWriter.close();
        String result = writer.toString();
        final String msg = result;

        new Thread() {
            @Override
            public void run() {
                // Toast 显示需要出现在一个线程的消息队列中
                Looper.prepare();
                //将异常记录到本地的数据库或者文件中.或者直接提交到后台服务器
                // LogUtils.INSTANCE.i("全局异常"+msg);
                FileUtils.WriteExToStorage(msg, "", "", 3, 0);
                Looper.loop();
            }
        }.start();
        return true;
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if (!handlerException(ex) && mDefaultException != null) {
            // 如果用户没有处理则让系统默认的异常处理器来处理
            mDefaultException.uncaughtException(thread, ex);
        } else { //否则自己进行处理
            Intent intent = new Intent();
            intent.setAction("ACTION_RK_REBOOT");
            Objects.requireNonNull(App.Companion.getContext()).sendBroadcast(intent, null);

//            try {  //Sleep 来让线程停止一会是为了显示Toast信息给用户，然后Kill程序
//                Thread.sleep(5000);
//            } catch (InterruptedException e) {
//                LogUtils.INSTANCE.i("全局异常" + e.getMessage());
//
//            } catch (Exception e) {
//                LogUtils.INSTANCE.i("全局异常" + e.getMessage());
//                Intent intent = new Intent();
//                intent.setAction("ACTION_RK_REBOOT");
//                Objects.requireNonNull(App.Companion.getContext()).sendBroadcast(intent, null);
//            }

            //如果不关闭程序,会导致程序无法启动,需要完全结束进程才能重新启动
            //android.os.Process.killProcess(android.os.Process.myPid());
            // System.exit(10);
        }

    }
}