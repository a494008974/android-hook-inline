package com.vst.player;

import android.app.Application;

/**
 * Created by Administrator on 2016/10/13.
 */
public class MainApplication extends Application {
    public static MainApplication instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;


        String path=this.getFilesDir().getAbsolutePath();
        String name="libcde.so";
        CopyFileFromAssets.copy(this, name, path, name);
    }
}
