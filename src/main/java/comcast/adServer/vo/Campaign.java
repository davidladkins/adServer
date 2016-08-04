package comcast.adServer.vo;

import java.io.Serializable;
import java.util.Date;


import com.fasterxml.jackson.annotation.*;


public class Campaign implements Serializable 
{

	/**
	 * David L. Adkins
	 * 20160801
	 */
	private static final long serialVersionUID = -4086430014675178890L;
	
	//partner_id
	@JsonProperty("partner_id")
	String partnerId;
	//duration in seconds
	@JsonProperty
	Integer duration;
	
	@JsonIgnore 
	Date adCreation;
	
	@JsonProperty("ad_content")
	String adContent;
	
	@JsonIgnore
	Boolean expired;
	
	
	public Campaign(String partnerId, Integer duration, String adContent )
	{
		this.adCreation = new Date();
		this.adContent = adContent;
		this.duration = duration;
		this.partnerId = partnerId;
	}	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Campaign [partnerId=" + partnerId + ", duration=" + duration + ", adCreation=" + adCreation
				+ ", adContent=" + adContent + ", isExpired()=" + expired + "]";
	}

	public String getPartnerId()
	{
		return partnerId;
	}
	@JsonProperty("partner_id")
	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}
	
	
	public Integer getDuration() {
		return duration;
	}
	@JsonProperty
	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	/**
	 * @return the adContent
	 */
	public String getAdContent() {
		return adContent;
	}

	/**
	 * @param adContent the adContent to set
	 */
	@JsonProperty("ad_content")
	public void setAdContent(String adContent) {
		this.adContent = adContent;
	}

	/**
	 * @return the expired
	 */
	@JsonIgnore
	public Boolean getExpired() 
	{
		Date currentDate = new Date();
		long adExpiration = this.adCreation.getTime() + this.getDuration()*1000; 
		//System.out.println(adExpiration + ":" + currentDate.getTime());
		expired = (currentDate.getTime() >= adExpiration);
		return expired;
	}

	/**
	 * @param expired the expired to set
	 */
	@JsonProperty
	public void setExpired(Boolean expired) {
		this.expired = expired;
	}
	
	
}
