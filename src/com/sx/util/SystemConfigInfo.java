package com.sx.util;

import java.util.ResourceBundle;

public class SystemConfigInfo {
	//配置文件路径
	private static final String CONFIG_FILEPATH = "com.sx.config.config";
	// 读取配置文件
	public static ResourceBundle coreconfig = ResourceBundle.getBundle(CONFIG_FILEPATH);
	// 数据库连接地址
	public static final String ARTICLE_FILE_FOLDER_CREATETIME = coreconfig.getString("articlefilefolder");
	// 读取上下文中的数据信息
	public static final String PROJECT_ROOT_PATH = coreconfig.getString("contextpath");

	
}
