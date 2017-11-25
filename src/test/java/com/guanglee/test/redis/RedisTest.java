package com.guanglee.test.redis;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.guanglee.weixin.SpringbootShiroCasApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=SpringbootShiroCasApplication.class)
public class RedisTest {
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	@Test
	public void templateTest() {
		System.out.println(redisTemplate);
		Map<String,String> testmap = new HashMap<>();
		testmap.put("test","success");
		redisTemplate.opsForValue().set("redismap", testmap);
		System.out.println("redis put success!!!");
		Map<String,String> succmap = (Map<String,String>) redisTemplate.opsForValue().get("redismap");
		System.out.println("redis get sucesss!!!");
		System.out.println("mapvalue is:" + succmap.get("test"));
		
	}
}
