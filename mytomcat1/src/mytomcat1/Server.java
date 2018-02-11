package mytomcat1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 1.0版本
 * 
 * @author erjun 2018年2月7日 下午5:57:32
 */
// tomcat的核心服务类
public class Server {
    //统计server服务端 接收多少次的请求，如服务器的访问量是多少
    private static int count = 0;
    
    public static void main(String[] args) {
        ServerSocket ss = null;

        Socket socket = null;
        
        OutputStream oStream = null;

        try {
            ss = new ServerSocket(4044);

            System.out.println("服务器初始化完毕，等待客户端的连接------");

            while(true){
                // 阻塞式的接收客户端的方法
                socket = ss.accept();
                
                //接下来，解析客户端里面的请求参数
                InputStream iStream = socket.getInputStream();
                
                //先定义一个缓存，用于存储字节的数组
                byte[] buff = new byte[1024];
                
                int len = iStream.read(buff);
               
                if (len > 0) {
                    //将读取的字节转换成字符串
                    String msg = new String(buff, 0, len);
                    System.out.println("====>:\t" + msg);
                } else {
                    System.out.println("=====:\t" + " bad request!");
                }
                
                //拿到客户端的输出字节流
                oStream = socket.getOutputStream();
//                String reMsg = "<html><head><title>节日快乐</title></head><body>当前时间:" 
//                        + "<br/>服务器回复:<font size='24' color='blue'>情人节快乐！</font></body></html>";
                String reMsg = "情人节快乐！";
                
                //将信息输出
                oStream.write(reMsg.getBytes());
                
                oStream.flush();
                
            }
           
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                oStream.close();
                //整个客户端请求，全部完成
                socket.close();
                //关闭服务器端的ss
                ss.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
