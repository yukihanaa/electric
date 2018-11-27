package com.ele;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableEurekaClient
//@EnableDiscoveryClient
/*@EnableHystrix
@EnableHystrixDashboard
@EnableCircuitBreaker*/
public class ClientElectricApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientElectricApplication.class, args);
	}



//	@Bean
//	public ServletRegistrationBean getServlet(){
//		HystrixMetricsStreamServlet streamServlet=new HystrixMetricsStreamServlet();
//		ServletRegistrationBean registrationBean=new ServletRegistrationBean(streamServlet);
//		registrationBean.setLoadOnStartup(1);
//		registrationBean.addUrlMappings("/actuator/hystrix.stream");
//		registrationBean.setName("HystrixMetricsStreamServlet");
//		return registrationBean;
//	}

}
