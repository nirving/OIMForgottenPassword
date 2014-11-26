package com.darkedges.oim.client;

import java.util.Hashtable;

import oracle.iam.platform.OIMClient;

public class OIMClientImpl {
	private static OIMClient client;
	private String OIMURL;
	private String OIMInitialContextFactory;

	public void setOIMURL(String oIMURL) {
		OIMURL = oIMURL;
	}

	public void setOIMInitialContextFactory(String oIMInitialContextFactory) {
		OIMInitialContextFactory = oIMInitialContextFactory;
	}
	
	public OIMClient getInstance() {
		if (client == null) {
			Hashtable<String, String> env = new Hashtable<String, String>();
			env.put(OIMClient.JAVA_NAMING_FACTORY_INITIAL,
					OIMInitialContextFactory);
			env.put(OIMClient.JAVA_NAMING_PROVIDER_URL, OIMURL);
			client = new OIMClient(env);
		}
		return client;
	}
}
