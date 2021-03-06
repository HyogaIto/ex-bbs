package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.Article;
import com.example.demo.domain.Comment;
import com.example.demo.form.ArticleForm;
import com.example.demo.form.CommentForm;
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

	@ModelAttribute
	public ArticleForm setUpArticleForm() {
		return new ArticleForm();
	}

	@ModelAttribute
	public CommentForm setUpCommentForm() {
		return new CommentForm();
	}

	/**
	 * 掲示板画面を表示する.
	 * 
	 * @return 掲示板画面
	 */
	@RequestMapping("")
	public String index(Model model) {

		List<Article> articles = articleRepository.findAllArticleAndComment();
		
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
	public String insertArticle(@Validated ArticleForm form, BindingResult result,Model model) {
		if (result.hasErrors()) {
			return index(model);
		}
		Article article = new Article();
		BeanUtils.copyProperties(form, article);
		articleRepository.insert(article);
		return "redirect:/bbs/";
	}

	/**
	 * コメント投稿を行う.
	 * 
	 * @param form 投稿したいコメントの内容
	 * @return リダイレクト：掲示板画面
	 */
	@RequestMapping("insertComment")
	public String insertComment(@Validated CommentForm form, BindingResult result,Model model) {
		if (result.hasErrors()) {
			return index(model);
		}
		Comment comment = new Comment();
		BeanUtils.copyProperties(form, comment);
		comment.setArticleId(Integer.parseInt(form.getArticleId()));
		commentRepository.insert(comment);
		return "redirect:/bbs/";
	}

	/**
	 * 記事を削除する.
	 * 
	 * @param articleId 記事ID（hidden）
	 * @return リダイレクト：掲示板画面
	 */
	@RequestMapping("deleteArticle")
	public String deleteArticle(String articleId) {
		// commentRepository.deleteByArticleId(Integer.parseInt(articleId));
		articleRepository.deleteById(Integer.parseInt(articleId));

		return "redirect:/bbs/";

	}

}
