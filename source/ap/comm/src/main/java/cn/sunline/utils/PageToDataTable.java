package cn.sunline.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

/**
 * page转成datatable支持的格式
 * @author lwp
 *
 */
//TODO  继承pagerequest封装
public class PageToDataTable {
	public final  static String SORT="ASC";
	
	public static Map<String,Object> convert(Page<?> page){
		Map<String,Object> map=new HashMap<String, Object>();
		if(page.getContent().isEmpty()){//data主体
		 map.put("customActionMessage", "暂无返回数据数据");
		 map.put("customActionStatus", "ok");
		}
	    map.put("iDisplayStart", page.getNumber());
	    map.put("iDisplayLength", page.getNumberOfElements());
	    map.put("iColumns", page.getContent().size());
	    map.put("sEcho", System.currentTimeMillis());
	    map.put("data", page.getContent());
	    map.put("iTotalDisplayRecords", page.getTotalElements());
	    map.put("iTotalRecords", page.getTotalElements());
		return map;		
	}
	
	public static Map<String,Object> convert(Page<?> page,Sort sort){
		//TODO 增见排序的方法
		return convert(page);
	}

}
