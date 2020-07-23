package com.jiwoong.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 * @author songjiwoong
 * 회원 가입시 입력 폼과 회원 테이블의 구조가 일치하지 않으므로 만튼 DTO.
 * 회원 가입 홈 혹은 회원 테이블이 변하더라도 수정에 용이함.
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MemberRequestDTO {
	
	@NotBlank(message = "아이디를 입력해주세요")
	@Pattern(regexp = "^[a-zA-Z0-9]{5,15}$", message = "아이디는 영어와 숫자 조합 5~15자리여야 합니다.") //영대.소문자, 숫자만 사용해서 5~15자리
	private String id;
	
	@NotBlank(message = "이름을 입력해주세요")
	private String name;
	
	@NotBlank(message = "비밀번호를 입력해주세요")
	@Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}", message="비밀번호는 영문 대/소문자, 숫자, 특수기호가 적어도 한개 이상 포함된 8~20자리여야 합니다.")
	private String pass;
	@NotBlank(message = "생년월일을 입력해 주세요")
	private String birth1;
	@NotBlank(message = "생년월일을 입력해 주세요")
	private String birth2;
	@NotBlank(message = "생년월일을 입력해 주세요")
	private String birth3;
	@NotBlank(message = "성별을 선택해 주세요")
	private String sex;
	@NotBlank(message = "전화번호를 입력해 주세요")
	@Pattern(regexp = "[0-9]{10,11}", message = "10~11자리의 숫자만 입력가능합니다")
	private String phone;
	@NotBlank(message = "이메일을 입력해 주세요")
	private String email1;
	@NotBlank(message = "이메일을 입력해 주세요")
	private String email2;
	@NotBlank(message = "주소를 입력해 주세요")
	private String address1;
	@NotBlank(message = "상세주소를 입력해 주세요")
	private String address2;
	@Size(max = 4)
	private String passCheckVal;
	
	private String getBirth() {
		return birth1 + birth2 + birth3;
	}
	private String getEmail() {
		return email1 + "@" + email2;
	}
	private String getAddress() {
		return address1 + address2;
	}
	
	public MemberDTO toMemberDTO() {
		return new MemberDTO(id, name, pass, sex, getAddress(), getEmail(), "", "", getBirth(), phone);
	}
}
