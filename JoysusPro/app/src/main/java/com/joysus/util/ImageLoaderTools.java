package com.joysus.util;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.joysus.R;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;

/**
 * UIL加载图片工具类
 */
public class ImageLoaderTools {
    private static ImageLoaderTools mImageLoaderWrapper;
    private static ImageLoader mImageLoader;
    private static final int DISK_CACHE_SIZE_BYTES = 50 * 1024 * 1024;
    private static final int MEMORY_CACHE_SIZE_BYTES = 2 * 1024 * 1024;
    public static String IMAGE_CACHE_PATH = "yyh/imageloader/Cache"; // 图片缓存路径

    private ImageLoaderTools(Context context) {
        setImageLoader(initImageLoader(context));
    }

    public static ImageLoaderTools getInstance(Context context) {
        if (mImageLoaderWrapper == null) {
            mImageLoaderWrapper = new ImageLoaderTools(context);
            return mImageLoaderWrapper;
        } else {
            return mImageLoaderWrapper;
        }
    }

    private static ImageLoader initImageLoader(Context context) {
        File cacheDir = StorageUtils.getOwnCacheDirectory(context,
                IMAGE_CACHE_PATH);

        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true).cacheOnDisk(true)
                .showImageOnLoading(R.mipmap.imagedefault)
                .showImageForEmptyUri(R.mipmap.imagedefault)
                .showImageOnFail(R.mipmap.imagedefault).build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                context).defaultDisplayImageOptions(defaultOptions)
                .diskCacheFileCount(100)
                .diskCache(new UnlimitedDiscCache(cacheDir))
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .diskCacheSize(DISK_CACHE_SIZE_BYTES)
                .memoryCacheSize(MEMORY_CACHE_SIZE_BYTES).build();

        ImageLoader tmpIL = ImageLoader.getInstance();
        tmpIL.init(config);
        return tmpIL;

    }

    public ImageLoader getImageLoader() {
        return mImageLoader;
    }

    private static void setImageLoader(ImageLoader mImageLoader) {
        ImageLoaderTools.mImageLoader = mImageLoader;
    }

    public void displayImage(String mResName, ImageView imageView) {
        if (mResName.startsWith("http://")) {
            mImageLoader.displayImage(mResName, imageView);
        } else if (mResName.startsWith("assets://")) {
            mImageLoader.displayImage(mResName, imageView);
        } else if (mResName.startsWith("file:///mnt")) {
            mImageLoader.displayImage(mResName, imageView);
        } else if (mResName.startsWith("content://")) {
            mImageLoader.displayImage(mResName, imageView);
        } else if (mResName.startsWith("drawable://")) {
            mImageLoader.displayImage(mResName, imageView);
        } else {
            Uri uri = Uri.parse(mResName);
            imageView.setImageURI(uri);
        }

    }
}
