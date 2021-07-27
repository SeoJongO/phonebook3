package com.javaex.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.PhoneDao;
import com.javaex.vo.PersonVo;

@Controller
public class PhoneController {

	// 필드
	@Autowired
	private PhoneDao phoneDao;
	// 생성자
	// 게터세터
	// 메소드 일반

	// 리스트
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String list(Model model) {
		System.out.println("list");

		List<PersonVo> personList = phoneDao.getPersonList();

		model.addAttribute("personList", personList);

		return "list";
	}

	// 등록폼
	@RequestMapping(value = "/writeForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String writeForm() {
		System.out.println("writeForm");

		return "writeForm";
	}

	// 등록
	@RequestMapping(value = "/write", method = { RequestMethod.GET, RequestMethod.POST })
	public String write(@ModelAttribute PersonVo personVo) {
		System.out.println("write");

		phoneDao.personInsert(personVo);

		return "redirect:/list";
	}
	
	@RequestMapping(value = "/write2", method = { RequestMethod.GET, RequestMethod.POST })
	public String write2(@RequestParam("name") String name,
						 @RequestParam("hp") String hp,
						 @RequestParam("company") String company) {
		System.out.println("write2");
		
		int count = phoneDao.personInsert2(name, hp, company);
		
		return "redirect:/list";
	}

	// 삭제
	@RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public String delete(@RequestParam("personId") int id) {
		System.out.println("delete");

		phoneDao.personDelete(id);

		return "redirect:/list";

	}

	// 수정폼
	@RequestMapping(value = "/updateForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String updateForm(Model model, @RequestParam("personId") int person_id) {
		System.out.println("updateForm");

		PersonVo personVo = phoneDao.getPerson(person_id);
		
		model.addAttribute("personVo", personVo);
		
		return "updateForm";
	}
	
	// 수정폼2
	@RequestMapping(value = "/updateForm2", method = { RequestMethod.GET, RequestMethod.POST })
	public String updateForm2(Model model, @RequestParam("personId") int person_id) {
		System.out.println("updateForm2");

		Map<String, Object> personMap = phoneDao.getPerson2(person_id);
		
		model.addAttribute("pMap", personMap);
		
		return "updateForm2";
	}

//	// 수정
//	@RequestMapping(value = "/update", method = { RequestMethod.GET, RequestMethod.POST })
//	public String update(@RequestParam("name") String name,
//					   	 @RequestParam("hp") String hp,
//						 @RequestParam("company") String company,
//			             @RequestParam("personId") String id) {
//		System.out.println("수정");
//		
//		PersonVo personVo = new PersonVo(name, hp, company, id);
//		
//		phoneDao.personUpdate(personVo);
//
//		return "redirect:/pb/list";
//	}
//	
//	@RequestMapping(value = "/update", method = { RequestMethod.GET, RequestMethod.POST })
//	public String update(@ModelAttribute PersonVo personVo, @RequestParam("id") int id) {
//		System.out.println("수정");
//		
////		PhoneDao phoneDao = new PhoneDao();
//		
//		System.out.println(personVo);
//		personVo.setPersonId(id);
//		phoneDao.personUpdate(personVo);
//
//		return "redirect:/pb/list";
//	}
//

}