package cn.backbone.tem.net;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by admin on 2016/8/31.
 */
public class SendThread implements Runnable {

    String host, sendMessage;
    int port, i;
    public  int flag;
    public Socket socket;
    private  Handler handler;

    public SendThread(String host, int port, int i, String sendMessage, int flag, Handler handler) {
        Log.d("chang","sendstart+gouzao");
        System.out.print("1111");
        this.host = host;
        this.port = port;
        this.i = i;
        this.sendMessage = sendMessage;
        this.flag = flag;
        this.handler = handler;
    }
    public void closeSocket() throws IOException {
        socket.close();
    }
    public boolean socketisok(){
        return socket.isConnected();
    }

    @Override
    public void run() {
        Log.d("chang","sendstart");
        try {
            socket = new Socket(host,port);
            //BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
            while (flag == 1){
                if (socket.isConnected()) {
                    if (!socket.isOutputShutdown()) {
                        Thread.sleep(i * 1000);
                        out.println(sendMessage);
                        Log.d("chang","sendOK");

                    }
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(socket == null){

                handler.sendEmptyMessage(22);
            }
        }
    }
}
