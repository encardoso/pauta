package com.apirest.southsystem.pauta;

//import java.util.ArrayList;
//import java.util.List;
//import java.util.UUID;
//
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import com.apirest.southsystem.pauta.document.Associado;
//import com.apirest.southsystem.pauta.respository.AssociadoRepository;
//
//
//@Component
//public class DadosDominio implements CommandLineRunner{
//
//	private final AssociadoRepository associadoRepository;
//
//	DadosDominio(AssociadoRepository associadoRepository) {
//		this.associadoRepository = associadoRepository;
//	}
//
//	@Override
//	public void run(String... args) throws Exception {
//		associadoRepository.deleteAll();
//		List<Associado> associados = new ArrayList<Associado>();
//		associados.add(new Associado(new String(UUID.randomUUID().toString()), "62955817066","Fulano"));
//		associados.add(new Associado(new String(UUID.randomUUID().toString()), "74887030061","Ciclano"));
//		associados.add(new Associado(new String(UUID.randomUUID().toString()), "74531997000","Beltrano"));
//		associadoRepository.saveAll(associados);		
//	}	
//}