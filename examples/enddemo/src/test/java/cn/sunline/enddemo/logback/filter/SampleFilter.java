package cn.sunline.enddemo.logback.filter;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;

/*
 * 实现过滤类
 * 1.继承Filter 抽象类
 * 2.重新实现 decide 方法；该方法会传入一个日志事件对象，从中可以获取日志的内容。
 * 
 */
public class SampleFilter extends Filter<ILoggingEvent> {
	private String key;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	@Override
	public FilterReply decide(ILoggingEvent event) {
		 
		//当打印的内容包含xml文件filter定义的key值，则会返回Accept,立即处理日志
		if (event.getMessage() != null && event.getMessage().contains(key)) {
			return FilterReply.ACCEPT;
		} else {
			//反则，交给配置文件中下一个过滤器处理
			return FilterReply.NEUTRAL;
			
			//拒绝
			//return FilterReply.DENY;
		}
	}

}
