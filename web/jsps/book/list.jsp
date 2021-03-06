<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>图书列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
	body {
		font-size: 10pt;
	}
	.icon {
		margin:10px;
		border: solid 2px gray;
		width: 160px;
		height: 180px;
		text-align: center;
		float: left;
	}
</style>
  </head>
  
  <body>
  <%--bookname是一个定义的变量,allbook是key值,通过key循环遍历--%>
  <%--用变量bookname 调用图片和名--%>

  <%--全部分类和分类查询 (从服务器得到,再循环遍历显示出来)
      虽然方法不同,但是都是循环显示,所以不用写两次循环遍历--%>

  <%--没想到:点击书或文字跳转,传参数bid到servlet--%>
  <c:forEach var="bookname" items="${allbook}">
      <div class="icon">
      <a href="<c:url value='/books?method=findOne&bid=${bookname.bid}'/>"><img src="<c:url value='${bookname.image}'/>" border="0"/></a>
      <br/>
      <a href="<c:url value='/books?method=findOne&bid=${bookname.bid}'/>">${bookname.bname}</a>
      </div>
  </c:forEach>


  <%--<div class="icon">--%>
    <%--<a href="<c:url value='/jsps/book/desc.jsp'/>"><img src="<c:url value='/book_img/8758723-1_l.jpg'/>" border="0"/></a>--%>
      <%--<br/>--%>
   	<%--<a href="<c:url value='/jsps/book/desc.jsp'/>">Java开发详解</a>--%>
  <%--</div>--%>
  <%--<div class="icon">--%>
    <%--<a href="<c:url value='/jsps/book/desc.jsp'/>"><img src="<c:url value='/book_img/8991366-1_l.jpg'/>" border="0"/></a>--%>
      <%--<br/>--%>
   	<%--<a href="<c:url value='/jsps/book/desc.jsp'/>">Java开发详解</a>--%>
  <%--</div>--%>
   <%--<div class="icon">--%>
    <%--<a href="<c:url value='/jsps/book/desc.jsp'/>"><img src="<c:url value='/book_img/9265169-1_l.jpg'/>" border="0"/></a>--%>
      <%--<br/>--%>
   	<%--<a href="<c:url value='/jsps/book/desc.jsp'/>">Java开发详解</a>--%>
  <%--</div>--%>
   <%--<div class="icon">--%>
    <%--<a href="<c:url value='/jsps/book/desc.jsp'/>"><img src="<c:url value='/book_img/9317290-1_l.jpg'/>" border="0"/></a>--%>
      <%--<br/>--%>
   	<%--<a href="<c:url value='/jsps/book/desc.jsp'/>">Java开发详解</a>--%>
  <%--</div>--%>
   <%--<div class="icon">--%>
    <%--<a href="<c:url value='/jsps/book/desc.jsp'/>"><img src="<c:url value='/book_img/20029394-1_l.jpg'/>" border="0"/></a>--%>
      <%--<br/>--%>
   	<%--<a href="<c:url value='/jsps/book/desc.jsp'/>">Java开发详解</a>--%>
  <%--</div>--%>
   <%--<div class="icon">--%>
    <%--<a href="<c:url value='/jsps/book/desc.jsp'/>"><img src="<c:url value='/book_img/20285763-1_l.jpg'/>" border="0"/></a>--%>
      <%--<br/>--%>
   	<%--<a href="<c:url value='/jsps/book/desc.jsp'/>">Java开发详解</a>--%>
  <%--</div>--%>
   <%--<div class="icon">--%>
    <%--<a href="<c:url value='/jsps/book/desc.jsp'/>"><img src="<c:url value='/book_img/20385925-1_l.jpg'/>" border="0"/></a>--%>
      <%--<br/>--%>
   	<%--<a href="<c:url value='/jsps/book/desc.jsp'/>">Java开发详解</a>--%>
  <%--</div>--%>
   <%--<div class="icon">--%>
    <%--<a href="<c:url value='/jsps/book/desc.jsp'/>"><img src="<c:url value='/book_img/22722790-1_l.jpg'/>" border="0"/></a>--%>
      <%--<br/>--%>
   	<%--<a href="<c:url value='/jsps/book/desc.jsp'/>">Java开发详解</a>--%>
  <%--</div>--%>
   <%--<div class="icon">--%>
    <%--<a href="<c:url value='/jsps/book/desc.jsp'/>"><img src="<c:url value='/book_img/22788412-1_l.jpg'/>" border="0"/></a>--%>
      <%--<br/>--%>
   	<%--<a href="<c:url value='/jsps/book/desc.jsp'/>">Java开发详解</a>--%>
  <%--</div>--%>

  
  </body>
 
</html>

