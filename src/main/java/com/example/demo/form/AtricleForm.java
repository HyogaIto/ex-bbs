package com.example.demo.form;

/**
 * 記事投稿フォーム.
 * 
 * @author hyoga.ito
 *
 */
public class AtricleForm {
	/** 投稿者名 */
	private String name;
	/** 投稿内容 */
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
