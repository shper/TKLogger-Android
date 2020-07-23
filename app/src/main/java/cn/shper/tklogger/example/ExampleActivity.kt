package cn.shper.tklogger.example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import cn.shper.tklogger.TKLogger
import cn.shper.tklogger.example.filter.VerboseLogFilter
import kotlinx.android.synthetic.main.activity_example.*

/**
 * Author : Shper
 * EMail : me@shper.cn
 * Date : 2020/6/10
 */
class ExampleActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_example)

    setupClickListener()
  }

  private fun setupClickListener() {
    verbose_filter_level_btn.setOnClickListener {
      TKLogger.addFilter(VerboseLogFilter())
    }

    verbose_level_btn.setOnClickListener {
      TKLogger.v("This is the verbose level log.")
    }

    debug_level_btn.setOnClickListener {
      val data = "{\"name\":\"shper\"}"

      TKLogger.d("This is the debug level log.", data)
    }

    info_level_btn.setOnClickListener {
      TKLogger.i("This is the info level log.")
    }

    warning_level_btn.setOnClickListener {
      TKLogger.w("This is the warning level log.")
    }

    error_level_btn.setOnClickListener {
      TKLogger.e("This is the error level log.")
    }

  }

}