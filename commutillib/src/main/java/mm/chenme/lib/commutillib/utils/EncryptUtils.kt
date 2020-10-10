package mm.chenme.lib.commutillib.utils

import java.lang.Exception
import java.math.BigInteger
import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

/**
 * Descriptions：加密工具类
 * StartVersion：
 * <p>
 * Author：ChenME
 * Date：2020/6/2
 * Email：ibelieve1210@163.com
 */

/*---------------------------------- MD2 --------------------------------------*/
fun String?.md2(): String = if (this == null || this.isEmpty()) "" else this.toByteArray(Charsets.UTF_8).md2()
fun ByteArray?.md2(): String = if (null == this || this.isEmpty()) "" else this.md2ToBytes().bytes2HexString()
fun ByteArray?.md2ToBytes(): ByteArray = this.hashTemplate("MD2")

/*---------------------------------- MD5 --------------------------------------*/
fun String?.md5(): String = if (this.isNullOrEmpty()) "" else this.toByteArray().md5()

fun String?.md5(salt: String?): String {
    if (this == null && salt == null) return ""
    if (salt == null) return this!!.toByteArray().md5ToBytes().bytes2HexString()
    return if (this == null) salt.toByteArray().md5ToBytes().bytes2HexString() else (this + salt).toByteArray().md5ToBytes().bytes2HexString()
}

fun ByteArray?.md5(): String = this.md5ToBytes().bytes2HexString()

fun ByteArray?.md5(salt: ByteArray?): String {
    if (this == null && salt == null) return ""
    if (salt == null) return this.md5ToBytes().bytes2HexString()
    if (this == null) return salt.md5ToBytes().bytes2HexString()
    val dataSalt = ByteArray(this.size + salt.size)
    System.arraycopy(this, 0, dataSalt, 0, this.size)
    System.arraycopy(salt, 0, dataSalt, this.size, salt.size)
    return dataSalt.md5ToBytes().bytes2HexString()
}

fun ByteArray?.md5ToBytes(): ByteArray = this.hashTemplate("MD5")

/*---------------------------------- SHA1 --------------------------------------*/
fun String?.sha1(): String = if (this.isNullOrEmpty()) "" else this.toByteArray().sha1()
fun ByteArray?.sha1(): String = this.sha1ToBytes().bytes2HexString()
fun ByteArray?.sha1ToBytes(): ByteArray = this.hashTemplate("SHA1")


fun ByteArray?.hashTemplate(algorithm: String): ByteArray {
    return if (this == null || this.isEmpty()) ByteArray(0) else try {
        val md: MessageDigest = MessageDigest.getInstance(algorithm)
        md.update(this)
        md.digest()
    } catch (e: NoSuchAlgorithmException) {
        e.printStackTrace()
        ByteArray(0)
    }
}


/*---------------------------------- HmacSHA256 --------------------------------------*/
fun hmacSHA256(sour: String, secret: String): String {
    return try {
        val mac = Mac.getInstance("HmacSHA256")
        mac.init(SecretKeySpec(secret.toByteArray(StandardCharsets.UTF_8), "HmacSHA256"))
        mac.update(sour.toByteArray(charset("UTF-8")))
        val result = mac.doFinal()
        BigInteger(1, result).toString(16)
    }catch (e:Exception){
        ""
    }
}
