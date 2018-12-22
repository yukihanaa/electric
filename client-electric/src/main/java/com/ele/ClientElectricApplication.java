package com.ele;

import com.ele.listener.NettyServerListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableEurekaClient
//@EnableDiscoveryClient
/*@EnableHystrix
@EnableHystrixDashboard
@EnableCircuitBreaker*/
public class ClientElectricApplication implements CommandLineRunner {

	@Autowired
	private NettyServerListener nettyServerListener;

	public static void main(String[] args) {
		SpringApplication.run(ClientElectricApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		nettyServerListener.start();
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
