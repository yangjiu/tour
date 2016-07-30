package com.joysus.entity;

/**
 * 每天登录的钱
 * 
 */
public class EveryMoneyModel {
	/**
	 * 返回json格式的数据 { "code": 1, "message": "", "data": { "update_time": 0,
	 * "create_time": 0, "extra": "9", "name": "promotion", "remark": "首次登陆的奖励",
	 * "id": 40, "sort": 0, "type": 0, "title": "首次登陆的奖励", "value": "9",
	 * "group": 0, "status": 0 } }
	 */
	private String update_time;
	private String create_time;
	private String extra;
	private String name;
	private String remark;
	private String id;
	private int sort;
	private int type;
	private String title;
	private String value;
	private String group;
	private String status;

	public String getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public String getExtra() {
		return extra;
	}

	public void setExtra(String extra) {
		this.extra = extra;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
