package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.PersonVo;

@Repository
public class PhoneDao {

	@Autowired
	private SqlSession sqlSession;
	
	public List<PersonVo> getPersonList() {
		
		List<PersonVo> personList = sqlSession.selectList("phonebook.selectList");
		
		return personList;
	}
	
	public int personInsert(PersonVo personVo) {
		
		int count = sqlSession.insert("phonebook.personInsert", personVo);
		
		return 1;
	}
	
	public int personInsert2(String name, String hp, String company) {
		
		Map<String, Object> personMap = new HashMap<String, Object>();
		
		personMap.put("name", name);
		personMap.put("hp", hp);
		personMap.put("company", company);
		
		System.out.println(personMap);
		
		int count = sqlSession.insert("phonebook.personInsert2", personMap);
		
		System.out.println(count);
		
		return 1;
	}
	
	public int personDelete(int person_id) {
		
		int count = sqlSession.delete("phonebook.personDelete", person_id);
		
		return 1;
	}
	
	public PersonVo getPerson(int person_id) {
		
		PersonVo personVo = sqlSession.selectOne("phonebook.selectPerson", person_id);
		System.out.println(personVo);
		return personVo;
	}
	
	public Map<String, Object> getPerson2(int person_id) {
		
		Map<String, Object> personMap = sqlSession.selectOne("phonebook.selectPerson2", person_id);
//		System.out.println(personMap.get("PERSON_ID"));
		
		return personMap;
	}
	
	public void personUpdate(String name, String hp, String company) {
		
		Map<String, Object> personMap = new HashMap<String, Object>();
		
		personMap.put("name", name);
		personMap.put("hp", hp);
		personMap.put("company", company);
		
		int count = sqlSession.update("phonebook.personUpdate", personMap);
		
		sqlSession.update("phonebook.personUpdate", personMap);
	}
	

}
