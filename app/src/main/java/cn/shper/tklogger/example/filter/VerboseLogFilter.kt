package cn.shper.tklogger.example.filter

import cn.shper.tklogger.TKLogLevel
import cn.shper.tklogger.filter.TKLogBaseFilter
import cn.shper.tklogger.model.TKLogModel

/**
 * Author : Shper
 * EMail : me@shper.cn
 * Date : 2020/6/11
 */
class VerboseLogFilter : TKLogBaseFilter {

  override fun handleFilter(tkLog: TKLogModel): TKLogModel {
    // Filter out debug messages
    if (tkLog.level <= TKLogLevel.VERBOSE) {
      tkLog.isIgnore = true
    }
    tkLog.internalMessage = null

    return tkLog
  }

}