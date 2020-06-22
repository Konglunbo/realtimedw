package com.zxxj.mock;
import com.github.javafaker.Faker;
import com.zxxj.commons.model.AdvertClickLog;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import com.alibaba.fastjson.JSON;
import java.util.*;
/**
 * @author shkstart
 * @create 2020-06-17 13:50
 */
public class MockKafka {
    public static void main(String[] args) throws InterruptedException {
        Properties props = new Properties();
        props.put("bootstrap.servers", "node02:6667");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<>(props);

        Random random = new Random();
        String keyPrefix = "data_increment_data.kafka.hdp-kafka.original_advert_click_log.original_advert_click_log.*.*.*...";
        while (true) {
            // 随机产生实时数据并通过Kafka生产者发送到Kafka集群
            for (AdvertClickLog advertClickLog : generateMockData()) {
                System.out.println(JSON.toJSONString(advertClickLog));
                producer.send(new ProducerRecord<String, String>("original_advert_click_log", keyPrefix+random.nextInt(30),JSON.toJSONString(advertClickLog)));
            }
            //随机sleep
            Thread.sleep(random.nextInt(5)*1000);
//            System.out.println("------------------------------------------------------------------------");
        }

    }

    private static List<AdvertClickLog> generateMockData() {
        Faker fakerZH = new Faker(new Locale("zh-CN"));

        /**
         * 随机确定生成多少条广告数据
         */
        int num = fakerZH.number().numberBetween(0, 51);
        List<AdvertClickLog> adList = new ArrayList<>();
        AdvertClickLog advertClickLog;
        for (int i = 1; i <=num ; i++) {
            advertClickLog = AdvertClickLog.builder()
                    .timestamp(System.currentTimeMillis())
                    .province(fakerZH.number().numberBetween(0, 10))
                    .adid(fakerZH.number().numberBetween(0, 21))
                    .userid(fakerZH.number().numberBetween(1, 101))
                    .build();
            advertClickLog.setCity(advertClickLog.getProvince());
            adList.add(advertClickLog);
        }

        return adList;
    }
}
