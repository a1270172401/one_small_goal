
import common.Column;
import common.Table;
import config.Config;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.CamelCaseUtils;
import utils.FileHelper;

import java.io.*;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author 念着倒才子傻
 */
public class Generator {
	private Properties properties = null;

	public Generator() throws Exception {
		properties = new Properties();
		String fileDir = this.getClass().getClassLoader().getResource("generator.xml").getFile();
		properties.loadFromXML(new FileInputStream(fileDir));
	}

	public Table parseTable(String tableName) throws Exception {
		String column = "%";
		System.out.println("driver>>"+ Config.JDBC_DRIVER);
		System.out.println("url>>"+Config.JDBC_URL);
		System.out.println("name>>"+Config.JDBC_USERNAME);
		System.out.println("password>>"+Config.JDBC_PASSWORD);
		System.out.println("catalog>>"+Config.CATALOG);
		System.out.println("schema>>"+Config.SCHEMA);

		Class.forName(Config.JDBC_DRIVER);
		Connection conn = java.sql.DriverManager.getConnection(Config.JDBC_URL, Config.JDBC_USERNAME, Config.JDBC_PASSWORD);
		DatabaseMetaData dmd = conn.getMetaData();

		ResultSet rs = dmd.getColumns(Config.CATALOG, Config.SCHEMA, tableName, column);
		List<Column> columns = new ArrayList<Column>();
		while (rs.next()) {
			Column c = new Column();
			c.setLabel(rs.getString("REMARKS"));
			String name = rs.getString("COLUMN_NAME");
			c.setName(CamelCaseUtils.toCamelCase(name));
			c.setDbName(name);
			String dbType = rs.getString("TYPE_NAME");
			int columnSize = rs.getInt("COLUMN_SIZE");
			if(dbType.equals("TINYINT")&&columnSize>1){
				c.setType("Integer");
			}else if(dbType.equals("TINYINT")&&columnSize==1){
				c.setType("Boolean");
			}else{
				String type = properties.getProperty(dbType);
				c.setType(type == null ? "String" : type);
			}
			c.setDbType(dbType);
			c.setLength(rs.getInt("COLUMN_SIZE"));
			c.setDecimalDigits(rs.getInt("DECIMAL_DIGITS"));
			c.setNullable(rs.getBoolean("NULLABLE"));
			columns.add(c);
		}
		List<Column> pkColumns = new ArrayList<Column>();
		ResultSet pkrs = dmd.getPrimaryKeys(Config.CATALOG, Config.SCHEMA, tableName);
		while(pkrs.next()){
			Column c = new Column();
			String name = pkrs.getString("COLUMN_NAME");
			c.setName(CamelCaseUtils.toCamelCase(name));
			c.setDbName(name);
			pkColumns.add(c);
		}

		conn.close();

		Table t = new Table();

		String prefiex = Config.TABLE_REMOVE_PREFIXES;
		String name = tableName;
		if( prefiex != null && !"".equals(prefiex) ){
			name = tableName.split(prefiex)[0];
		}
		t.setName(CamelCaseUtils.toCamelCase(name));
		t.setDbName(tableName);
		t.setColumns(columns);
		t.setPkColumns(pkColumns);
		return t;
	}

	/**
	 * <p>Discription:[生成映射文件和实体类]</p>
	 * Created on 2015年2月4日
	 * @param tableName 要声称映射文件和实体类的表名称
	 * @param tableDescAndCat 表描述
	 * @throws Exception
	 */
	public void gen(String tableName,String tableDescAndCat,String id,String modelId) throws Exception {
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_21);
		String outRoot = Config.OUT_PATH;
		//将首字母转为大写
		StringBuffer buffer = new StringBuffer();
		String namePart1 = modelId.substring(0, 1).toUpperCase();
		String namePart2 = modelId.substring(1);
		buffer.append(namePart1+namePart2);
		Map<String, Object> root = new HashMap<String, Object>();
		Table t = this.parseTable(tableName);
		t.setTableDesc(tableDescAndCat.split("_")[0]);
		root.put("table", t);
		root.put("className", t.getNameUpper());
		root.put("classNameLower", t.getName());
		root.put("primaryKey", id);
		root.put("modelId", modelId);
		root.put("modelIdFirstUpper", buffer);
		root.put("package", Config.BASE_PACKAGE);
		root.put("date", Config.DATE);
		root.put("year", Config.YEAR);
		root.put("author", Config.AUTHOR);
		root.put("email", Config.EMAIL);
		root.put("packageFirst",Config.PACKAGE_FIRST);
		String templateDir = this.getClass().getClassLoader().getResource("templates").getPath();
		File tdf = new File(templateDir);
		List<File> files = FileHelper.findAllFile(tdf);
		for(File f: files){
			String parentDir = "";
			if( f.getParentFile().compareTo(tdf) != 0 ){
				parentDir = f.getParent().split("templates")[1];
			}
			cfg.setClassForTemplateLoading(this.getClass(), "/templates" + parentDir);

			Template template = cfg.getTemplate(f.getName(),"UTF-8");
			String parentFileDir = FileHelper.genFileDir(parentDir, root);
			parentFileDir = parentFileDir.replace(".", "/");
			String file = FileHelper.genFileDir(f.getName(),root).replace(".ftl", ".java");
			File newFile = FileHelper.makeFile(outRoot + parentFileDir + "/" + file);
			Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream( newFile ), "UTF-8"));
			template.process(root, out);
		}
	}

	/**
	 * 1 修改config目录下相应的数据库配置
	 * 2 修改该main方法中的表名，多个map使用多个
	 * 2 运行该main方法
	 * 3 生成代码完毕
	 */
	public static void main(String[] args) throws Exception {
		System.out.println("开始生成模版文件...");
		Generator g = new Generator();
		Map<String, String> map = new HashMap<String, String>();
		map.put("small_goal_user","用户表");
		Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry<String, String> e = it.next();
			//id 是数据库主键字段
			g.gen(e.getKey(), e.getValue(),"id","id");
		}
		System.out.println("模版文件生成完毕...");
	}
}
