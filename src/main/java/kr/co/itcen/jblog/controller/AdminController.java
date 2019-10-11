package kr.co.itcen.jblog.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.itcen.jblog.result.ApiResult;
import kr.co.itcen.jblog.security.MyAuth;
import kr.co.itcen.jblog.service.BlogService;
import kr.co.itcen.jblog.service.CategoryService;
import kr.co.itcen.jblog.service.PostService;
import kr.co.itcen.jblog.vo.BlogVo;
import kr.co.itcen.jblog.vo.CategoryVo;
import kr.co.itcen.jblog.vo.PostVo;
import kr.co.itcen.jblog.vo.UserVo;

@MyAuth
@Controller
@RequestMapping("/{userId:(?!assets).*}/admin")
public class AdminController {
	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private PostService postService;
	
	@GetMapping({"", "/basic"})
	public String basicView(@ModelAttribute @PathVariable String userId, HttpSession session, Model model) {
		// 본인여부 검사 
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		
		if (authUser.getId().equals(userId) == false) {
			return "redirect:/" + userId;
		}
		
		BlogVo blogVo = new BlogVo();
		blogVo.setId(userId);
		blogVo = blogService.selectBlogById(blogVo).getData();
		
		model.addAttribute("blogVo", blogVo);
		
		return "blog/blog-admin-basic";
	}
	
	@PostMapping({"", "/basic"})
	public String basic(@ModelAttribute @PathVariable String userId, BlogVo blogVo, Errors errors, HttpSession session, Model model) {
		// 본인여부 검사
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		
		if (authUser.getId().equals(userId) == false) {
			return "redirect:/" + userId;
		}
		
		blogVo.setId(authUser.getId());
		
		// 유효성 검사
		blogVo.updateValidCheck(errors);
		
		if (errors.hasErrors()) {
			model.addAttribute("blogVo", blogVo);
			return "blog/blog-admin-basic";
		}
		
		ApiResult<BlogVo> apiResult = blogService.updateBlog(blogVo);
		
		// apiResult의 status에 따라 return 분기 처리
		if (!apiResult.isStatus()) {
			errors.rejectValue("title", apiResult.getCode(), apiResult.getMessage());
			return "blog/blog-admin-basic";
		}
		
		return "redirect:/" + blogVo.getId() + "/admin";
	}
	
	// 게시글 등록 페이지
	@GetMapping("/write")
	public String writeView(@ModelAttribute @PathVariable String userId, @ModelAttribute PostVo postVo, HttpSession session, Model model) {
		// 본인확인
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		
		if (authUser.getId().equals(userId) == false) {
			return "redirect:/" + userId;
		}
		
		// blog 정보 세팅
		BlogVo blogVo = new BlogVo();
		blogVo.setId(userId);
		blogVo = blogService.selectBlogById(blogVo).getData();
		
		model.addAttribute("blogVo", blogVo);
		
		// blogId 세팅
		CategoryVo categoryVo = new CategoryVo();
		categoryVo.setBlogId(authUser.getId());
		
		// 카테고리 리스트 조회 및 model 설정
		ApiResult<CategoryVo> categoryResult = categoryService.selectCategoryByBlogId(categoryVo);
		
		if (categoryResult.isStatus()) {
			model.addAttribute("categoryList", categoryResult.getDatas());
		}
		
		return "blog/blog-admin-write";
	}
	
	// 게시글 등록
	@PostMapping("/write")
	public String write(@ModelAttribute @PathVariable String userId, PostVo postVo, Errors errors, HttpSession session, Model model) {
		// 본인확인
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		
		if (authUser.getId().equals(userId) == false) {
			return "redirect:/" + userId;
		}
		
		BlogVo blogVo = new BlogVo();
		blogVo.setId(userId);
		blogVo = blogService.selectBlogById(blogVo).getData();
		
		model.addAttribute("blogVo", blogVo);
		
		// 유효성 검사
		postVo.createValidCheck(errors);
		
		CategoryVo categoryVo = new CategoryVo();
		categoryVo.setBlogId(authUser.getId());
		ApiResult<CategoryVo> categoryResult = null;
		
		if (errors.hasErrors()) {
			// 카테고리 리스트 조회 및 model 설정
//			CategoryVo categoryVo = new CategoryVo();
//			categoryVo.setBlogId(authUser.getId());
			categoryResult = categoryService.selectCategoryByBlogId(categoryVo);
			
			if (categoryResult.isStatus()) {
				model.addAttribute("categoryList", categoryResult.getDatas());
			}
			
			return "blog/blog-admin-write";
		}
		
		// 지정한 카테고리가 본인의 블로그에 속한 카테고리인지 검사
		categoryVo.setNo(postVo.getCategoryNo());
		categoryResult = categoryService.existCheck(categoryVo);
		
		if (!categoryResult.isStatus()) {
			return "redirect:/" + userId;
		}
		
		ApiResult<PostVo> postResult = postService.createPost(postVo);
		
		if (postResult.isStatus()) {
			// 결과에 따라 return 분기, 성공시 redirect blog main, 실패시 errors와 함께 페이지 재 로딩
			return "redirect:/" + userId + "/" + postVo.getCategoryNo() + "/" + postVo.getNo();
		} else {
			return "blog/blog-admin-write";
		}
	}
	
	// 카테고리 관리 페이지
	@GetMapping("/category")
	public String categoryView(@ModelAttribute @PathVariable String userId, HttpSession session, Model model) {
		// 본인확인
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		
		if (authUser.getId().equals(userId) == false) {
			return "redirect:/" + userId;
		}
		
		BlogVo blogVo = new BlogVo();
		blogVo.setId(userId);
		blogVo = blogService.selectBlogById(blogVo).getData();
		
		model.addAttribute("blogVo", blogVo);
		
		// category 조회 및 model 추가
		CategoryVo categoryVo = new CategoryVo();
		categoryVo.setBlogId(authUser.getId());
		
		ApiResult<CategoryVo> apiResult = categoryService.selectWithPostCntByBlogIdAndCategoryNo(categoryVo);
		model.addAttribute("categoryList", apiResult.getDatas());
		
		return "blog/blog-admin-category";
	}
}
