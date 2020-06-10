package cn.shper.tklogger

import cn.shper.tklogger.destination.TKLogBaseDestination
import cn.shper.tklogger.destination.TKLogConsoleDestination
import cn.shper.tklogger.filter.TKLogBaseFilter
import java.util.*
import kotlin.collections.ArrayList

/**
 * Author : Shper
 * EMail : me@shper.cn
 * Date : 2020/6/10
 */
object TKLogger {

  var loggerTag = "TKLogger"

  var minLevel = TKLogLevel.VERBOSE

  var destinations = ArrayList<TKLogBaseDestination>()
  var filters = ArrayList<TKLogBaseFilter>()

  fun setup(tag: String = "TKLogger", level: TKLogLevel = TKLogLevel.VERBOSE) {
    loggerTag = tag

    minLevel = level

    addDestination(TKLogConsoleDestination())
  }

  /** Destination */
  fun addDestination(destination: TKLogBaseDestination): Boolean {
    if (destinations.contains(destination)) {
      return false
    }

    destinations.add(destination)
    return true
  }

  /** Filter */
  fun addFilter(filter: TKLogBaseFilter): Boolean {
    if (filters.contains(filter)) {
      return false
    }

    filters.add(filter)
    return true
  }

  /** Levels */

  fun v(message: String? = null, internalMessage: String? = null) {
    dispatchLog(TKLogLevel.VERBOSE, message, internalMessage)
  }

  fun d(message: String? = null, internalMessage: String? = null) {
    dispatchLog(TKLogLevel.DEBUG, message, internalMessage)

  }

  fun i(message: String? = null, internalMessage: String? = null) {
    dispatchLog(TKLogLevel.INFO, message, internalMessage)
  }

  fun w(message: String? = null, internalMessage: String? = null) {
    dispatchLog(TKLogLevel.WARN, message, internalMessage)
  }

  fun e(message: String? = null, internalMessage: String? = null) {
    dispatchLog(TKLogLevel.ERROR, message, internalMessage)
  }

  /** Inner Function */

  // 2020-06-10 16:26:54 💚 D/TKLogger RootViewController.debugLogBtnClickFun():145 - This is the debug level log.

  private fun dispatchLog(level: TKLogLevel,
                          message: String? = null,
                          internalMessage: String? = null) {

    if (level.ordinal < minLevel.ordinal) {
      return
    }

    val threadName = getThreadName()

    val stackTraceElement = getStackTraceElement()
    val clazzName: String = stackTraceElement.className
    val functionName: String = stackTraceElement.methodName
    val line: Int = stackTraceElement.lineNumber

    // Use filters to process logs
    filters.forEach { filter ->
      // TODO
    }

    // dispatch the logs to destination
    destinations.forEach { destination ->
      if (destination.asynchronously) {
        // TODO
        destination.handlerLog(level,
                               message,
                               internalMessage,
                               threadName,
                               clazzName,
                               functionName,
                               line)
      } else {
        // TODO
      }
    }
  }

  private fun getThreadName(): String {
    var threadName = Thread.currentThread().name
    if (threadName.toLowerCase(Locale.ROOT) == "main") {
      threadName = ""
    }

    return threadName
  }

  private fun getStackTraceElement(): StackTraceElement {
    val temp = Thread.currentThread().stackTrace
    // Java function stack first element is 'getStackTrace'
    // Kotlin function stack first element is 'getThreadStackTrace', second is 'getStackTrace'
    // TODO 找到 TKLogger 的最先一个的调用栈 的 上一个栈 就是调用方的 函数
    val stackTraceElement: StackTraceElement = if (temp[0].methodName == "getThreadStackTrace") {
      Thread.currentThread().stackTrace[6]
    } else {
      Thread.currentThread().stackTrace[5]
    }

    var clazzName = stackTraceElement.className
    clazzName = clazzName.substring(clazzName.lastIndexOf(".") + 1)

    return StackTraceElement(clazzName,
                             stackTraceElement.methodName + "()",
                             stackTraceElement.fileName,
                             stackTraceElement.lineNumber)
  }

}