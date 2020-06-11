package cn.shper.tklogger.example

import cn.shper.tklogger.TKLogLevel
import cn.shper.tklogger.filter.TKLogBaseFilter
import cn.shper.tklogger.model.FilterResult

/**
 * Author : Shper
 * EMail : me@shper.cn
 * Date : 2020/6/11
 */
class ExampleLogFilter: TKLogBaseFilter() {

  override fun handleFilter(level: TKLogLevel,
                            message: String?,
                            internalMessage: String?,
                            threadName: String,
                            clazzName: String,
                            fileName: String,
                            functionName: String): FilterResult {

    return super.handleFilter(level,
                              "AA$message",
                              internalMessage,
                              threadName,
                              clazzName,
                              fileName,
                              functionName)
  }

}