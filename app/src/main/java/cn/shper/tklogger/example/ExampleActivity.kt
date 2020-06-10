package cn.shper.tklogger.example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import cn.shper.tklogger.TKLogger
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Author : Shper
 * EMail : me@shper.cn
 * Date : 2020/6/10
 */
class ExampleActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    TKLogger.setup()

    start_btn.setOnClickListener {
      TKLogger.d("AAA")

      Thread(Runnable {
        TKLogger.v("BBB")
      }).start()

    }
  }


}