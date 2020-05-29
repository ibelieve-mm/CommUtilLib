package mm.chenme.lib.commutillib.utils

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders

/**
 * Descriptions：
 * StartVersion：
 * <p>
 * Author：ChenME
 * Date：2020/5/29
 * Email：ibelieve1210@163.com
 */
inline fun <reified T : ViewModel> FragmentActivity.bindViewModel() = ViewModelProviders.of(this).get(T::class.java)