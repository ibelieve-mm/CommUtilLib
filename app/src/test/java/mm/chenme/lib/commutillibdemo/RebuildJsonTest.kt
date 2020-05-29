package mm.chenme.lib.commutillibdemo

import com.google.gson.Gson
import org.junit.Test
import java.io.*

/**
 * Descriptions：
 * StartVersion：
 * <p>
 * Author：ChenME
 * Date：2020/5/22
 * Email：ibelieve1210@163.com
 */

class RebuildJsonTest {
    @Test
    fun reBuildJsonString() {

        try { // 防止文件建立或读取失败，用catch捕捉错误并打印，也可以throw

            // implementation 'com.google.code.gson:gson:2.6.2'
            /**
             * 读取文件中字符串
             */
            val pathname = "location.json" // 相对路径，即项目app文件夹下（在 Java 的单元测试的情况下）
            val filename = File(pathname) // 要读取以上路径的文件
            val reader = InputStreamReader(FileInputStream(filename)) // 建立一个输入流对象reader
            val br = BufferedReader(reader) // 建立一个对象，它把文件内容转成计算机能读懂的语言
            val sb = StringBuilder()
            var line: String? = null
            while (br.readLine().also { line = it } != null) {
                sb.append(line)
            }

            /**
             * 数据处理
             */
            val location = Gson().fromJson(sb.toString(), LocationData::class.java) // 将json字符串转换成实体类
            // 使用循环添加数据
            location.result.geoLocations.forEach {
                it.value.forEach { city ->
                    city.value.add(0, District("全选"))
                }
            }
            val result = Gson().toJson(location) // 将实体类还原成json字符串

            /**
             * 写出数据
             */
            val writeName = File("output.json") // 相对路径，如果没有则要建立一个新的output.json
            writeName.createNewFile() // 创建新文件
            val out = BufferedWriter(FileWriter(writeName))
            out.write(result)
            out.flush() // 把缓存区内容压入文件
            out.close() // 最后记得关闭文件
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    data class LocationData(
        var code: Int,
        var result: LocationResult
    )

    data class LocationResult(
        var geoLocations: List<Province>,
        var district: List<Province>,
        var rbd: MutableList<Province>
    )

    data class Province(
        var name: String = "",
        var value: MutableList<City>
    )

    data class City(
        var name: String = "",
        var value: MutableList<District>
    )

    data class District(
        var name: String = "",
        var value: String = ""
    )
}