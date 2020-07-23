package mm.chenme.lib.commutillibdemo.consts

/**
 * Descriptions：
 * StartVersion：
 * <p>
 * Author：ChenME
 * Date：2020/6/13
 * Email：ibelieve1210@163.com
 */
object Flag {
    const val Param_PageType = "Param_PageType"
    const val Param_PageTitle = "Param_PageTitle"

    object InputArea {
        const val Param_InputContent = "Param_InputContent"
        const val Type_Remark4ClientInfo = 0xfffff // 客户信息备注
        const val Type_Remark4Charge = 0xffffe // 客户充值备注
        const val Type_ArticleTitle = 0xffffd // 发布文章标题
        const val Type_ArticleContent = 0xffffc // 发布文章内容
    }

    object ClientInfo {
        const val Param_ClientInfo = "Param_ClientInfo"
        const val Param_ClientName = "Param_ClientName"
        const val Type_New = 0xfffef
        const val Type_Modify = 0xfffee
    }

    object Article{
        const val Type_4Boss = 0xfffdf
        const val Type_4Technician = 0xfffde
    }
    object TextShowPage{
        const val Type_AppIntroduce = 0xfffcf
        const val Type_AppUseProtocol = 0xfffce
        const val Type_PersonalInfoOrPrivacyProtect = 0xfffcd
    }
}
