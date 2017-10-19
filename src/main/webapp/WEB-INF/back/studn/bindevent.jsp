<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
     <form class="layui-form">
          <div class="layui-form-item">
              <div class="layui-input-inline">
                  <select name="contrller">
                    <option value="加分项:学习区域管理条例">加分项:学习区域管理条例</option>
                    <option value="加分项:住宿区域管理条例">加分项:住宿区域管理条例</option>
                    <option value="加分项:学习状态管理条例">加分项:学习状态管理条例</option>
                    <option value="加分项:生活状态管理条例">加分项:生活状态管理条例</option>
                    <option value="减分项:学习区域管理条例">减分项:学习区域管理条例</option>
                    <option value="减分项:住宿区域管理条例">减分项:住宿区域管理条例</option>
                    <option value="减分项:学习状态管理条例">减分项:学习状态管理条例</option>
                    <option value="减分项:生活状态管理条例">减分项:生活状态管理条例</option>
                  </select>
              </div>
          </div>
          <div class="layui-form-item layui-form-text">
              <div class="layui-input-block" style="margin-left: 0px;">
                  <table class="layui-table">
                  	 <thead>
			          <tr>
			            <th>事件名</th>
			            <th>类型</th>
			            <th>分数值</th>
			            <th>操作</th>
			          </tr>
			        </thead>
                    <tbody>
                      <c:forEach var="event" items="${eventList}">
                      	<tr>
				            <th>${event.title}</th>
				            <th>${event.type}</th>
				            <th>${event.learnValue==0?event.liveValue:event.learnValue}</th>
				            <th><a href="javascript:;" onclick="bindEvent('${sessionScope.sessionUser.username}',${userId},${event.id})">[绑定]</a></th>
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
       </form>
    </div>
	<script>
		var bindEvent=function(username,userId,eventId){
			var url="admin/studn/bindEvent.jhtml?username="+username+"&userId="+userId+"&eventId="+eventId;
			 //发异步，把数据提交给php
            $.post(url,function(data){
	           	layer.alert("执行成功", {icon: 6},function () {
	                   // 获得frame索引
	                   var index = parent.layer.getFrameIndex(window.name);
	                   //关闭当前frame
	                   parent.layer.close(index);
	                   parent.location.reload();
	              });
           });
		};
	</script>
  </body>

</html>