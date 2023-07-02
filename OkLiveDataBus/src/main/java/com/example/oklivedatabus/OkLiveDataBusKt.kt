package com.example.oklivedatabus

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.lang.reflect.Field
import java.lang.reflect.Method

/**
 * object 单例模式 去掉粘性事件（有关粘性的开关） Kotlin版本
 */
object OkLiveDataBusKt {

    // 存放订阅者
    // Any 任一对象
    private val bus: MutableMap<String, BusMutableLiveData<Any>> by lazy { HashMap() }

    // 泛型
    // 主构造
    // Any? 是Oject， * 星投影 忽略 不关注
    class BusMutableLiveData<T> private constructor() : MutableLiveData<T>() {
        private val TAG = "OkLiveDtaBusKt"

        var isStick: Boolean = false;

        // 次构造
        constructor(isStick: Boolean) : this() {
            this.isStick = isStick
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
            super.observe(owner, observer)

            if (isStick) {
                // 默认启用粘性
                Log.d(TAG, "observe: 启用粘性")
            } else {
                Log.d(TAG, "observe: 去除粘性")
                hook(observer)
            }
        }

        private fun hook(observer: Observer<in T>) {
            // TODO: 23-7-1 1. 得到 mLastVersion
            // 获取到LiveData类中的mObservers对象
            // ::class.java 转java类
            val liveDataClass = LiveData::class.java
            var mObserversField: Field = liveDataClass.getDeclaredField("mObservers")
            mObserversField.isAccessible = true // 私有修饰 也可以访问

            //获取到这个成员变量的对象  Any == Object
            val mObserversObject: Any = mObserversField.get(this)

            //得到map对象的class对象
            val mObserversClass = mObserversObject.javaClass
            // 获取到mObservers对象的get方法
            val get: Method = mObserversClass.getDeclaredMethod("get", Any::class.java)
            get.isAccessible = true

            // 执行get方法
            val invokeEntry = get.invoke(mObserversObject, observer)

            // 取到entry中的value is “AAA” is String    is是判断类型 as是转换类型
            var observerWraper: Any? = null
            if (invokeEntry != null && invokeEntry is Map.Entry<*, *>) {
                observerWraper = invokeEntry.value
            }
            if (observerWraper == null) {
                throw NullPointerException("observerWraper is null")
            }

            // 得到observerWraper的类对象
            val superclass: Class<*> = observerWraper.javaClass.superclass
            val mLastVersion: Field = superclass.getDeclaredField("mLastVersion")
            mLastVersion.isAccessible = true

            // TODO: 23-7-1 2. 得到 mVersion
            val mVersion: Field = liveDataClass.getDeclaredField("mVersion")
            mVersion.isAccessible = true

            // TODO: 23-7-1 3.  对齐 mLastVersion mVersion
            val mVersionValue = mVersion.get(this)
            mLastVersion.set(observerWraper, mVersionValue)
        }
    }

    // 暴露一个函数，给外界注册 订阅者关系
    // key 名字，type 类型 等价于 MutableLiveData<type> key
    @Synchronized
    fun <T> with(key: String, type: Class<T>, isStick: Boolean = true): BusMutableLiveData<T> {
        if (!bus.containsKey(key)) {
            bus[key] = BusMutableLiveData(isStick)
        }

        //as强转 类型转换
        return bus[key] as BusMutableLiveData<T>
    }
}