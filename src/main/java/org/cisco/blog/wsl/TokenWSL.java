package org.cisco.blog.wsl;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.cisco.blog.persist.Token;
import org.cisco.blog.persist.TokenService;
import org.cisco.blog.persist.User;
import org.cisco.blog.persist.UserService;


@Path("/session")
public class TokenWSL {
	
	private String username;
	private String password;
	private String token;
	
	public TokenWSL() {
	}
	
	public TokenWSL(String username, String password) {
		System.out.println(username +" " + password);
		this.password = password;
		this.username = username;
	}
	
	public void setUsername( String username){
		this.username = username;
	}
	
	public String getUsername(){		
		return this.username;
	}
	
	public void setPassword( String password){
		this.password = password;
	}
	
	public String getPassword(){		
		return this.password;
	}

	public void setToken( String token){
		this.token = token;
	}
	
	public String getToken(){		
		return this.token;
	}
	
	void createToken () {
		UserService userService = new UserService();
		TokenService tokenService = new TokenService();
		
		if ( userService.isValidPassword(username, password) ) {
			System.out.println("Valid password");
			Token token;
			//check if the token already exist
			token = tokenService.findByUserName(username);
			
			if ( token == null ) {
				User user = userService.findByUsername(username);
				Token session = new Token(user);
				try {
					tokenService.persist(session);
					this.token   = session.getUUID(); 
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			} else {
				// TODO update expiry time
			}
			this.password = null;
			this.username = token.getUserName();
			this.token   =  token.getUUID();
			
			//create session
		} else {
			System.out.println("inValid password");
			this.token   = null; 
			this.password = null;
			this.username = null;
		}
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public TokenWSL getToken(TokenWSL wsl) throws Exception {
		System.out.println(username +" " + password);
		wsl.createToken();
		return wsl;
	}	
}