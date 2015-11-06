package cn.sunline.enddemo.logback.filter;

import org.slf4j.Marker;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.turbo.TurboFilter;
import ch.qos.logback.core.spi.FilterReply;

/*
 * 实现过滤类
 * 1.继承TurboFilter 抽象类 (extend the Filter abstract class)
 * 2.重新实现 decide 方法；该方法会传入一个日志事件对象，从中可以获取日志的内容。(implement the decide() method)
 * 
 */
public class SampleTurboFilter extends TurboFilter {
	private String key;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	@Override
	public FilterReply decide(Marker marker, Logger logger, Level level,
			String format, Object[] params, Throwable t) {
		if (format != null && format.contains(key)) {
			return FilterReply.ACCEPT;
		} else {
			//反则，交给配置文件中下一个过滤器处理
			//return FilterReply.NEUTRAL;
			
			//拒绝
			return FilterReply.DENY;
		}
	}

}
