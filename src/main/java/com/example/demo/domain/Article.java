package com.example.demo.domain;

import java.util.List;

/**
 * 記事情報が入るドメイン.
 * 
 * @author hyoga.ito
 *
 */
public class Article {
	/** 記事ID */
	private Integer id;
	/** 投稿者名 */
	private String name;
	/** 投稿内容 */
	private String content;
	/** コメント一覧 */
	private List<Comment> commentList;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<Comment> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", name=" + name + ", content=" + content + ", commentList=" + commentList + "]";
	}

}
