package cn.shper.tklogger.thread

import java.io.File
import java.io.FileFilter
import java.lang.Exception
import java.util.concurrent.*

/**
 * Author : Shper
 * EMail : me@shper.cn
 * Date : 2020/6/10
 */
object ThreadPoolUtils {

  private const val DEVICE_INFO_UNKNOWN = -1

  private val CORE_POOL_SIZE = Math.max(2, numberOfCPUCores) + 1

  private val MAX_POOL_SIZE = CORE_POOL_SIZE * 10

  private const val KEEP_ALIVE: Long = 30

  private val numberOfCPUCores: Int
    get() {
      return try {
        File("/sys/devices/system/cpu/").listFiles(CPU_FILTER)?.size ?: DEVICE_INFO_UNKNOWN
      } catch (e: Exception) {
        DEVICE_INFO_UNKNOWN
      }
    }

  private val CPU_FILTER = FileFilter { pathname ->
    val path = pathname.name
    //regex is slow, so checking char by char.
    if (path.startsWith("cpu")) {
      for (i in 3 until path.length) {
        if (path[i] < '0' || path[i] > '9') {
          return@FileFilter false
        }
      }
      return@FileFilter true
    }
    false
  }

  fun createThreadPool(): ThreadPoolExecutor {
    return ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE, TimeUnit.SECONDS, LinkedBlockingQueue())
  }

}