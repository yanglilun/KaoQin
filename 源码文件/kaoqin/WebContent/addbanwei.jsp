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
	</head>
	<body>
		<h3 class="text-center text-success">管理班委</h3>
		<hr >
		<div class="col-xs-10 col-xs-offset-1">
			<select class="class form-control">
				<option selected="selected" value="0">请选择班级</option>
				<c:forEach items="${allclass }"  var="c" varStatus="statu">
				<option value="${c.classid }">${c.classname }</option>
				</c:forEach>
			</select>			
		</div>
		<span class="text-center col-xs-12 text-info" style="margin-top: 10px;">如要设置班委请单击,撤销自动识别</span>
		<button type="button" class="btn btn-danger col-xs-8 col-xs-offset-2" onclick="history.go(-1)">返回</button>
		<table class="table table-striped">
			
		</table>
		<script type="text/javascript">
			$('select.class').on('change',function(){
				var tab = $('table.table')
				// 重置tab
				tab.text('');
				var classid = $(this).val();
				$.post('stu','method=getStuByClassid&classid='+classid,function(data){
					// 转成list
					var list = JSON.parse(data);
					for(var l of list){
						var sid = $('<td>').text(l.sid).addClass('text-center');
						var sname = $('<td>').text(l.sname).addClass('text-center');
						var bw = $('<td>').addClass('text-center');
						var tr = $('<tr>');
						tr.append(sid);
						tr.append(sname);
						
						if(l.level=='1'){
							bw.text('班委')
							tr.append(bw);
							tr.css('background-color','rgba(255,0,0,0.5)');
						}else{
							tr.append(bw);
						}
						// 把tr加到table
						tab.append(tr);
					}
				})
				
				// 事件委托,监听tr
				$('table.table').on('click','tr',function(){
					// 获取sid,sname,level文字
					var sid = $(this).find('td')[0].innerText;
					var sname = $(this).find('td')[1].innerText;
					var level = $(this).find('td')[2].innerText;
					// 存储classid
					var classid = $('select.class').val();
					
					// 判断是否已经是班委
					if(level!='班委'){
						if(confirm('设置 '+sname+' 为班委？')){
							$.post('stu','method=setlevel&sid='+sid+'&level=1',function(data){
								if(data='true'){
									alert('添加成功！默认密码123123');
									// 刷新界面,通过classid在获取一次
									window.location.reload();
								}
							})
						}else{
							return;
						}
					}else{
						if(confirm('撤销 '+sname+' 班委？')){
							$.post('stu','method=setlevel&sid='+sid+'&level=0',function(data){
								if(data='true'){
									alert('撤销成功');
									// 刷新界面,通过classid在获取一次
									window.location.reload();
								}
							})
						}else{
							return;
						}
					}
				})
			})
		</script>
	</body>
</html>
