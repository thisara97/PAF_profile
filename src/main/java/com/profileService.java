package com;

import model.profile;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//for JSON
import com.google.gson.*;
//for XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;
@Path("/profile")

public class profileService {
		
	profile profileObj = new profile();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readProfile()
	{
		return profileObj.readProfile();
	}
	
	// insert user details
			@POST
			@Path("/insert")
			@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
			@Produces(MediaType.TEXT_PLAIN)
			public String insertProfile(
			
				
				@FormParam("name") String name,
				@FormParam("address") String address,
				@FormParam("email") String email,
				@FormParam("contact") String contact)
			

			
					{
						 String output = profileObj.insertProfile(name,address,email,contact);
						 return output;
					}
			//update user details
			@PUT
			@Path("/update")
			@Consumes(MediaType.APPLICATION_JSON)
			@Produces(MediaType.TEXT_PLAIN)
			public String updateProfile(String profileData)
			{
			   //Convert the input string to a JSON object
				JsonObject profileObject = new JsonParser().parse(profileData).getAsJsonObject();
								 
			    //Read the values from the JSON object
							
					String uid = profileObject.get("uid").getAsString();
					String name = profileObject.get("name").getAsString();
					String address = profileObject.get("address").getAsString();
				    String email = profileObject.get("email").getAsString();
				    String contact = profileObject.get("contact").getAsString();
				    
					String output = profileObj.updateProfile(uid,name,address,email,contact);
					return output;
			}
		//delete user details
		@DELETE
		@Path("/delete")
		@Consumes(MediaType.APPLICATION_XML)
		@Produces(MediaType.TEXT_PLAIN)
		public String deleteProfile(String profileData)
		{
			//Convert the input string to an XML document
			Document doc =Jsoup.parse(profileData, "",Parser.xmlParser());
			
			//Read the value from the element <idUnit>
			 String uid = doc.select("uid").text();
			 String output = profileObj.deleteProfile(uid);
			 return output;
		}
		

}

	
