package cn.shper.tklogger;

import androidx.annotation.Keep;

import java.util.concurrent.ThreadPoolExecutor;

import cn.shper.tklogger.destination.TKLogBaseDestination;
import cn.shper.tklogger.filter.TKLogBaseFilter;

/**
 * Author: Shper
 * Email: me@shper.cn
 * Version: V0.1 2022/3/9
 */
@Keep
public class TKLoggerKt {

  private TKLoggerKt() throws InstantiationException {
    throw new InstantiationException();
  }

//  public static void setup(String tag) {
//    TKLogger.INSTANCE.setup(tag, TKLogLevel.VERBOSE, null);
//  }
//
//  public static void setup(String tag, TKLogLevel level) {
//    TKLogger.INSTANCE.setup(tag, level, null);
//  }
//
//  public static void setup(String tag, TKLogLevel level, ThreadPoolExecutor threadPool) {
//    TKLogger.INSTANCE.setup(tag, level, threadPool);
//  }
//
//  public static Boolean addDestination(TKLogBaseDestination destination) {
//    return TKLogger.INSTANCE.addDestination(destination);
//  }
//
//  public static Boolean addFilter(TKLogBaseFilter filter) {
//    return TKLogger.INSTANCE.addFilter(filter);
//  }
//
//  public static Boolean addFilter(TKLogBaseFilter filter, int priority) {
//    return TKLogger.INSTANCE.addFilter(filter, priority);
//  }

  public static void v(String message) {
    TKLogger.INSTANCE.v(message, null, null);
  }

  public static void v(String message, Throwable tr) {
    TKLogger.INSTANCE.v(message, null, tr);
  }

  public static void v(String message, String internalMessage) {
    TKLogger.INSTANCE.v(message, internalMessage, null);
  }

  public static void v(String message, String internalMessage, Throwable tr) {
    TKLogger.INSTANCE.v(message, internalMessage, tr);
  }

  public static void d(String message) {
    TKLogger.INSTANCE.d(message, null, null);
  }

  public static void d(String message, Throwable tr) {
    TKLogger.INSTANCE.d(message, null, tr);
  }

  public static void d(String message, String internalMessage) {
    TKLogger.INSTANCE.d(message, internalMessage, null);
  }

  public static void d(String message, String internalMessage, Throwable tr) {
    TKLogger.INSTANCE.d(message, internalMessage, tr);
  }

  public static void i(String message) {
    TKLogger.INSTANCE.i(message, null, null);
  }

  public static void i(String message, Throwable tr) {
    TKLogger.INSTANCE.i(message, null, tr);
  }

  public static void i(String message, String internalMessage) {
    TKLogger.INSTANCE.i(message, internalMessage, null);
  }

  public static void i(String message, String internalMessage, Throwable tr) {
    TKLogger.INSTANCE.i(message, internalMessage, tr);
  }

  public static void w(String message) {
    TKLogger.INSTANCE.w(message, null, null);
  }

  public static void w(String message, Throwable tr) {
    TKLogger.INSTANCE.w(message, null, tr);
  }

  public static void w(String message, String internalMessage) {
    TKLogger.INSTANCE.w(message, internalMessage, null);
  }

  public static void w(String message, String internalMessage, Throwable tr) {
    TKLogger.INSTANCE.w(message, internalMessage, tr);
  }

  public static void e(String message) {
    TKLogger.INSTANCE.e(message, null, null);
  }

  public static void e(String message, Throwable tr) {
    TKLogger.INSTANCE.e(message, null, tr);
  }

  public static void e(String message, String internalMessage) {
    TKLogger.INSTANCE.e(message, internalMessage, null);
  }

  public static void e(String message, String internalMessage, Throwable tr) {
    TKLogger.INSTANCE.e(message, internalMessage, tr);
  }

}
