package br.edu.uepb.springexample.model;

public class AuthToken {
	private String token;
	
	public AuthToken() { }

	public AuthToken(String token) {
		this.token = token;
	}

	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}
}
