package com.gestiondeprojet.service;

import java.util.List;

import com.gestiondeprojet.Dao.MemberDao;
import com.gestiondeprojet.Enteties.Member;

public class MemberService {
	  private final MemberDao memberDao;

	    public MemberService() {
	        this.memberDao = new MemberDao();
	    }

	    public List<Member> getAllMembers(int offset, int limit) {
	        return memberDao.getAllMembers(offset, limit);
	    }

	    public Member getMemberById(int id) {
	        return memberDao.getMemberById(id);
	    }

	    public void addMember(Member member) {
	        memberDao.addMember(member);
	    }

	    public void updateMember(Member member) {
	        memberDao.updateMember(member);
	    }

	    public void deleteMember(int id) {
	        memberDao.deleteMember(id);
	    }
	    
	    public List<Member> getMembresByEquipe(int equipeId, int page, int pageSize) {
	        return memberDao.getMembresByEquipe(equipeId, page, pageSize);
	    }

	    
	    public int getTotalMembresCountByEquipe(int equipeId) {
	        return memberDao.getTotalMembresCountByEquipe(equipeId);
	    }

}
