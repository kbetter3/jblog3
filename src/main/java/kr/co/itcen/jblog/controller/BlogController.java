package kr.co.itcen.jblog.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.itcen.jblog.result.ApiResult;
import kr.co.itcen.jblog.service.BlogService;
import kr.co.itcen.jblog.service.CategoryService;
import kr.co.itcen.jblog.service.PostService;
import kr.co.itcen.jblog.service.UserService;
import kr.co.itcen.jblog.vo.BlogVo;
import kr.co.itcen.jblog.vo.CategoryVo;
import kr.co.itcen.jblog.vo.PostVo;
import kr.co.itcen.jblog.vo.UserVo;

@Controller
@RequestMapping("/{userId:(?!assets).*}")
public class BlogController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private PostService postService;
	
	@GetMapping({"", "/{categoryId}", "/{categoryId}/{postId}"})
	public String blogView(@ModelAttribute @PathVariable String userId, @PathVariable Optional<Integer> categoryId, @PathVariable Optional<Integer> postId, Model model) {
		PostVo postVo = new PostVo();
		CategoryVo categoryVo = new CategoryVo();
		BlogVo blogVo = new BlogVo();
		UserVo userVo = new UserVo();
		
		// 존재하는 사용자인지 조회
		userVo.setId(userId);
		ApiResult<UserVo> userResult = userService.idDuplicationCheck(userVo);
		
		if (userResult.isStatus()) {
			// id가 중복이이면 false 중복이 아니면 true -> 없는 유저
			return "redirect:/";
		}
		
		// blog 정보 조회
		blogVo.setId(userId);
		ApiResult<BlogVo> blogResult = blogService.selectBlogById(blogVo);
		
		if (!blogResult.isStatus()) {
			return "redirect:/" + userId;
		}
		
		model.addAttribute("blogVo", blogResult.getData());
		
		// category 조회
		categoryVo.setBlogId(userId);
		ApiResult<CategoryVo> categoryResult = categoryService.selectCategoryByBlogId(categoryVo);
		List<CategoryVo> categoryList = categoryResult.getDatas();
		
		// categoryId 분기처리 및 유효성검사
		if (categoryId.isPresent()) {
			CategoryVo filteredCategoryVo = categoryList.stream()
					.filter(category -> category.getNo() == categoryId.get().intValue())
					.findAny()
					.orElse(null)
			;
			
			if (filteredCategoryVo == null) {
				return "redirect:/" + userId;
			}
			
			categoryVo.setNo(categoryId.get());
		} else {
			// category는 null값일 수 없음
			categoryVo = categoryList.get(0);
		}
		
		model.addAttribute("categoryList", categoryList);
		
		// post list 조회
		postVo.setCategoryNo(categoryVo.getNo());
		ApiResult<PostVo> postListResult = postService.selectListByCategoryNo(postVo);
		
		model.addAttribute("postList", postListResult.getDatas());
		
		// postId 분기처리
		if (postId.isPresent()) {
			postVo.setNo(postId.get());
		} else if (!postListResult.getDatas().isEmpty()) {
			postVo.setNo(postListResult.getDatas().get(0).getNo());
		}
		
		// path variable에 따라 분기
		ApiResult<PostVo> postResult = postService.selectByNoAndCategoryNo(postVo);
		
		model.addAttribute("postVo", postResult.getData());
		
		return "blog/blog-main";
	}
}
