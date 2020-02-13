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
		<style type="text/css">
			div.class{
				height: 30px;
			}
			span.class{
				line-height: 30px;
				font-size: 20px;
				height: 100%;
			}
			select.class{
				height: 100%;
			}
			button{
			margin-top: 5px;
			}
		</style>
	</head>
	<body>
		<div class="container">
			<h3 class="text-center text-info">班级管理</h3>
			<p class="text-center">专业:${subname}</p>
			<p class="text-danger text-center">若班内编号重复，请单击该学生以修改</p>
			<p class="text-danger text-center">删除学生请长按</p>
			
			<hr >
			<div class="class container">
				<span class="class text-center text-danger col-xs-6">选择班级</span>
				
				<select class="class col-xs-5 col-xs-offset-1">
					<option selected="selected" value="0">请选择班级</option>
					<c:forEach items="${allclass }"  var="c" varStatus="statu">
					<option value="${c.classid }">${c.classname }</option>
					</c:forEach>
				</select>
			</div>
			<button type="button" class="btn btn-warning col-xs-12" onclick="addclass()">没有班级？<span class="text-danger">添加班级</span></button>
			<button type="button" class="btn btn-warning col-xs-12" onclick="addstudent()">没有学生？<span class="text-danger">添加学生</span></button>
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
				if('${classid}' != null){
					$('select.class').val('${classid}');
				}
				
				//监听班级修改
				$('select.class').on('change',function(){
					//获取值
					var classid = $(this).val();
					//获取当前班级学生编号
					$.post('stu','method=THshownum&classid='+classid,function(data){
						window.location=data;
					})
				});
				
				//返回上一级
				$('#return').on('click',function(){
					history.go(-1);
				})
				//重置编号
				$('#reset').on('click',function(){
					if(confirm('确认重置班内编号？')){
						$.post('stu','method=THinitnum',function(data){
							//刷新页面
							alert(data);
							//获取当前班级学生编号
							var classid = '${classid}';
							$.post('stu','method=THshownum&classid='+classid,function(data){
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
								//获取当前班级学生编号
								var classid = '${classid}';
								$.post('stu','method=THshownum&classid='+classid,function(data){
									window.location=data;
								})
							}
						})
					}
				})
			})
			// 长按删除学生
			var e=0;
			$('tr').on("touchstart",function(event){
				var me = $(this);
				e = setTimeout(function(){
					// 长按事件写这里
					// 获取学号
					var sid = me.find('td')[0].innerText;
					// 获取姓名
					var sname = me.find('td')[1].innerText;
					// 提示框
					if(confirm('确定删除学生 '+sname+' ?')){
						$.post('stu','method=delete&sid='+sid,function(data){
							alert(data);
							//获取当前班级学生编号
							var classid = '${classid}';
							$.post('stu','method=THshownum&classid='+classid,function(data){
								window.location=data;
							})
						})
					}
					// 清除默认事件
					event.preventDefault();
				},1000);
			});
			
			$('tr').on('touchend',function(event){
				// 松开时清除定时器事件
				clearTimeout(e);
			});
			
			
			function addclass(){
				$.post('sub','method=loadSubject');
				window.location="addclass.jsp";
			}
			
			function addstudent(){
				window.location="addstudent.jsp";
			}
		</script>
	</body>
</html>
