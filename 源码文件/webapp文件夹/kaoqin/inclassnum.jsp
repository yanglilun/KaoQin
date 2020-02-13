<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<meta charset="utf-8">
		<link rel="stylesheet" type="text/css" href="css/bootstrap.css"/>
		<link rel="stylesheet" type="text/css" href="css/inclassnum.css"/>
		<script src="tool/jquery-1.11.3.js" type="text/javascript" charset="utf-8"></script>
		<title></title>
	</head>
	<body>
		<div class="container">
			<h3 class="text-center text-info">班内编号</h3>
			<p class="text-center">${cname }</p>
			<p class="text-danger text-center">若班内编号重复，请单击该学生以修改</p>
			<hr >
			<button type="button" class="btn btn-danger col-xs-6" id="return">返回</button>
			<button type="button" class="btn btn-info col-xs-6" id="reset">重置编号</button>
			<table class="table text-center table-striped">
				<tr>
					<th class="text-center">学号</th>
					<th class="text-center">姓名</th>
					<th class="text-center">班内编号</th>
				</tr>
				<c:forEach items="${allstu }" var="s" varStatus="stat">
					<tr>
						<td>${s.sid }</td>
						<td>${s.sname }</td>
						<td>${s.num }</td>
					</tr>
				</c:forEach>
					
					
			</table>
			
		</div>
		<script type="text/javascript">
			$(function(){
				//返回上一级
				$('#return').on('click',function(){
					history.go(-1);
				})
				//重置编号
				$('#reset').on('click',function(){
					if(confirm('确认重置班内编号？')){
						$.post('stu','method=initnum',function(data){
							//刷新页面
							alert(data);
							$.post('stu','method=shownum',function(data){
								window.location=data;
							})
						})						
					}
				})
				
				$('tr').on('click',function(){
					// 获取学号
					var sid = $(this).find('td')[0].innerText;
					// 获取姓名
					var sname = $(this).find('td')[1].innerText;
					// 班内编号
					var num = $(this).find('td')[2].innerText;
					// 输入新的编号
					var newnum = prompt('设置 '+sname+' 新的班内编号');
					if(newnum!=null){
						$.post('stu','method=updatenum&sid='+sid+'&num='+newnum,function(data){
							if(data=='true'){
								alert('修改成功!');
								//刷新页面
								$.post('stu','method=shownum',function(data){
									window.location=data;
								})
							}
						})
					}
				})
			})
		</script>
	</body>
</html>
