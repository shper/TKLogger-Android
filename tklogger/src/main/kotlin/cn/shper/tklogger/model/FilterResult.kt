package cn.shper.tklogger.model

import androidx.annotation.Keep

/**
 * Author : Shper
 * EMail : me@shper.cn
 * Date : 2020/6/11
 */
@Keep
class FilterResult {

  var isIgnore: Boolean = true
  var message: String? = null
  var internalMessage: String? = null
  var clazzName: String? = null
  var fileName: String? = null
  var functionName: String? = null

}