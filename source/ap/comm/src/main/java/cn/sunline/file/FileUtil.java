package cn.sunline.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.sunline.exception.SumpException;
import cn.sunline.utils.CharacterTools;

public class FileUtil {
	private static Logger logger = LoggerFactory.getLogger(FileUtil.class);
	// 文件名称
	private String fileName;
	// 文件路径
	private String filePath;
	// 分隔符
	private String splitChar = "|";
	// 字符集
	private String encode = "UTF-8";
	// 一次处理笔数
	private Integer cnt = 0;
	// 是否带统计笔数
	private String calFlag = "N";
	
	//统计笔数所在位置
	private Integer calCharAt;
	
	//明细开始行数
	private Integer beginLine;
	public FileUtil() {

	}

	public FileUtil(String fileName, String filePath) {
		this.fileName = fileName;
		this.filePath = filePath;
	}

	public FileUtil(String fileName, String filePath, String split,
			String encode, Integer dealCnt, String calFlag ,Integer calCharAt,Integer beginLine) {
		this.fileName = fileName;
		this.filePath = filePath;
		this.splitChar = CharacterTools.convertChar(split);
		this.encode = encode;
		this.cnt = dealCnt;
		this.calFlag = calFlag;
		this.calCharAt = calCharAt;
		this.beginLine = beginLine;
	}

	public void readFile(DataWriter dataWriter, String inputDate) {
		Path path = FileSystems.getDefault().getPath(filePath, fileName);
		int iCnt = 0;
		if (cnt != null)
			iCnt = cnt.intValue();

		logger.info("=======文件路径===========" + path.toString());
		if (Files.isReadable(path)) {
			// 注意此处的newBufferedRead的charset参数，如果和所要读取的文件的编码不一致，则会抛出异常
			// java的新特性，不用自己关闭流
			try {
				BufferedReader br = Files.newBufferedReader(path,
						Charset.forName(encode));
				
				String line = "";
				List<String[]> inputDatas = new ArrayList<>();

				if ("Y".equals(calFlag)) {
					// 第一行为统计笔数
					line = br.readLine();
					logger.debug("=======头行数据===========" + line);
					String[] head = line.split(splitChar,-1);
					dataWriter.writerHeadData(head, inputDate);
					logger.debug("记录数======================="+(Integer.valueOf(head[calCharAt])+beginLine-1));
					for (int i = 1, j = 1; i <= (Integer.valueOf(head[calCharAt])+beginLine-1); i++, j++) {
						line = br.readLine();
						if(i<beginLine){
							logger.debug("跳过一行！");
							continue;
						}
						logger.info("行数据=======" + line);

						String[] column = line.split(splitChar,-1);
						inputDatas.add(column);
						// 计数到处理条数时，进行数据处理
						if (iCnt != 0 && j % iCnt == 0) {
							dataWriter.writerDBData(inputDatas, inputDate);
							inputDatas.clear();
						}
					}
				} else {
					line = br.readLine();
					logger.debug("=======头行数据===========" + line);
					String[] head = line.split(splitChar,-1);
					dataWriter.writerHeadData(head, inputDate);
					int j = 1;
					while ((line = br.readLine()) != null) {
						if(j<beginLine){
							continue;
						}
						logger.info("行数据=======" + line);
						String[] column = line.split(splitChar,-1);

						inputDatas.add(column);
						// 计数到处理条数时，进行数据处理
						if (iCnt != 0 && j % iCnt == 0) {
							dataWriter.writerDBData(inputDatas, inputDate);
							inputDatas.clear();
						}
						j++;
					}
				}

				dataWriter.writerDBData(inputDatas, inputDate);

			} 
			catch (IOException e) {
				logger.error("无法读取文件" + filePath + fileName);
				throw new SumpException("1104", "无法读取文件" + filePath +File.separator+ fileName);
			} 
			catch (NullPointerException e) {
				throw new SumpException("1104", "文件格式有误" + filePath +File.separator+ fileName);
			}
		} else {
			logger.error("文件权限不正确，不可读" + filePath + fileName);
			throw new SumpException("1104", "文件不存在" + filePath +File.separator + fileName);
		}

	}
	
	/**
	 * 判断文件是否存在
	 * @return
	 */
	public boolean exsistFile(){
		Path path = FileSystems.getDefault().getPath(filePath, fileName);
		return Files.exists(path);
	}
}
