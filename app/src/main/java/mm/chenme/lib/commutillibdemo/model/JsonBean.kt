package mm.chenme.lib.commutillibdemo.model

/**
 * Descriptions：
 * <p>
 * Author：ChenME
 * Date：2020/5/1
 * Email：ibelieve1210@163.com
 */

data class DataBean(
    var data: List<CityBean>
)

data class CityBean(
    var name: String = "",
    var isSelected: Boolean = false,
    var selectCount: Int = 0,
    var value: List<AreaBean>
)

data class AreaBean(
    var name: String = "",
    var isSelected: Boolean = false,
    var selectCount: Int = 0,
    var value: List<StreetBean>
)

data class StreetBean(
    var name: String = "",
    var isSelected: Boolean = false,
    var value: Long = 0L
)


data class ResultBean(
    var city: String = "",
    var area: String = "",
    var street: String = "",
    var cIndex: Int = -1,
    var aIndex: Int = -1,
    var code: Long = 0L
)