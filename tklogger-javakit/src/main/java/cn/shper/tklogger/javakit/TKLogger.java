package cn.shper.tklogger.javakit;

import androidx.annotation.Keep;

/**
 * Author: Shper
 * Email: me@shper.cn
 * Version: V0.1 2022/4/27
 */
@Keep
public class TKLogger {

    private TKLogger() throws InstantiationException {
        throw new InstantiationException();
    }

    public static void v(String message) {
        cn.shper.tklogger.TKLogger.INSTANCE.v(message, null, null);
    }

    public static void v(String message, Throwable tr) {
        cn.shper.tklogger.TKLogger.INSTANCE.v(message, null, tr);
    }

    public static void v(String message, String internalMessage) {
        cn.shper.tklogger.TKLogger.INSTANCE.v(message, internalMessage, null);
    }

    public static void v(String message, String internalMessage, Throwable tr) {
        cn.shper.tklogger.TKLogger.INSTANCE.v(message, internalMessage, tr);
    }

    public static void d(String message) {
        cn.shper.tklogger.TKLogger.INSTANCE.d(message, null, null);
    }

    public static void d(String message, Throwable tr) {
        cn.shper.tklogger.TKLogger.INSTANCE.d(message, null, tr);
    }

    public static void d(String message, String internalMessage) {
        cn.shper.tklogger.TKLogger.INSTANCE.d(message, internalMessage, null);
    }

    public static void d(String message, String internalMessage, Throwable tr) {
        cn.shper.tklogger.TKLogger.INSTANCE.d(message, internalMessage, tr);
    }

    public static void i(String message) {
        cn.shper.tklogger.TKLogger.INSTANCE.i(message, null, null);
    }

    public static void i(String message, Throwable tr) {
        cn.shper.tklogger.TKLogger.INSTANCE.i(message, null, tr);
    }

    public static void i(String message, String internalMessage) {
        cn.shper.tklogger.TKLogger.INSTANCE.i(message, internalMessage, null);
    }

    public static void i(String message, String internalMessage, Throwable tr) {
        cn.shper.tklogger.TKLogger.INSTANCE.i(message, internalMessage, tr);
    }

    public static void w(String message) {
        cn.shper.tklogger.TKLogger.INSTANCE.w(message, null, null);
    }

    public static void w(String message, Throwable tr) {
        cn.shper.tklogger.TKLogger.INSTANCE.w(message, null, tr);
    }

    public static void w(String message, String internalMessage) {
        cn.shper.tklogger.TKLogger.INSTANCE.w(message, internalMessage, null);
    }

    public static void w(String message, String internalMessage, Throwable tr) {
        cn.shper.tklogger.TKLogger.INSTANCE.w(message, internalMessage, tr);
    }

    public static void e(String message) {
        cn.shper.tklogger.TKLogger.INSTANCE.e(message, null, null);
    }

    public static void e(String message, Throwable tr) {
        cn.shper.tklogger.TKLogger.INSTANCE.e(message, null, tr);
    }

    public static void e(String message, String internalMessage) {
        cn.shper.tklogger.TKLogger.INSTANCE.e(message, internalMessage, null);
    }

    public static void e(String message, String internalMessage, Throwable tr) {
        cn.shper.tklogger.TKLogger.INSTANCE.e(message, internalMessage, tr);
    }

}
