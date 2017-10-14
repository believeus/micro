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
      <xblock>
        <span class="x-right" style="line-height:40px">共有数据：88 条</span>
      </xblock>
      <form class="layui-form">
	      <table class="layui-table">
	        <thead>
	          <tr>
	            <th>学员名</th>
	            <th>事件名</th>
	            <th>申请驳回信息</th>
	            <th>积分值</th>
	            <th>状态</th>
	            <th>操作</th>
	           </tr>
	        </thead>
	        <tbody>
		        <c:forEach var="userEvent" items="${userEventList}">
		        	<tr>
		        		<td>${userEvent.username}<a href="javascript:;" onclick="x_admin_show('积分详情','admin/stu/myDo.jhtml?userId=${userEvent.userId}',850,500)" >&nbsp;&nbsp;[积分详情]</a></td>
		        		<td>${userEvent.title}</td>
		        		<td>${userEvent.message}</td>
		        		<td>${userEvent.value}</td>
		        		<td style="width: 160px;">
		        			<div class="layui-input-inline" >
			                  <select id="status"  name="status" class="valid">
			                    <option value="管理员审核中">管理员审核中</option>
			                    <option value="邀约仲裁面谈">邀约仲裁面谈</option>
			                    <option value="酌情减分">酌情减分</option>
			                    <option value="证据确凿已定案">证据确凿已定案</option>
			                  </select>
		             		</div>
		        		</td>
		        		<td class="td-manage">
			              <a href="javascript:;"  onclick="del(this,${userEvent.id})" >&nbsp;&nbsp;[删除]</a>
		            	</td>
		        	</tr>
		         </c:forEach>
	        </tbody>
	      </table>
      </form>
      <script>
	      $(function(){
	    	  $('#status').change(function(){
	    	    alert($(this).children('option:selected').val());
		    	var p1=$(this).children('option:selected').val();//这就是selected的值
		    	var p2=$('#param2').val();//获取本页面其他标签的值
		    	
	    	  });
    	 }) 
      </script>
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
	 function del(obj,userEventId){
        layer.confirm('确认要删除吗？',function(index){
            //发异步删除数据
            var url='admin/stu/delBindEvent.jhtml?userEventId='+userEventId;
            $.post(url,function(){
	            	 $(obj).parents("tr").remove();
	                 layer.msg('已删除!',{icon:1,time:1000});
	                 parent.location.reload();
           	 }
            );
            
        });
    };
	</script>
  </body>

</html>