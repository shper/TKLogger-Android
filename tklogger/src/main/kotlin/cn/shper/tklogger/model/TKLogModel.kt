package cn.shper.tklogger.model

import androidx.annotation.Keep
import cn.shper.tklogger.TKLogLevel

/**
 * Author : Shper
 * EMail : me@shper.cn
 * Date : 2020/6/11
 */
@Keep
class TKLogModel {

  var isIgnore: Boolean = false

  var level: TKLogLevel = TKLogLevel.VERBOSE
  var message: String? = null
  var internalMessage: String? = null
  var clazzName: String? = null
  var fileName: String? = null
  var functionName: String? = null
  var threadName: String? = null
  var lineNum: Int? = null

}