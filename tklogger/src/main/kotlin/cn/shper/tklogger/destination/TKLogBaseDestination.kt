package cn.shper.tklogger.destination

import cn.shper.tklogger.LevelColor
import cn.shper.tklogger.LevelString
import cn.shper.tklogger.TKLogLevel
import cn.shper.tklogger.TKLogger
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

/**
 * Author : Shper
 * EMail : me@shper.cn
 * Date : 2020/6/10
 */
abstract class TKLogBaseDestination {

  open var format = "%Dyyyy-MM-dd HH:mm:ss %C %L/%T %t %N.%F:%l - %M %I"

  /// runs in own serial background thread for better performance
  open var asynchronously = true

  /// set custom log level words for each level
  open var levelString = LevelString()

  /// set custom log level colors for each level
  open var levelColor = LevelColor()

  open fun handlerLog(level: TKLogLevel,
                      message: String? = null,
                      internalMessage: String? = null,
                      threadName: String,
                      clazzName: String,
                      functionName: String,
                      line: Int): String? {
    return formatLog(level, message, internalMessage, threadName, clazzName, functionName, line)
  }

  fun formatLog(level: TKLogLevel,
                message: String? = null,
                internalMessage: String? = null,
                threadName: String,
                clazzName: String,
                functionName: String,
                line: Int): String? {

    var text = ""
    val phrases = ("%i${this.format}").split("%")

    phrases.forEach { phrase ->
      if (phrase.isEmpty()) {
        return@forEach
      }
      val phrasePrefix = phrase[0]
      val remainingPhrase = phrase.subSequence(1, phrase.length)

      when (phrasePrefix) {
        'i' -> {
          text += remainingPhrase
        } // ignore
        'D' -> {
          text += formatDate(remainingPhrase)
        }
        'C' -> {
          text += paddedString(colorForLevel(level), remainingPhrase.toString())
        }
        'L' -> {
          text += paddedString(levelWord(level), remainingPhrase.toString())
        }
        'T' -> {
          text += paddedString(loggerTag(), remainingPhrase.toString())
        }
        't' -> {
          text += paddedString(threadName, remainingPhrase.toString())
        }
        'N' -> {
          text += paddedString(clazzName, remainingPhrase.toString())
        }
        'F' -> {
          text += paddedString(functionName, remainingPhrase.toString())
        }
        'l' -> {
          text += paddedString(line.toString(), remainingPhrase.toString())
        }
        'M' -> {
          text += paddedString(message ?: "", remainingPhrase.toString())
        }
        'I' -> {
          text += paddedString(internalMessage ?: "", remainingPhrase.toString())
        }
        else -> {
          text += remainingPhrase
        }
      }
    }

    return text
  }

  fun paddedString(str1: String, str2: String): String {
    var str = str1 + str2
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