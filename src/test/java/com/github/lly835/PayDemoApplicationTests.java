package com.github.lly835;

import com.github.lly835.config.WechatAccountConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PayDemoApplicationTests {

	@Test
	public void contextLoads() {
		WechatAccountConfig wechatAccountConfig=new WechatAccountConfig();
		System.out.println(wechatAccountConfig.getMchId());
	}

}
