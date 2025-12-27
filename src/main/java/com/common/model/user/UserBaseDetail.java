package com.common.model.user;

import com.common.enums.RegistrationType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserBaseDetail implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	private UUID id;

	private String firstName;

	private String lastName;

	@Email
	private String email;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Size(min = 8, max = 20, groups = { RequestLoginValidation.class, RequestChangePasswordValidation.class, RequestRegistrationValidation.class})
	private String password;

	private String countryCode;

	private String phoneNumber;

	private String profileUrl;

	@NotNull(message = "registration type must not be null")
	private RegistrationType registrationType;

	private String provider;

	private String socialId;


	public interface RequestLoginValidation {
	}

	public interface RequestChangePasswordValidation {
	}

	public interface RequestRegistrationValidation {
	}


}
