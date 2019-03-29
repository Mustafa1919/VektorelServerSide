package test.server;

import java.io.IOException;

import spook.socket.arrival.LayerSocket;

public class TestServerRunner {
	public static void main(String[] args) {
		try {
			LayerSocket layerSocket = new LayerSocket();
			layerSocket.StartListening();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
