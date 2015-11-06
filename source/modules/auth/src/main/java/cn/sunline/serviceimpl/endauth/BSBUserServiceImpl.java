package cn.sunline.serviceimpl.endauth;

import org.springframework.stereotype.Service;

import cn.sunline.domain.endauth.BSBUser;
import cn.sunline.service.endauth.BSBUserService;

@Service("BSBUserService")
public class BSBUserServiceImpl implements BSBUserService {
	@Override
	public BSBUser login(BSBUser user) {
		System.out.println(user.getPasswd());
		// Map<String, Object> recvMsg=service.callSerive("socket",
		// "{'userid':'"+user.getUserid()+"','passwd':'"+user.getPasswd()+"','pswdfg':'"+user.getPswdfg()+"'}");

		return user;
	}

}
