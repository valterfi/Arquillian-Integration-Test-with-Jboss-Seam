package org.jboss.seam.example.test;

import javax.faces.context.FacesContext;

import org.jboss.seam.contexts.Lifecycle;
import org.junit.After;
import org.junit.Before;

public abstract class SeamTest {
	
	protected FacesContext facesContext;
	
	//protected UserTransaction userTx = null;

	@Before
	public void before() throws Exception {
		Lifecycle.beginCall();
		
		facesContext = ContextMocker.mockServletRequest();
		
		//userTx = (UserTransaction) org.jboss.seam.Component.getInstance("org.jboss.seam.transaction.transaction");
		//userTx.setTransactionTimeout(10 * 600);  //set timeout to 60 * 10 = 600 secs = 10 mins
		//userTx.begin();
		
		setUp();
	}

	@After
	public void after() throws Exception {
		tearDown();
		
		facesContext.release();
		
		Lifecycle.endCall();
	}
	
	protected void setUp() {
		
	}
	
	protected void tearDown() {
		
	}

}
