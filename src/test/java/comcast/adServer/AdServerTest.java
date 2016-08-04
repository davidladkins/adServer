package comcast.adServer;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

//import com.fasterxml.jackson.databind.ObjectMapper;

import comcast.adServer.service.AdService;
import comcast.adServer.vo.Campaign;
@FixMethodOrder( MethodSorters.NAME_ASCENDING)
public class AdServerTest {

	AdService service = new AdService();
	@Before
	public void setUp() throws Exception 
	{
	
	}
	@Test
	public void atestAddCampaing() 
	{
		try 
		{
			assertTrue(service.addCampaing(new Campaign("acme",3,"this is my first campaing")));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test(expected = Exception.class)
	public void btestExceptionAddCampaing() throws Exception 
	{
			assertTrue(service.addCampaing(new Campaign("acme",2,"this is my second campaing")));
	
	}
	
	@Test
	public void ctestGetAdd() 
	{
		try 
		{
			System.out.println(service.getAd("acme").toString());
		}
		catch (Exception e) 
		{
			System.out.println(e.getMessage());
		}
	}

	@Test(expected = Exception.class)
	public void dtestGetExpiredAd() throws InterruptedException, Exception
	{
		Thread.sleep(3000);
		System.out.println(service.getAd("acme").toString());
	}
	
	@Test
	public void ftestAddCampaing() 
	{
		try 
		{
			assertTrue(service.addCampaing(new Campaign("acme",5,"this is my first campaing")));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void testGetAds() 
	{
		try {
			assertEquals(2, service.getAds("acme").size());
			System.out.println();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	/*
	@Test
	public void xTestJson()
	{
		
		try 
		{
		
			ObjectMapper mapper = new ObjectMapper();
			 String json = mapper.writeValueAsString( service.getAd("acme"));
			 System.out.println(json);
			 assertEquals("{\"duration\":5,\"partner_id\":\"acme\",\"ad_content\":\"this is my first campaing\"}",json);
		        //{"duration":30,"partner_id":"acme","ad_content":"My first Campaign"}
		
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	*/
}
