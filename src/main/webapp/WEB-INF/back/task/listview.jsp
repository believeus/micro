<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="date" uri="/WEB-INF/lib/datetag.tld"%>  
<!DOCTYPE html>
<html>
  
  <head>
   <base href="<%=basePath%>">
    <meta charset="UTF-8">
    <title>分布式师徒积分制管理系统</title>
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
    <div class="x-nav">
      <span class="layui-breadcrumb">
        <a href="">首页</a>
        <a href="">演示</a>
        <a>
          <cite>导航元素</cite></a>
      </span>
      <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" href="javascript:location.replace(location.href);" title="刷新">
        <i class="layui-icon" style="line-height:30px">ဂ</i></a>
    </div>
    <div class="x-body">
      <div class="layui-row">
        <form class="layui-form layui-col-md12 x-so">
          <input class="layui-input" placeholder="开始日" name="start" id="start">
          <input class="layui-input" placeholder="截止日" name="end" id="end">
          <input type="text" name="username"  placeholder="请输入用户名" autocomplete="off" class="layui-input">
          <button class="layui-btn"  lay-submit="" lay-filter="sreach"><i class="layui-icon">&#xe615;</i></button>
        </form>
      </div>
      <xblock>
        <button class="layui-btn" onclick="x_admin_show('添加任务','admin/task/addview.jhtml',350,500)"><i class="layui-icon"></i>添加</button>
        <span class="x-right" style="line-height:40px">共有数据：88 条</span>
      </xblock>
      <table class="layui-table">
        <thead>
          <tr>
          	<th>发布人</th>
            <th>任务名</th>
            <th>开始时间</th>
            <th>完成时间</th>
            <th>奖励积分</th>
            <th>状态</th>
            <th>操作</th>
           </tr>
        </thead>
        <tbody>
        <c:forEach var="task" items="${tasklist}">
        	<tr>
        		<td>${task.user.username}</td>
        		<td>${task.title}</td>
        		<td>${task.begintime}</td>
        		<td>${task.endtime}</td>
        		<td>${task.value}</td>
        		<td>
        		   <c:choose>
        		   	 <c:when test="${sessionScope.sessionUser.id !=task.user.id}">
        		   	 	<a href="javascript:;" onclick="icandoIt(${task.id},${serssionScope.sessionUser.id},${task.user.id})">${task.status}</a>
        		   	 </c:when>
        		   	 <c:otherwise>
        		   	 	<a href="javascript:;">${task.status}</a>
        		   	 </c:otherwise>
        		   </c:choose>
        			
        		</td>
        		<td class="td-manage">
	             
	              <a title="编辑"  onclick="x_admin_show('编辑','admin/stu/editView.jhtml?id=${user.id}',600,500)" href="javascript:;">
	                <i class="layui-icon">&#xe642;</i>
	              </a>
	              <a onclick="x_admin_show('修改密码','member-password.html',600,400)" title="修改密码" href="javascript:;">
	                <i class="layui-icon">&#xe631;</i>
	              </a>
	              <a title="删除" onclick="del(this,${task.id})" href="javascript:;">
	                <i class="layui-icon">&#xe640;</i>
	              </a>
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
    <script>
      layui.use('laydate', function(){
        var laydate = layui.laydate;
        
        //执行一个laydate实例
        laydate.render({
          elem: '#start' //指定元素
        });

        //执行一个laydate实例
        laydate.render({
          elem: '#end' //指定元素
        });
      });
     

      /*用户-删除*/
      function del(obj,id){
          layer.confirm('确认要删除吗？',function(index){
              //发异步删除数据
              $.post("admin/task/del.jhtml?taskId="+id,function(){
            	  $(obj).parents("tr").remove();
                  layer.msg('已删除!',{icon:1,time:1000});
              });
             
          });
      }
      
     var icandoIt=function(taskId,curUserId,publishUserId){
    	 layer.confirm('确认要接单吗？',function(index){
    		 var url="";
             //发异步删除数据
             $.post(url,function(){
           	  $(obj).parents("tr").remove();
                 layer.msg('已删除!',{icon:1,time:1000});
             });
            
         });
     };

    //?taskId=${task.id}&curuserId=${serssionScope.sessionUser.id}&publishUserId
    </script>
  </body>

</html>