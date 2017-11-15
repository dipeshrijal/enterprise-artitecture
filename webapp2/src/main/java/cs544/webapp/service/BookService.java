package cs544.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cs544.webapp.dao.IBookDao;

@Service
public class BookService {
	
	@Autowired
	private IBookDao bookDao;

}
