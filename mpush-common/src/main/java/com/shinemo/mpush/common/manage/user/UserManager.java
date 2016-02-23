package com.shinemo.mpush.common.manage.user;

import java.util.List;

import com.shinemo.mpush.api.RedisKey;
import com.shinemo.mpush.tools.redis.manage.RedisManage;

//查询使用
public class UserManager {
	
	public void userOnline(String userId) {
    	String onlineKey = RedisKey.getUserOnlineKey();
    	RedisManage.rpush(onlineKey, userId);
    	String offlineKey = RedisKey.getUserOfflineKey();
    	RedisManage.lrem(offlineKey, userId);
    }

    public void userOffline(String userId) {
        String onlineKey = RedisKey.getUserOnlineKey();
    	RedisManage.lrem(onlineKey, userId);
    	String offlineKey = RedisKey.getUserOfflineKey();
    	RedisManage.rpush(offlineKey, userId);
    }
    
    //在线用户
    public long onlineUserNum(){
    	String onlineKey = RedisKey.getUserOnlineKey();
    	return RedisManage.llen(onlineKey);
    }
    //离线用户
    public long offlineUserNum(){
    	String offlineKey = RedisKey.getUserOfflineKey();
    	return RedisManage.llen(offlineKey);
    }
    
    //在线用户列表
    public List<String> onlineUserList(int start,int size){
    	if(size<=10){
    		size = 10;
    	}
    	String onlineKey = RedisKey.getUserOnlineKey();
    	return RedisManage.lrange(onlineKey, start, size-1, String.class);
    }
    
    //离线用户
    public List<String> offlineUserList(int start,int size){
    	if(size<=10){
    		size = 10;
    	}
    	String offlineKey = RedisKey.getUserOfflineKey();
    	return RedisManage.lrange(offlineKey, start, size-1, String.class);
    }
	
}