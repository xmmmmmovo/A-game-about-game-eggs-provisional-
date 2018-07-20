package com.example.a41448.huawu.chatUI;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import cn.bmob.newim.db.dao.DaoMaster;
import cn.bmob.newim.db.dao.DaoSession;

public class App extends Application {

    private static App instance;

    public DaoSession getDaoSession() {
        return daoSession;
    }

    private DaoSession daoSession;

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        DaoMaster.DevOpenHelper mHelper = new DaoMaster.DevOpenHelper(this, "chat-message", null);
        SQLiteDatabase db = mHelper.getWritableDatabase();
        daoSession = new DaoMaster(db).newSession();
        initImageLoader(this);
        instance = this;
    }

    /**
     * 初始化ImageLoader
     */
    public static void initImageLoader(Context context) {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                context)
                .threadPoolSize(3).threadPriority(Thread.NORM_PRIORITY - 2)
                //.memoryCache(new WeakMemoryCache())
                .denyCacheImageMultipleSizesInMemory()
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .build();
        ImageLoader.getInstance().init(config);
    }
}
