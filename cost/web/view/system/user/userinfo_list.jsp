<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%
	//获取绝对路径路径 
	String path = request.getContextPath();
			String basePath = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ path + "/";
	%>
<%--引入分页标签--%>
<%@ taglib prefix="d" uri="http://displaytag.sf.net" %>	   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath %>" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>用户管理-用户查询</title>
<link href="resource/css/bootstrap.min.css" rel="stylesheet" />
<script type="text/javascript" src="resource/js/jquery.min.js"></script>
<script type="text/javascript"
	src="resource/js/bootstrap.min.js"></script>
<script type="text/javascript">
$(function(){
	//绑定全选按钮选中事件
	$("#cks").click(function(){
		//获取所有多选框的checked属性
		var allCheck=this.checked;
		//获取所有name属性为userIds的input标签,让其跟随着复选框变化
		$("input[name=userIds]").each(function(){
			this.checked=allCheck;
		});
	});
	
	//绑定点击删除按钮事件
	$("#del").click(function(){
		//获取所有已经选中的name属性为ids的input标签
		var cks=$("input[name=userIds]:checked");
		
		if(cks.length<1){
			alert("请选择要删除的用户");
			return;
		}
		//confirm(arg) 点击确定时返回true,点击取消返回false
		if(confirm("确认要删除吗？")){
			//修改form表单的action属性
			$("#f1").prop("action","system/userDelete");
			//jquery提交form表单
			$("#f1").submit();
		}
		
		
	});
	
})

</script>	
</head>
<body>
<div>
		<ul class="breadcrumb" style="margin: 0px;">
			<li>系统管理</li>
			<li>用户管理</li>
			<li>用户查询</li>
		</ul>
	</div>
	<div class="alert alert-warning alert-dismissible fade in" role="alert" style="display:${tip==null?'none':'block' }; margin-bottom: 0px;">
	<h4 align="center" style="color: red">${tip }</h4>
	</div>
	<form action="system/userQuery" id="f1" class="form-inline" method="post">
		<div class="row alert alert-info" style="margin: 0px; padding: 5px;">
			<div class="form-group" >
				<label>用户编号:</label>
				<%--el表达式，在文本框回显id和name内容--%>
				<input type="text" class="form-control" name="userId" value="${user.userId==0?'':user.userId }" placeholder="请输入用户编号" />
				<label>用户姓名:</label>
				<input type="text" class="form-control" name="userName" value="${user.userName }"  placeholder="请输入用户姓名" />
			</div>
			<input type="submit" class="btn btn-danger" value="查询"> <a
				href="view/system/user/userinfo_add.jsp" class="btn btn-success">添加用户</a>
			<input type="button" class="btn btn-warning" id="del" value="删除用户">
		</div>
		<div class="row" style="padding: 15px;">
			<%--应用分页标签，name相当于cl表达式，pagesize显示行数，requestURL访问地址--%>
			<d:table class="table table-hover table-condensed" name="userList" pagesize="3" requestURI="system/userQuery">
				<%--<d:column property="ck" title="<input type='checkbox'  id='all'  />"></d:column>--%>
				<%--property要与user属性名称一致，并且调用的是其get()方法--%>
				<%--title属性值存放在<th>标签里--%>
				<d:column property="ck" title="<input type='checkbox' id='cks'>"></d:column>
				<d:column property="userId" title="用户编号"></d:column>
				<d:column property="userName" title="用户姓名"></d:column>
				<d:column property="userSex" title="用户性别"></d:column>
				<d:column property="userAge" title="用户年龄"></d:column>
				<d:column property="userPhone" title="用户电话"></d:column>
				<d:column property="userRole" title="用户角色"></d:column>
				<d:column property="userAccount" title="用户账户"></d:column>
				<d:column property="userPwd" title="用户密码"></d:column>
				<d:column property="userBasic" title="用户薪资"></d:column>
				<d:column value="修改" href="system/userUpdate" paramId="userId" paramProperty="userId"></d:column>
			</d:table>
		</div>
	</form>
</body>
</html>