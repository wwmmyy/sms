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
// * ��ǰ�Ľ����client
// * 
// * @author wmy
// *
// */
//class TCPClient_BAK extends JFrame implements ActionListener{
//
//	private String adb_path = "D:\\Program Files (x86)\\android\\android-sdk\\platform-tools\\adb";// adb����·��
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
//		// info="{\"info\":{\"id\":\"4028826f50f419b40150f41c9cc5000b\",\"scontent\":\"xxx���۳���ΪAPI������Ӧ�ó���ӿڣ����������Ա���Ļ�������\","
//		// + "\"screatetime\":\"2015-11-11 09:15:27\",\"sstate\":\"1\", "
//		// +
//		// "\"userids\":\"15823361027,18565398524,15201835179,\"},\"result\":\"1\"}";
//		String info = "{\"state\":\"secceed\",\"message\":\"�����ɹ�\",\"data\":[{\"CircleDate\":0,\"CreateTime\":\"2016-03-02 15:24:18\",\"FromUserId\":142,\"Id\":241,\"IsSend\":false,\"MsgType\":0,\"SendMessage\":\"������������������������������\",\"SendMobelPhone\":\"18565398524\",\"SendTime\":\"2016-03-02 15:24:18\",\"SendTimes\":0,\"ToUserId\":0,\"Remark\":\"��Ϣ����\"},{\"CircleDate\":0,\"CreateTime\":\"2016-03-02 15:24:37\",\"FromUserId\":142,\"Id\":242,\"IsSend\":false,\"MsgType\":0,\"SendMessage\":\"��ɶ�Ķ��ǵ��Ƿ����Ϸ����Ϸ���㷸���ϵķ�ʽ�󰡰�����ʦ��ʿ���ʿ���ʿ���\",\"SendMobelPhone\":\"18565398524\",\"SendTime\":\"2016-03-02 15:24:37\",\"SendTimes\":0,\"ToUserId\":0,\"Remark\":\"��Ϣ����\"}]}";
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
//		jb=new JButton("�������ŷ���");
//		jb.addActionListener(this);
//		jp=new JPanel();
//		jsp=new JScrollPane(jta);
//		jp.add(jtf);
//		jp.add(jb);
//		
//		this.setTitle("����ǺǺǺǺǺ�");
//		this.add(jsp,"Center");
//		this.add(jp,"South");
//		this.setSize(400,300);
////		this.setIconImage((new ImageIcon("image/ͷ��.GIF").getImage()));
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
//	TimerTask mtask;// ��ʱ�������������������Ҫ���͵Ķ���
//	Timer mtimer;
//	static  boolean  pause = false; // ���ڱ�ʾ�����ܷ�������������ţ����յ�Ҫ���͵Ķ���ʱ���������Ƚ����ŷ�����ټ�����⡣
//	int periord = 30000;// ���ڱ�ʾ�����ļ��ʱ��
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
//		System.out.println("ֹͣ�˶��ŷ��͹���" + "ֹͣ�˶��ŷ��͹���");
//	}
//
//	public boolean sendSmsToUsers() {
//		threadPoll = Executors.newCachedThreadPool(); // �����̳߳�
//		System.out.println("���ŷ��͹���������" + "���ŷ��͹���������");
//		mtimer = new Timer();
//		// ���ö�ʱ������ʱ�������ͼͷ����ť�Ƿ񱻵��
//		mtask = new TimerTask() {
//			@Override
//			public void run() {
//				// threadPoll.execute(
//				System.out.println("��ǰ�Ļ�ȡ���ŷ��͵�����״̬Ϊ����" + "���ţ�"
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
//								// System.out.println("��ȡ����urlΪ��������"+DistApp.serverUrl);
//								// String totalresult = UtilTool
//								// .getInfoFromServer("http://"+DistApp.serverUrl+"/service/gsmservice.asmx/GetNewMessages",
//								// map);
//								String totalresult = UtilTool.getInfoFromServer(
//												"http://192.168.1.192/distmobile/service/gsmservice.asmx/GetNewMessages",
//												map);
//
//								System.out.println("��ȡ���ķ��ؽ��Ϊ " +totalresult.length()+":::"+ totalresult);
//								// Message msg2 = new Message();
//								if (totalresult != null&&  totalresult.length()>60) {// ����˵�����ж�����Ҫ���ʹ�ʱ��ʱ��������һ��,ͬʱ˵����Ҫ���͵Ķ��Ų�Ϊ��
//								// JSONObject obj = new JSONObject(
//								// totalresult);
//								// String result = obj .optString("result");
//								// if (result != null && result.trim()
//								// .equals("1")) {// ˵���ж�����Ҫ����
// 									
//									pause=true;
//									// ����ȡ�ķ��ͽ������ȥ
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
//								// TODO �Զ����ɵ� catch ��
//								// UtilsTool.saveErrorFile(e, "�������쳣��־��" +
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
//		mtimer.schedule(mtask, 10000, periord);// ÿ��30s����һ��
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
//			// ���������8090�˿ڰ󶨵�PC������8080�˿ڣ�������PC��8080��������ʱʵ�����Ƿ����������8090�˿�
//			// Runtime.getRuntime().exec(G3ExpPCclient.adb_path +
//			// " �Cs emulator-5554 forward tcp:8080 tcp:8090");�������������
//			Runtime.getRuntime().exec(adb_path + " forward tcp:5050 tcp:5060");// �������
//			// System.out.println("�Ѿ���������˿�8090�󶨵�PC�˿�8080 " + adb_path);
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
//			// dout.writeUTF("client send info������"+info);
//			dout.writeUTF(info);
//
//			DataInputStream din = new DataInputStream(in);
//			String msg = din.readUTF();
//			System.out.println("client �յ�:" + msg);
//			// if(msg.equals("ssssssss")){ //�����յ���server�˴�����ɷ��ص���Ϣ
//			// dout.writeUTF("OK");
//			Thread.sleep(3000); // ��ʾ����Ϣ������������
//
////			setPause(false);
//			
//
//			if(msg!=null && !msg.equals("")){
//				Map<String, String> map = new HashMap<String, String>(); 
//				map.put("idArr",msg);  
////				 //Log.d("��ȡ���ķ��ؽ��Ϊ :::smsIds.toString()idArr", alreadySendSmsIds.toString()+"..");
////				map.put("idArr", "317,");//��ʱ���ڲ�����֤��  
//				String totalresult = UtilTool.getInfoFromServer("http://192.168.1.192/distmobile/service/gsmservice.asmx/UpdateMessageState", map);  
//				System.out.println(" �����ɹ���client ֹͣ��Ŷ" +totalresult+ pause);
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
//			System.out.println("client socket ֹͣ��Ŷ��������");
//		}
//	}
//
// 
//	 
//	
//}
