package com.base.baseproject.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.base.baseproject.R;


/**
 * Created by iman.
 * iman.neofight@gmail.com
 */

public class FileHandler {
    private static File BASE_PATH;

    private static FileHandler mFileHandler;
    public static FileHandler getInstance(Context ctx){
        ctx = ctx.getApplicationContext();
        if(mFileHandler == null)
            mFileHandler = new FileHandler(ctx);

        BASE_PATH = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator +
                ctx.getString(R.string.app_normalized_name));
        BASE_PATH.mkdirs();

        return mFileHandler;
    }


    Context mContext;
    private FileHandler(Context ctx){
        mContext = ctx;
    }



    public File getFile(String fileName){
        return new File(BASE_PATH + "/" + fileName);
    }
    public File createNewFile(String fileName){
        File myFile =
                new File(BASE_PATH + "/" + fileName);
        try {
            myFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return myFile;
    }
    public boolean isExistsFile(String fileName){
        return new File(BASE_PATH + "/" + fileName).exists();
    }
    public boolean deleteFile(String fileName){
        return new File(BASE_PATH + "/" + fileName).delete();
    }

    public void copy(File src, File dst){
        try {
            InputStream in = new FileInputStream(src);
            OutputStream out = new FileOutputStream(dst);

            // Transfer bytes from in to out
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            in.close();
            out.close();
        } catch (IOException e) {}
    }
}
