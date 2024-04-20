package com.owner.suanfa.suanfa;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 滑动窗口算法
 * 借助treeMap实现令牌桶算法
 * 5s内允许通过10个请求
 */
@Slf4j
public class TokenBucket {
    private int capacity = 10; // 令牌桶容量
    private Long limitTime = 5000L; // 5s
    private TreeMap<Long, Long> treeMap = new TreeMap<>();//有序队列，存放时间戳

    public static void main(String[] args) {
        TokenBucket tokenBucket = new TokenBucket();
        for (int i = 0; i < 20; i++) {
            try {
                Thread.sleep(50); // 模拟请求间隔
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (tokenBucket.allowRequest()) {
                log.info("Allowed");
            } else {
                log.info("Limited");
            }

        }
    }

    /**
     * 判断请求是否允许通过
     * @return
     */
    private boolean allowRequest() {
        long now = System.currentTimeMillis();
        long minTime = now - limitTime;
        this.zRemRangeByScore(minTime);
        //log.info("treeMap:{}", JSONUtil.toJsonStr(treeMap));
        //获取元素个数
        AtomicInteger allowNum = this.zRangeByScore(now);
        return allowNum.get() > 0;
    }

    /**
     * 获取队列中满足要求的元素个数
     *
     * @param member
     */
    public AtomicInteger zRangeByScore(Long member) {
        AtomicInteger allowNum = new AtomicInteger(capacity);
        treeMap.subMap(0L, true, member, true).forEach((key, value) -> {
            allowNum.decrementAndGet();
        });
        if (allowNum.get() > 0) {
            treeMap.put(member, 1L);
        }
        return allowNum;
    }

    /**
     * 删除队列中过期的元素
     *
     * @param minTime
     */
    public void zRemRangeByScore(Long minTime) {
        treeMap.entrySet().removeIf(entry -> entry.getKey() < minTime);
    }



}
