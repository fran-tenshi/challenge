package com.conductor.challenge.entitys;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dados {
	
	@Id
	private String id;
	
	@NotNull(message = "O nome deve ser informado")
	private String name;
	
	@NotNull(message = "A data deve ser informada")
	private String date;
	
	@NotNull(message = "O estado deve ser informado")
	private String state;
	
	@NotNull(message = "O brvery deve ser informado")
	private String bravery;
	
	@NotNull(message = "O low deve ser informado")
	private String low;

}