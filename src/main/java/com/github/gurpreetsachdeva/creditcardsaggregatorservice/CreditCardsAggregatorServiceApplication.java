package com.github.gurpreetsachdeva.creditcardsaggregatorservice;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication(scanBasePackages = { "com.github.gurpreetsachdeva.creditcardsaggregatorservice"})
@SpringBootApplication
//@SpringBootApplication(scanBasePackages={"com.github.gurpreetsachdeva.creditcardsaggregatorservice.service", "com.github.gurpreetsachdeva.creditcardsaggregatorservice.controller"})
public class CreditCardsAggregatorServiceApplication implements ApplicationRunner{
	
	


    private static final Logger logger = LoggerFactory.getLogger(CreditCardsAggregatorServiceApplication.class);
	
	 public static void main(String... args) throws Exception {
	        SpringApplication.run(CreditCardsAggregatorServiceApplication.class, args);
	    }

	    @Override
	    public void run(ApplicationArguments args) throws Exception {
	    	
	        logger.info("Application started with command-line arguments: {}", Arrays.toString(args.getSourceArgs()));
	        logger.info("NonOptionArgs: {}", args.getNonOptionArgs());
	        logger.info("OptionNames: {}", args.getOptionNames());

	        for (String name : args.getOptionNames()){
	            logger.info("arg-" + name + "=" + args.getOptionValues(name));
	        }

	    }

}
