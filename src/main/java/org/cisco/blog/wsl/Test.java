package org.cisco.blog.wsl;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.cisco.blog.persist.Question;


@Path("/upppppser")
public class Test {
	
	
//
//	public Test() {
//	}
//
//	//@POST
//	//@Consumes(MediaType.APPLICATION_JSON)
//	//public Response userCreate (){
//		
////		return Response.status(200)
////				.entity("addUser is called, userAgent : " + userAgent)
////				.build();
//		
//	//}
//	
//	
////	@PUT  
//	//idempotent
////	public Response userEdit (@Context HttpHeaders headers){
////		return Response.status(200)
////				.entity("addUser is called, userAgent : " + userAgent)
////				.build();
////	}
//	
//	@DELETE
//	@Path("/{param}")
//	public void userDelete(@PathParam("param") String id) throws Exception {
//		
//	}
//	
//	@GET
//	@Produces(MediaType.APPLICATION_JSON)
//	public List<UserWsl> userGet() throws Exception {
//		UserWsl  a = new UserWsl();
//		return a.userReadAll();
//	}
//	
//	
//	@GET
//	@Path("/a")
//	@Produces(MediaType.APPLICATION_JSON)
//	public List<QuestionWsl> ueseGet() throws Exception {
//		QuestionWsl  a = new QuestionWsl();
//		return a.userReadAll();
//	}
//	
//	
//	@GET
//	
//	@Path("/{param}")
//	public void userGet(@PathParam("param") String param) throws Exception {
//		String username=null;
//		//param is either username or id whatever try to get user
//		try{
//			int id =Integer.parseInt(param);
//		} catch (Exception e) {
//			username = param;
//		}
//		
//		if (username != null) {
//		} else {
//		}
//		
//		return user;
//		
//		
//		
//		//id.valueOf(id)
//		
		
//	}
}