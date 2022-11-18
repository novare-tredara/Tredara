package com.novare.tredara;

import org.jobrunr.jobs.mappers.JobMapper;
import org.jobrunr.storage.InMemoryStorageProvider;
import org.jobrunr.storage.StorageProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TredaraApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(TredaraApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TredaraApplication.class, args);
		LOGGER.info("TredaraApplication has started !");
	}

	@Bean
	public StorageProvider storageProvider(JobMapper jobMapper) {
		InMemoryStorageProvider storageProvider = new InMemoryStorageProvider();
		storageProvider.setJobMapper(jobMapper);
		LOGGER.info("TredaraApplication has configured the JobRunr storage provider !");
		return storageProvider;
	}
}
