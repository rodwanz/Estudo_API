package com.wanzeler.estudoapi.api.model.inpt;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteIdInput {
	
	@NotNull
	private Long id;

}
