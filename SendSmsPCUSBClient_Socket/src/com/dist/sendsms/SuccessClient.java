//package com.dist.sendsms;
//
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.OutputStreamWriter;
//import java.io.PrintWriter;
//import java.net.Socket;
///**
// * ����ϰ汾��client
// * @author john
// *
// */
//public class SuccessClient implements Runnable {
//	private  String adb_path = "D:\\Program Files (x86)\\android\\android-sdk\\platform-tools\\adb";// adb����·��
//	private    String HOST = "localhost";
//	private    int PORT = 8080;
//	private Socket socket = null;
//	private BufferedReader in = null;
//	private PrintWriter out = null;
//
//	private String sendInfo;
//
//	private static boolean threadStart = false;
//	
//	public SuccessClient(String path,String host,int port,String sendInfo){
//		this.adb_path=path;
//		this.HOST=host;
//		this.PORT=port; 
//		this.sendInfo = sendInfo;
////		initClient(sendInfo);
//	}
//	
//	
// 
//	public SuccessClient( ) { 
//	} 
//
//
//	public SuccessClient(String sendInfo) {
//		initClient(sendInfo);
//	} 
//
//	private void initClient(String sendInfo) {
//		threadStart = true;
//		
//		try {
//			// ���������8090�˿ڰ󶨵�PC������8080�˿ڣ�������PC��8080��������ʱʵ�����Ƿ����������8090�˿�
//			// Runtime.getRuntime().exec(G3ExpPCclient.adb_path +
//			// " �Cs emulator-5554 forward tcp:8080 tcp:8090");�������������
//			Runtime.getRuntime().exec(
//					 adb_path + " forward tcp:8080 tcp:8090");// �������
//			System.out.println("�Ѿ���������˿�8090�󶨵�PC�˿�8080 " + adb_path);
//		} catch (IOException e1) {
//			e1.printStackTrace();
//
//			if (mclientCallback != null) {
//				mclientCallback.onResult(Result.FAILURE,"�����ֻ�ʧ�ܣ�����adb·�����˿�");
//			}
//		}
//
//		try {
//			socket = new Socket(HOST, PORT);
//			in = new BufferedReader(new InputStreamReader( socket.getInputStream(), "GBK"));
//			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter( socket.getOutputStream())), true);
//		} catch (IOException ex) {
//			if (mclientCallback != null) {
//				mclientCallback.onResult(Result.UNCONNECTED,"�����ֻ��쳣���������û�п���");
//			}
//			
//
//			ex.printStackTrace();
//			System.out.println("login exception " + ex.getMessage()); 
//		}
//	}
//
//	// public void openUrl(String msg) {
//	// if (socket.isConnected() && !socket.isOutputShutdown()) {
//	// out.println(msg);
//	// // try {
//	// // socket.close();
//	// // System.exit(-1);
//	// // } catch (IOException e) {
//	// // // TODO Auto-generated catch block
//	// // e.printStackTrace();
//	// // }
//	// }
//	// }
//	@Override
//	public void run() {
//		// TODO Auto-generated method stub
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		System.out.println("���������ӵ���������");
//		try {
//			while (threadStart) { // ѭ���ķ������ݺͼ���
//			// String msg = "http://www.baidu.com";
//				if (socket != null && socket.isConnected()
//						&& !socket.isInputShutdown()) {
//					// System.out.println("���ӵ��������ɹ���");
//					// msg = br.readLine();
//					out.println(sendInfo);
//					String str = in.readLine();
//					if (str != null) {
//						// System.out.println(str);
//						if (mclientCallback != null) {
//							if (str.equals("received")) {// ��ʾ�ֻ��˽��յ�������Ϣ
//								mclientCallback.onResult(Result.RECEIVED,"�ֻ����Ѿ���ȡ��Ϣ");
//							} else if (str.equals("sended")) {// ��ʾ�ֻ����Ѿ�������϶���ִ����Ϣ
//								mclientCallback.onResult(Result.SUCCESS,"�ֻ����Ѿ��ɹ���ɷ�������");
//								break;
//							}
//
//						}
//						// if (str.equals("byebye")) {
//						// System.out.println("�����Ѿ�������");
//						// break;
//						// }
//					} else {
//						if (mclientCallback != null) {
//							mclientCallback.onResult(Result.FAILURE,"�����ֻ�����ֵΪ��ֵ");
//						}
//						break;
//					}
//				} else {
//					if (mclientCallback != null) {
//						mclientCallback.onResult(Result.FAILURE,"�����ֻ�ʧ�ܻ��ֻ����ͷ����ѹر�");
//					}
//					break;
//					// System.out.println("���ӵ�������ʧ�ܣ�");
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			if (mclientCallback != null) {
//				mclientCallback.onResult(Result.FAILURE,"�����ֻ�����ֵ�쳣");
//			}
//		} finally {
//
//			threadStart = false;
//			// if (mclientCallback != null) {
//			// mclientCallback.onResult(result.CLOSED);
//			// }
//
//			if (socket != null) {
//				try {
//					in.close();
//					out.close();
//					socket.close();
//				} catch (IOException e) {
//					socket = null;
//					System.out.println("�ͻ��� finally �쳣:" + e.getMessage());
//				}
//			}
//
//		}
//	}
//
//	public void shutDown() {
//		threadStart = false;
//	}
//
//	private clientCallback mclientCallback;
//
//	public void setclientCallback(clientCallback cb) { 
//		mclientCallback = cb;
//		initClient(sendInfo);
//	}
//
//	public enum Result {
//		FAILURE, SUCCESS, RECEIVED ,UNCONNECTED;
//	}
//
//	public interface clientCallback {
//		public void onResult(Result closed,String reason);
//	}
//
//	public static void main(String[] args) {
//
////		final G3ExpPCclient pc = new G3ExpPCclient( "PC���ֻ����͵���Ϣ����������������������������������������");
//		String info="{\"info\":{\"id\":\"4028826f50f419b40150f41c9cc5000b\",\"scontent\":\"xxx���۳���ΪAPI������Ӧ�ó���ӿڣ����������Ա���Ļ�������\","
//		  		+ "\"screatetime\":\"2015-11-11 09:15:27\",\"sstate\":\"1\", "
//		  		+ "\"userids\":\"15823361027,18565398524,15201835179,\"},\"result\":\"1\"}";
//		
//		 SuccessClient pc = new SuccessClient();
//		 pc.sendStart( "D:\\Program Files (x86)\\android\\android-sdk\\platform-tools\\adb",
//              "localhost",8080 ,info,5); 
//	} 
//	
//
///**
// * �������ŷ���
// * @param path
// * @param host
// * @param port
// * @param sendInfo
// * @param count
// */
//	public  void sendStart(final String path,final String host,final int port,final String sendInfo,final int count) { 
//			final SuccessClient pc = new SuccessClient( path, host, port, sendInfo);
//			
//			pc.setclientCallback(new clientCallback() {
//				@Override
//				public void onResult(Result result,String reason) { 
//					if(result==Result.UNCONNECTED){
////						�������ʧ�ܣ���ͻ����ڶ�ν��ж�������
//						final int mcount=count-1;
//						if(mcount>0){
//							new Thread(new Runnable() { 
//								@Override
//								public void run() {
//									try {
//										Thread.sleep(10000);
//										// TODO Auto-generated method stub
//										sendStart( path, host, port, sendInfo,mcount);
//									} catch (InterruptedException e) {
//										// TODO Auto-generated catch block
//										e.printStackTrace();
//									}
//									
//								}
//							}).start();
//						} 
//					}else if (result==Result.SUCCESS){ 
//						System.out.println("�ֻ�������Ϣ�ɹ�"); 
//					}
//					
//					
//					
//					System.out .println("�ֻ����Ͷ������ӽ��.onResult()" + result+"::"+reason);
//				}
//			});
//			
//			new Thread(pc).start();
//	}
//}