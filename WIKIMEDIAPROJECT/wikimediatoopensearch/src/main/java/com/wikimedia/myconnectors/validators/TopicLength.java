package com.wikimedia.myconnectors.validators;
import org.apache.kafka.common.config.ConfigDef.Validator;
import org.apache.kafka.common.config.ConfigException;

public class TopicLength implements Validator{

	@Override
	public void ensureValid(String topicname, Object value) {
		// TODO Auto-generated met
		String topicname1 = (String) value;
		if ( topicname1.length() > 250 || topicname1.length() ==0) {
			throw new ConfigException(topicname1,value,"hey! name of topic should be less than 250 length");
		}
	}

}
