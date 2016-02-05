package cn.sunline.comm.utils;

import java.io.File;
/**
 * 
 * <p>
 *   <li>功能说明：</li>
 *   <li>controller层参数接口，需要配置的参数可以常量配置，需要使用的controller只需要实现此类即可</li>
 * </p>
 * TIME 2015年11月14日-上午9:01:39
 * created by wanggl 
 * @email wanggl@sunline.cn
 */
public interface CommParamUtils {
	/**
	 * 图像文件保存路径
	 */
	public final String IMAGE_PATH = "F:"+File.separatorChar+"file"+File.separatorChar+"images"+File.separatorChar;
	/**
	 * 通用下载请求路径
	 */
	public final String COMM_DOWN_URL = "/rest/download/downLoadFile";
	/**
	 * 黑名单文件上传文件保存路径
	 */
	public final String BLACK_LIST_PATH = "F:"+File.separatorChar+"file"+File.separatorChar+"blacklist"+File.separatorChar;
	/**
	 * 合同模板保存路径
	 */
	public final String GDCTS_MODEL_PATH = File.separatorChar+"home"+File.separatorChar+"gdctsfile"+File.separatorChar+"model"+File.separatorChar;
}
