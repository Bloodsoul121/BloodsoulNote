package com.gionee.bloodsoulnote.heap.lrucache;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

public class ImgLruCache extends LruCache<String, Bitmap> {
    /**
     *  for caches that do not override {@link #sizeOf}, this is
     *                the maximum number of entries in the cache. For all other caches,
     *                this is the maximum sum of the sizes of the entries in this cache.
     */
    public ImgLruCache() {
        // 构造方法传入当前应用可用最大内存的八分之一
        super((int) (Runtime.getRuntime().maxMemory() / 8));
    }

    // 重写sizeOf方法，并计算返回每个Bitmap对象占用的内存
    @Override
    protected int sizeOf(String key, Bitmap value) {
        return value.getByteCount();  // 统一单位 bit
    }

    /**
     *  当缓存被移除时调用，第一个参数是表明缓存移除的原因，true表示被LruCache移除，false表示被主动remove移除，可不重写
     */
    @Override
    protected void entryRemoved(boolean evicted, String key, Bitmap oldValue, Bitmap newValue) {
        // true --为释放空间被删除；false --get、put 或 remove 导致
        super.entryRemoved(evicted, key, oldValue, newValue);
    }

    /**
     *  当get方法获取不到缓存的时候调用，如果需要创建自定义默认缓存，可以在这里添加逻辑，可不重写
     */
    @Override
    protected Bitmap create(String key) {
        return super.create(key);
    }

    @Override
    public void resize(int maxSize) {
        super.resize(maxSize);
    }

    @Override
    public void trimToSize(int maxSize) {
        super.trimToSize(maxSize);
    }

}
