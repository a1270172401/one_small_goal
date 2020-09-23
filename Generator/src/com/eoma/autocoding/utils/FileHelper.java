package com.eoma.autocoding.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileHelper {

	public static List<File> findAllFile(File file){
		List<File> files = new ArrayList<File>();
		if(file.listFiles().length > 0 ){
			for(File f: file.listFiles()){
				if( f.isFile() ){
					files.add(f);
				}else{
					files.addAll(findAllFile(f));
				}
			}
		}
		return files;
	}
	
	/**
	 * 
	 * @param fileName �ļ�ȫ·����
	 * @throws IOException 
	 */
	public static File makeFile(String fileName) throws IOException{
        File f = new File(fileName);
        if(!f.exists()){
        	f.getParentFile().mkdirs();
        	f.createNewFile();
        }
        return f;
	}
	/**
	 * �� dir�е�${package}�滻Ϊ��Ӧ������ֵ
	 * @param dir
	 * @param params
	 * @return
	 */
	public static String genFileDir(String dir,Map<String,Object> params){
		String input = dir;
		Pattern p = Pattern.compile("\\$\\{([^}]*)\\}*");
		Matcher m = p.matcher(input);
		while(m.find()){
			String name = m.group(1);
			String value  = String.valueOf(params.get(name));
			input = input.replace(m.group(), value);
		}
		return input;
	}
}
