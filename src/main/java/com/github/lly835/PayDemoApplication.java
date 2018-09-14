package com.github.lly835;

import com.github.lly835.config.WechatAccountConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PayDemoApplication {

	public static void main(String[] args) {

		SpringApplication.run(PayDemoApplication.class, args);
		WechatAccountConfig wechatAccountConfig=new WechatAccountConfig();
		System.out.println(wechatAccountConfig.getMchId());
	}
}
