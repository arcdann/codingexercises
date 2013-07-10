package com.daniloff.minesweeper.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.daniloff.minesweeper.serverAPI.Request;
import com.daniloff.minesweeper.serverAPI.Response;

public class MiningServer {

	public static void main(String[] args) throws Exception {
		System.out.println("Server console: The server is running");
		int clientNumber = 0;
		ServerSocket listener = new ServerSocket(9898);
		try {
			while (true) {
				new MineLayer(listener.accept(), clientNumber++).start();
			}
		} finally {
			listener.close();
		}
	}

	private static class MineLayer extends Thread {
		private Socket socket;
		private int clientNumber;
		Request requestIn;
		private ResponseMaker responseMaker;
		Response response;
		public ObjectOutputStream outputStream;

		public MineLayer(Socket socket, int clientNumber) {
			this.socket = socket;
			this.clientNumber = clientNumber;
			log("New connection with client# " + clientNumber + " at " + socket);
		}

		public void run() {
			try {
				while (true) {
					InputStream is = socket.getInputStream();
					outputStream = new ObjectOutputStream(socket.getOutputStream());
					outputStream.flush();
					ObjectInputStream ois = new ObjectInputStream(is);
					try {
						requestIn = (Request) ois.readObject();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}

					response = new Response();
					responseMaker = new ResponseMaker();
					responseMaker.setRequestIn(requestIn);
					responseMaker.setResponse(response);
					responseMaker.prepareResponse();

					outputStream.writeObject(response);
				}
			} catch (IOException e) {
				log("Error handling client# " + clientNumber + ": " + e);
			} finally {
				try {
					socket.close();
				} catch (IOException e) {
					log("Couldn't close a socket, what's going on?");
				}
				log("Connection with client# " + clientNumber + " closed");
			}
		}

		private void log(String message) {
			System.out.println("Server log: " + message);
		}
	}
}
