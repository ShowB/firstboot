package com.showb.firstboot.utils.redis;

import lombok.Getter;
import org.springframework.data.redis.connection.DataType;

import java.util.concurrent.TimeUnit;

@Getter
public enum RedisType {

    TOKEN("token:", DataType.STRING, true, 12, TimeUnit.HOURS),
    GOODS_SCAN("goods_scan:", DataType.HASH, true, 12, TimeUnit.HOURS),
    METADATA("metadata:", DataType.STRING, false, -1, TimeUnit.HOURS),
    DAILY_SEQUENCE("daily_seq:", DataType.STRING, true, 1, TimeUnit.DAYS),
    ;

    private final String prefix;
    private final DataType type;
    private final Boolean isExpire;
    private final long timeout;
    private final TimeUnit unit;

    RedisType(String prefix, DataType type, Boolean isExpire, long timeout, TimeUnit unit) {
        this.prefix = prefix;
        this.type = type;
        this.isExpire = isExpire;
        this.timeout = timeout;
        this.unit = unit;
    }

    /**
     * RMS에서 사용하는 Redis Key Naming
     */
    public String produceRedisKey(String key) {
        return this.prefix + key;
    }

}
