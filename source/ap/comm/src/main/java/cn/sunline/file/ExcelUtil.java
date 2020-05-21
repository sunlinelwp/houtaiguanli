package cn.sunline.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.sunline.exception.SumpException;
import cn.sunline.utils.DateTools;





public class ExcelUtil {

	private final static Logger logger = LoggerFactory
			.getLogger(ExcelUtil.class);

	// 文件名称
	private String fileName;
	// 文件路径
	private String filePath;

	public ExcelUtil() {

	}

	public ExcelUtil(String fileName, String filePath) {
		this.fileName = fileName;
		this.filePath = filePath;
	}

	/**
	 * 写入Excel文件
	 * 
	 * @param sheetName
	 *            sheet名称
	 * @param columns
	 *            列表头
	 * @param cells
	 *            数据项
	 */
	@SuppressWarnings("resource")
	public void writeExcel(String sheetName, List<String> columns,
			List<List<ExcelEntity>> cells) {

		logger.debug("====文件名称=======" + fileName);
		Workbook wb = null;
		if (fileName.endsWith("xlsx")) {
			wb = new XSSFWorkbook();
		} else if (fileName.endsWith("xls")) {
			wb = new HSSFWorkbook();
		} else {
			throw new SumpException("不是Excel文件格式");
		}

		Sheet sheet = wb.createSheet(sheetName);

		short rowIndex = 0;
		// 创建列表
		Row row = sheet.createRow(rowIndex);
		for (int i = 0; i < columns.size(); i++) {
			row.createCell(i).setCellValue(columns.get(i));
		}

		// 允许写入空数据
		if (cells != null) {
			for (List<ExcelEntity> rows : cells) {
				rowIndex++;
				Row rowColumn = sheet.createRow(rowIndex);

				int cellIndex = 0;
				for (ExcelEntity m : rows) {
					Cell cell = rowColumn.createCell(cellIndex);
					switch (m.getDataType()) {
					case 1:
						cell.setCellValue(m.getData());
						break;
					case 2:
						cell.setCellValue(DateTools.String2Date(m.getData(),
								DateTools.YYYYMMDD));
						break;
					case 3:
						cell.setCellValue(Double.valueOf(m.getData()));
						break;
					default:
						throw new SumpException("不支持的数据类型");

					}
					cellIndex++;
				}
			}
		}

		FileOutputStream fileOut = null;
		File file = new File(filePath + fileName);
		try {
			fileOut = new FileOutputStream(file);
			wb.write(fileOut);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new SumpException("没有找到文件");
		} catch (IOException e) {
			e.printStackTrace();
			throw new SumpException("文件写入异常");
		} finally {
			try {
				if (fileOut != null)
					fileOut.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new SumpException("文件关闭异常");
			}
		}

	}

	/**
	 * 读取Excel文件
	 * @param headRow 列表名和头行数，即跳过读取的行数
	 * @param cells   读取的数据
	 */
	public void readExcel(int headRow,List<List<ExcelEntity>> cells) {
		InputStream inp = null;
		Workbook wb = null;
		try {
			inp = new FileInputStream(filePath + fileName);
			
			wb = WorkbookFactory.create(inp);
		    Sheet sheet = wb.getSheetAt(0);
		    int rowIndex = 0;
		    for (Row row : sheet) {
		    	rowIndex++;
		    	if(rowIndex <= headRow)
		    		continue;
		    	List<ExcelEntity> datas = new LinkedList<>();
		        for (Cell cell : row) {
		        	ExcelEntity m = new ExcelEntity();
		        	short dataType = 1;
		        	logger.debug("类型======================="+cell.getCellType());
		            switch (cell.getCellType()) {
		                case Cell.CELL_TYPE_STRING:
		                	dataType = 1;
		                    m.setData(cell.getRichStringCellValue().getString());
		                    break;
		                case Cell.CELL_TYPE_NUMERIC:
		                	logger.debug("是否日期=============="+DateUtil.isCellDateFormatted(cell));
		                    if (DateUtil.isCellDateFormatted(cell)) {
		                    	dataType= 2;
		                    	m.setData(DateTools.Date2String(cell.getDateCellValue(), DateTools.YYYYMMDD));
		                    } else {
		                    	//将数字类型先转成字符串类型，避免大数字变成科学计数法表示
		                    	dataType= 3;
		                    	cell.setCellType(Cell.CELL_TYPE_STRING);
		                    	m.setData(cell.getStringCellValue());
		                    }
		                    break;
		                case Cell.CELL_TYPE_BOOLEAN:
		                	dataType= 4;
		                	m.setData(String.valueOf(cell.getBooleanCellValue()));
		                    break;
		                case Cell.CELL_TYPE_FORMULA:
		                	dataType= 5;
		                	m.setData(cell.getCellFormula());
		                    break;
		                case Cell.CELL_TYPE_BLANK:
		                	dataType = 6;
		                	m.setData(null);
		                	break;
		                default:
		                	throw new SumpException("未识别数据类型");
		            }
		            
		            m.setDataType(dataType);
		            
		            datas.add(m);
		        }
		        cells.add(datas);
		    }
		    
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new SumpException("没有找到文件");
		}catch (EncryptedDocumentException | InvalidFormatException
				| IOException e) {
			e.printStackTrace();
			throw new SumpException("读取EXCEL文件失败");
		}finally{
			if(inp != null){
				try {
					inp.close();
				} catch (IOException e) {
					e.printStackTrace();
					throw new SumpException("文件关闭异常");
				}
			}
		}
	}
}
