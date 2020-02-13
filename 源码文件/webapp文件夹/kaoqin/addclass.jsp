<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<meta charset="utf-8">
		<title></title>
		<link rel="stylesheet" type="text/css" href="css/bootstrap.css"/>
		<script src="tool/jquery-1.11.3.js" type="text/javascript" charset="utf-8"></script>
		<style type="text/css">
			span{
				margin-top: 15px;
			}
			button{
				margin-top: 10px;
			}
			span.chakan{
				text-decoration: underline;
			}
		</style>
	</head>
	<body>
	<form action="class" method="post">
		<input type="hidden" name="method" value="addClass">
		<div class="container">
			<h3 class="text-center text-success">添加班级</h3>
			<span class="col-xs-12 text-center">学院：${gname}</span>
			<hr >
			<span class="col-xs-12 text-center">请选择专业</span>
			<select name="subid" class="form-control">
				<c:forEach items="${allsub }" var="sub" varStatus="statu">
				<option value="${sub.subid}">${sub.subname}</option>
				</c:forEach>
			</select>
			<span class="col-xs-7 text-right">请输入班级代码</span><span class="col-xs-5 text-info chakan">查看班级代码</span>
			<input type="text" name="classid" id="classid" placeholder="请输入班级代码（如101）" class="form-control"/>
			<span class="col-xs-12 text-center">请输入班级名</span>
			<input type="text" name="classname" id="classname" placeholder="请输入班级名" class="form-control"/>
			<span class="col-xs-12 text-center">请输入届数</span>
			<input type="text" name="year" id="year" placeholder="请输入届数(如2019)" class="form-control"/>
			<button class="btn btn-success btn-block">添加班级</button>
			<button type="button" class="btn btn-danger btn-block" onclick="history.go(-1)">返回</button>
		</div>
	</form>
		<script type="text/javascript">
			$(function(){
				console.log(${allsub});
				$('span.chakan').on('click',function(){
					var subid = $('select[name=subid]').val();
					$.post('class','method=showclass&subid='+subid,function(data){
						alert(data);
					})
				})
			})
		</script>
	</body>
</html>
