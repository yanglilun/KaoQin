<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<meta charset="utf-8">
		<link rel="stylesheet" type="text/css" href="css/bootstrap.css"/>
		<script src="tool/jquery-1.11.3.js" type="text/javascript" charset="utf-8"></script>
		<title></title>
	</head>
	<body>
		<h3 class="text-center text-success">补签列表</h3>
		<span class="col-xs-12 text-center">专业：${subname}</span>
		<hr >
		<table class="apply table table-striped">
			<tr>
				<th>学号</th>
				<th>周次</th>
				<th>星期</th>
				<th>原因</th>
				<th>状态</th>
			</tr>
		</table>
		<button type="button" class="btn col-xs-12 btn-danger" onclick="history.go(-1)">返回</button>
		<script type="text/javascript">
			$(function(){
				// 追加apply
				$.post('apply','method=showApply',function(data){
					var applys = JSON.parse(data);
					console.log(applys)
					for(var a of applys){
						var aid = $('<td>');
						aid.text(a.aid);
						aid.hide();
						
						var sid = $('<td>');
						sid.text(a.sid);
						
						var week = $('<td>');
						week.text(a.week);
						
						var day = $('<td>');
						day.text(a.day);
						
						var reason = $('<td>');
						reason.text(a.reason);
						
						var status = $('<td>');
						switch(a.status){
							case 0:
								status.text('未审核');
								status.css('color','blue');
								break;
							case 1:
								status.text('已通过');
								status.css('color','green');
								break;
							case 2:
								status.text('未通过');
								status.css('color','red');
								break;
						}
						
						var tr = $('<tr>');
						tr.append(aid);
						tr.append(sid);
						tr.append(week);
						tr.append(day);
						tr.append(reason);
						tr.append(status);
						
						$('table.apply').append(tr);
					}
				});
				
				$('table.apply').on('click','tr',function(){
					var aid = $(this).find('td')[0].innerText;
					$.post('apply','method=saveaid&aid='+aid,function(data){
						window.location=data;
					});
				});
			});
			
		</script>
	</body>
</html>
