package cn.shper.tklogger.example.log

import cn.shper.tklogger.TKLogLevel
import cn.shper.tklogger.filter.TKLogBaseFilter
import cn.shper.tklogger.model.FilterResult

/**
 * Author : Shper
 * EMail : me@shper.cn
 * Date : 2020/6/11
 */
class ReleaseLogFilter: TKLogBaseFilter() {

  override fun handleFilter(level: TKLogLevel,
                            message: String?,
                            internalMessage: String?,
                            threadName: String,
                            clazzName: String,
                            fileName: String,
                            functionName: String): FilterResult {

    // Filter out debug messages
    var isIgnore = false
    if (level <= TKLogLevel.DEBUG) {
      isIgnore = true
    }

    // Filter out internal messages
    val emptyMessage = null

    return FilterResult().apply {
      this.isIgnore = isIgnore
      this.message = message
      this.internalMessage = emptyMessage
      this.clazzName = clazzName
      this.fileName = fileName
      this.functionName = functionName
    }
  }

}