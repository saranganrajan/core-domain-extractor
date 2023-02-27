package com.saranganrajan.apps.coredomainextractor;

import com.saranganrajan.apps.coredomainextractor.external.processor.feign.DomainProcessFeignClient;
import com.saranganrajan.apps.coredomainextractor.extract.CsvFileExtractor;
import com.saranganrajan.apps.coredomainextractor.extract.FileExtractor;
import com.saranganrajan.apps.coredomainextractor.model.PolicyTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;


import java.util.List;

@EnableFeignClients("com.saranganrajan.apps.coredomainextractor")
@SpringBootApplication
public class CoreDomainExtractorApplication implements CommandLineRunner {

	@Autowired
	DomainProcessFeignClient feignClient;

	public static void main(String[] args) {
		SpringApplication.run(CoreDomainExtractorApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		FileExtractor fileExtractor = new CsvFileExtractor(feignClient);
		fileExtractor.extractRawData();
	}

}
