package com.online.ticketReservationSystem.helper;

import com.online.ticketReservationSystem.model.Users;
import org.springframework.security.core.userdetails.User;

public class UsersHelper extends User{

	private static final long serialVersionUID = 1L;
	   public UsersHelper(Users user) {
	      super(
	    		  user.getUsername(),
	    		  user.getPassword(),
	    		  user.getListOfgrantedAuthorities()
	    		);
	   }
}
