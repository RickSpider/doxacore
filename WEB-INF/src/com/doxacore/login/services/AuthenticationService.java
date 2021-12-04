package com.doxacore.login.services;

import com.doxacore.login.UserCredential;

public interface AuthenticationService {
	
	
		/**login with account and password**/
		public boolean login(String account, String password);
		
		/**logout current user**/
		public void logout();
		
		/**get current user credential**/
		public UserCredential getUserCredential();
		


}