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
    <link rel="stylesheet" href="static/public/lib/layui/css/layui.css">
    <link rel="stylesheet" href="static/public/lib/layui/css/modules/layer/default/layer.css">
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript" src="static/public/lib/layui/layui.all.js" charset="utf-8"></script>
    <script type="text/javascript" src="static/public/js/xadmin.js"></script>
	 <script>
	 $(function(){
		 layui.use(['form','element'],
				    function() {
				       layer = layui.layer;
				       element = layui.element;
				    });
	 });
    </script>
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
      <xblock>
        <button class="layui-btn" onclick="x_admin_show('添加悬赏','admin/task/addview.jhtml',350,550)"><i class="layui-icon"></i>添加悬赏分</button>
        <span class="x-right" style="line-height:40px">共有数据：88 条</span>
      </xblock>
      <table class="layui-table" lay-filter="table">
        <thead>
          <tr>
          	<th>发布任务人</th>
            <th>任务名</th>
            <th>任务类型</th>
            <th>开始时间</th>
            <th>完成时间</th>
            <th>悬赏分</th>
            <th>状态</th>
            <th>任务认领人</th>
            <th>操作</th>
           </tr>
        </thead>
        <tbody>
        <c:forEach var="task" items="${tasklist}" varStatus="status">
        	<tr>
        		<td><span>${task.user.username}</span></td>
        		<td>
        			<c:choose>
        				<c:when test="${task.title ==null}">
        					<a href="javascript:;" onclick="x_admin_show('更改密码','admin/task/editQ.jhtml?taskId=${task.id}',500,200)" style="color: #30A89D;">老师编写学生询问的问题</a>
        				</c:when>
        				<c:otherwise><span>${task.title}</span></c:otherwise>
        			</c:choose>
        		</td>
        		<td><span><c:choose><c:when test="${task.type=='live'}">生活分</c:when><c:when test="${task.type=='learn'}">学习分</c:when><c:otherwise>学习任务对赌分</c:otherwise></c:choose></span></td>
        		<td><span>${task.begintime}</span></td>
        		<td><span>${task.endtime}</span></td>
        		<td><span>${task.value}</span></td>
        		<td>
	        		 <c:choose>
	        		 	<c:when test="${task.aidUser ==null}">
	        		 		<span style="color: #0056E3;">[任务等待认领]</span>
	        		 	</c:when>
	        		 	<c:when test="${task.aidUser !=null&&task.status !=null}">
	        		 		<span style="color: #0056E3;">${task.status}</span>
	        		 	</c:when>
	        		 	<c:otherwise>
	        		 		<span style="color: red;">任务已被认领</span>
	        		 	</c:otherwise>
	        		 </c:choose>
        		</td>
        		<td>
        			<c:choose>
        				<c:when test="${task.status eq '任务已被指派' }">
        					<span>${task.aidUser.truename}[学:${task.aidUser.learnValue}][生:${task.aidUser.liveValue}]</span>
        				</c:when>
        			    <c:when test="${sessionScope.sessionUser.id == task.user.id }">
        					<a href="javascript:;">[自己不能认领]</a>
        				</c:when>
        				<c:when test="${sessionScope.sessionUser.id != task.user.id &&task.aidUser ==null }">
        					<a href="javascript:;" onclick="icandoIt(${task.id},${sessionScope.sessionUser.id })">[申请认领该任务]</a>
        				</c:when>
        				<c:when test="${task.aidUser !=null }">
        					<span style="color: red;font-size: 18px;">[${task.aidUser.truename}]</span>
        				</c:when>
        			</c:choose>
        		</td>
        		<td class="td-manage">
	        		<c:choose>
	        			<c:when test="${task.status eq '任务已经完成' }">
	        				<span>任务已经结束</span>
	        			</c:when>
						<c:when test="${sessionScope.sessionUser.id == task.user.id && task.aidUser==null}">
							<a title="编辑"  href="javascript:;" onclick="del(this,${task.id})">[删除任务]</a>
		           			<a title="编辑"  onclick="x_admin_show('编辑','admin/task/editview.jhtml?taskId=${task.id}',600,500)" href="javascript:;">[编辑任务]</a>
						</c:when>
						<c:when test="${sessionScope.sessionUser.id == task.user.id && task.aidUser!=null}">
							<a title="编辑"  onclick="x_admin_show('编辑','admin/task/taskstatus.jhtml?taskId=${task.id}',400,350)" href="javascript:;">${task.status}</a>
						</c:when>
						<c:otherwise>
		          			<span>[无权查看]</span>
						</c:otherwise>
	        		</c:choose>
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
          elem: '#end'//指定元素
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
      
     var icandoIt=function(taskId,aidUserId){
    	 layer.confirm('确认要接单吗？',function(index){
    		 var url="admin/task/iCanDoIt.jhtml?taskId="+taskId+"&aidUserId="+aidUserId;
             //发异步删除数据
             $.post(url,function(data){
            	 layer.alert("接单成功", {icon: 6},function () {
            		 window.location.reload();
                 });
             });
            
         });
     };

    </script>
  </body>

</html>