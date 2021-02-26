package br.com.alura.loja;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ProjetoTest {

	
	
	private HttpServer server;

	@Before
	public void startaServidor(){
		server=Servidor.inicializaServidor();
	}
	
	@After
	public void mataServidor(){
		server.stop();
	}
	
	@Test
	public void projetoTest(){
		Client client= ClientBuilder.newClient();
		WebTarget target=client.target("http://localhost:8080");
		String conteudo=target.path("/projetos").request().get(String.class);
		System.out.println(conteudo);	
	}
	
	
	
}
