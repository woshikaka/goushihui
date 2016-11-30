package com.sfmy.gsh.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sfmy.gsh.constant.AdType;

/**
 * 首页轮播广告
 * @author 黄燕针
 */
@Entity
@Table(name = "ad")
public class Ad {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 255, nullable = false)
	private String href;
	
	/**
	 * file的key
	 */
	@Column(name = "oss_key", length = 70, nullable = false)
	private String ossKey;

	@Column(name = "is_use", nullable = false)
	private Boolean isUse = false;

	@Enumerated
	@Column(name = "ad_type", nullable = false)
	private AdType adType;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getOssKey() {
		return ossKey;
	}

	public void setOssKey(String ossKey) {
		this.ossKey = ossKey;
	}

	public Boolean getIsUse() {
		return isUse;
	}

	public void setIsUse(Boolean isUse) {
		this.isUse = isUse;
	}

	public AdType getAdType() {
		return adType;
	}

	public void setAdType(AdType adType) {
		this.adType = adType;
	}
}
