//package com.dist.yibuclient;
//
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.OutputStreamWriter;
//import java.io.PrintWriter;
//import java.io.Reader;
//import java.io.Writer;
//import java.net.ServerSocket;
//import java.net.Socket;
//import java.nio.CharBuffer;
//import java.util.Date;
//
///*{user:jiangwh }*/
//
//public class SocketServer {
//
//	private final static String SOAP_BEGIN = "<SOAP-ENV:Envelope";
//	private final static String SOAP_END = "</SOAP-ENV:Envelope>";
//
//	public static void main(String[] args) throws IOException {
//		SocketServer socketServer = new SocketServer();
//		socketServer.start();
//	}
//
//	public void start() throws IOException {
//		ServerSocket serverSocket = new ServerSocket(18889);
//		while (true) {
//			Socket socket = serverSocket.accept();
//			new SocketThread(socket).start();
//		}
//	}
//
//	class SocketThread extends Thread {
//		private Socket socket;
//		private String temp;
//
//		public Socket getSocket() {
//			return socket;
//		}
//
//		public void setSocket(Socket socket) {
//			this.socket = socket;
//		}
//
//		public SocketThread(Socket socket) {
//			this.socket = socket;
//		}
//
//		public void run() {
//			try {
//				Reader reader = new InputStreamReader(socket.getInputStream());
//				Writer writer = new PrintWriter(new OutputStreamWriter(socket
//						.getOutputStream(), "GBK"));
//				CharBuffer charBuffer = CharBuffer.allocate(8192);
//				int readIndex = -1;
//				while ((readIndex = reader.read(charBuffer)) != -1) {
//					charBuffer.flip();
//					temp += charBuffer.toString();
//					if (temp.indexOf(SOAP_BEGIN) != -1
//							&& temp.indexOf(SOAP_END) != -1) {
//						// 传送一个soap报文
//						System.out.println(new Date().toLocaleString()+"server:"+temp);
//						temp="";
//						writer.write("receive the soap message");
//						writer.flush();
//					} else if (temp.indexOf(SOAP_BEGIN) != -1) {
//						// 包含开始，但不包含
//						temp = temp.substring(temp.indexOf(SOAP_BEGIN));
//					}	
//					if (temp.length() > 1024 * 16) {
//						break;
//					}
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			} finally {
//				if (socket != null) {
//					if (!socket.isClosed()) {
//						try {
//							socket.close();
//						} catch (IOException e) {
//							e.printStackTrace();
//						}
//					}
//				}
//			}
//
//		}
//	}
//}
