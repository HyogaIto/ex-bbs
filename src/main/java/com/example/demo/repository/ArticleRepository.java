package com.example.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
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
		String sql="select id,name,content form "+TABLE_NAME+" order by name;";
		List<Article> articleList=template.query(sql, ARTICLE_ROW_MAPPER);
		return articleList;
		
	}
}
