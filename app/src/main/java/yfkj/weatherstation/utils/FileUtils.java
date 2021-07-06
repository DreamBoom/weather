package yfkj.weatherstation.utils;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.RandomAccessFile;

public class FileUtils {

    /**
     * @param content:    要写的内容
     * @param filedirname 文件夹的名字
     * @param filename:   文件的名字
     * @param mode:       以什么方式往里面去写 0 1 2 3
     * @param ways:       两种方式 Buffer RandomAccessFile  Print  0 1 2
     * @Function: 将content写到指定的文件的指定的目录下去
     * @Return:
     */
    public static void WriteDataToStorage(String content, String filedirname, String filename, int mode, int ways) {
        String FileName = filedirname + File.separator + filename;   //拼接字符串  文件的存储路径
        File subfile = new File("/storage/emulated/0/ipPath");  //文件夹路径和文件路径   判断文件是否存在
        if (subfile.exists()) {
            subfile.setWritable(true);
            boolean readable = subfile.canRead();
            boolean writeable = subfile.canWrite();
            //Log.i(""," 文件创建成功" + "readable:" + readable + " writeable:" + writeable);
            return;
        } else {
            try {
                subfile.createNewFile();
            } catch (IOException e) {
                //Log.i("LOG_Error", "文件创建出错  " + e.getMessage());
                e.printStackTrace();
            }
        }
        int Context_Mode = mode;
        int Ways = ways;
        if (Context_Mode == 0) {
            Context_Mode = Context.MODE_PRIVATE;  //该文件只能被当前程序读写。
        } else if (Context_Mode == 1) {
            Context_Mode = Context.MODE_APPEND;   //以追加方式打开该文件，应用程序可以向该文件中追加内容。
        } else if (Context_Mode == 2) {
            Context_Mode = Context.MODE_WORLD_READABLE;  //该文件的内容可以被其他应用程序读取。
        } else if (Context_Mode == 3) {
            Context_Mode = Context.MODE_WORLD_WRITEABLE;  //该文件的内容可由其他程序读、写。
        } else {
            Context_Mode = Context.MODE_WORLD_WRITEABLE;  //省的烦   反正都可以读
        }
        if (Ways == 0) {
          //  Log.i("LOG_Info", "BufferWriter");
            FileOutputStream fileOutputStream = null;
            BufferedWriter bufferedWriter = null;
            OutputStreamWriter outputStreamWriter = null;
            try {
                //fileOutputStream = openFileOutput(FileName, Context_Mode);  contains a path separator 报错
                fileOutputStream = new FileOutputStream(subfile);
                bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, "utf-8"));  //解决输入中文的问题
                bufferedWriter.write(content + "\t");
                bufferedWriter.flush();
                bufferedWriter.close();
                //outputStreamWriter = new OutputStreamWriter(fileOutputStream, "utf-8");   //两种方式都可以
                //outputStreamWriter.write(content);
                //outputStreamWriter.flush();
                //outputStreamWriter.close();
            } catch (Exception e) {
                e.printStackTrace();
              //  Log.i("LOG_Error", "写入数据出错 " + e.getMessage());
            } finally {
                if (bufferedWriter != null) {
                    try {
                        bufferedWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else if (Ways == 1) {
           // Log.i("LOG_Info", "RandomAccessFile");
            try {
                RandomAccessFile raf = new RandomAccessFile(subfile, "rw");
                raf.seek(subfile.length());
                raf.write(content.getBytes());
                raf.close();
            } catch (Exception e) {
                e.printStackTrace();
              //  Log.i("LOG_Error", "写入数据出错 " + e.getMessage());
            }
        } else if (Ways == 2) {
           // Log.i("LOG_Info", "Printer");
            try {
                FileOutputStream fileoutputStream = new FileOutputStream(subfile);
                //openFileOutput("text2", Context.MODE_PRIVATE);
                PrintStream ps = new PrintStream(fileoutputStream);
                ps.print(content + "\t");
                ps.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            Ways = 0;
        }
    }


    //异常文件
    public static void WriteExToStorage(String content, String filedirname, String filename, int mode, int ways) {
        String FileName = filedirname + File.separator + filename;   //拼接字符串  文件的存储路径
        File subfile = new File("/storage/emulated/0/ExPath");  //文件夹路径和文件路径   判断文件是否存在
        if (subfile.exists()) {
            subfile.setWritable(true);
            boolean readable = subfile.canRead();
            boolean writeable = subfile.canWrite();
            //Log.i(""," 文件创建成功" + "readable:" + readable + " writeable:" + writeable);
            return;
        } else {
            try {
                subfile.createNewFile();
            } catch (IOException e) {
                //Log.i("LOG_Error", "文件创建出错  " + e.getMessage());
                e.printStackTrace();
            }
        }
        int Context_Mode = mode;
        int Ways = ways;
        if (Context_Mode == 0) {
            Context_Mode = Context.MODE_PRIVATE;  //该文件只能被当前程序读写。
        } else if (Context_Mode == 1) {
            Context_Mode = Context.MODE_APPEND;   //以追加方式打开该文件，应用程序可以向该文件中追加内容。
        } else if (Context_Mode == 2) {
            Context_Mode = Context.MODE_WORLD_READABLE;  //该文件的内容可以被其他应用程序读取。
        } else if (Context_Mode == 3) {
            Context_Mode = Context.MODE_WORLD_WRITEABLE;  //该文件的内容可由其他程序读、写。
        } else {
            Context_Mode = Context.MODE_WORLD_WRITEABLE;  //省的烦   反正都可以读
        }
        if (Ways == 0) {
            //  Log.i("LOG_Info", "BufferWriter");
            FileOutputStream fileOutputStream = null;
            BufferedWriter bufferedWriter = null;
            OutputStreamWriter outputStreamWriter = null;
            try {
                //fileOutputStream = openFileOutput(FileName, Context_Mode);  contains a path separator 报错
                fileOutputStream = new FileOutputStream(subfile);
                bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, "utf-8"));  //解决输入中文的问题
                bufferedWriter.write(content + "\t");
                bufferedWriter.flush();
                bufferedWriter.close();
                //outputStreamWriter = new OutputStreamWriter(fileOutputStream, "utf-8");   //两种方式都可以
                //outputStreamWriter.write(content);
                //outputStreamWriter.flush();
                //outputStreamWriter.close();
            } catch (Exception e) {
                e.printStackTrace();
                //  Log.i("LOG_Error", "写入数据出错 " + e.getMessage());
            } finally {
                if (bufferedWriter != null) {
                    try {
                        bufferedWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else if (Ways == 1) {
            // Log.i("LOG_Info", "RandomAccessFile");
            try {
                RandomAccessFile raf = new RandomAccessFile(subfile, "rw");
                raf.seek(subfile.length());
                raf.write(content.getBytes());
                raf.close();
            } catch (Exception e) {
                e.printStackTrace();
                //  Log.i("LOG_Error", "写入数据出错 " + e.getMessage());
            }
        } else if (Ways == 2) {
            // Log.i("LOG_Info", "Printer");
            try {
                FileOutputStream fileoutputStream = new FileOutputStream(subfile);
                //openFileOutput("text2", Context.MODE_PRIVATE);
                PrintStream ps = new PrintStream(fileoutputStream);
                ps.print(content + "\t");
                ps.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            Ways = 0;
        }
    }

    /**
     * @param fileDirName: 文件夹的路径
     * @Function: 列出文件夹下所有文件的名字
     * @Return:
     */
    private File[] ListFileDirName(String fileDirName) {
        File fileDir = new File(fileDirName);
        File[] files = new File[0];
        if (fileDir.isDirectory()) {
            files = fileDir.listFiles();
        }
        for (File a : files) {   //可以利用适配器做成界面  完成为了玩没意思
         //   Log.i("LOG_Info", a.toString());
        }
        return files;
  /*  /storage/emulated/0/Documents/SQLite/abcd.txt  手机的测试结果
      /mnt/internal_sd/Documents/SQLite/abcd.txt ARM板的测试结果*/
    }


    /**
     * @param fileDirName:文件夹目录
     * @param fileName:文件名字
     * @param ways:读取文件的方式
     * @Function: 从存储路径中读出数据
     * @Return:
     */
    public static String ReadDataFromStorage(String fileDirName, String fileName, int ways) throws IOException {
        ///storage/emulated/0/ipPath
        //storage/emulated/0/ipPath
        File file = new File("/storage/emulated/0/", "ipPath");
        if(!file.exists()){
           // Log.i("LOG_Info", "文件不存在");
            return "";
        }
        String fileContent = "";
        int Ways = ways;
        if (Ways == 0) {
          //  Log.i("LOG_Info", "FileInputStream");
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                byte[] bytes = new byte[fileInputStream.available()];
                fileInputStream.read(bytes);
                String result = new String(bytes);
               // Log.i("LOG_Info", "读取的内容是：" + result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (Ways == 1) {   //最好使用 Buffer 缓冲流，安全机制 大量的文件
          //  Log.i("LOG_Info", "Bufferreader");
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                String readline = "";
                StringBuffer stringBuffer = new StringBuffer();
                while ((readline = bufferedReader.readLine()) != null) {
                    stringBuffer.append(readline);
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                    Log.i("LOG_Info", "读取的内容是：" + stringBuffer);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (Ways == 2) {
          //  Log.i("LOG_Info", "Input+Buffer");
            try {

                InputStreamReader read = new InputStreamReader(new FileInputStream(file), "UTF-8");
                BufferedReader reader = new BufferedReader(read);
                String line;
                while ((line = reader.readLine()) != null) {
                    fileContent += line;
                }
                reader.close();
                read.close();
                //Log.i("读取的内容", fileContent);
            } catch (Exception e) {
                e.printStackTrace();
             //   Log.i("LOG_Error", e.getMessage());
            }
        } else{
            Ways = 2;
        }
        return fileContent;
    }

    /**
     * @param file: 文件/文件夹的路径
     * @Function: 文件夹  文件的删除
     * @Return:
     */
    private void DeleteFileDirORFile(File file) {
        if (file.exists() == false) {
            return;
        } else {
            if (file.isFile()) {
                file.delete();
                return;
            }
            if (file.isDirectory()) {
                File[] childFile = file.listFiles();
                if (childFile == null || childFile.length == 0) {
                    return;
                }
                if (childFile.length > 1) {
                    for (File f : childFile) {
                        DeleteFileDirORFile(f);
                    }
                }
            }
        }
    }
}
