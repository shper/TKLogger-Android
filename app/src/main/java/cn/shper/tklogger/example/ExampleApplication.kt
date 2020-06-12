package cn.shper.tklogger.example

import android.app.Application
import cn.shper.tklogger.TKLogger
import cn.shper.tklogger.destination.TKLogConsoleDestination
import cn.shper.tklogger.destination.TKLogDiskDestination
import cn.shper.tklogger.example.log.ReleaseLogFilter
import java.sql.BatchUpdateException

/**
 * Author : Shper
 * EMail : me@shper.cn
 * Date : 2020/6/11
 */
class ExampleApplication: Application() {

  override fun onCreate() {
    super.onCreate()

    setupTKLogger()
  }

  private fun setupTKLogger() {
    // Init to TKLogger
    TKLogger.setup()

    if (!BuildConfig.DEBUG) {
      TKLogger.addFilter(ReleaseLogFilter())
    }

    TKLogger.addDestination(TKLogConsoleDestination())
    TKLogger.addDestination(TKLogDiskDestination(this))
  }

}