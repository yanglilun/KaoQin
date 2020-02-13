<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<meta charset="utf-8">
		<link rel="stylesheet" type="text/css" href="css/bootstrap.css"/>
		<link rel="stylesheet" type="text/css" href="css/banwei.css"/>
		<link rel="stylesheet" type="text/css" href="css/index.css"/>
		<link rel="stylesheet" type="text/css" href="tool/donghua.css"/>
		<script src="tool/jquery-1.11.3.js" type="text/javascript" charset="utf-8"></script>
		<title></title>
		<style type="text/css">
		h2{
		display:block;
		position: relative;
		padding:0;
		margin:0;
		margin-top:10px;
		height:40px;
		width: 75%;
		float: left;
		}
		span.tname{
		display:block;
		position: relative;
		margin-top:10px;
		height:40px;
		width: 25%;
		font-size:10px;
		line-height:40px;
		float: left;
		}
		</style>
	</head>
	<body>
		<div class="root col-xs-12">
			<div class="bw-head">
				<h2 class="text-center text-info">教师管理界面</h2><span class="tname">教师:${me.tname }</span>
			</div>
			<div class="bw-main">
				<button class="btn btn-success" onclick="luru()">
					<img src="img/录入.png" >
					<span class="lab">录入考勤</span>
				</button>
				<button class="btn btn-warning" onclick="guanli()">
					<img src="img/id.png" >
					<span class="lab">管理班级</span>
				</button>
				<button class="btn btn-info" onclick="kaoqin()">
					<img src="img/考勤.png" >
					<span class="lab">查询考勤信息</span>
				</button>
				<button class="btn btn-info" onclick="shenpi()">
					<img src="img/申请.png" >
					<span class="lab">审批补签</span>
				</button>
				<button class="btn btn-success" onclick="shengcheng()">
					<img src="img/表.png" >
					<span class="lab">生成总表</span>
				</button>
				<button class="btn btn-warning" onclick="addbanwei()">
					<img src="img/管理员.png" >
					<span class="lab">管理班委</span>
				</button>
				<button class="btn btn-warning" onclick="repassword()">
					<img src="img/修改密码%20(1).png" >
					<span class="lab">修改密码</span>
				</button>
				<button class="btn btn-danger" onclick="logout()">
					<img src="img/注销.png" >
					<span class="lab">退出登录</span>
				</button>
				
				
			</div>
		</div>
		<script type="text/javascript">
// 			欢迎
			$(function(){
				var height = window.innerHeight;
				$('div.root').css('height',height);
				$('div.root').css('margin-top',-height/2);
			});
			
			
			function logout(){
				if(confirm('确定退出？')){
					$.post('th','method=logout',function(data){
						window.location="admin.html";
					});					
				}
			}
			function repassword(){
				window.location="th-rep.html";
			}
			function guanli(){
				window.location="th-classnum.jsp";
			}
			function luru(){
				window.location="th-luru.jsp";
			}
			function kaoqin(){
				window.location="th-kaoqin.jsp";
			}
			function shenpi(){
				window.location="th-shenpi.jsp";
			}
			function shengcheng(){
				window.location="endlist.html";
			}
			function addbanwei(){
				window.location="addbanwei.jsp";
			}
			
			
		</script>
	</body>
</html>
