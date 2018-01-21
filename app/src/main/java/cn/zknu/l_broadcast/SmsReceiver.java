package cn.zknu.l_broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.Toast;

import java.util.Date;

/**
 * Created by sun on 2018/1/18.
 */

public class SmsReceiver extends BroadcastReceiver {
    private static final String TAG="SmsReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG,"接收到短信");
        //如果收到短信  
        if (intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")) {
            //取消这条有序广播（取消后会让其它应用收不到短信）  
            abortBroadcast();
            Bundle bundle=intent.getExtras();
            if (bundle!=null) {//如果数据不为空  
                //获得收到的短信数据  
                Object[] objArray=(Object[]) bundle.get("pdus");
                //根据objArray的大小创建一个SmsMessage数组，用于封装短信内容  
                SmsMessage[]smsMessage=new SmsMessage[objArray.length];
                StringBuffer sb=new StringBuffer();
                sb.append("时间："+new DateFormat().format("yyyy-MM-dd hh.mm.ss", new Date()));
                //遍历smsMessage数组取出所有短信  
                for (int i = 0; i < smsMessage.length; i++) {
                    //将每条字节类型的短信数据转换成SmsMessage对象  
                    smsMessage[i]=SmsMessage.createFromPdu((byte[])objArray[i]);
                    //获取短信发送地址  
                    sb.append("发送者："+smsMessage[i].getOriginatingAddress());
                    //获取短信内容  
                    sb.append("短信内容："+smsMessage[i].getDisplayMessageBody()+"\n");
                }
                Toast.makeText(context, sb.toString(), Toast.LENGTH_LONG).show();
            }
        }
    }
}
