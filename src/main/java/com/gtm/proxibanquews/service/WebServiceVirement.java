package com.gtm.proxibanquews.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.gtm.proxibanquews.dao.IVirementDao;
import com.gtm.proxibanquews.domaine.Virement;

@Path("/")
@Service("virementService")
public class WebServiceVirement {

	@Autowired
	@Qualifier("virementDao")
	private IVirementDao dao;

	public IVirementDao getDao() {
		return dao;
	}

	public void setDao(IVirementDao dao) {
		this.dao = dao;
	}

	@GET
	@Path("/test")
	@Produces({ "application/json" })
	public Response getTest() {
		ApplicationContext appContext = new ClassPathXmlApplicationContext("data-beans.xml");
		IVirementDao dao = (IVirementDao) appContext.getBean("virementDao");
		Virement v = dao.findOne(2);
		String a = String.valueOf(v.getMontant());
		return Response.ok("{\"valeur\":\"TURLUTUTU"+ a+"\"}", MediaType.APPLICATION_JSON).build();
	}

	@GET
	@Path("/json")
	@Produces({ "application/json" })
	public ArrayList<Long> getVirementJSON(@QueryParam("date1") String date1, @QueryParam("date2") String date2) {

		ArrayList<Long> liste = new ArrayList<Long>();
		liste = getSectionPourCamembert(date1, date2);

		// GenericEntity<ArrayList<Long>> entity = new
		// GenericEntity<ArrayList<Long>>(liste) {};
		return liste;
	}

	public ArrayList<Long> getSectionPourCamembert(String sDate1, String sDate2) {
		ApplicationContext appContext = new ClassPathXmlApplicationContext("data-beans.xml");
		IVirementDao dao = (IVirementDao) appContext.getBean("virementDao");
		
		Date date1, date2;
		try {
			date1 = new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);
			date2 = new SimpleDateFormat("yyyy-MM-dd").parse(sDate2);
		} catch (Exception e) {
			ArrayList<Long> liste = new ArrayList<Long>();
			return liste;
		}
		
		ArrayList<Long> listSection = new ArrayList<Long>();
		ArrayList<Virement> malist = (ArrayList<Virement>) dao.findAll().stream()
				.filter(c -> c.getDate().before(date2) && c.getDate().after(date1)).collect(Collectors.toList());

		long section1 = malist.stream().filter(c -> c.getMontant() > 0 && c.getMontant() <= 200).count();
		listSection.add(section1);

		long section2 = malist.stream().filter(c -> c.getMontant() > 200 && c.getMontant() <= 500).count();
		listSection.add(section2);

		long section3 = malist.stream().filter(c -> c.getMontant() > 500 && c.getMontant() <= 1000).count();
		listSection.add(section3);

		long section4 = malist.stream().filter(c -> c.getMontant() > 1000 && c.getMontant() <= 5000).count();
		listSection.add(section4);

		long section5 = malist.stream().filter(c -> c.getMontant() > 5000).count();
		listSection.add(section5);

		return listSection;

	}

}
