package Injector;

import Consumer.*;
import Service.FacebookService;

public class SMSServiceInjector implements MessageServiceInjector {
	 
	@Override
	public Consumer getConsumer() {
		return new Application(new FacebookService());
	}
}