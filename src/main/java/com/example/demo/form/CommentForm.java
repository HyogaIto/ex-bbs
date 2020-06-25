package com.example.demo.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 入力されたコメント情報フォーム.
 * 
 * @author hyoga.ito
 *
 */
public class CommentForm {
	/** 記事ID */
	private String articleId;
	/** コメント者名 */
	@NotBlank(message = "名前は必須です")
	@Size(max = 30, message = "名前は30文字以内で入力してください")
	private String name;
	/** コメント内容 */
	@NotBlank(message = "コメントは必須です")
	private String content;

	public int getIntArticleId() {
		return Integer.parseInt(articleId);
	}

	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
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

	@Override
	public String toString() {
		return "CommentForm [articleId=" + articleId + ", name=" + name + ", content=" + content + "]";
	}

}
