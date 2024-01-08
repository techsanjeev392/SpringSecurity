package com.sanjeev.learning.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserRequest {

	Integer id;
	String userName;
	String emailId;
	String password;
}
