package cn.shper.tklogger

import androidx.annotation.Keep

/**
 * Author : Shper
 * EMail : me@shper.cn
 * Date : 2020/6/10
 */
@Keep
enum class TKLogLevel {
  VERBOSE,
  DEBUG,
  INFO,
  WARN,
  ERROR,
}

@Keep
class LevelString {
  var verbose = "V"
  var debug = "D"
  var info = "I"
  var warning = "W"
  var error = "E"
}

@Keep
class LevelColor {
  var verbose = "💜"    // silver
  var debug = "💙"      // green
  var info = "💚"       // blue
  var warning = "💛"    // yellow
  var error = "💔"      // red
}