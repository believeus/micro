<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="date" uri="/WEB-INF/lib/datetag.tld"%>  
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
  
  <head>
  <base href="<%=basePath%>">
    <meta charset="UTF-8">
    <title>欢迎页面-X-admin2.0</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="static/public/css/font.css">
    <link rel="stylesheet" href="static/public/css/xadmin.css">
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript" src="static/public/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="static/public/js/xadmin.js"></script>
    <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
    <!--[if lt IE 9]>
      <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
      <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  
  <body>
    <div class="x-body">
          <div class="layui-form-item layui-form-text">
           
              <div class="layui-input-block" style="margin-left: 0px;">
                  <table class="layui-table">
                  	 <thead>
			          <tr>
			            <th>事件名</th>
			            <th>类型</th>
			            <th>分数值</th>
			            <th>发生时间</th>
			            <th>纠察员</th>
			            <th>状态</th>
			          </tr>
			        </thead>
                    <tbody>
                     <c:forEach var="userEvent" items="${userEventList}">
                     	<tr>
                        <td><span>${userEvent.title}</span></td>
                        <td><span>${userEvent.type=="live"?"生活分":"学习分"}</span></td>
                        <td><span>${userEvent.value}</span></td>
                        <td><span><date:date value="${userEvent.createTime}" pattern="yyyy-MM-dd"></date:date></span></td>
                        <td><span>${userEvent.observer}</span></td>
                        <td> 
                          	<c:choose>
                          		<c:when test="${userEvent.status eq '事件发生'}">
                          			<a href="javascript:;" onclick="x_admin_show('申请仲裁信息填写','admin/studn/disagreeView.jhtml?userId=${userId}&userEventId=${userEvent.id}',450,300)" >&nbsp;&nbsp;[申请仲裁]</a>
                          		</c:when>
                          		<c:otherwise>
                          			<a href="javascript:;" >[${userEvent.status}]</a>
                          		</c:otherwise>
                          	</c:choose>
                        	 <c:if test="${sessionScope.sessionUser.username eq 'admin' }">
                          		<a href="javascript:;"  onclick="del(this,${userEvent.id})" >&nbsp;&nbsp;[删除]</a>
                          	</c:if>
                        </td>
                      </tr>
                     </c:forEach>
                      
                     
                    </tbody>
                  </table>
                  <div class="page">
        <div>
          <a class="prev" href="">&lt;&lt;</a>
          <a class="num" href="">1</a>
          <span class="current">2</span>
          <a class="num" href="">3</a>
          <a class="num" href="">4</a>
          <a class="num" href="">5</a>
          <a class="num" href="">489</a>
          <a class="next" href="">&gt;&gt;</a>
        </div>
      </div>
              </div>
          </div>
    </div>
	 <script>
	 function del(obj,userEventId){
        layer.confirm('确认要删除吗？',function(index){
            //发异步删除数据
            var url='admin/studn/delBindEvent.jhtml?userEventId='+userEventId;
            $.post(url,function(){
	            	 $(obj).parents("tr").remove();
	                 layer.msg('已删除!',{icon:1,time:1000});
	                 window.location.reload();
           	 }
            );
            
        });
    };
	</script>
  </body>

</html>