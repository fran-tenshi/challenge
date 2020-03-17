package com.conductor.challenge.resources;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.conductor.challenge.entitys.Dados;
import com.conductor.challenge.repositories.DadosRepository;

@RestController
@RequestMapping("/api")
public class Test {
	
	@Autowired
	DadosRepository dadosRepository;	
	
	@GetMapping
	public List<Dados> index() {
		
		ExampleMatcher matcher = ExampleMatcher.matchingAll().withIgnoreCase();
		
		List<Dados> ts = dadosRepository.findAll(Example.of(new Dados(), matcher));
	   
		return ts;

	}
	
	@GetMapping("/filter")
    public String filter(String id) {
    	
        Optional<Dados> ts = dadosRepository.findById(id);

		return ts.toString();
		
    }

	@PostMapping(path = "/save", produces = "application/json")
	public String save(@RequestBody Dados dados) {
						
		dadosRepository.save(dados);
		
		return "Mensagem enviada!";
		
	}
	
}
