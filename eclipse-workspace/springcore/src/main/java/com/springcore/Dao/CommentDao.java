package com.springcore.Dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;

import com.springcore.entity.Comment;

public class CommentDao {
	@Autowired
	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	public List<Comment> getComment(Comment c){
		List<Comment> ans= new ArrayList<>();
		try {
			ans=hibernateTemplate.findByExample(c);
		}catch (Exception e) {
			System.out.println("some error has occured: "+e.getLocalizedMessage() );
			return null;
		}
		
		return ans;
	}
	
	public boolean deleteComment(Comment c) {
		try {
			hibernateTemplate.delete(c);
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("could not delete the comment due to some error");
			return false;
		}
		return true;
	}
	
	public Comment getCommentById(int id) {
		Comment c;
		try {
			c=hibernateTemplate.get(Comment.class, id);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("some error has occured");
			return null;
		}
		return c;
	}
}
