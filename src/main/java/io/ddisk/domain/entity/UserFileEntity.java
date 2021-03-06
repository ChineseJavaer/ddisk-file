package io.ddisk.domain.entity;

import io.ddisk.utils.UUIDUtil;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/** 用户文件
 * @Author: Richard.Lee
 * @Date: created by 2021/2/20
 */
@Data
@Entity
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@Table(name = "user_file")
public class UserFileEntity implements Serializable {

	/**
	 * 用户文件id
	 */
	@Id
	@Column(name = "id", length = 32)
	private String id = UUIDUtil.random32();

	/**
	 * 所属用户ID
	 */
	@Column(name = "user_id", nullable = false)
	private Long userId;

	/**
	 * 用户文件名
	 */
	@Column(name = "file_name", nullable = false)
	private String filename;

	/**
	 * 扩展名
	 */
	@Column(name = "extension")
	private String extension;

	/**
	 * 所在目录，根目录为null
	 */
	@Column(name = "pid", length = 32)
	private String pid;

	/**
	 * 真正的文件，当为文件夹是，此处为Null
	 */
	@Column(name="file_id")
	private String fileId;

	/**
	 * 用户文件最后更新时间
	 */
	@Column(name = "update_time", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTime;

	/**
	 * 用户文件创建时间
	 */
	@Column(name = "create_time", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;

	/**
	 * 是否删除
	 */
	@Column(name = "is_delete", nullable = false)
	private Boolean delete;

	@Column(name = "is_dir", nullable = false)
	private Boolean dir;

	@Column(name = "delete_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date deleteTime;

	/**
	 * 创建新建私有的文件
	 * @param userId    用户ID
	 * @param filename      文件名
	 * @param extension  文件扩展名
	 * @param pid       父目录ID
	 * @param fileId  文件实体id
	 */
	public UserFileEntity(Long userId, String filename, String extension, String pid, String fileId) {
		this.userId = userId;
		this.filename = filename;
		this.extension = extension;
		this.pid = pid;
		this.fileId = fileId;
		this.updateTime = new Date();
		this.createTime = new Date();
		this.delete = false;
		this.dir = false;
		this.deleteTime = null;
	}

	/**
	 * 创建新建私有的文件夹
	 * @param userId    用户ID
	 * @param filename      文件夹名
	 * @param pid       父目录ID
	 */
	public UserFileEntity(Long userId, String filename, String pid) {
		this.userId = userId;
		this.filename = filename;
		this.pid = pid;
		this.fileId = null;
		this.extension = null;
		this.updateTime = new Date();
		this.createTime = new Date();
		this.delete = false;
		this.dir = true;
		this.deleteTime = null;
	}
}
