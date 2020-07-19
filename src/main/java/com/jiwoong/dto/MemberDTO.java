package com.jiwoong.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDTO {
	
	private String id;
	private String name;
	private String pass;
	private String sex;// M or W (mysql은 check제약조건이 없음)
	private String adress;
	private String email;
	private String regidate;
	private String sms;// yes or no
	private String level;//default 'member'
	private String birth;
	private String phone;

}
