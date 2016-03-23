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
// * 针对老版本的client
// * @author john
// *
// */
//public class SuccessClient implements Runnable {
//	private  String adb_path = "D:\\Program Files (x86)\\android\\android-sdk\\platform-tools\\adb";// adb所在路径
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
//			// 把虚拟机的8090端口绑定到PC本机的8080端口，这样当PC向8080发送数据时实际上是发到虚拟机的8090端口
//			// Runtime.getRuntime().exec(G3ExpPCclient.adb_path +
//			// " –s emulator-5554 forward tcp:8080 tcp:8090");这个方法不好用
//			Runtime.getRuntime().exec(
//					 adb_path + " forward tcp:8080 tcp:8090");// 这个好用
//			System.out.println("已经将虚拟机端口8090绑定到PC端口8080 " + adb_path);
//		} catch (IOException e1) {
//			e1.printStackTrace();
//
//			if (mclientCallback != null) {
//				mclientCallback.onResult(Result.FAILURE,"连接手机失败，请检查adb路径及端口");
//			}
//		}
//
//		try {
//			socket = new Socket(HOST, PORT);
//			in = new BufferedReader(new InputStreamReader( socket.getInputStream(), "GBK"));
//			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter( socket.getOutputStream())), true);
//		} catch (IOException ex) {
//			if (mclientCallback != null) {
//				mclientCallback.onResult(Result.UNCONNECTED,"连接手机异常，服务可能没有开启");
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
//		System.out.println("调用了连接到服务器！");
//		try {
//			while (threadStart) { // 循环的发送数据和监听
//			// String msg = "http://www.baidu.com";
//				if (socket != null && socket.isConnected()
//						&& !socket.isInputShutdown()) {
//					// System.out.println("连接到服务器成功！");
//					// msg = br.readLine();
//					out.println(sendInfo);
//					String str = in.readLine();
//					if (str != null) {
//						// System.out.println(str);
//						if (mclientCallback != null) {
//							if (str.equals("received")) {// 表示手机端接收到发送信息
//								mclientCallback.onResult(Result.RECEIVED,"手机端已经获取信息");
//							} else if (str.equals("sended")) {// 表示手机端已经发送完毕而回执的消息
//								mclientCallback.onResult(Result.SUCCESS,"手机端已经成功完成发送任务");
//								break;
//							}
//
//						}
//						// if (str.equals("byebye")) {
//						// System.out.println("聊天已经结束了");
//						// break;
//						// }
//					} else {
//						if (mclientCallback != null) {
//							mclientCallback.onResult(Result.FAILURE,"连接手机返回值为空值");
//						}
//						break;
//					}
//				} else {
//					if (mclientCallback != null) {
//						mclientCallback.onResult(Result.FAILURE,"连接手机失败或手机发送服务已关闭");
//					}
//					break;
//					// System.out.println("连接到服务器失败！");
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			if (mclientCallback != null) {
//				mclientCallback.onResult(Result.FAILURE,"连接手机返回值异常");
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
//					System.out.println("客户端 finally 异常:" + e.getMessage());
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
////		final G3ExpPCclient pc = new G3ExpPCclient( "PC向手机发送的信息。。。。。。。。。。。。。。。。哒哒哒哒");
//		String info="{\"info\":{\"id\":\"4028826f50f419b40150f41c9cc5000b\",\"scontent\":\"xxx不论称它为API或者是应用程序接口，这两个略显冰冷的机器化！\","
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
// * 启动短信发送
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
////						如果连接失败，则客户端在多次进行断线重连
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
//						System.out.println("手机接收消息成功"); 
//					}
//					
//					
//					
//					System.out .println("手机发送短信连接结果.onResult()" + result+"::"+reason);
//				}
//			});
//			
//			new Thread(pc).start();
//	}
//}