package com.conductor.challenge.resources;

import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;

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

	@PostMapping(path = "/send", produces = "application/json")
	public String save(@RequestBody Dados dados) {
						
		try {
			
			Socket cliente = new Socket("localhost", 2222);
			ObjectOutputStream entrada = new ObjectOutputStream(cliente.getOutputStream());
			entrada.writeUTF(dados.getName());
			entrada.flush();
			entrada.close();
			
			cliente.close();
			System.out.println("Conexão encerrada");
			
			dadosRepository.save(dados);

			
		} catch (Exception e) {
			
			System.out.println("Erro: " + e.getMessage());
			
		}
				
		return "Mensagem enviada, "+dados.getName();
		
	}

	@PostMapping(path = "/send/pipe", produces = "text/plain;charset=UTF-8")
	public String savePipe(@RequestBody String dados) {	
		
		Dados dt = new Dados();
		
		String[] value_split = dados.split("\\|");
		
		dt.setName(value_split[0]);
		dt.setDate(value_split[1]);
		dt.setState(value_split[2]);
		dt.setBravery(value_split[3]);
		dt.setLow(value_split[4]);
		
		try {
			
			Socket cliente = new Socket("localhost", 2222);
			ObjectOutputStream entrada = new ObjectOutputStream(cliente.getOutputStream());
			entrada.writeUTF(dt.getName());
			entrada.flush();
			entrada.close();
			
			cliente.close();
			System.out.println("Conexão encerrada");
			
			dadosRepository.save(dt);

			
		} catch (Exception e) {
			
			System.out.println("Erro: " + e.getMessage());
			
		}
				
		return "Mensagem enviada, "+dt;
	}	
	
}
