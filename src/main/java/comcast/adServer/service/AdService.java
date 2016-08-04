package comcast.adServer.service;


import java.util.concurrent.ConcurrentHashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;

//import com.fasterxml.jackson.databind.ObjectMapper;

//import com.fasterxml.jackson.core.JsonProcessingException;

import comcast.adServer.vo.Campaign;
import comcast.adServer.vo.Campaigns;
@Path("/ad")
public class AdService 
{
	public static ConcurrentHashMap<String,Campaigns> campaignDB = new ConcurrentHashMap<>();

	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/{partner_id}")
	public Campaign getAd(@PathParam("partner_id") String id) throws Exception 
	{

		Campaign campaign = new Campaign(id,30,"My first Campaign");
		Campaigns campaigns = new Campaigns();
		if(campaigns.add(campaign))
			campaignDB.put(campaign.getPartnerId(), campaigns);
		else
		{
			throw new Exception("Invalid Campaing");
		}
		return campaign;
		
//		if(campaignDB.get(id).getLast().getExpired())  
	//		throw new Exception("no active campaings");
		//else return campaignDB.get(id).getLast();
	}
	@GET
	@Path("/all/{partner_id}")

	@Produces({MediaType.APPLICATION_JSON})
	public Campaigns getAds(@PathParam("id") String id) 
	{
		return campaignDB.get(id);
	}
	
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.TEXT_PLAIN})
	public Boolean addCampaing(Campaign campaign) throws Exception
	{
		
		if(campaignDB.containsKey(campaign.getPartnerId()))
		{
			Campaigns campaigns = campaignDB.get(campaign.getPartnerId());
			if(!campaigns.add(campaign) )
			{
				throw new Exception("Invalid Campaing");
			}
		}
		else //new partner;
		{	
			Campaigns campaigns = new Campaigns();
			if(campaigns.add(campaign))
				campaignDB.put(campaign.getPartnerId(), campaigns);
			else
			{
				throw new Exception("Invalid Campaing");
			}
		}
		return true;
	
	}
}
