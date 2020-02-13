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
			textarea.word{
				height: 250px;
				resize: none;
			}
			button{
				margin-top: 10px;
			}
		</style>
	</head>
	<body>
		<h3 class="text-center text-success">添加学生</h3>
		<span class="col-xs-12 text-center">专业:${subname}</span>
		<br><hr >
		<span class="col-xs-6 text-center">选择班级</span>
		<select name="classid" class="col-xs-5">
			<c:forEach items="${allclass }" var="c" varStatus="statu">
			<option value="${c.classid}">${c.classname }</option>
			</c:forEach>
		</select>
		<span class="col-xs-12 text-center text-info">请在下面文本框输入学生信息</span>
		<span class="col-xs-12 text-center text-danger">格式：学号 姓名（空格,-,制表符分开）</span>
		<textarea class="word col-xs-10 col-xs-offset-1"></textarea>
		<button type="button" id="sub" class="btn btn-success col-xs-4 col-xs-offset-1">提交</button>
		<button type="button" onclick="chakan()" class="btn btn-info col-xs-4 col-xs-offset-2">查看学生</button>
		<button type="button" onclick="history.go(-1)" class="btn btn-danger col-xs-10 col-xs-offset-1">返回</button>
		</form>
		<script type="text/javascript">
			$(function(){
				$('button#sub').on('click',function(){
						var subname = '${subname}';
						var classid = $('select[name=classid]').val();
						var word = $('textarea.word').val();
						$.post('stu','method=addStudentByWord&subname='+subname+'&classid='+classid+'&word='+word,function(data){
							alert(data);
						})
				})
			})
			
			function chakan(){
				window.location="th-classnum.jsp";
			}
			
		</script>
	</body>
</html>
