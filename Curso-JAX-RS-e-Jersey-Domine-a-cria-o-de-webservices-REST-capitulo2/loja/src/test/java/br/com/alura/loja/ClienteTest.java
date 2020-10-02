package br.com.alura.loja;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import junit.framework.Assert;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.modelo.Carrinho;

public class ClienteTest {

	private HttpServer server;
	
//    @Test
//    public void testaQueAConexaoComOServidorFunciona() {
//        Client client = ClientBuilder.newClient();
//        WebTarget target = client.target("http://www.mocky.io");
//        String conteudo = target.path("/v2/52aaf5deee7ba8c70329fb7d").request().get(String.class);
//        Assert.assertTrue(conteudo.contains("<rua>Rua Vergueiro 3185"));
//    }
    
	@Before//Antes de rodarmos os testes
	public void startaServidor() {
	    this.server=Servidor.inicializaServidor();
	}
	
	@After//Depois de rodar o servidor
	 public void mataServidor() {
        server.stop();
    }
	
    @Test
    public void testaQueBuscarUmCarrinhoTrazOCarrinhoEsperado() {
    	Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080");
        String conteudo = target.path("/carrinhos").request().get(String.class);
        Carrinho carrinho=(Carrinho) new XStream().fromXML(conteudo); //Deserealizar o xml e transformar em Objeto
        Assert.assertEquals("Rua Vergueiro 3185, 8 andar", carrinho.getRua());
        System.out.println(carrinho.getRua());
    }
    
    
}