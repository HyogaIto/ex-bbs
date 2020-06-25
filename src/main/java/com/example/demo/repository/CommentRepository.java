package com.example.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Comment;

/**
 * コメント情報を操作するリポジトリ.
 * 
 * @author hyoga.ito
 *
 */
@Repository
public class CommentRepository {

	/**	SQL結果をオブジェクトに入れるローマッパー */
	private final RowMapper<Comment> COMMENT_ROW_MAPPER = (rs, i) -> {
		Comment comment = new Comment();
		comment.setId(rs.getInt("id"));
		comment.setName(rs.getString("name"));
		comment.setContent(rs.getString("content"));
		comment.setArticleId(rs.getInt("article_id"));
		return comment;
	};

	/**　SQL実行用変数　*/
	@Autowired
	NamedParameterJdbcTemplate template;

	/**	テーブル名 */
	private final String TABLE_NAME = "comments";

	/**
	 * 記事IDからコメントを検索する.
	 * 
	 * @param articleId 記事ID
	 * @return　コメント一覧
	 */
	public List<Comment> findByArticleId(Integer articleId) {
		String sql = "select id,name,content,article_id from " + TABLE_NAME + " "
				+ " where article_id=:articleId order by id desc;";
		SqlParameterSource param = new MapSqlParameterSource("articleId", articleId);
		List<Comment> comments = template.query(sql, param, COMMENT_ROW_MAPPER);
		return comments;

	}
	
	/**
	 * コメントを追加する.
	 * 
	 * @param comment　追加したいコメント
	 */
	public void insert(Comment comment) {
		String sql="insert into "+TABLE_NAME+"(name,content,article_id) values(:name,:content,:articleId);";
		SqlParameterSource param=new MapSqlParameterSource("name",comment.getName()).addValue("content", comment.getContent())
				.addValue("articleId", comment.getArticleId());
		template.update(sql, param);
	}
	
	/**
	 * 記事IDからコメントを削除する.
	 * 
	 * @param articleId 記事ID
	 */
	public void deleteById(int articleId) {
		String sql="delete from "+TABLE_NAME+" where article_id=:articleId";
		SqlParameterSource param=new MapSqlParameterSource("articleId",articleId);
		template.update(sql, param);
	}

}
