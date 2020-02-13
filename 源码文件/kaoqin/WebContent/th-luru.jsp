<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<meta charset="utf-8">
		<link rel="stylesheet" type="text/css" href="css/bootstrap.css"/>
		<link rel="stylesheet" type="text/css" href="css/luru.css"/>
		<script src="tool/jquery-1.11.3.js" type="text/javascript" charset="utf-8"></script>
		<title></title>
	</head>
	<body>
		<form>
			<input type="hidden" name="method" id="method" value="luru" />
		<div class="container">
			<div class="lr-head">
				<h3 class="text-success text-center"><span>考勤录入</span></h3>
			</div>
			<div class="info">
				<div class="col-xs-4 bold text-center">
					教师<br>
					<span id="name" class="mark">${me.tname}</span>
				</div>
				<div class="col-xs-4 bold text-center">
					专业<br>
					<span id="subname" class="mark">${subname}</span>
				</div>
				<div class="col-xs-4 bold text-center">
					选择班级<br>
					<select name="classid" class="class">
						<option selected="selected" value="0">请选择班级</option>
						<c:forEach items="${allclass}"  var="c" varStatus="statu">
						<option value="${c.classid }">${c.classname }</option>
						</c:forEach>
					</select>
				</div>
				<div class="col-xs-6">
					<h6 class="col-xs-12 text-center">当前周次</h6>
				</div>
				<div class="col-xs-6">
					<h6 class="col-xs-12 text-center">当前星期</h6>
				</div>
				<div class="col-xs-6">	
					<select name="week" class="week col-xs-7 col-xs-push-2">
						<option value ="1">1</option>
						<option value ="2">2</option>
						<option value ="3">3</option>
						<option value ="4">4</option>
						<option value ="5">5</option>
						<option value ="6">6</option>
						<option value ="7">7</option>
						<option value ="8">8</option>
						<option value ="9">9</option>
						<option value ="10">10</option>
						<option value ="11">11</option>
						<option value ="12">12</option>
						<option value ="13">13</option>
						<option value ="14">14</option>
						<option value ="15">15</option>
						<option value ="16">16</option>
						<option value ="17">17</option>
						<option value ="18">18</option>
						<option value ="19">19</option>
					</select>
				</div>
				<div class="col-xs-6">
					<select name="day" class="week col-xs-9 col-xs-push-1">
						<option value ="1">星期一</option>
						<option value ="2">星期二</option>
						<option value ="3">星期三</option>
						<option value ="4">星期四</option>
						<option value ="5">星期五</option>
						<option value ="6">星期六</option>
						<option value ="7">星期天</option>
					</select>
				</div>
			</div>
			<div class="col-xs-12 text-center">
				<span class="text-info">请在下面输入框输入迟到学生的尾号</span><span class="text-danger">（空格分开）</span>
			</div>
			<div class="input col-xs-12">
				<textarea class="word" name="word"></textarea>
			</div>
			<button type="button" class="btn btn-success col-xs-10 col-xs-offset-1" id="sub" style="margin-bottom: 5px;">提交</button>
			<button type="button" class="btn btn-info col-xs-4 col-xs-offset-1" id="shownum">查看尾号</button>
			<button type="button" class="btn btn-danger col-xs-4 col-xs-offset-2" id="return">返回</button>
			
		</div>
		</form>
		<script type="text/javascript">
			$(function(){
				//监听班级修改
				$('select.class').on('change',function(){
					//获取值
					var classid = $(this).val();
					//获取当前班级学生编号
					$.post('stu','method=THshownum&classid='+classid,function(data){
						console.log(${allstu});
					})
				});
				// 返回
				$('#return').on('click',function(){
					history.go(-1);
				})
				// 查看尾号
				$('#shownum').on('click',function(){
					window.location="th-classnum.jsp";
				})
				
				$('#sub').on('click',function(){
					if($('textarea.word').val()==""){
						alert('请输入迟到学生');
						return;
					}
					// 提交表单
					var f = $('form').serialize();
						$.post('late',f,function(data){
							alert(data);
							$('textarea.word').val('');
					})
					
					// 获取当前的周次
					var week = parseInt($('select[name=week]').val());
					// 获取当前的星期
					var day = parseInt($('select[name=day]').val());
					// 设置新的周次
					if(day<7){
						$('select[name=day]').val(day+1);
					}else{
						$('select[name=day]').val(1);
						$('select[name=week]').val(week+1);
					}
				})
			})
		</script>
	</body>
</html>
