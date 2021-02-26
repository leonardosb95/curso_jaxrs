package br.com.alura.loja;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.junit.Test;

import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.modelo.Carrinho;

public class ClienteTest {
	
	@Test
	public void testaQueAConexaoComServidorFunciona(){		
		Client client=ClientBuilder.newClient();
		 WebTarget target = client.target("http://localhost:8080");
		  String conteudo = target.path("/carrinhos/2").request().get(String.class);
		Carrinho carrinho=(Carrinho) new XStream().fromXML(conteudo);
		 System.out.println(carrinho.getRua());
	}
	

}
