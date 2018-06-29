package org.jboss.seam.example.test;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public abstract class ContextMocker extends FacesContext {

	private static final Release RELEASE = new Release();

	private ContextMocker() {

	}

	public static FacesContext mockServletRequest() {
		FacesContext context = mock(FacesContext.class);
		setCurrentInstance(context);
		Mockito.doAnswer(RELEASE).when(context).release();
		Map<String, Object> session = new HashMap<String, Object>();
		
		ServletContext servletContext = mock(ServletContext.class);
		when(servletContext.getContextPath()).thenReturn("/grpfor");
		
		HttpServletRequest request = mock(HttpServletRequest.class);
		when(request.getRemoteAddr()).thenReturn("127.0.0.1");
		when(request.getServerName()).thenReturn("localhost");
		when(request.getServerPort()).thenReturn(8080);
		when(request.getServletContext()).thenReturn(servletContext);
		
		ExternalContext ext = mock(ExternalContext.class);
		when(ext.getSessionMap()).thenReturn(session);
		when(context.getExternalContext()).thenReturn(ext);
		when(ext.getRequest()).thenReturn(request);
		when(ext.isUserInRole(anyString())).thenReturn(true);
		when(ext.getRequestServletPath()).thenReturn("/pagesPublic/");
		
		return context;
	}
	
	private static class Release implements Answer<Void> {

	    public Void answer(InvocationOnMock invocation) throws Throwable {
	        setCurrentInstance(null);
	        return null;
	    }
	}

}
