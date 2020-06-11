package cn.shper.tklogger.example.log

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
                              message,
                              internalMessage,
                              threadName,
                              clazzName,
                              fileName,
                              functionName)
  }

}