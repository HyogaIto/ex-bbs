package com.example.demo.form;

import javax.validation.constraints.NotBlank;

/**
 * 記事投稿フォーム.
 * 
 * @author hyoga.ito
 *
 */

public class ArticleForm {
	/** 投稿者名 */
	@NotBlank(message="名前は必須です")
	private String name;
	/** 投稿内容 */
	@NotBlank(message="内容は必須です")
	private String content;

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

	@Override
	public String toString() {
		return "AtricleForm [name=" + name + ", content=" + content + "]";
	}

}
