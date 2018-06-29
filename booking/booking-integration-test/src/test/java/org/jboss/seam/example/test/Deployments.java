package org.jboss.seam.example.test;

import java.io.File;

import org.eu.ingwar.tools.arquillian.extension.suite.annotations.ArquillianSuiteDeployment;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.OverProtocol;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;

@ArquillianSuiteDeployment
public class Deployments {
	
	@Deployment(name = "seam-booking.ear")
	@OverProtocol("Servlet 3.0")
	public static Archive<?> createDeployment() {
		
		final String testWarName = "booking-web.war";
		
		final EnterpriseArchive ear = ShrinkWrap.createFromZipFile(EnterpriseArchive.class, new File("../booking-ear/target/seam-booking.ear"));
		ear.delete("booking-web.war");
		
		File[] libs = Maven.resolver()
				.resolve("org.mockito:mockito-all:1.9.5")
				.withoutTransitivity().asFile();
		 
		final WebArchive war = ShrinkWrap.create(WebArchive.class, testWarName);
		war.addPackages(true, EmptyTest.class.getPackage())
		.addAsLibraries(libs)
		.addAsWebInfResource("components-test.xml", "components.xml")
        .addAsWebInfResource("web.xml");
		
        ear.addAsModule(war);

		return ear;
	}

}
