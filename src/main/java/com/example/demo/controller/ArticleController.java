package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.Article;
import com.example.demo.repository.ArticleRepository;

/**
 * 掲示板画面の表示を行う.
 * 
 * @author hyoga.ito
 *
 */
@Controller
@RequestMapping("/ex-bbs")
public class ArticleController {
	/**	リポジトリの参照を注入 */
	@Autowired
	private ArticleRepository articleRepository;

	/**
	 * 掲示板画面を表示する.
	 * 
	 * @return　掲示板画面
	 */
	@RequestMapping("")
	public String index() {
		return "ex-bbs";
	}

	/**
	 * 記事一覧の表示を行う.
	 * 
	 * @param model リクエストスコープ
	 * @return　掲示板画面
	 */
	@RequestMapping("insertArticle")
	public String insertArticle(Model model) {
		List<Article> articles = articleRepository.findAll();
		model.addAttribute("artcles", articles);
		return index();
	}

	@RequestMapping("insertComment")
	public String insertComment() {
		return index();
	}

	@RequestMapping("deleteArticle")
	public String deleteArticle() {
		return index();
	}

}
