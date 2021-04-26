package com.AShield.emailService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@SpringBootApplication
@EnableAsync
@Configuration
public class EmailServiceApplication {
	


//@Bean("threadPoolTaskExecutor")
  // public TaskExecutor getAsyncExecutor() {
    //   ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
      // executor.setCorePoolSize(20);
      // executor.setMaxPoolSize(1000);
      // executor.setWaitForTasksToCompleteOnShutdown(true);
      // executor.setThreadNamePrefix("Async-");
      // return executor;
//
  // }

	public static void main(String[] args) {
		SpringApplication.run(EmailServiceApplication.class, args);
	}

}
