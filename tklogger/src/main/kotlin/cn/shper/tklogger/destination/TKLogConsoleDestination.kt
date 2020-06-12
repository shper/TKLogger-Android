package cn.shper.tklogger.destination

import android.util.Log
import cn.shper.tklogger.LevelColor
import cn.shper.tklogger.TKLogLevel
import cn.shper.tklogger.TKLogger

/**
 * Author : Shper
 * EMail : me@shper.cn
 * Date : 2020/6/10
 */
class TKLogConsoleDestination : TKLogBaseDestination() {

  override var format = "%C %t %c.%f:%l - %M %I"

  override fun handlerLog(level: TKLogLevel,
                          message: String?,
                          internalMessage: String?,
                          threadName: String,
                          clazzName: String,
                          fileName: String,
                          functionName: String,
                          line: Int): String? {

    val logStr = super.handlerLog(level,
                                  message,
                                  internalMessage,
                                  threadName,
                                  clazzName,
                                  fileName,
                                  functionName,
                                  line) ?: ""

    when (level) {
      TKLogLevel.VERBOSE -> {
        Log.v(TKLogger.loggerTag, logStr)
      }
      TKLogLevel.DEBUG -> {
        Log.d(TKLogger.loggerTag, logStr)
      }
      TKLogLevel.INFO -> {
        Log.i(TKLogger.loggerTag, logStr)
      }
      TKLogLevel.WARN -> {
        Log.w(TKLogger.loggerTag, logStr)
      }
      TKLogLevel.ERROR -> {
        Log.e(TKLogger.loggerTag, logStr)
      }
    }

    return null
  }

  override fun hashCode(): Int {
    return this::javaClass.name.hashCode()
  }

  override fun equals(other: Any?): Boolean {
    return this.hashCode() == other.hashCode()
  }

}