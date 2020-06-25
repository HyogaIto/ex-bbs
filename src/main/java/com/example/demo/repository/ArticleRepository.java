package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Article;
import com.example.demo.domain.Comment;

/**
 * 記事情報を操作するリポジトリ.
 * 
 * @author hyoga.ito
 *
 */
@Repository
public class ArticleRepository {

	/** SQLの結果をArticleオブジェクトに入れるローマッパー */
	private final RowMapper<Article> ARTICLE_ROW_MAPPER = (rs, i) -> {
		Article article = new Article();
		article.setId(rs.getInt("id"));
		article.setName(rs.getString("name"));
		article.setContent(rs.getString("content"));
		return article;
	};

	/** SQLの結果をArticleオブジェクトに入れる */
	private final ResultSetExtractor<List<Article>> ARTICLE_AND_COMMENT_RS_EXTRACTOR = (rs) -> {

		List<Article> articles = new ArrayList<>();
		List<Comment> comments = null;
		Article article = null;

		int beforeArticleId = -1;

		while (rs.next()) {
			int nowArticleId = rs.getInt("a_id");

			if (beforeArticleId != nowArticleId) {
				article = new Article();
				article.setId(rs.getInt("a_id"));
				article.setName(rs.getString("a_name"));
				article.setContent(rs.getString("a_content"));
				comments = new ArrayList<>();
				article.setCommentList(comments);

				articles.add(article);
			}

			if (rs.getInt("com_id") != 0) {
				Comment comment = new Comment();
				comment.setId(rs.getInt("com_id"));
				comment.setName(rs.getString("com_name"));
				comment.setContent(rs.getString("com_content"));
				comment.setArticleId(rs.getInt("article_id"));
				comments.add(comment);
			}

			beforeArticleId = nowArticleId;
		}

		return articles;

	};

	/** SQL実行用変数 */
	@Autowired
	NamedParameterJdbcTemplate template;

	/** テーブル名 */
	private final String TABLE_NAME = "articles";

	/**
	 * 記事情報の全件検索を行う.
	 * 
	 * @return 全件記事リスト
	 */
	public List<Article> findAll() {
		String sql = "select id,name,content from " + TABLE_NAME + " order by id desc;";
		List<Article> articleList = template.query(sql, ARTICLE_ROW_MAPPER);
		return articleList;

	}

	public List<Article> findAllArticleAndComment() {
		String sql = "select a.id as a_id,a.name as a_name,a.content as a_content,"
				+ "c.id as com_id,c.name as com_name, c.content as com_content,c.article_id " + "from " + TABLE_NAME
				+ " as a left outer join comments as c "
				+ "on a.id=c.article_id order by a.id desc, c.id desc;";
		List<Article> articleList = template.query(sql, ARTICLE_AND_COMMENT_RS_EXTRACTOR);
		return articleList;

	}

	/**
	 * 記事をテーブルに追加する.
	 * 
	 * @param article 追加したい記事情報
	 */
	public void insert(Article article) {
		String sql = "insert into " + TABLE_NAME + "(name,content) values(:name,:content);";
		SqlParameterSource param = new MapSqlParameterSource("name", article.getName()).addValue("content",
				article.getContent());
		template.update(sql, param);
	}

	/**
	 * 記事をテーブルから削除する.
	 * 
	 * @param id 記事ID
	 */
	public void deleteById(int id) {
		String sql = "WITH comment_delete AS ( DELETE FROM comments" + " where article_id=:id)"
				+ " DELETE FROM articles where id=:id;";
		SqlParameterSource param = new MapSqlParameterSource("id", id);
		template.update(sql, param);
	}
}
