package com.example.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Article;

/**
 * 記事情報を操作するリポジトリ.
 * 
 * @author hyoga.ito
 *
 */
@Repository
public class ArticleRepository {
	
	/**	SQLの結果をArticleオブジェクトに入れるローマッパー */
	private final RowMapper<Article> ARTICLE_ROW_MAPPER=(rs,i)->{
		Article article=new Article();
		article.setId(rs.getInt("id"));
		article.setName(rs.getString("name"));
		article.setContent(rs.getString("content"));
		return article; 
	};
	
	/**	SQL実行用変数 */
	@Autowired
	NamedParameterJdbcTemplate template;
	
	/**	テーブル名 */
	private final String TABLE_NAME="articles";
	
	/**
	 * 記事情報の全件検索を行う.
	 * 
	 * @return 全件記事リスト
	 */
	public List<Article> findAll(){
		String sql="select id,name,content from "+TABLE_NAME+" order by id desc;";
		List<Article> articleList=template.query(sql, ARTICLE_ROW_MAPPER);
		return articleList;
		
	}
	
	
	/**
	 * 記事をテーブルに追加する.
	 * 
	 * @param article 追加したい記事情報
	 */
	public void insert(Article article) {
		String sql="insert into "+TABLE_NAME+"(name,content) values(:name,:content);";
		SqlParameterSource param=new MapSqlParameterSource("name",article.getName()).addValue("content", article.getContent());
		template.update(sql, param);
	}
}
