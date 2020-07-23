package cn.shper.tklogger.destination

import android.util.Log
import cn.shper.tklogger.LevelColor
import cn.shper.tklogger.TKLogLevel
import cn.shper.tklogger.TKLogger
import cn.shper.tklogger.model.TKLogModel

/**
 * Author : Shper
 * EMail : me@shper.cn
 * Date : 2020/6/10
 */
class TKLogConsoleDestination : TKLogBaseDestination() {

  override var format = "%C %t %c.%f:%l - %M %I"

  override fun handlerLog(tkLog: TKLogModel) {

    val logStr = formatLog(tkLog) ?: ""
    when (tkLog.level) {
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
  }

  override fun hashCode(): Int {
    return this::javaClass.name.hashCode()
  }

  override fun equals(other: Any?): Boolean {
    return this.hashCode() == other.hashCode()
  }

}