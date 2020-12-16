package priv.fandeshan.demo.hystrixhystrixdashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrixDashboard

public class HystrixHystrixDashboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(HystrixHystrixDashboardApplication.class, args);
	}

}
