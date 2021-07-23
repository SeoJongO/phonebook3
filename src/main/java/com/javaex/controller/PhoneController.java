package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.PhoneDao;
import com.javaex.vo.PersonVo;

@Controller
@RequestMapping(value = "/pb")
public class PhoneController {
	
	//필드
	@Autowired
	private PhoneDao phoneDao;
	//생성자
	//게터세터
	//메소드 일반

	@RequestMapping(value = "/test")
	public String test() {
		System.out.println("test");

		return "/WEB-INF/views/test.jsp";
		// DispatcherServlet 야 "/WEB-INF/views/test.jsp" 에 포워드 해줘
	}

	@RequestMapping(value = "/read/{no}", method = { RequestMethod.GET, RequestMethod.POST })
	public String read(@PathVariable("no") int no) {
		System.out.println("/read/{" + no + "}");

		return "";
	}

//	=====================================================================================

	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String list(Model model) {
		System.out.println("list");

		// Dao
//		PhoneDao phoneDao = new PhoneDao();

		// Dao method -> data
		List<PersonVo> personList = phoneDao.getPersonList();
		System.out.println(personList);

		model.addAttribute("personList", personList);

		return "/WEB-INF/views/list.jsp";
	}

	@RequestMapping(value = "/writeForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String writeForm() {
		System.out.println("writeForm");

		return "/WEB-INF/views/writeForm.jsp";
	}

	/*
	 * 파라미터가 있을 수도 없을 수도
	 * 
	 * @RequestMapping(value="/write", method= {RequestMethod.GET, RequestMethod.POST}) 
	 * public String write(@RequestParam("name") String name,
	 * 					   @RequestParam("hp") String hp,
	 *                     @RequestParam(value="company", required=false, defaultValue="-1") String company) { 
	 * System.out.println("write"); 
	 * System.out.println(name);
	 * System.out.println(hp); 
	 * System.out.println(company);
	 * 
	 * PersonVo personVo = new PersonVo(name, hp, company);
	 * System.out.println(personVo); // PhoneDao phoneDao = new PhoneDao(); //
	 * phoneDao.personInsert(personVo);
	 * 
	 * return "/WEB-INF/views/list.jsp"; }
	 */

//	/* 파라미터 알아서 담아줌
	@RequestMapping(value = "/write", method = { RequestMethod.GET, RequestMethod.POST })
	public String write(@ModelAttribute PersonVo personVo /* , @RequestParam("name") String name */) {
		System.out.println("write");

		// @ModelAttribute -> new PersonVo()
		// -> 기본생성자 + setter

		System.out.println(personVo);
//		System.out.println(name);

//		PhoneDao phoneDao = new PhoneDao();
		phoneDao.personInsert(personVo);

		return "redirect:/pb/list";
	}
//	*/

//수정폼
	@RequestMapping(value = "/updateForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String updateForm(@RequestParam("personId") int id, Model model) {
		System.out.println("수정폼");
		
//		PhoneDao phoneDao = new PhoneDao();
		
		PersonVo personVo = phoneDao.getPerson(id);
		
		model.addAttribute("personVo", personVo);

		return "/WEB-INF/views/updateForm.jsp";
	}

//수정
//	@RequestMapping(value = "/update", method = { RequestMethod.GET, RequestMethod.POST })
//	public String update(@RequestParam("name") String name,
//					   	 @RequestParam("hp") String hp,
//						 @RequestParam("company") String company,
//			             @RequestParam("personId") String id) {
//		System.out.println("수정");
//		
//		PhoneDao phoneDao = new PhoneDao();
//		
//		PersonVo personVo = new PersonVo(name, hp, company, id);
//		
//		phoneDao.personUpdate(personVo);
//
//		return "redirect:/pb/list";
//	}
	
	@RequestMapping(value = "/update", method = { RequestMethod.GET, RequestMethod.POST })
	public String update(@ModelAttribute PersonVo personVo, @RequestParam("id") int id) {
		System.out.println("수정");
		
//		PhoneDao phoneDao = new PhoneDao();
		
		System.out.println(personVo);
		personVo.setPersonId(id);
		phoneDao.personUpdate(personVo);

		return "redirect:/pb/list";
	}

//삭제
	@RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public String delete(@RequestParam("personId") int id) {
		System.out.println("삭제");
		
//		PhoneDao phoneDao = new PhoneDao();
		
		phoneDao.personDelete(id);
		
		return "redirect:/pb/list";

	}
}