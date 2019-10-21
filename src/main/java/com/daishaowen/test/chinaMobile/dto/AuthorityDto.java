package com.daishaowen.test.chinaMobile.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Date;

/**
 * 权限
 *
 * @author yuanjian
 *
 */
@SuppressWarnings("PMD.CyclomaticComplexity")
public class AuthorityDto implements Serializable {

	private static final long serialVersionUID = -6126538899260640628L;

	private Integer id;

	private String name;

	@JsonIgnore
	private Date createTime;

	@JsonIgnore
	private Date updateTime;

	@JsonIgnore
	private Integer delFlag;

	private String description;

	private Integer level;

	private String parentName;

	public Integer getId() {

		return id;
	}

	public void setId(final Integer id) {

		this.id = id;
	}

	public String getName() {

		return name;
	}

	public void setName(final String name) {

		this.name = name;
	}

	public Date getCreateTime() {

		return createTime;
	}

	public void setCreateTime(final Date createTime) {

		this.createTime = createTime;
	}

	public Date getUpdateTime() {

		return updateTime;
	}

	public void setUpdateTime(final Date updateTime) {

		this.updateTime = updateTime;
	}

	public Integer getDelFlag() {

		return delFlag;
	}

	public void setDelFlag(final Integer delFlag) {

		this.delFlag = delFlag;
	}

	public String getDescription() {

		return description;
	}

	public void setDescription(final String description) {

		this.description = description;
	}

	public Integer getLevel() {

		return level;
	}

	public void setLevel(final Integer level) {

		this.level = level;
	}

	public String getParentName() {

		return parentName;
	}

	public void setParentName(final String parentName) {

		this.parentName = parentName;
	}

	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + ((level == null) ? 0 : level.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((parentName == null) ? 0 : parentName.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {

		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final AuthorityDto other = (AuthorityDto) obj;
		if (level == null) {
			if (other.level != null) {
				return false;
			}
		} else if (!level.equals(other.level)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (parentName == null) {
			if (other.parentName != null) {
				return false;
			}
		} else if (!parentName.equals(other.parentName)) {
			return false;
		}
		return true;
	}

}