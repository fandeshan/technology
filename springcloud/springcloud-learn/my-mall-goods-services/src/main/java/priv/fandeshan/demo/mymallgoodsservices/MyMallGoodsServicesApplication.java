package priv.fandeshan.demo.mymallgoodsservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import priv.fandeshan.demo.tools.api.EnableResponseResult;

@SpringBootApplication
@EnableEurekaClient
@EnableTransactionManagement
@EnableResponseResult
public class MyMallGoodsServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyMallGoodsServicesApplication.class, args);
	}

}
