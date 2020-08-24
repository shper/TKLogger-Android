package cn.shper.tklogger.destination

import androidx.annotation.Keep
import cn.shper.tklogger.LevelColor
import cn.shper.tklogger.LevelString
import cn.shper.tklogger.TKLogLevel
import cn.shper.tklogger.TKLogger
import cn.shper.tklogger.model.TKLogModel
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

/**
 * Author : Shper
 * EMail : me@shper.cn
 * Date : 2020/6/10
 */
@Keep
abstract class TKLogBaseDestination {

  open var format = "%Dyyyy-MM-dd HH:mm:ss %C %L/%T %t %c.%f:%l - %M %I"

  /// set custom log level words for each level
  open var levelString = LevelString()

  /// set custom log level colors for each level
  open var levelColor = LevelColor()

  abstract fun handlerLog(tkLog: TKLogModel)

  fun formatLog(tkLog: TKLogModel): String? {

    var text = ""
    val phrases = ("%i${this.format}").split("%")

    phrases.forEach { phrase ->
      if (phrase.isEmpty()) {
        return@forEach
      }
      val phrasePrefix = phrase[0]
      val remainingPhrase = phrase.subSequence(1, phrase.length)

      when (phrasePrefix) {
        'i' -> { // ignore
          text += remainingPhrase
        }
        'D' -> { // Date
          text += formatDate(remainingPhrase)
        }
        'C' -> { // LevelColor
          text += paddedString(colorForLevel(tkLog.level), remainingPhrase.toString())
        }
        'L' -> { // Level
          text += paddedString(levelWord(tkLog.level), remainingPhrase.toString())
        }
        'T' -> { // Tag
          text += paddedString(loggerTag(), remainingPhrase.toString())
        }
        't' -> { // threadName
          text += paddedString(tkLog.threadName, remainingPhrase.toString())
        }
        'c' -> { // clazzName
          text += paddedString(tkLog.clazzName, remainingPhrase.toString())
        }
        'F' -> { // fileName
          text += paddedString(tkLog.fileName, remainingPhrase.toString())
        }
        'f' -> { // functionName
          text += paddedString(tkLog.functionName, remainingPhrase.toString())
        }
        'l' -> { // line
          text += paddedString(tkLog.lineNum?.toString(), remainingPhrase.toString())
        }
        'M' -> { // Message
          text += paddedString(tkLog.message, remainingPhrase.toString())
        }
        'I' -> { // internal
          text += paddedString(tkLog.internalMessage, remainingPhrase.toString())
        }
        else -> {
          text += remainingPhrase
        }
      }
    }

    return text
  }

  fun paddedString(str1: String?, str2: String?): String {
    var str = (str1 ?: "") + (str2 ?: "")
    if (str == " ") {
      str = ""
    }

    return str
  }

  fun formatDate(pattern: CharSequence): String {
    val format: DateFormat = SimpleDateFormat(pattern.toString(), Locale.getDefault())
    return format.format(Date())
  }

  /**
   * returns color string for level
   */
  fun colorForLevel(level: TKLogLevel): String {
    return when (level) {
      TKLogLevel.DEBUG -> {
        levelColor.debug
      }
      TKLogLevel.INFO -> {
        levelColor.info
      }
      TKLogLevel.WARN -> {
        levelColor.warning
      }
      TKLogLevel.ERROR -> {
        levelColor.error
      }
      else -> {
        levelColor.verbose
      }
    }
  }

  /**
   * returns the string of a level
   */
  fun levelWord(level: TKLogLevel): String {
    return when (level) {
      TKLogLevel.DEBUG -> {
        levelString.debug
      }
      TKLogLevel.INFO -> {
        levelString.info
      }
      TKLogLevel.WARN -> {
        levelString.warning
      }
      TKLogLevel.ERROR -> {
        levelString.error
      }
      else -> {
        levelString.verbose
      }
    }
  }

  fun loggerTag(): String {
    return TKLogger.loggerTag
  }

}