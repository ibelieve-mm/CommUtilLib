package mm.chenme.lib.commutillibdemo.netdemo

/**
 * Descriptions：
 * StartVersion：
 * <p>
 * Author：ChenME
 * Date：2020/5/29
 * Email：ibelieve1210@163.com
 */


/*

"cityId":"CH010100",
"cityName":"北京",
"forecastDate":"2020-05-29",
"list":[]

 */
data class Weather(
    var cityId: String,
    var cityName: String,
    var forecastDate: String,
    var list: MutableList<InnerWeather>
)

/*

"type":"体感温度",
"index":"3",
"shortDesc":"较凉",
"longDesc":"老年、幼儿、体弱者外出需要带上薄围巾、薄手套。"

 */
data class InnerWeather(
    var type: String,
    var index: String,
    var shortDesc: String,
    var longDesc: String
)
