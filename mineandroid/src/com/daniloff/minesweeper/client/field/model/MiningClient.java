package com.daniloff.minesweeper.client.field.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.daniloff.minesweeper.serverAPI.Request;
import com.daniloff.minesweeper.serverAPI.Response;

public class MiningClient {

	public Request request;

	public ObjectOutputStream outputStream;
	public ObjectInputStream inputStream;

	Socket socket;
	ObjectInputStream ois;

	public void connectToServer() throws IOException {
		// String serverAddress = "localhost";// ************
		String serverAddress = "192.168.1.16";// ************

		socket = new Socket(serverAddress, 9898);
		outputStream = new ObjectOutputStream(socket.getOutputStream());
		ois = new ObjectInputStream(socket.getInputStream());
	}

	public Response getResponse() throws IOException {
		Response responseIn = null;
		try {
			responseIn = (Response) ois.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return responseIn;
	}

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}
}
