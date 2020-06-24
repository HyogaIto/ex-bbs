package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.Article;
import com.example.demo.form.ArticleForm;
import com.example.demo.repository.ArticleRepository;

/**
 * 掲示板画面の表示を行う.
 * 
 * @author hyoga.ito
 *
 */
@Controller
@RequestMapping("/bbs")
public class ArticleController {
	/** リポジトリの参照を注入 */
	@Autowired
	private ArticleRepository articleRepository;


	/**
	 * 掲示板画面を表示する.
	 * 
	 * @return 掲示板画面
	 */
	@RequestMapping("")
	public String index(Model model) {
		List<Article> articles = articleRepository.findAll();
		List<Article> newOrderAticles=new ArrayList<>();
		for(int i=0;i<articles.size();i++) {
			newOrderAticles.add(i,articles.get(articles.size()-(i+1)));
		}
		model.addAttribute("articles", newOrderAticles);
		return "ex-bbs";
	}

	/**	 */
	@RequestMapping("insertArticle")
	public String insertArticle(ArticleForm form){
		Article article=new Article();
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
