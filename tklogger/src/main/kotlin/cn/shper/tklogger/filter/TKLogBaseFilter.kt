package cn.shper.tklogger.filter

import androidx.annotation.Keep
import cn.shper.tklogger.TKLogLevel
import cn.shper.tklogger.model.FilterResult

/**
 * Author : Shper
 * EMail : me@shper.cn
 * Date : 2020/6/10
 */
@Keep
open abstract class TKLogBaseFilter {

  open fun handleFilter(level: TKLogLevel,
                        message: String? = null,
                        internalMessage: String? = null,
                        threadName: String,
                        clazzName: String,
                        fileName: String,
                        functionName: String): FilterResult {

    return FilterResult().apply {
      this.isIgnore = false
      this.message = message
      this.internalMessage = internalMessage
      this.clazzName = clazzName
      this.fileName = fileName
      this.functionName = functionName
    }
  }
}