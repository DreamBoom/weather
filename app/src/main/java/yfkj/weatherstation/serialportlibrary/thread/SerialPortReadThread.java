package yfkj.weatherstation.serialportlibrary.thread;

import android.os.SystemClock;

import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author Kongqw
 * @date 2017/11/14
 * 串口消息读取线程
 */

public abstract class SerialPortReadThread extends Thread {
    /**
     * 数据接收
     * @param bytes 接收到的数据
     */
    public abstract void onDataReceived(byte[] bytes);
    private InputStream mInputStream;
    private byte[] mReadBuffer;

    public SerialPortReadThread(InputStream inputStream) {
        mInputStream = inputStream;
        mReadBuffer = new byte[1024];
    }

    @Override
    public void run() {
        super.run();
        while (!isInterrupted()) {
            try {
                if (null == mInputStream) {
                    return;
                }
                SystemClock.sleep(500);
                int size = mInputStream.read(mReadBuffer);
                if (-1 == size || 0 >= size) {
                    return;
                }
                byte[] readBytes = new byte[size];
                System.arraycopy(mReadBuffer, 0, readBytes, 0, size);
                onDataReceived(readBytes);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
    }

    @Override
    public synchronized void start() {
        super.start();
    }

    /**
     * 关闭线程 释放资源
     */
    public void release() {
        interrupt();
        if (null != mInputStream) {
            try {
                mInputStream.close();
                mInputStream = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
