//package com.showb.firstboot.utils.redis;
//
//import lombok.NonNull;
//import org.apache.commons.lang3.BooleanUtils;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//
//@Component
//public class RedisUtils {
//    private final RedisTemplate<String, Object> redisTemplate;
//
//
//    RedisUtils(RedisTemplate<String, Object> redisTemplate) {
//        this.redisTemplate = redisTemplate;
//    }
//
//
//    /**
//     * String Type {Key,Value}를 Redis 에 저장
//     */
//    public void setOpsForValue(RedisType redisType, String key, String value) {
//        redisTemplate.opsForValue().set(redisType.produceRedisKey(key), value);
//        this.expire(redisType, key);
//    }
//
//    /**
//     * String Type Redis Value 를 반환
//     */
//    @NonNull
//    public String getOpsForValue(RedisType redisType, String key) {
//        Object result = redisTemplate.opsForValue().get(redisType.produceRedisKey(key));
//        if (result == null) {
//            return "";
//        }
//        return String.valueOf(result);
//    }
//
//    /**
//     * Redis 에 저장된 Key 를 삭제
//     */
//    public void delete(RedisType redisType, String key) {
//        redisTemplate.delete(redisType.produceRedisKey(key));
//    }
//
//    /**
//     * 일별로 초기화되는, businessName 을 key 로 갖는 시퀀스의 다음 값을 digit 자릿수에 맞추어 0을 채워서 획득
//     * 시퀀스가 아직 없으면 1 로 새로 생성하여 리턴
//     * ex) digit=5 --> 00001
//     */
//    public String getDailySeqNextVal(LocalDate today, String businessName, int digit) {
//        RedisType redisType = RedisType.DAILY_SEQUENCE;
//        String key = String.format("%s-%s", businessName, today.format(DateTimeFormatter.ofPattern("yyyyMMdd")));
//        this.expire(redisType, key);
//
//        Long seq = redisTemplate.opsForValue()
//                .increment(redisType.produceRedisKey(key), 1);
//
//        return StringUtils.leftPad(String.valueOf(seq), digit, "0");
//    }
//
//
//    /**
//     * expire 설정이 돼 있는 RedisType 만 expire 설정
//     */
//    private void expire(RedisType redisType, String key) {
//        if (BooleanUtils.isTrue(redisType.getIsExpire())) {
//            redisTemplate.expire(redisType.produceRedisKey(key), redisType.getTimeout(), redisType.getUnit());
//        }
//    }
//}
