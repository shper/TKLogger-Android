package cn.shper.tklogger

import android.util.Log
import androidx.annotation.Keep
import cn.shper.tklogger.destination.TKLogBaseDestination
import cn.shper.tklogger.filter.TKLogBaseFilter
import cn.shper.tklogger.model.TKLogModel
import cn.shper.tklogger.thread.ThreadPoolUtils
import java.util.*
import java.util.concurrent.ThreadPoolExecutor
import kotlin.collections.ArrayList
import kotlin.collections.HashSet

/**
 * Author : Shper
 * EMail : me@shper.cn
 * Date : 2020/6/10
 */
@Keep
object TKLogger {

  var loggerTag = "TKLogger"

  var minLevel = TKLogLevel.VERBOSE

  private var destinations = HashSet<TKLogBaseDestination>()

  private var filters = ArrayList<TKLogBaseFilter>()

  fun setup(tag: String = "TKLogger",
            level: TKLogLevel = TKLogLevel.VERBOSE,
            threadPool: ThreadPoolExecutor? = null) {
    this.loggerTag = tag
    this.minLevel = level
    ThreadPoolUtils.threadPool = threadPool
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

  fun addFilter(filter: TKLogBaseFilter, priority: Int): Boolean {
    if (filters.contains(filter)) {
      return false
    }

    filters.add(priority, filter)
    return true
  }

  /** Levels */

  fun v(message: String? = null, internalMessage: String? = null, tr: Throwable? = null) {
    dispatchLog(TKLogLevel.VERBOSE, message, internalMessage, tr)
  }

  fun d(message: String? = null, internalMessage: String? = null, tr: Throwable? = null) {
    dispatchLog(TKLogLevel.DEBUG, message, internalMessage, tr)
  }

  fun i(message: String? = null, internalMessage: String? = null, tr: Throwable? = null) {
    dispatchLog(TKLogLevel.INFO, message, internalMessage, tr)
  }

  fun w(message: String? = null, internalMessage: String? = null, tr: Throwable? = null) {
    dispatchLog(TKLogLevel.WARN, message, internalMessage, tr)
  }

  fun e(message: String? = null, internalMessage: String? = null, tr: Throwable? = null) {
    dispatchLog(TKLogLevel.ERROR, message, internalMessage, tr)
  }

  /** Inner Function */

  private fun dispatchLog(level: TKLogLevel,
                          msg: String? = null,
                          interMsg: String? = null,
                          tr: Throwable? = null) {

    if (level.ordinal < minLevel.ordinal) {
      return
    }

    val stackTraceElement = getCallerStackTraceElement()
    var tkLog = TKLogModel().apply {
      this.level = level
      this.threadName = getThreadName()
      this.message = msg
      this.internalMessage = interMsg
      tr?.let {
        this.throwableMessage = Log.getStackTraceString(it)
      }
      this.clazzName = stackTraceElement.className
      this.fileName = stackTraceElement.fileName
      this.functionName = stackTraceElement.methodName
      this.lineNum = stackTraceElement.lineNumber
    }

    ThreadPoolUtils.threadPool?.execute {
      // Use filters to process logs
      filters.forEach { filter ->
        try {
          tkLog = filter.handleFilter(tkLog)
          if (tkLog.isIgnore) {
            return@execute
          }
        } catch (e: Exception) {
          e.printStackTrace()
        }
      }

      // dispatch the logs to destination
      destinations.forEach { destination ->
        ThreadPoolUtils.threadPool?.execute {
          try {
            destination.handlerLog(tkLog)
          } catch (e: Exception) {
            e.printStackTrace()
          }
        }
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

  private fun getCallerStackTraceElement(): StackTraceElement {
    val stackTraces = Thread.currentThread().stackTrace
    var callerStackTrace: StackTraceElement? = null

    run breaking@{
      stackTraces.forEachIndexed { index, stackTraceElement ->
        if (stackTraceElement.className.startsWith(TKLogger.javaClass.name)
          && index < stackTraces.size
          && !stackTraces[index + 1].className.startsWith(TKLogger.javaClass.name)) {
          callerStackTrace = stackTraces[index + 1]
          return@breaking
        }
      }
    }

    var clazzName = callerStackTrace?.className
    clazzName = clazzName?.substring(clazzName.lastIndexOf(".") + 1)

    var fileName = callerStackTrace?.fileName
    fileName = fileName?.substring(0, fileName.lastIndexOf("."))

    return StackTraceElement(clazzName,
                             callerStackTrace?.methodName + "()",
                             fileName,
                             callerStackTrace?.lineNumber ?: 0)
  }

}