## 基于 Kotlin 以及 AndroidX 封装的一个常用的工具库

### 1. 当前版本：`0.0.1`

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
    implementation 'com.github.ibelieve-mm:CommUtilLib:{版本号}'
}
```

---

### 历史版本

#### 0.0.1 预览版
> 1. 扩展 Data 类的方法；
> 2. 加入自定义 Log 工具类：Lt；