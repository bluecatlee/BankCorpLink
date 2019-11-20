package com.github.bluecatlee.bcm.invoke;

import com.github.bluecatlee.bcm.constant.Constants;
import com.github.bluecatlee.bcm.exception.BcmException;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

@Slf4j
public class SocketCall implements Call {

    private String host;
    private Integer port;
    private Integer timeout;

    public SocketCall(String host, Integer port, Integer timeout) {
        this.host = host;
        this.port = port;
        this.timeout = timeout;
    }

    /**
     * socket请求
     * 7位报头+数据报
     *      报头：第1位表示标识位 默认0
     *            后6位表示报文(数据报)长度,左对齐,右补空格
     *
     * @param request
     * @return
     */
    @Override
    public String execute(String request) {
        try {
            Socket socket = new Socket(host, port); //建立 socket
            socket.setSoTimeout(timeout);
            BufferedOutputStream wr = null;

            byte[] messageByte = request.getBytes(Constants.DEFAULT_CHARSET);
            String length = "0" + messageByte.length + "       ";  //7 位头
            length = length.substring(0, 7);
            request = length + request;
            log.debug("BCM socket call request:" + request);
            messageByte = request.getBytes(Constants.DEFAULT_CHARSET);

            int sleepTime = 1000;    //1s，线程休眠时间
            Thread.sleep(sleepTime);

            wr = new BufferedOutputStream(socket.getOutputStream());//开 始发送消息
            wr.write(messageByte);
            wr.flush();
            //接收回应消息
            BufferedReader rd = new BufferedReader(new InputStreamReader(socket.getInputStream(), Constants.DEFAULT_CHARSET));
            String line = null;

            StringBuffer sb = new StringBuffer();
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }
            //处理返回
            String returnStr = new String(sb.toString().getBytes());
            wr.close();
            rd.close();
            socket.close();
            log.debug("BCM socket call response:" + returnStr);

            String head = returnStr.substring(0, 7).trim();
            int i = Integer.valueOf(head).intValue();
            if (i > 0) {
                returnStr = returnStr.substring(7); // 去掉前7位
            } else {
                String reason;
                if (i == -170 || i == -179) {
                    reason = "创建socket连接失败";
                } else if (i == -172) {
                    reason = "发送请求报文头错误";
                } else if (i == -173) {
                    reason = "发送请求报文体失败";
                } else if (i == -174) {
                    reason = "接受应答报文头错误";
                } else if (i == -175) {
                    reason = "接收报文体失败";
                } else {
                    reason = "未知错误";
                }
                log.error("BCM socket call failure: " + reason);
                throw new BcmException(reason);
            }
            return returnStr;
        } catch (Exception e) {
            throw new BcmException(e);
        }

    }

}
