package cn.shper.tklogger.filter

import androidx.annotation.Keep
import cn.shper.tklogger.TKLogLevel
import cn.shper.tklogger.model.TKLogModel

/**
 * Author : Shper
 * EMail : me@shper.cn
 * Date : 2020/6/10
 */
@Keep
open interface TKLogBaseFilter {

  fun handleFilter(tkLog: TKLogModel): TKLogModel

}