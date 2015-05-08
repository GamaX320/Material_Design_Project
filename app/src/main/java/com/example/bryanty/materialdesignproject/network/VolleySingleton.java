package com.example.bryanty.materialdesignproject.network;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.example.bryanty.materialdesignproject.MyApplication;

/**
 * Created by BRYANTY on 08/05/2015.
 */
//Volley will queue all you traffic to single queue, so no point that your application will not than one queue
public class VolleySingleton {

    private static VolleySingleton volleySingleton= null;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;

    private VolleySingleton() {
        mRequestQueue= Volley.newRequestQueue(MyApplication.getAppContext());
        mImageLoader= new ImageLoader(mRequestQueue, new ImageLoader.ImageCache() {
            //Lru cache is holds strong references to a limited number of values
            private LruCache<String,Bitmap> cache= new LruCache<>((int)(Runtime.getRuntime().maxMemory() / 1024)/8);

            @Override
            public Bitmap getBitmap(String url) {
                return cache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                cache.put(url, bitmap);
            }
        });

    }

    public static VolleySingleton getInstance(){
        if(volleySingleton== null){
            volleySingleton= new VolleySingleton();
        }
        return volleySingleton;
    }

    public RequestQueue getRequestQueue(){
        return mRequestQueue;
    }

    public ImageLoader getImageLoader(){
        return mImageLoader;
    }
}
