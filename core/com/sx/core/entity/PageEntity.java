package com.sx.core.entity;

import java.io.File;
import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class PageEntity implements Serializable {

	private Integer id;
	private List<String> conditions;
	private String condition;
	private Integer start;
	private Integer end;
	private Integer total;
	private Integer limit;
	private String ids;
	private String dir;
	private String sort;
	private String value;
	private String label;
	private String text;
	private String iconCls;
	private Boolean leaf;
	
	private File Filedata;
	private String FiledataFileName;
	/**
	 * @return the leaf
	 */
	public Boolean getLeaf() {
		return leaf;
	}
	/**
	 * @param leaf the leaf to set
	 */
	public void setLeaf(Boolean leaf) {
		this.leaf = leaf;
	}
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the iconCls
	 */
	public String getIconCls() {
		return iconCls;
	}
	/**
	 * @param iconCls the iconCls to set
	 */
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}
	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}
	/**
	 * @return the sort
	 */
	public String getSort() {
		return sort;
	}
	/**
	 * @param sort the sort to set
	 */
	public void setSort(String sort) {
		this.sort = sort;
	}
	/**
	 * @return the dir
	 */
	public String getDir() {
		return dir;
	}
	/**
	 * @param dir the dir to set
	 */
	public void setDir(String dir) {
		this.dir = dir;
	}
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public Integer getEnd() {
		return end;
	}
	public void setEnd(Integer end) {
		this.end = end;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	/**
	 * @return the limit
	 */
	public Integer getLimit() {
		return limit;
	}
	/**
	 * @param limit the limit to set
	 */
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	/**
	 * @return the conditions
	 */
	public List<String> getConditions() {
		return conditions;
	}
	/**
	 * @param conditions the conditions to set
	 */
	public void setConditions(List<String> conditions) {
		this.conditions = conditions;
	}
	/**
	 * @return the ids
	 */
	public String getIds() {
		return ids;
	}
	/**
	 * @param ids the ids to set
	 */
	public void setIds(String ids) {
		this.ids = ids;
	}
	/**
	 * @return the condition
	 */
	public String getCondition() {
		return condition;
	}
	/**
	 * @param condition the condition to set
	 */
	public void setCondition(String condition) {
		this.condition = condition;
	}
	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}
	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}
	/**
	 * @return the filedata
	 */
	public File getFiledata() {
		return Filedata;
	}
	/**
	 * @param filedata the filedata to set
	 */
	public void setFiledata(File filedata) {
		Filedata = filedata;
	}
	/**
	 * @return the filedataFileName
	 */
	public String getFiledataFileName() {
		return FiledataFileName;
	}
	/**
	 * @param filedataFileName the filedataFileName to set
	 */
	public void setFiledataFileName(String filedataFileName) {
		FiledataFileName = filedataFileName;
	}
	
	
	
}
