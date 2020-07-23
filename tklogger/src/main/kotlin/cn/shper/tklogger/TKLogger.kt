package cn.shper.tklogger

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

  private fun dispatchLog(level: TKLogLevel,
                          msg: String? = null,
                          interMsg: String? = null) {

    if (level.ordinal < minLevel.ordinal) {
      return
    }

    val stackTraceElement = getCallerStackTraceElement()
    var tkLog = TKLogModel().apply {
      this.level = level
      this.threadName = getThreadName()
      this.message = msg
      this.internalMessage = interMsg
      this.clazzName = stackTraceElement.className
      this.fileName = stackTraceElement.fileName
      this.functionName = stackTraceElement.methodName
      this.lineNum = stackTraceElement.lineNumber
    }

    ThreadPoolUtils.threadPool?.execute {
      // Use filters to process logs
      filters.forEach { filter ->
        tkLog = filter.handleFilter(tkLog)

        if (tkLog.isIgnore) {
          return@execute
        }
      }

      // dispatch the logs to destination
      destinations.forEach { destination ->
        destination.handlerLog(tkLog)
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