package cn.sunline.client;

import java.util.Map;

import cn.sunline.client.dao.ApSysServs;

public interface LttsConn {
	
	Map<String, Object> sendMsg(byte[] sendBuffer, String rspMsgCd,
			ApSysServs serv) ;
	Map<String, Object> sendMsg(byte[] sendBuffer, 
			ApSysServs serv) ;
}
