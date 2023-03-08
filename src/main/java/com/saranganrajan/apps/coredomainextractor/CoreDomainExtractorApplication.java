package com.saranganrajan.apps.coredomainextractor;

import com.saranganrajan.apps.coredomainextractor.external.processor.feign.CoreProcessFeignClient;
import com.saranganrajan.apps.coredomainextractor.extract.CsvFileExtractor;
import com.saranganrajan.apps.coredomainextractor.extract.FileExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;

@EnableFeignClients("com.saranganrajan.apps.coredomainextractor")
@SpringBootApplication
public class CoreDomainExtractorApplication implements CommandLineRunner {

	@Autowired
	CoreProcessFeignClient feignClient;

	public static void main(String[] args) {
		SpringApplication.run(CoreDomainExtractorApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		processIncomingFiles();
	}

	@Scheduled(fixedDelay = 1800000, initialDelay = 1800000)
	public void processIncomingFiles() throws IOException {
		FileExtractor fileExtractor = new CsvFileExtractor(feignClient);
		fileExtractor.extractRawData();
	}

}
