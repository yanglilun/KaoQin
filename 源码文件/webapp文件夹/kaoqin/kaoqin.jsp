<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<meta charset="utf-8">
		<link rel="stylesheet" type="text/css" href="css/bootstrap.css"/>
		<link rel="stylesheet" type="text/css" href="css/kaoqin.css"/>
		<script src="tool/jquery-1.11.3.js" type="text/javascript" charset="utf-8"></script>
		<title></title>
	</head>
	<body>
		<div class="container">
			<h3 class="text-center text-info">迟到记录</h3>
			<p class="text-danger text-center">${cname }</p>
			<span class="col-xs-5">选择周次:</span>
			<select name="week" value="${defweek}" class="col-xs-5 col-xs-offset-2")>
				<option value =""></option>
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
			<table class="table table-bordered table-hover table-striped">
				<tr>
					<th>姓名</th>
					<th>一</th>
					<th>二</th>
					<th>三</th>
					<th>四</th>
					<th>五</th>
					<th>六</th>
				</tr>
				<c:forEach items="${latelist }" var="l" varStatus="stat">
				<tr>
					<td>${l.name }</td>
					<td>${l.monday }</td>
					<td>${l.tuesday }</td>
					<td>${l.wednesday }</td>
					<td>${l.thursday }</td>
					<td>${l.friday }</td>
					<td>${l.sunday }</td>
				</tr>
				</c:forEach>
			</table>
			<button type="button" class="btn btn-block btn-danger" onclick="history.go(-1)">返回</button>
		</div>
		<script type="text/javascript">
			
			$(function(){
				var defweek = '${defweek}';
				$('select[name=week]').val(defweek);
				$.post('late','method=load&week='+defweek);
				
				$('select[name=week]').on('change',function(){
					var week = $(this).val();
					$.post('late','method=load&week='+week,function(data){
						if(data=='true'){
							window.location.reload();
						}
					});
				});
			});
		</script>
	</body>
</html>
