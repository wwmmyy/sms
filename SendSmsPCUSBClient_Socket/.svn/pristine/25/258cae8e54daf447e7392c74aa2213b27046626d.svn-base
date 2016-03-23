//package com.dist.sendsms;
//
///**
// *TCPClient
// *@author Winty wintys@gmail.com
// *@version 2008-12-15
// */
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.io.DataInputStream;
//import java.io.DataOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.net.Socket;
//import java.net.UnknownHostException;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Timer;
//import java.util.TimerTask;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
//import javax.swing.ImageIcon;
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//import javax.swing.JScrollPane;
//import javax.swing.JTextArea;
//import javax.swing.JTextField;
//
//import com.dist.client.tool.UtilTool;
//
///**
// * 当前改进版的client
// * 
// * @author wmy
// *
// */
//class TCPClient_BAK extends JFrame implements ActionListener{
//
//	private String adb_path = "D:\\Program Files (x86)\\android\\android-sdk\\platform-tools\\adb";// adb所在路径
//	private String HOST = "localhost";
//	private int PORT = 5050;
//	
//	JTextArea jta;
//	JTextField jtf;
//	JButton jb;
//	JPanel jp;
//	JScrollPane jsp;
//	
//
//	public static void main(String[] args) throws Exception {
//		// String
//		// info="{\"info\":{\"id\":\"4028826f50f419b40150f41c9cc5000b\",\"scontent\":\"xxx不论称它为API或者是应用程序接口，这两个略显冰冷的机器化！\","
//		// + "\"screatetime\":\"2015-11-11 09:15:27\",\"sstate\":\"1\", "
//		// +
//		// "\"userids\":\"15823361027,18565398524,15201835179,\"},\"result\":\"1\"}";
//		String info = "{\"state\":\"secceed\",\"message\":\"操作成功\",\"data\":[{\"CircleDate\":0,\"CreateTime\":\"2016-03-02 15:24:18\",\"FromUserId\":142,\"Id\":241,\"IsSend\":false,\"MsgType\":0,\"SendMessage\":\"顶顶顶顶顶顶顶顶顶顶顶顶顶顶的\",\"SendMobelPhone\":\"18565398524\",\"SendTime\":\"2016-03-02 15:24:18\",\"SendTimes\":0,\"ToUserId\":0,\"Remark\":\"消息发送\"},{\"CircleDate\":0,\"CreateTime\":\"2016-03-02 15:24:37\",\"FromUserId\":142,\"Id\":242,\"IsSend\":false,\"MsgType\":0,\"SendMessage\":\"啊啥的都是但是犯得上犯得上发射点犯得上的方式大啊啊啊大师傅士大夫士大夫士大夫\",\"SendMobelPhone\":\"18565398524\",\"SendTime\":\"2016-03-02 15:24:37\",\"SendTimes\":0,\"ToUserId\":0,\"Remark\":\"消息发送\"}]}";
//
//		TCPClient_BAK temp = new TCPClient_BAK();
////		// temp.PCClient(info); 
////		temp.sendSmsToUsers();
//		
//		 
//		
//		
//	}
//	
//	
//	
//	public TCPClient_BAK() {
//		// TODO Auto-generated constructor stub
//		jta=new JTextArea();
//		jtf=new JTextField(25);
//		jb=new JButton("开启短信服务");
//		jb.addActionListener(this);
//		jp=new JPanel();
//		jsp=new JScrollPane(jta);
//		jp.add(jtf);
//		jp.add(jb);
//		
//		this.setTitle("标题呵呵呵呵呵呵");
//		this.add(jsp,"Center");
//		this.add(jp,"South");
//		this.setSize(400,300);
////		this.setIconImage((new ImageIcon("image/头像.GIF").getImage()));
//		this.setVisible(true);
//	}
//	
//	
// 
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		// TODO Auto-generated method stub
//		if(e.getSource()==jb)
//		{
//			sendSmsToUsers();
//		}
//	}
//
//
//
//
//
//	ExecutorService threadPoll;
//	TimerTask mtask;// 计时器用于周期性联网检测要发送的短信
//	Timer mtimer;
//	static  boolean  pause = false; // 用于表示现在能否继续联网检测短信，当收到要发送的短信时。我们是先将短信发完后再继续检测。
//	int periord = 30000;// 用于表示联网的间隔时间
//
//	public void stopSendSms() {
//		if (mtask != null) {
//			mtask.cancel();
//		}
//		if (mtimer != null) {
//			mtimer.cancel();
//		}
//		if (threadPoll != null)
//			threadPoll.shutdown();
//		System.out.println("停止了短信发送功能" + "停止了短信发送功能");
//	}
//
//	public boolean sendSmsToUsers() {
//		threadPoll = Executors.newCachedThreadPool(); // 创建线程池
//		System.out.println("短信发送功能启动了" + "短信发送功能启动了");
//		mtimer = new Timer();
//		// 设置定时器，定时监测主视图头部按钮是否被点击
//		mtask = new TimerTask() {
//			@Override
//			public void run() {
//				// threadPoll.execute(
//				System.out.println("当前的获取短信发送的联网状态为：：" + "短信："
//						+ pause);
//				if (!pause) {
//					new Thread(new Runnable() {
//						@Override
//						public void run() { 
//							// if(UtilTool.checkNet(HttpSmsService.this)){ 
//							Map<String, String> map = new HashMap<String, String>();
//							map.put("deviceNumber", "123456");
//							try {
//								// String
//								// url="http://192.168.1.192/distmobile/service/gsmservice.asmx/GetNewMessages";
//								// String totalresult = UtilsTool
//								// .getInfoFromServer( DistApp.http_url, map);
//								// System.out.println("获取到的url为：：：："+DistApp.serverUrl);
//								// String totalresult = UtilTool
//								// .getInfoFromServer("http://"+DistApp.serverUrl+"/service/gsmservice.asmx/GetNewMessages",
//								// map);
//								String totalresult = UtilTool.getInfoFromServer(
//												"http://192.168.1.192/distmobile/service/gsmservice.asmx/GetNewMessages",
//												map);
//
//								System.out.println("获取到的返回结果为 " +totalresult.length()+":::"+ totalresult);
//								// Message msg2 = new Message();
//								if (totalresult != null&&  totalresult.length()>60) {// 这里说明是有短信需要发送此时将时间给间隔大一点,同时说明需要发送的短信不为空
//								// JSONObject obj = new JSONObject(
//								// totalresult);
//								// String result = obj .optString("result");
//								// if (result != null && result.trim()
//								// .equals("1")) {// 说明有短信需要发送
// 									
//									pause=true;
//									// 将获取的发送结果发出去
//									// if (onServerInfoListener != null) {
//									// onServerInfoListener.onServerInfo(totalresult);
//									// }
//									// }
//									TCPClient_BAK temp = new TCPClient_BAK();
//									temp.PCClient(totalresult);
//
//								}
//
//							} catch (Exception e) {
//								// TODO 自动生成的 catch 块
//								// UtilsTool.saveErrorFile(e, "发短信异常日志：" +
//								// Math.random());
//								e.printStackTrace();
//								// if (onServerInfoListener != null) {
//								// onServerInfoListener .onServerInfo("");
//								// }
//							}
//						}
//						// }else{
//						//
//						// }
//
//					})
//					// );
//					.start();
//				}
//			}
//		};
//		mtimer.schedule(mtask, 10000, periord);// 每隔30s调用一次
//
//		return true;
//	}
//
////	public boolean isPause() {
////		return pause;
////	}
////
////	public void setPause(boolean pause) {
////		this.pause = pause;
////	}
//
//	InputStream in;
//	OutputStream out;
//	Socket client ;
//	private void PCClient(String info) throws UnknownHostException,
//			IOException, InterruptedException {
//		try {
//			// 把虚拟机的8090端口绑定到PC本机的8080端口，这样当PC向8080发送数据时实际上是发到虚拟机的8090端口
//			// Runtime.getRuntime().exec(G3ExpPCclient.adb_path +
//			// " –s emulator-5554 forward tcp:8080 tcp:8090");这个方法不好用
//			Runtime.getRuntime().exec(adb_path + " forward tcp:5050 tcp:5060");// 这个好用
//			// System.out.println("已经将虚拟机端口8090绑定到PC端口8080 " + adb_path);
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}
//
//		try {
//			 client = new Socket(HOST, PORT);
//			// Socket client = new Socket("127.0.0.1" , 5050);
//
//			 in = client.getInputStream();
//			 out = client.getOutputStream();
//			DataOutputStream dout = new DataOutputStream(out);
//			// dout.writeUTF("client send info：：："+info);
//			dout.writeUTF(info);
//
//			DataInputStream din = new DataInputStream(in);
//			String msg = din.readUTF();
//			System.out.println("client 收到:" + msg);
//			// if(msg.equals("ssssssss")){ //代表收到了server端处理完成返回的信息
//			// dout.writeUTF("OK");
//			Thread.sleep(3000); // 表示将信息反馈给服务器
//
////			setPause(false);
//			
//
//			if(msg!=null && !msg.equals("")){
//				Map<String, String> map = new HashMap<String, String>(); 
//				map.put("idArr",msg);  
////				 //Log.d("获取到的返回结果为 :::smsIds.toString()idArr", alreadySendSmsIds.toString()+"..");
////				map.put("idArr", "317,");//暂时用于测试验证的  
//				String totalresult = UtilTool.getInfoFromServer("http://192.168.1.192/distmobile/service/gsmservice.asmx/UpdateMessageState", map);  
//				System.out.println(" 反馈成功，client 停止了哦" +totalresult+ pause);
//				// }
//			}
//
//
//		} catch ( Exception e1) {
//			e1.printStackTrace();
//		}finally{
//			pause=false;
//			out.close();
//			in.close();
//			client.close();
//			System.out.println("client socket 停止了哦噢噢噢噢");
//		}
//	}
//
// 
//	 
//	
//}
