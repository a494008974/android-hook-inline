package com.letv.pp.service;


import java.net.Socket;
import java.util.UUID;

public class LeService
{
  public static long Handle = -1L;
  private static LeService Object = null;
  
  private LeService()
  {
    for (int i=16890; i>9000; i--)
    {
      if (!checkProt(i))
      {
        Handle = accaStartServiceWithParams(String.format("http_port=%d&app_id=1000&log_tag=vst&expect=3&log_level=0&cache.max_size=30M&downloader.pre_download_size=10M&enable_keep_alive=off&pp.enable_upnp=on&downloader.urgent_slice_num=3&m3u8_target_duration=3&app_version=3.0&ostype=android&hwtype=android&enable_authorize=ON&task_stop_sleep_time=0&channel_default_multi=1&channel_max_count=1&app_channel=vst&uuid=%s&sdk=cde&network_type=1", new Object[] { Integer.valueOf(i), UUID.randomUUID().toString() }));
        if (Handle > 0L) {
          CdeService.port = accaGetServicePort(Handle);
        }
        return;
      }
    }
  }

  public boolean checkProt(int paramInt)
  {
    try
    {
      new Socket("127.0.0.1", paramInt);
      return true;
    }
    catch (Throwable localThrowable) {}
    return false;
  }

  private static native long accaGetServicePort(long paramLong);
  
  private static native long accaStartServiceWithParams(String paramString);
  
  private static native long accaStopService(long paramLong);
  
  public static void close()
  {
    if (Handle > 0L) {
      accaStopService(Handle);
    }
  }
  
  public static LeService get()
  {
    if (Object == null) {
      Object = new LeService();
    }
    return Object;
  }
}
