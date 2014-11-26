package com.darkedges.oim.client;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;

import javax.security.auth.login.LoginException;

import oracle.iam.passwordmgmt.api.ChallengeResponseService;
import oracle.iam.passwordmgmt.exception.ChallengeResponseException;
import oracle.iam.passwordmgmt.vo.Challenge;
import oracle.iam.platform.OIMClient;
import oracle.iam.platform.annotations.ServiceMethod;
import oracle.iam.selfservice.exception.AuthSelfServiceException;
import oracle.iam.selfservice.exception.UserAccountDisabledException;
import oracle.iam.selfservice.exception.UserAccountInvalidException;
import oracle.iam.selfservice.uself.uselfmgmt.api.UnauthenticatedSelfService;

public class Test {
	public static OIMClient client;
	private static String OIMUserName = "xelsysadm";
	private static String OIMPassword = "c89Tzh!899";
	private static String OIMURL = "t3://localhost:14000";
	private static String OIMInitialContextFactory = "weblogic.jndi.WLInitialContextFactory";

	public static void loginWithCustomEnv() throws LoginException {
		Hashtable<String, String> env = new Hashtable<String, String>();
		env.put(OIMClient.JAVA_NAMING_FACTORY_INITIAL, OIMInitialContextFactory);
		env.put(OIMClient.JAVA_NAMING_PROVIDER_URL, OIMURL);
		client = new OIMClient(env);
		client.login(OIMUserName, OIMPassword.toCharArray());
	}

	private static void loginUnAuthenticed() {
		OIMClientImpl clientImpl = new OIMClientImpl();
		clientImpl.setOIMInitialContextFactory(OIMInitialContextFactory);
		clientImpl.setOIMURL(OIMURL);
		client = clientImpl.getInstance();

	}

	@ServiceMethod(unauthenticated = true)
	private static void getCredentials(String uid) {
		ChallengeResponseService service = client
				.getService(ChallengeResponseService.class);
		try {
			Set<Challenge> c = service.getChallengesForUser(uid,
					Locale.ENGLISH);
			for (Iterator<Challenge> iterator = c.iterator(); iterator
					.hasNext();) {
				Challenge challenge = iterator.next();
				System.out.println(challenge);
			}
		} catch (ChallengeResponseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void setPassword(String uid) {
		HashMap<String,Object> map = new HashMap<String,Object>();
		UnauthenticatedSelfService service = client.getService(UnauthenticatedSelfService.class);
		try {
			String[] c = service.getChallengeQuestions(uid);
			for (int i = 0; i < c.length; i++) {
				System.out.println(c[i]);
			}
			map.put("What is your mother's maiden name?","dixon");
			map.put("What is the name of your pet?","pluto");
			map.put("What is your favorite color?","blue");
//			boolean a = service.resetPassword(uid, map, "c89Tzh!893".toCharArray());
//			System.out.println(a);
		} catch (UserAccountDisabledException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UserAccountInvalidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AuthSelfServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]) {
		// loginWithCustomEnv();
		loginUnAuthenticed();
		//getCredentials("nirving");
		setPassword("nirving");
	}
}
