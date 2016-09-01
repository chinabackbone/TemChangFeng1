package cn.backbone.tem.net;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Random;

import cn.backbone.tem.temchangfeng.DynamicChartLineActivity2;

/**
 * Created by admin on 2016/8/31.
 */
public class ReceiveThread implements Runnable{
    private  String host,content=null;
    private  int port;
    private Handler myhandler;
    public int flag;
    public Socket socket;
    public ReceiveThread(String host, int port, Handler myhandler,int flag){
        this.host = host;
        this.port = port;
        this.myhandler = myhandler;
        this.flag = flag;
    }
    public void closeSocket() throws IOException {
        socket.close();
    }
    public boolean socketisok(){
        return socket!=null;
    }
    @Override
    public void run() {

        Log.d("chang","start receive Thread");
        try {
            socket = new Socket(host,port);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while (flag == 1){
                if(socket.isConnected()){
                    if(!socket.isInputShutdown()){
                        //下面注释的 真实要加上
                        //if((content = in.readLine()) != null){
                            //sleep 真实要去掉 模拟接收到的温度。
                            Thread.sleep(1000);
                            DynamicChartLineActivity2.startID=1;
                            content ="111111";

                            Random r = new Random();
                            double tem =20+ r.nextDouble();


                            Message message = new Message();
                            Bundle bundle = new Bundle();
                            content = content + tem;
                            bundle.putString("rece",content);
                            message.setData(bundle);
                            message.what = 11;
                            myhandler.sendMessage(message);
                            Log.d("chang","receive_sendmessage");
                       // }
                    }
                }
            }
            if(flag==0){
                DynamicChartLineActivity2.startID = 0;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
