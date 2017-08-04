package com.gtm.proxibanquews.presentation;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
//import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.gtm.proxibanquews.service.WebServiceVirement;

//import com.gtm.proxibanquews.service.WebServiceEmploye;
//import com.gtm.proxibanquews.service.WebServiceVirement;

public class ClientLauncher {

	public static void main(String[] args) {
		// TEST METHOD
//		ApplicationContext appContext = new ClassPathXmlApplicationContext("data-beans.xml");
//		WebServiceVirement service = (WebServiceVirement) appContext.getBean("virementService");
//		ArrayList<Long> data = service.getSectionPourCamembert("2013-01-01", "2017-01-01");
//
//		System.out.println("START");
//
//		for (Long value : data)
//			System.out.println(value);
//
//		// CLIENT
//		Client client = ClientBuilder.newClient();
//		WebTarget target = client.target("http://localhost:8080/proxibanquews/rest/test");
//		Response response = target.request().get();
//		String value = response.readEntity(String.class);
//		System.out.println("RESPONSE : ");
//		System.out.println(value);
//		response.close();

		// CLIENT 2
//		Client client = ClientBuilder.newClient( new ClientConfig().register( LoggingFilter.class ) );
//		WebTarget webTarget = client.target("http://localhost:8080/JerseyDemos/rest").path("employees");
		 
//		Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_XML);
//		Response response = invocationBuilder.get();
		 
//		Employees employees = response.readEntity(Employees.class);
//		List<Employee> listOfEmployees = employees.getEmployeeList();
		
		System.out.println("CLIENT 2 : ");
		Client client2 = ClientBuilder.newClient();
		WebTarget target2 = client2.target("http://localhost:8080/proxibanquews/rest/json");
		Response response2 = target2.queryParam("date1", "2017-07-01").queryParam("date2", "2017-07-09").request()
				.get();
		ArrayList<Long> value2 = response2.readEntity(new GenericType<ArrayList<Long>>() {});
		System.out.println("RESPONSE : ");
		for (Long l : value2)
			System.out.println(l);
		response2.close();
	}
}
