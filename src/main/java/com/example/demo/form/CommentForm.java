package com.example.demo.form;

/**
 * 入力されたコメント情報フォーム.
 * 
 * @author hyoga.ito
 *
 */
public class CommentForm {
	/**	記事ID */
	private String articleId;
	/**	コメント者名 */
	private String name;
	/**	コメント内容 */
	private String content;
	
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
