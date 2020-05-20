## 基于 Kotlin 以及 AndroidX 封装的一个常用的工具库

### 1. 当前版本：`0.0.1.1`

### 2. 使用步骤：

```groovy
// 1. root build.gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}

// 2. dependency
dependencies {
    implementation "com.github.ibelieve-mm:CommUtilLib:{版本号}"
}
```

---

### lib  已经依赖的三方库（使用时需要排除）

1. 基本组件

```groovy
api "androidx.constraintlayout:constraintlayout:1.1.3"
api "androidx.recyclerview:recyclerview:1.1.0"
```

2. 网络框架

```groovy
api "com.squareup.retrofit2:retrofit:2.5.0"
api "com.squareup.retrofit2:converter-gson:2.5.0"
api "com.squareup.retrofit2:adapter-rxjava2:2.5.0"
api "com.squareup.okhttp3:logging-interceptor:3.12.3"
api "io.reactivex.rxjava2:rxjava:2.2.4"
api "io.reactivex.rxjava2:rxandroid:2.1.0"
api "android.arch.lifecycle:extensions:1.1.1"
```

---

### 关于 Proguard

#### 1. 打包时删除 log 日志

```proguard
### --- start: 去除自定义的 Log 日志 ---------------------------------------------------------
-assumenosideeffects class mm.chenme.lib.commutillib.utils.* {
    # mm.chenme.lib.commutillib.utils.LogUtil
    *** loge(...);
    *** logw(...);
    *** logd(...);
    *** logi(...);
    *** logd(...);
}
### --- end: 去除自定义的 Log 日志 -----------------------------------------------------------
```

---

### 历史版本

#### 0.0.1.1 预览版
> 1. 扩展 Date(日期)类 的方法；
> 2. 加入自定义 Log 工具；