<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%
	//获取绝对路径路径 
	String path = request.getContextPath();

			String basePath = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ path + "/";
	%>      
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath %>" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>用户管理-个人信息</title>
<link href="resource/css/bootstrap.min.css" rel="stylesheet" />
<!-- <link href="resource/css/main.css" rel="stylesheet" /> -->
<script type="text/javascript" src="resource/js/jquery.min.js"></script>
<script type="text/javascript"
	src="resource/js/bootstrap.min.js"></script>
<script type="text/javascript" src="resource/validation/jquery.validate.js"></script>
<script type="text/javascript">
$(function(){
	
	 $("#f1").validate();
	 

})
</script>	
<style type="text/css">.error{color: red;}</style>

</head>
<body>
<div>
		<ul class="breadcrumb" style="margin: 0px;">
			<li>系统管理</li>
			<li>个人信息</li>
		</ul>
	</div>
	<div class="alert alert-warning alert-dismissible fade in" role="alert" style="display:${tip==null?'none':'block' };margin-bottom: 0px;">
     	<h4 align="center" style="color: red">${tip }</h4>
    </div>
<form action="system/updateMyUsers" id="f1" method="post" class="form-horizontal">
		<h5 class="page-header alert-info"
			style="margin: 0px; padding: 10px; margin-bottom: 10px;">基本信息</h5>
			<input type="hidden" name="userId" value="${userInfo.userId }" />
		<!-- 开始1 -->
		<div class="row">
			<div class="col-xs-5">
				<div class="form-group ">
					<label class="col-xs-3 control-label">用户姓名</label>
					<div class="col-xs-9 ">
						<input type="text" class="form-control" required name="userName" value="${userInfo.userName }" placeholder="请输入用户姓名" />
					</div>
				</div>
			</div>
			<div class="col-xs-5">
				<div class="form-group ">
					<label class="col-xs-3 control-label">用户年龄</label>
					<div class="col-xs-9 ">
						<input type="text" class="form-control" name="userAge" value="${userInfo.userAge }" placeholder="请输入用户年龄" />
					</div>
				</div>
			</div>
		</div>
		<!--结束1 -->
		<!-- 开始2 -->
		<div class="row">
			<div class="col-xs-5">
				<div class="form-group ">
					<label class="col-xs-3 control-label">用户性别</label>
					<div class="col-xs-3 ">
						<select name="userSex" class="form-control">
							<option ${userInfo.userSex=='保密'?'selected':'' } >保密</option>
							<option ${userInfo.userSex=='男'?'selected':'' }>男</option>
							<option ${userInfo.userSex=='女'?'selected':'' }>女</option>
						</select>
					</div>
				</div>
			</div>
			<div class="col-xs-5">
				<div class="form-group ">
					<label class="col-xs-3 control-label">电话号码</label>
					<div class="col-xs-9 ">
						<input type="text" class="form-control" name="userPhone" value="${userInfo.userPhone }" placeholder="请输入电话号码" />
					</div>
				</div>
			</div>
		</div>
		<!--结束2 -->
		<!-- 开始3 -->
		<div class="row">
			<div class="col-xs-5">
				<div class="form-group ">
					<label class="col-xs-3 control-label">用户角色</label>
					<div class="col-xs-4 ">
						<%--显示用户角色信息--%>
						 <p class="form-control-static">${userInfo.userRole}</p>
							<%--创建并隐藏roleId的目的是为了在修改时共用DAO层userUpdate()方法，否则没有获取不到roleId值，update语句会报错无法修改对象--%>
							<input type="hidden" name="roleId" value="${userInfo.roleId}">
					 		<%--隐藏一个userRole是为了刷新时回显该值--%>
						<input type="hidden" name="userRole" value="${userInfo.userRole}">
					</div>
				</div>
			</div>
			<div class="col-xs-5">
				<div class="form-group ">
					<label class="col-xs-3 control-label">用户薪资</label>
					<div class="col-xs-9 ">
						 <p class="form-control-static">${userInfo.userBasic}</p>
						<%--与上同样目的，避免修改，又不发生语句错误--%>
						<%--若不共用DAO层方法，那该input标签可以省略不要--%>
					<input type="hidden" name="userBasic" value="${userInfo.userBasic}">
					</div>
				</div>
			</div>
		</div>
		<!--结束3 -->

		<h5 class="page-header alert-info"
			style="margin: 0px; padding: 10px; margin-bottom: 10px;">账号信息</h5>
		<!-- 开始5 -->
		<div class="row">
			<div class="col-xs-5">
				<div class="form-group ">
					<label class="col-xs-3 control-label">用户账号</label>
					<div class="col-xs-9">
					 <p class="form-control-static">${userInfo.userAccount}</p>
						<input type="hidden" name="userAccount" value="${userInfo.userAccount}">
					</div>
				</div>
			</div>
			<div class="col-xs-5">
				<div class="form-group ">
					<label class="col-xs-3 control-label">用户密码</label>
					<div class="col-xs-9 ">
						<input type="text" class="form-control"  value="${userInfo.userPwd }"  required name="userPwd" placeholder="请输入用户密码" />
					</div>
				</div>
			</div>
		</div>
		<!--结束5 -->

		<div class="row">
			<div class="col-xs-3 col-xs-offset-4">
				<input type="submit" class="btn btn-success" value="修改个人信息" /> 
			</div>

		</div>

	</form>
</body>
</html>