package cn.shper.tklogger.destination

import android.content.Context
import cn.shper.tklogger.TKLogLevel
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.lang.Exception

/**
 * Author : Shper
 * EMail : me@shper.cn
 * Date : 2020/6/10
 */
class TKLogDiskDestination(private val context: Context) : TKLogBaseDestination() {

  val DATE_FORMAT = "yyyy-MM-dd_HH-mm-ss"

  var logFile: File? = null

  init {
    createLogDir()
  }

  override fun handlerLog(level: TKLogLevel,
                          message: String?,
                          internalMessage: String?,
                          threadName: String,
                          clazzName: String,
                          fileName: String,
                          functionName: String,
                          line: Int): String? {

    val logStr = super.handlerLog(level,
                                  message,
                                  internalMessage,
                                  threadName,
                                  clazzName,
                                  fileName,
                                  functionName,
                                  line)
    logStr?.let {
      saveToFile(it)
    }

    return logStr
  }

  fun createLogDir() {
    val cacheDir = context.cacheDir
    val tkLoggerDir = File(cacheDir, "TKLogger")
    tkLoggerDir.mkdirs()

    val fileName = "TKLogger_${formatDate(DATE_FORMAT)}.log"
    logFile = File(tkLoggerDir, fileName)
    logFile?.createNewFile()
  }

  fun saveToFile(logStr: String) {
    logFile?.let {
      var fileWriter: FileWriter? = null
      var bufferedWriter: BufferedWriter? = null

      try {
        fileWriter = FileWriter(it, true)
        bufferedWriter = BufferedWriter(fileWriter)

        bufferedWriter.write(logStr)
        bufferedWriter.newLine()
        bufferedWriter.flush()
      } catch (e: Exception) {
        e.printStackTrace()
      } finally {
        bufferedWriter?.close()
        fileWriter?.close()
      }
    }
  }

  override fun hashCode(): Int {
    return this.javaClass.name.hashCode()
  }

  override fun equals(other: Any?): Boolean {
    return this.hashCode() == other.hashCode()
  }

}