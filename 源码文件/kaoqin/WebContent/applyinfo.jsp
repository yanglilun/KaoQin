<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<meta charset="utf-8">
		<link rel="stylesheet" type="text/css" href="css/bootstrap.css"/>
		<script src="tool/jquery-1.11.3.js" type="text/javascript" charset="utf-8"></script>
		<title></title>
		<style type="text/css">
			img#pic{
				position: relative;
				width: 100%;
			}
		</style>
	</head>
	<body>
		<h3 class="text-center text-success">补签详情</h3>
		<hr >
		<table class="table table-bordered table-striped">
			<tr>
				<td>编号</td>
				<td>学号</td>
			</tr>
			<tr>
				<td id="aid">1</td>
				<td id="sid">2</td>
			</tr>
			<tr>
				<td>周次</td>
				<td>星期</td>
			</tr>
			<tr>
				<td id="week">3</td>
				<td id="day">4</td>
			</tr>
			<tr>
				<td>原因</td>
				<td>状态</td>
			</tr>
			<tr>
				<td id="reason">5</td>
				<td id="status">6</td>
			</tr>
			<tr>
				<td colspan="2">凭证</td>
			</tr>
			<tr>
				<td colspan="2">
					<img  id="pic" src="" >
				</td>
			</tr>
		</table>
		<button type="button" class="btn col-xs-6 btn-success" onclick="tongguo()">通过</button>
		<button type="button" class="btn col-xs-6 btn-info" onclick="tuihui()">退回</button>
		<button type="button" class="btn col-xs-12 btn-danger" onclick="history.go(-1)">返回</button>
		
		<script type="text/javascript">
			$(function(){
				$.post('apply','method=showApply',function(data){
					var applys = JSON.parse(data);
					console.log(applys)
					for(var a of applys){
						if(a.aid==${aid}){
							$('td#aid').text(a.aid);
							$('td#sid').text(a.sid);
							$('td#week').text(a.week);
							$('td#day').text(a.day);
							$('td#reason').text(a.reason);
							$('img#pic').prop('src','../imgs/kaoqin/'+a.path);
							var status = a.status;
							switch (status) {
							case 0:
								status='未审核'
								break;
							case 1:
								status='已通过'
								break;
							case 2:
								status='未通过'
								break;
							default:
								break;
							}
							$('td#status').text(status);
							
						}
					}
				});
			})
			
			function tongguo(){
				$.post('apply','method=checkApply&check=true',function(data){
					alert(data);
					window.location.reload();
				})
			}
			function tuihui(){
				$.post('apply','method=checkApply&check=false',function(data){
					alert(data);
					window.location.reload();
				})
			}
		</script>
	</body>
</html>
