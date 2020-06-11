package cn.shper.tklogger.example

import android.app.Application
import cn.shper.tklogger.TKLogger
import cn.shper.tklogger.destination.TKLogConsoleDestination
import cn.shper.tklogger.destination.TKLogDiskDestination
import cn.shper.tklogger.example.log.ExampleLogFilter

/**
 * Author : Shper
 * EMail : me@shper.cn
 * Date : 2020/6/11
 */
class ExampleApplication: Application() {

  override fun onCreate() {
    super.onCreate()

    // Init to TKLogger
    TKLogger.setup()
    TKLogger.addFilter(ExampleLogFilter())
    TKLogger.addDestination(TKLogConsoleDestination())
    TKLogger.addDestination(TKLogDiskDestination(this))
  }

}