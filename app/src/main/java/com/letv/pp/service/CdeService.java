package com.letv.pp.service;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Base64;

import com.vst.player.MD5;
import com.vst.player.MainApplication;
import com.vst.so.parser.Galaxy;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

public class CdeService
{
  private static CdeService Object = null;
  private static String baseUrl;
  public static String pkName = "com.elinkway.tvlive2";
  public static long port = 0L;
  public static long gport = 19687L;
  private boolean isSo = false;

  static
  {
    baseUrl = null;
  }
  
  private CdeService()
  {
    try
    {
      if (!this.isSo)
      {
          this.isSo = true;
          String str = MainApplication.instance.getFilesDir().getPath()+ File.separator+"libcde.so";
          System.load(str);
          Galaxy.get().start(MainApplication.instance);
          if ((initLinkShell() > -1)) {
            LeService.get();
          }

      }
      return;
    }
    catch (Throwable e)
    {
    }
  }
  
  public static CdeService get()
  {
    if (Object == null) {
      Object = new CdeService();
    }
    return Object;
  }
  
  public static void stopPlay()
  {
    if ((baseUrl != null) && (port > 0L))
    {
//      tool.get().Connection(String.format("http://127.0.0.1:%d/play/stop?enc=base64&url=%s&mediatype=m3u8", new Object[] { Long.valueOf(port), baseUrl }));
      baseUrl = null;
    }
  }
  
  public Context getApplicationContext()
  {
    return MainApplication.instance;
  }
  
  public PackageManager getPackageManager()
  {
    return new getManager();
  }
  
  public String getPackageName()
  {
    return pkName;
  }
  
  public native int getTime();
  
  public native String getURLFromLinkShell(String url);
  
  public native String getVersion();
  
  
 public String get_HostUrl(String url)
  {
      String build_id = MD5.string2MD5(Build.ID);
      String time = MD5.string2MD5(build_id+System.currentTimeMillis());
      String str = String.format("%s_%s", build_id,time);
      byte[] urlByte;
      try {
          urlByte = Base64.encode(str.getBytes("utf-8"), Base64.NO_WRAP);
          str = new String(urlByte, "utf-8");
      } catch (UnsupportedEncodingException e) {
      }
      url = url + "&app_ver=2.9.8&hwtype=MP-LK_3128B&ostype=android&p1=2&p2=2w&platid=10&play=0&playid=1&splatid=1011&termid=3&uuid="+UUID.randomUUID()+"&biasen="+str;
      return get_baseUrl(url);
  }
  
 public String parserTimeShift(String url,long timeshift)
  {
      url = url + "&app_ver=2.9.1&hwtype=MP-LK_3128B&ostype=android&p1=2&p2=2w&platid=10&play=0&playid=1&splatid=1011&termid=3&uuid="+UUID.randomUUID()+"&timeshift=-"+timeshift;
	  return get_baseUrl(url);
  }
  
  public String get_baseUrl(String baseUrl)
  {
    long l = System.currentTimeMillis() / 1000L;
    
    String str = getURLFromLinkShell(baseUrl);
	  byte[] urlByte;
	  try {
			urlByte = Base64.encode(str.getBytes("utf-8"), Base64.NO_WRAP);
			str = new String(urlByte, "utf-8");
	  } catch (UnsupportedEncodingException e) {
	  }
    return String.format("http://127.0.0.1:%s/play?enc=base64&url=%s&ext=m3u8&mediatype=m3u8&tagtime=%d", new Object[] { port, str, getTime() });
  }
  
  public native int initLinkShell();
  
  public native int setEnv(String p1, String p2);
}
