package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.Article;
import com.example.demo.form.ArticleForm;
import com.example.demo.repository.ArticleRepository;
import com.example.demo.repository.CommentRepository;

/**
 * 掲示板画面の表示を行う.
 * 
 * @author hyoga.ito
 *
 */
@Controller
@RequestMapping("/bbs")
public class ArticleController {
	/** 記事リポジトリの参照を注入 */
	@Autowired
	private ArticleRepository articleRepository;

	/** コメントリポジトリの参照を注入 */
	@Autowired
	private CommentRepository commentRepository;

	/**
	 * 掲示板画面を表示する.
	 * 
	 * @return 掲示板画面
	 */
	@RequestMapping("")
	public String index(Model model) {
		List<Article> articles = articleRepository.findAll();

		for (Article article : articles) {
			article.setCommentList(commentRepository.findByArticleId(article.getId()));
		}

		model.addAttribute("articles", articles);
		return "ex-bbs";
	}

	/**
	 * 記事投稿を行う.
	 * 
	 * @param form 投稿したい記事の内容
	 * @return リダイレクト：掲示板画面
	 */
	@RequestMapping("insertArticle")
	public String insertArticle(ArticleForm form) {
		Article article = new Article();
		BeanUtils.copyProperties(form, article);
		articleRepository.insert(article);
		return "redirect:/bbs/";
	}

	@RequestMapping("insertComment")
	public String insertComment() {
		return null;
	}

	@RequestMapping("deleteArticle")
	public String deleteArticle() {
		return null;
	}

}
