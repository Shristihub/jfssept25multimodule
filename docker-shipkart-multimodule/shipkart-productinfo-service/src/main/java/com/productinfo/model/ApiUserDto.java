package com.productinfo.model;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ApiUserDto {
	private String username;
	private String password;
	private Integer apiuserId;
	private Set<String> roles;

}
