package cn.shper.tklogger.destination

import cn.shper.tklogger.TKLogLevel

/**
 * Author : Shper
 * EMail : me@shper.cn
 * Date : 2020/6/10
 */
class TKLogDiskDestination: TKLogBaseDestination() {

  override fun handlerLog(level: TKLogLevel,
                          message: String?,
                          internalMessage: String?,
                          threadName: String,
                          clazzName: String,
                          functionName: String,
                          line: Int): String? {
    return super.handlerLog(level,
                            message,
                            internalMessage,
                            threadName,
                            clazzName,
                            functionName,
                            line)
  }



}