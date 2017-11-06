<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="date" uri="/tag/datetag.tld"%>  
<!DOCTYPE html>
<html>
  
  <head>
   <base href="<%=basePath%>">
    <meta charset="UTF-8">
    <title>分布式师徒积分制管理系统</title>
    <meta name="renderer" content="webkit">
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
		 $("#categorySel").val("${categoryId}");
		 $("#categorySel").change(function(){
			     // 发送ajax请求选择数据
			     location.href="admin/course/list.jhtml?categoryId="+$(this).val();
			});
		 
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
      	  <c:if test="${sessionScope.sessionUser.username eq 'admin'}">
        	<button class="layui-btn" onclick="x_admin_show('添加课程','admin/course/addView.jhtml',400,550)"><i class="layui-icon"></i>添加</button>
         </c:if>
       		<select id="categorySel" style="width: 246px;height: 38px;font-weight: bolder;">
       			<option value="0">点击选择分类筛选</option>
       		<c:forEach items="${categoryList}" var="category">
       			<option value="${category.id }">${category.name }</option>
       		</c:forEach>
       		</select>
      </xblock>
      <table class="layui-table">
        <thead>
          <tr>
          	<th>课程分类</th>
            <th>课程名称</th>
            <th>视频总时长</th>
            <th>时间规划</th>
            <th>课程简介</th>
            <th>操作</th>
           </tr>
        </thead>
        <tbody>
        <c:forEach var="course" items="${pageList.content}">
        		<tr>
        			<td><span>${course.category.name}</span></td>
	        		<td><span>${course.name}</span></td>
	        		<td><span>${course.vidHour}</span></td>
	        		<td><span>${course.studyTime}</span></td>
	        		<td><span id="introduce_${course.id}"></span></td>
	        		<script>
	        		  var introduce="${course.introduce}";
	            	  if (introduce.length > 15) {
	            		  $("#introduce_${course.id}").text(introduce.substring(0,15)+"...");
	            	  }else{
	            		  $("#introduce_${course.id}").text(introduce);
	            	  }
	        		</script>
	        		<td class="td-manage">
	        			 <a title="编辑"  onclick="x_admin_show('编辑','admin/course/editView.jhtml?id=${course.id}',400,550)" href="javascript:;">[编辑]</a>
			             <a href="javascript:;" onclick="del(this,${course.id})">[删除]</a>
	            	</td>
        		</tr>
        </c:forEach>
        </tbody>
      </table>
      <div class="page">
        <div>
        </div>
      </div>
        <script type="text/javascript">
            var url = 'admin/course/list.jhtml?categoryId=${categoryId}&pageNumber=';
            var totalPage = "${pageList.totalPages}";
            var pageNumber = "${pageList.pageNumber}";
            initPage(url,totalPage,pageNumber);
        </script>

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

      
      /*删除*/
      function del(obj,id){
          layer.confirm('确认要删除吗？',function(index){
              //发异步删除数据
              var url="admin/course/del_course.jhtml?id="+id;
              $.post(url,function(){
            	  $(obj).parents("tr").remove();
                  layer.msg('已删除!',{icon:1,time:1000});
                  setTimeout("location.reload()", 3000 );
              });
              
          });
      }
    </script>
  </body>

</html>