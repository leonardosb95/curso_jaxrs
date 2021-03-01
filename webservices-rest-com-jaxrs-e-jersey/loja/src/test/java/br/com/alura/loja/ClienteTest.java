package br.com.alura.loja;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sun.xml.internal.stream.Entity;

import br.com.alura.loja.modelo.Carrinho;
import br.com.alura.loja.modelo.Produto;

public class ClienteTest {
	
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
	public void testaQueAConexaoComServidorFunciona(){
		
		Client client=ClientBuilder.newClient();
		 WebTarget target = client.target("http://localhost:8080");
		
		Carrinho carrinho= new Carrinho();
		carrinho.adiciona(new Produto(3L,"Teste",999,3));
		carrinho.setRua("Rua Vergueiro");
		carrinho.setCidade("São Paulo");
		String xml=carrinho.toXML();
		//POST
       javax.ws.rs.client.Entity<String> entity=javax.ws.rs.client.Entity.entity(xml, MediaType.APPLICATION_XML);
		Response response=target.path("/carrinhos").request().post(entity);
		
		//GET
		Client client2=ClientBuilder.newClient();
		 WebTarget target2 = client2.target("http://localhost:8080");
		  //String conteudo = target2.path("/carrinhos/2").request().get(String.class);
		//Carrinho carrinho=(Carrinho) new XStream().fromXML(conteudo);
		 Response response2=target2.path("/carrinhos/2").request().get();
		 System.out.println(response2);
	}
	
	


}
