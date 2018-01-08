<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>图书详细</title>
    
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
	div {
		margin:20px;
		border: solid 2px gray;
		width: 150px;
		height: 150px;
		text-align: center;
	}
	li {
		margin: 10px;
	}
	a {
		background: url(<c:url value='/images/all.png'/>) no-repeat;
		display: inline-block;
		
		background-position: 0 -70px;
		margin-left: 30px;
		height: 36px;
		width: 146px;
	}
	a:HOVER {
		background: url(<c:url value='/images/all.png'/>) no-repeat;
		display: inline-block;
		
		background-position: 0 -106px;
		margin-left: 30px;
		height: 36px;
		width: 146px;
	}
</style>
  </head>
  
  <body>
  <%--点击书后跳转到此--%>
  <%--book1是--%>

  <div>
      <img src="<c:url value='${book1.image}'/>" alt="">
  </div>
<ul>
    <li>书名:${book1.bname}</li>
    <li>作者:${book1.author}</li>
    <li>单价:${book1.price}元</li>
</ul>


  <%--<div>--%>
    <%--<img src="<c:url value='/book_img/8758723-1_l.jpg'/>" border="0"/>--%>
  <%--</div>--%>
  <%--<ul>--%>
    <%--<li>书名：Java开发详解</li>--%>
    <%--<li>作者：张孝祥</li>--%>
    <%--<li>单价：39.9元</li>--%>
  <%--</ul>--%>

  <%--点击跳转到购物车--%>
  <form id="form" action="<c:url value='/car'/>" method="post">
  	<input type="text" size="3" name="count" value="1"/>
      <input type="hidden" name="bid" value="${book1.bid}">
      <input type="hidden" name="method" value="addbuybook" >
  </form>
  <a href="javascript:document.getElementById('form').submit();"></a>
  </body>
</html>
