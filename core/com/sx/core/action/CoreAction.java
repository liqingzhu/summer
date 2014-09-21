package com.sx.core.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sx.core.entity.AttachmentEntity;
import com.sx.core.entity.CoreEntity;
import com.sx.core.services.ICoreBiz;
import com.sx.rbac.entity.SysUserEntity;
//import com.sx.tshh.entity.UserInfoEntity;
import com.sx.util.DateUtil;
import com.sx.util.MyUtils;
import com.sx.util.SystemConfigInfo;
import com.sx.util.exception.SXException;
import com.sx.util.message.ISendMessage;
import com.sx.util.message.MessageEntity;



@Namespace(value="/back/jsp/core")  
@ParentPackage(value="coreaction")
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class CoreAction extends BaseAction {
	private Log log = LogFactory.getLog(this.getClass());
	@Resource
	private ICoreBiz coredao;
	@Autowired
	private ISendMessage sendmessage;
	/**
	 * 按照编号删除附件信息
	 **/
	public boolean deleteFileInfo(String filepath) throws SXException{
		boolean flag = false;
		try {
			File myDelFile = new File(filepath);
			if (myDelFile.exists()) {
				flag = myDelFile.delete();
			}else{
				flag = false;
			}
		} catch (Exception e) {
			// TODO: handle exception
			log.error("按照表单编号删除附件信息!",e);
		}
		return flag;
	}
	/**
	 * 上传文件公共方法
	 * 返回上传后表单文件名称
	 * @param String root 上传路径    
	 * @param String filename 文件名称
	 * @param File file 文件
	 **/
	public AttachmentEntity getUpdateFileInfo(String root,String filename,File file) throws SXException{
		AttachmentEntity attachment = new AttachmentEntity();
		String _webrootpath = getServletContext().getContextPath();
		//获取网页的路径信息
		//获取物理路径信息
		//获取系统在计算机中的物理路径 如 C：\ncfams\webroot
		//E:\MyProject\PracticeProject\lsh\WebRoot\back//attachmentworkflow\2011-09-05day\
		String rootPath = getSession().getServletContext().getRealPath("/"+root); 
	    //根据配置的标识来创建日期文件夹 重属性文件中读取叫 dateflag 的属性
		String day = SystemConfigInfo.ARTICLE_FILE_FOLDER_CREATETIME;
		//用来创建文件夹的名称
		String sp = rootPath +"\\"+ DateUtil.getDateName(day)+day+"\\";
		//根据给定的名称 ”sp“ 创建文件夹
		MyUtils.mkDirectory(sp);
	    //获取网页中的web路径
		String webcontentpath = _webrootpath+"/"+root+"/"+DateUtil.getDateName(day)+day;
		//开始进行上传的操作
        String filevalue = MyUtils.upload(filename.replaceAll(" ", ""), sp, file);  
        //上传完成后获取文件网站全目录信息
        attachment.setFvaluename(filevalue);
        attachment.setFvaluepath(webcontentpath);
        attachment.setFdisplayname(filename);
		return attachment;
	}
	@Action(value="index",results={@Result(name="index",location="/index.jsp")})  
	public String goToIndex(){
		CoreEntity core = new CoreEntity();
		core.setTitle("AAAAAAAAA");
		coredao.saveInfo(core);
//		System.out.println(areadao);
		return "index";
	}
	@Action(value="printinfo")  
	public void outPrintinfo() throws SXException{
		try {
			MessageEntity message = new MessageEntity();
			Map<String,String> data = new HashMap<String,String>();
			data.put("username", "张三疯14：24");
			message.setData(data);
			message.setTemplatelocation("hello.vm");
			message.setTitle("Title");
			message.setSender("qhdtshh@sina.cn");
			message.setReceiver("qhdtshh@sina.cn");
			sendmessage.sendMessage(message);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("打印数据信息",e);
		}
		outJsonString("{success:true}");
	}
	
	private File fileimg;
	private String fileimgFileName;
	private static final String CONTENT_TYPE = "text/html; charset=utf-8";
//	public static final String ROOT = "back.war";
	public static final String ROOT = "front/attachment";
//	public static final String ROOTPATH = "/sx";
	/**
	 * 下载文件
	 * realname 文件真正的名字
	 * filename 文件物理地址
	 * path     文件存在路径
	 * 
	 */
	public void downLoadAttachment(String realname,String filename,String path){
        
		String filePath = path;//getRealyPath(path+"\\"+filename);
		// System.out.println("---"+filePath+"---");
		boolean isInline = false;
		getResponse().reset();
		try {
			java.io.File f = new java.io.File(filePath);
			if (f.exists() && f.canRead()) {
				String mimetype = null;
				mimetype = getServletContext().getMimeType(filePath);
				if (mimetype == null) {
					mimetype = "application/octet-stream;charset=iso8859-1";
				}
				getResponse().setContentType(mimetype);
				String ua = getRequest().getHeader("User-Agent"); //  获取终端类型    
				if (ua == null)
					ua = "User-Agent:Mozilla/4.0(compatible; MSIE 6.0;)";
				boolean isIE = ua.toLowerCase().indexOf("msie") != -1; //  是否为 IE    
				if (isIE && !isInline) {
					mimetype = "application/x-msdownload";
				}
				//  下面我们将设法让客户端保存文件的时候显示正确的文件名, 具体就是将文件名   
				//  转换为 ISO8859-1 编码    
				String inlineType = isInline ? "inline" : "attachment"; //  是否内联附件    
				getResponse().setHeader("Content-Disposition", inlineType
						+ ";filename=\""
						+ java.net.URLEncoder.encode(filename, "UTF-8")
						+ "\"");
				getResponse().setContentLength((int) f.length()); //  设置下载内容大小    
				byte[] buffer = new byte[4096]; //  缓冲区    
				BufferedOutputStream output = null;
				BufferedInputStream input = null;
				try {
					output = new BufferedOutputStream(getResponse().getOutputStream());
					input = new BufferedInputStream(new FileInputStream(f));
					int n = (-1);
					while ((n = input.read(buffer, 0, 4096)) > -1) {
						output.write(buffer, 0, n);
						output.flush();//
					}
					getResponse().flushBuffer();
				}catch(SocketException ex){
					log.error("下载文件出错 SocketException", ex);
					ex.printStackTrace();
				}catch (Exception e) {
					e.printStackTrace();
					String simplename = e.getClass().getSimpleName();
					if(!"ClientAbortException".equals(simplename)){
						log.error("下载文件出错", e);
					}else{
						log.error("客户端取消了文件下载！");
					}
				} //  用户可能取消了下载    
				finally {
					if (input != null){input.close();}
					if (output != null){output.close();}
				}
			}
			return;
		} catch(SocketException ex){
			log.error("下载文件出错 SocketException", ex);
		}catch (Exception ex) {
			log.error("下载文件出错 Exception", ex);
			return;
		}
	}
	/**
	 * 上传文件公共方法
	 * 返回上传后表单文件名称
	 **/
	public AttachmentEntity getUpdateFileInfo(String root,String displayname) throws SXException{
		/*SysUserEntity _user = super.getUserFromSession();
		System.out.println(_user);*/
		AttachmentEntity attachment = new AttachmentEntity();
		String _uploadpath = getRequest().getParameter("path");
		String _webrootpath = getServletContext().getContextPath();
		//获取网页的路径信息
		//获取物理路径信息
		String path = "";
		if (_uploadpath==null||("").equals(_uploadpath)) {
			path = "webattachment";
		} else {
			path = _uploadpath;
		}
		//文件上传
		//获取系统在计算机中的物理路径 如 C：\ncfams\webroot
		//E:\MyProject\PracticeProject\lsh\WebRoot\back//attachmentworkflow\2011-09-05day\
//		String rootPath = getSession().getServletContext().getRealPath("/"); 
//		rootPath += root+"\\"+path+"\\";  
//	    //根据配置的标识来创建日期文件夹 重属性文件中读取叫 dateflag 的属性
//		String day = SystemConfigInfo.ARTICLE_FILE_FOLDER_CREATETIME;
//		//用来创建文件夹的名称
//		String sp = rootPath + DateUtil.getDateName(day)+day+"//";
		//文件保存位置，当前项目下的upload/mail
		String uploadDir = root + File.separatorChar;
		//每天上传的文件根据日期存放在不同的文件夹
		String autoCreatedDateDirByParttern = "yyyy" + File.separatorChar + "MM" + File.separatorChar + "dd"+ File.separatorChar;
		String autoCreatedDateDir = DateFormatUtils.format(new java.util.Date(), autoCreatedDateDirByParttern);
		String ctxDir = super.getRealyPath(String.valueOf(File.separatorChar));
		if (!ctxDir.endsWith(String.valueOf(File.separatorChar))) {
			ctxDir = ctxDir + File.separatorChar;
		}
//		File savePath = new File(ctxDir + uploadDir + autoCreatedDateDir);
//		if (!savePath.exists()) {
//			savePath.mkdirs();
//		}
		String sp = "";
	    //获取网页中的web路径
		if(path.lastIndexOf("\\")>0){
			path = path.substring(0,path.lastIndexOf("\\"));	
			sp = ctxDir + uploadDir+path + autoCreatedDateDir;
		}else{
			sp = ctxDir + uploadDir+path +"/"+ autoCreatedDateDir;
		}
		//根据给定的名称 ”sp“ 创建文件夹
		MyUtils.mkDirectory(sp);
//		String webcontentpath = _webrootpath+"/"+root+"/"+path+"/"+DateUtil.getDateName(day)+day;
		String webcontentpath = _webrootpath+"/"+root+"/"+path+"/"+autoCreatedDateDir;
		webcontentpath = webcontentpath.replaceAll("\\\\", "/");
		//开始进行上传的操作
        String filename = MyUtils.upload(this.getFileimgFileName().replaceAll(" ", ""), sp, this.getFileimg());  
        //上传完成后获取文件网站全目录信息
//        String wholefilename = webcontentpath+"/"+filename;
        attachment.setFvaluename(filename);
        attachment.setFvaluepath(webcontentpath);
        attachment.setFdisplayname(displayname);
		return attachment;
	}
	/**
	 * 上传文件公共方法
	 * 返回上传后表单文件名称
	 **/
	public AttachmentEntity getUpdateFileInfo() throws SXException{
		AttachmentEntity attachment = new AttachmentEntity();
		String _uploadpath = getRequest().getParameter("path");
		String _webrootpath = getServletContext().getContextPath();
		//获取网页的路径信息
		//获取物理路径信息
		String path = "";
		if (_uploadpath==null||("").equals(_uploadpath)) {
			path = "webattachment";
		} else {
			path = _uploadpath;
		}
		//文件上传
		//获取系统在计算机中的物理路径 如 C：\ncfams\webroot
		//E:\MyProject\PracticeProject\lsh\WebRoot\back//attachmentworkflow\2011-09-05day\
		String rootPath = getSession().getServletContext().getRealPath("/"); 
		rootPath += ROOT+"\\"+path+"\\";  
	    //根据配置的标识来创建日期文件夹 重属性文件中读取叫 dateflag 的属性
		String day = SystemConfigInfo.ARTICLE_FILE_FOLDER_CREATETIME;
		//用来创建文件夹的名称
		String sp = rootPath + DateUtil.getDateName(day)+day+"//";
		//根据给定的名称 ”sp“ 创建文件夹
		MyUtils.mkDirectory(sp);
	    //获取网页中的web路径
		String webcontentpath = _webrootpath+"/"+ROOT+"/"+path+"/"+DateUtil.getDateName(day)+day;
		//开始进行上传的操作
        String filename = MyUtils.upload(this.getFileimgFileName().replaceAll(" ", ""), sp, this.getFileimg());  
        //上传完成后获取文件网站全目录信息
//        String wholefilename = webcontentpath+"/"+filename;
        attachment.setFvaluename(filename);
        attachment.setFvaluepath(webcontentpath);
        attachment.setFdisplayname(fileimgFileName);
		return attachment;
	}
	/**
	 *上传图片文件 
	 **/
	public void uploadFileImage() throws SXException{
		try {
			String _uploadpath = getRequest().getParameter("path");
			String _webrootpath = getServletContext().getContextPath();
			//获取网页的路径信息
			//获取物理路径信息
			String path = "";
			if (_uploadpath==null||("").equals(_uploadpath)) {
				path = "webattachment";
			} else {
				path = _uploadpath;
			}
			//文件上传
			//获取系统在计算机中的物理路径 如 C：\ncfams\webroot
			//E:\MyProject\PracticeProject\lsh\WebRoot\back//attachmentworkflow\2011-09-05day\
			String rootPath = getSession().getServletContext().getRealPath("/"); 
			rootPath += ROOT+"\\"+path+"\\";  
		    //根据配置的标识来创建日期文件夹 重属性文件中读取叫 dateflag 的属性
			String day = SystemConfigInfo.ARTICLE_FILE_FOLDER_CREATETIME;
			//用来创建文件夹的名称
			String sp = rootPath + DateUtil.getDateName(day)+day+"//";
			//根据给定的名称 ”sp“ 创建文件夹
			MyUtils.mkDirectory(sp);
		    //获取网页中的web路径
			String webcontentpath = _webrootpath+"/"+ROOT+"/"+path+"/"+DateUtil.getDateName(day)+day;
			//开始进行上传的操作
	        String filename = MyUtils.upload(this.getFileimgFileName().replaceAll(" ", ""), sp, this.getFileimg());  
	        //上传完成后获取文件网站全目录信息
	        String wholefilename = webcontentpath+"/"+filename;
	        outJsonString("{success:true,filepath:'"+wholefilename+"'}");
		} catch (Exception e) {
			outJsonString("{success:false}");
			log.error("上传表单附件信息",e);
		}
	}
	/**
	 * 文件上传
	 **/
	public void uploadFileInfo() throws SXException{
//		System.out.println("======>"+fileimgFileName);
		outJsonString("{success:true}");
	}
	/**
	 *设置加载页面共享目录数据信息 
	 **/
	public String includeLibs(){
		String page = "includelibs";
		try {
			page = "includelibs";
		} catch (Exception e) {
			log.error("设置共享目录数据信息!",e);
		}
		return page ;
	}
	/**
	 * @return the fileimg
	 */
	public File getFileimg() {
		return fileimg;
	}
	/**
	 * @param fileimg the fileimg to set
	 */
	public void setFileimg(File fileimg) {
		this.fileimg = fileimg;
	}
	/**
	 * @return the fileimgFileName
	 */
	public String getFileimgFileName() {
		return fileimgFileName;
	}
	/**
	 * @param fileimgFileName the fileimgFileName to set
	 */
	public void setFileimgFileName(String fileimgFileName) {
		this.fileimgFileName = fileimgFileName;
	}
	
	public SysUserEntity getUserFromSession(){
		SysUserEntity user = (SysUserEntity)super.getSession().getAttribute("sysuser");
		return user;
	}
	
	/**
	 *获取登录后的用户信息 
	 *前台注册用户
	 **/
	/*public UserInfoEntity getUserInfoFromSession(){
		return (UserInfoEntity)getSession().getAttribute("userinfoentity");
	}*/
}
