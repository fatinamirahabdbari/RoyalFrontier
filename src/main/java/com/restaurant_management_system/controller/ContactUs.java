/*
 * Online DB : OK
 */

package com.restaurant_management_system.controller;

import com.restaurant_management_system.beans.Contact;
import com.restaurant_management_system.model.ContactUsDB;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
///import java.time.LocalDateTime; 

public class ContactUs extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ContactUs() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/jsp/contactUs.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String subject = request.getParameter("subject");
		String message = request.getParameter("message");

		DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		String now = date.format(LocalDateTime.now());
		LocalDateTime dateTime = LocalDateTime.parse(now, date);

		Contact contact = new Contact();
		contact.setName(name);
		contact.setEmail(email);
		contact.setSubject(subject);
		contact.setMessage(message);
		contact.setDate(dateTime);

		ContactUsDB contactUsDB = new ContactUsDB();
		String result = contactUsDB.messageContact(contact);

		request.getSession().setAttribute("result", result);

		response.sendRedirect("jsp/ContactUs.jsp");
	}

}


