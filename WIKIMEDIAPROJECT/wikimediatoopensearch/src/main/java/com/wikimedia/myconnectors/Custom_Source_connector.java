package com.wikimedia.myconnectors;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.connect.connector.Task;
import org.apache.kafka.connect.source.SourceConnector;

public class Custom_Source_connector extends SourceConnector{
	 private MySourceConnectorConfig myconfig;
	@Override
	public String version() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void start(Map<String, String> props) {
		// TODO Auto-generated method stub
		myconfig = new MySourceConnectorConfig(props);

	}

	@Override
	public Class<? extends Task> taskClass() {
		// TODO Auto-generated method stub
		return MyConnectorTask.class;
	}

	@Override
	public List<Map<String, String>> taskConfigs(int maxTasks) {
		// TODO Auto-generated method stub
		ArrayList<Map<String,String>> configs = new ArrayList<>(maxTasks);
		for (int i=0; i<=maxTasks; i++) {
			configs.add(myconfig.originalsStrings());
		}
		return configs;
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub

	}

	@Override
	public ConfigDef config() {
		// TODO Auto-generated method stub
		return MySourceConnectorConfig.conf();
	}

}
