<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.springbook.biz.board.impl.BoardDAO" %>
<%@ page import="com.springbook.biz.board.BoardVO" %>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<title> 글 목록</title>
<script src="js/jquery-3.6.0.min.js"></script>
</head>
<body>
	<center>
		<h1>글 목록</h1>
		<h3>${user.name }님 환영합니다...<a href="logout.do">Log-out</a></h3>
		
		<!-- 검색시작 -->
		<form id="form_search">
			<table border="1" cellpadding="0" cellspacing="0" width="700">
				<tr>
					<td align="right">
						<select name="searchCondition">
							<c:forEach var="condition" items="${conditionMap}">
								<option value="${condition.value}">${condition.key}
							</c:forEach>
						</select>
						<input name="searchKeyword" type="text">
						<input id="search_btn" type="button" value="검색">
					</td>
				</tr>
			</table>
		</form>
		<!-- 검색 종료 -->
		
		<table border="1" cellpadding="0" cellspacing="0" width="700" id="tblresult">
			<tr>
				<th bgcolor="orange" width="100">번호</th>
				<th bgcolor="orange" width="200">제목</th>
				<th bgcolor="orange" width="150">작성자</th>
				<th bgcolor="orange" width="150">등록일</th>
				<th bgcolor="orange" width="100">조회수</th>
			</tr>
			
			<c:forEach var="board" items="${boardList }">
				<tr>
					<td>${board.seq }</td>
					<td align="left"><a href="getBoard.do?seq=${board.seq }">${board.title }</a></td>
					<td>${board.writer }</td>
					<td>${board.regDate }</td>
					<td>${board.cnt }</td>
				</tr>
			</c:forEach>
		</table>
		<br>
		<a href="insertBoard.jsp">새글 등록</a>
	</center>
<script>
	$(document).ready(function(){
		$("#search_btn").click(function(){
			$.ajax({
				url : 'searchBoardList.do',
				type : 'POST',
				//보낼 데이터
				data : $("#form_search").serialize(),
				success : function(response){
					var data = JSON.parse(response);
					console.log(data);
					var htmlString = "";
					htmlString += "<tr>";
					htmlString += '		<th bgcolor="orange" width="100">번호</th>';
					htmlString += '		<th bgcolor="orange" width="100">제목</th>';
					htmlString += '		<th bgcolor="orange" width="100">작성자</th>';
					htmlString += '		<th bgcolor="orange" width="100">등록일</th>';
					htmlString += '		<th bgcolor="orange" width="100">조회수</th>';
					htmlString +="</tr>";
					for(var i=0; i<data.boardList.length; i++){
						htmlString +="<tr>";
						htmlString +="	<td>"+data.boardList[i].seq + "</td>";
						htmlString +="	<td>";
						htmlString +="		<a href='getBoard.do?seq=" + data.boardList[i].seq + "'>" + data.boardList[i].title + "</a>";
						htmlString +="	</td>";
						htmlString +="	<td>"+data.boardList[i].writer+"</td>";
						htmlString +="	<td>"+data.boardList[i].regDate+"</td>";
						htmlString +="	<td>"+data.boardList[i].cnt+"</td>";
						htmlString +="</tr>";
					}
					$("#tblresult").html(htmlString);
				},
				error : function(e){
					console.log(e);
				}
			});
		});
	});
</script>
</body>
</html>