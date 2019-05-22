package com.segeg.avin.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class CacheEx {
    Cache<Integer, String> cache = Caffeine.newBuilder()
//            .expireAfterWrite(5, TimeUnit.SECONDS)            //데이터에 쓰기를 한 시점에서 5초 동안 저장
//            .refreshAfterWrite(5, TimeUnit.SECONDS)
            .expireAfterAccess(5, TimeUnit.SECONDS)     //데이터에 접근하면 계속해서 갱신됨 5초 기달리고 또 5초 기달리고
            .maximumSize(10_1000)
            .build();

    @Test
    public void start() throws InterruptedException {
        cache.put(1, "Hello world");

        new Thread(()->{
            while(true){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String obj = cache.getIfPresent(1);
                System.out.println(obj);
//                cache.get(1, (value)->{
//                    System.out.println(value);
//                    return "";
//                });
            }
        }).start();

        Thread.sleep(200000);
    }
}
