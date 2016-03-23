//package com.dist.yibuclient;
//
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.OutputStreamWriter;
//import java.io.PrintWriter;
//import java.net.Socket;
//import java.net.UnknownHostException;
//
//import com.warner.client.SuccessClient;
//
///*{  user:jiangwh }*/
//
//public class SocketClient {
//
//	// public static final Object locked = new Object();
//	// public static final BlockingQueue<String> queue = new
//	// ArrayBlockingQueue<String>(
//	// 1024 * 100);
//
//	class SendThread extends Thread {
//		private Socket socket;
//		private String info;
//
//		public SendThread(Socket socket,String info) {
//			this.socket = socket;
//			this.info=info;
//		}
//
//		public void closeConn() {
//			if (socket != null) {
//				try { 
//					System.out.println("ִ���˹رճ�����s");
//					streamClosed();
//					socket.close();
//					socket = null;
//				} catch (IOException e) {
//					socket = null;
//					System.out.println("�ͻ��� finally �쳣:" + e.getMessage());
//				}
//			}
//
//		} 
//
//		@Override
//		public void run() {
//
//			BufferedReader br = new BufferedReader(new InputStreamReader(
//					System.in));
//
//			try {
//				while (threadStart) {
//					// String send = getSend();
//					// PrintWriter pw = new PrintWriter(new
//					// OutputStreamWriter(socket.getOutputStream()));
//					if (!socket.isClosed() && socket.isConnected()) {
//						if (!socket.isOutputShutdown()) {
//							// System.err.println("���ӳɹ�s");
//							out = new PrintWriter(new BufferedWriter(
//									new OutputStreamWriter(
//											socket.getOutputStream())), true);
//							// pw.write(send);
//							out.println(br.readLine()); 
////							sleep(3000);
////							out.println(info);
//							out.flush(); 
//						} else {
//							streamClosed();
//							System.out.println("�߳��Ѿ��ر�s");
//							break;
//						}
//					} else {
//						streamClosed();
//						System.out.println("�߳��Ѿ��ر�s");
//						break;
//					}
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			} finally {
//				threadStart = false;
//				st.closeConn();
//				rt.closeConn();
//			}
//		}
//		// public String getSend() throws InterruptedException{
//		// Thread.sleep(1000);
//		// return
//		// "<SOAP-ENV:Envelope>"+System.currentTimeMillis()+"</SOAP-ENV:Envelope>";
//		// }
//	}
//	
//	
//	private void streamClosed() throws IOException {
//		in.close();
//		out.close();
//		if(mclientCallback!=null){
//				mclientCallback.onClosed("���ӷ������쳣");
//			}
//	}
//	
//	
//	class ReceiveThread extends Thread {
//		private Socket socket;
//
//		public ReceiveThread(Socket socket) {
//			this.socket = socket;
//		}
//
//		public void closeConn() {
//			if (socket != null) {
//				try { 
//					System.out.println("ִ���˹رճ�����r");
//					streamClosed();
//					socket.close();
//					socket = null;
//				} catch (IOException e) {
//					socket = null;
//					System.out.println("�ͻ��� finally �쳣:" + e.getMessage());
//				}
//			}
//		}
//
//		@Override
//		public void run() { 
//			
//			try {
//				while (threadStart) {
//					// Reader reader = new
//					// InputStreamReader(socket.getInputStream());
//					// CharBuffer charBuffer = CharBuffer.allocate(8192);
//					// int index = -1;
//					// while((index=reader.read(charBuffer))!=-1){
//					// charBuffer.flip();
//					// System.out.println("client:"+charBuffer.toString());
//					// }
//					if (!socket.isClosed() && socket.isConnected()) {
//
//						if (!socket.isOutputShutdown()) { 
//							in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "GBK")); 
//							String str = in.readLine();
//							if (str != null) {
//								System.out.println(str);
//								if (str.equals("byebye")) {
//									System.out.println("�����Ѿ�������r");
//									break;
//								}
//								if(mclientCallback!=null){
//					 				mclientCallback.onSuccess("���ӷ������ɹ�����ȡ����ֵΪ������"+str);
//					 			} 
//							} else {
//								streamClosed();
//								System.err.println("�����Ѿ��Ͽ�r");
//								break; 
//							}
//						} else {
//							streamClosed();
//							System.out.println("�߳��Ѿ��ر�r");
//							break; 
//						}
//					} else {
//						streamClosed();
//						System.out.println("�߳��Ѿ��ر�r");
//						break;
//					}
//				}
//
//			} catch (Exception e) {
//				e.printStackTrace();
//			} finally {
//
//				threadStart = false;
//				// out.println("byebye");
//				// out.flush(); 
//				st.closeConn();
//				rt.closeConn();  
//			}
//
//		}
//	}
//
//	public void start(String info) {
//		      
//			try {
//				socket = new Socket(HOST, PORT);
//				st = new SendThread(socket,info);
//				st.start();
//				rt = new ReceiveThread(socket);
//				rt.start();
//			} catch (Exception e) {
//	// 			e.printStackTrace();
//	 			System.out.println("���ӷ������쳣"); 
//	 			if(mclientCallback!=null){
//	 				mclientCallback.onFailure("���ӷ������쳣");
//	 			}
//	 			
//			}
//	}
//
//	public static final String adb_path = "D:\\Program Files (x86)\\android\\android-sdk\\platform-tools\\adb";// adb����·��
//	private static final String HOST = "localhost";
//	private static final int PORT = 8080;
//	private static Socket socket = null;
//	private BufferedReader in = null;
//
//	private static boolean threadStart = false;
//	ReceiveThread rt = null;
//	SendThread st = null;
//
//	private PrintWriter out = null;
//	private clientCallback mclientCallback;
//	
//	
//
//	
//	
//	public void setclientCallback( clientCallback   cb){
//		    mclientCallback=cb;
//	}
//	public interface clientCallback{
//	      public void onSuccess(String str); 
//	      public void onFailure(String str);  
//	      public void onClosed(String str); 
//	}
//
//	public static void main(String[] args) {
//		try {
//			// ���������8090�˿ڰ󶨵�PC������8080�˿ڣ�������PC��8080��������ʱʵ�����Ƿ����������8090�˿�
//			// Runtime.getRuntime().exec(G3ExpPCclient.adb_path +
//			// " �Cs emulator-5554 forward tcp:8080 tcp:8090");�������������
//			Runtime.getRuntime().exec( adb_path + " forward tcp:8080 tcp:8090");// �������
//			System.out.println("�Ѿ���������˿�8090�󶨵�PC�˿�8080 " + adb_path);
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}
//
//		threadStart = true; 
//		SocketClient  temp=new SocketClient();
//		temp.setclientCallback(new clientCallback() { 
//			@Override
//			public void onSuccess(String str) {
//				// TODO Auto-generated method stub 
//				System.out
//						.println("SocketClient.main(...).new clientCallback() {...}.onSuccess()");
//			}
//			
//			@Override
//			public void onFailure(String str) {
//				// TODO Auto-generated method stub 
//				System.out
//						.println("SocketClient.main(...).new clientCallback() {...}.onFailure()");
//			}
//			
//			@Override
//			public void onClosed(String str) {
//				// TODO Auto-generated method stub 
//				System.out
//						.println("SocketClient.main(...).new clientCallback() {...}.onClosed()");
//			}
//		});
//		temp.start("���ƶ��ն����͵���Ϣ������������������");
//		
//		
//		
////		try {
////			new SocketClient().start();
////		} catch (UnknownHostException e) {
////// 			e.printStackTrace();
//// 			System.out.println("û�����ӷ�����");
////		} catch (IOException e) {
////// 			e.printStackTrace();
//// 			System.out.println("���ӷ������쳣");
////		}
//		
//		
//	}
//}
