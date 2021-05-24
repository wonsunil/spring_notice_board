package com.example.demo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class Function {
	public void alert(String str, String location, HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>alert('"+str+"');</script>");
		this.location(location, out);
		out.flush();
	};
	
	public void location(String location, PrintWriter out) {
		out.println("<script>location.href='"+location+"'</script>");
	};
}
