package com.lightweight.hybridcustomruntime.conf;

import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.util.StringUtils;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.lightweight.hybridcustomruntime.Application;

@Configuration
@EnableDynamoDBRepositories(basePackages = "com.lightweight.hybridcustomruntime.repositories")
public class DynamoDBConfig {

	private static final String AMAZON_DYNAMO_DB_ENDPOINT = "AMAZON_DYNAMO_DB_ENDPOINT";

	private static final String AMAZON_AWS_ACCESS_KEY = "AMAZON_AWS_ACCESS_KEY";

	private static final String AMAZON_AWS_SECRET_KEY = "AMAZON_AWS_SECRET_KEY";

	private static final String AMAZON_DYNAMO_DB_ENDPOINT_PROP_KEY = "amazon.dynamodb.endpoint";

	private static final String AMAZON_AWS_ACCESS_KEY_PROP_KEY = "amazon.aws.accesskey";

	private static final String AMAZON_AWS_SECRET_KEY_PROP_KEY = "amazon.aws.secretkey";

	@Bean
	public AmazonDynamoDB amazonDynamoDB() {
		AmazonDynamoDB amazonDynamoDB = new AmazonDynamoDBClient(amazonAWSCredentials());
		String amazonDynamoDBEndpoint = !isNull(System.getenv(AMAZON_DYNAMO_DB_ENDPOINT))
				? System.getenv(AMAZON_DYNAMO_DB_ENDPOINT)
				: getProperty(AMAZON_DYNAMO_DB_ENDPOINT_PROP_KEY);
		if (!StringUtils.isEmpty(amazonDynamoDBEndpoint)) {
			amazonDynamoDB.setEndpoint(amazonDynamoDBEndpoint);
		}
		return amazonDynamoDB;
	}

	@Bean
	public AWSCredentials amazonAWSCredentials() {
		String amazonAWSAccessKey = !isNull(System.getenv(AMAZON_AWS_ACCESS_KEY)) ? System.getenv(AMAZON_AWS_ACCESS_KEY)
				: getProperty(AMAZON_AWS_ACCESS_KEY_PROP_KEY);
		String amazonAWSSecretKey = !isNull(System.getenv(AMAZON_AWS_SECRET_KEY)) ? System.getenv(AMAZON_AWS_SECRET_KEY)
				: getProperty(AMAZON_AWS_SECRET_KEY_PROP_KEY);
		return new BasicAWSCredentials(amazonAWSAccessKey, amazonAWSSecretKey);
	}

	private boolean isNull(String input) {
		return (null == input);
	}

	private String getProperty(String key) {
		GenericApplicationContext context = Application.getInstance().getContext();
		String value = (null != context) ? context.getEnvironment().getProperty(key) : null;
		return !isNull(value) ? value : "";
	}
}