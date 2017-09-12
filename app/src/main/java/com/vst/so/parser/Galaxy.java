package com.vst.so.parser;

import android.content.Context;

public class Galaxy {
    private static Galaxy Instance = null;
    private boolean isSo = false;

    public Galaxy()
    {
        try
        {
            if (!this.isSo)
            {
                this.isSo = true;
//                String str = MainApplication.instance.getFilesDir().getPath()+ File.separator+"libgalaxy.so";
//                System.load(str);
                System.loadLibrary("galaxy");
            }
            return;
        }
        catch (Throwable e)
        {

        }
    }
    public static Galaxy get()
    {
        if (Instance == null) {
            Instance = new Galaxy();
        }
        return Instance;
    }

    public native String start(Context context);
    public native String end();
}
