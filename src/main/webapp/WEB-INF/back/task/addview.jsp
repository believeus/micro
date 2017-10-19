<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
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
        <input type="hidden" name="userEventId" >
          <div class="layui-form-item">
              <label for="L_email" class="layui-form-label">
                  <span class="x-red">*</span>任务名
              </label>
              <div class="layui-input-inline">
                  <input type="text"   name="title" lay-verify="required" autocomplete="off" class="layui-input">
              </div>
          </div>
           <div class="layui-form-item">
              <label for="L_username" class="layui-form-label">
                  <span class="x-red">*</span>类型
              </label>
              <div class="layui-input-inline">
                  <select lay-filter="select"  name="type" class="valid">
                    <option value="learn">学习分</option>
                    <option value="live">生活分</option>
                  </select>
              </div>
          </div>
          <div class="layui-form-item">
              <label for="L_username" class="layui-form-label">
                  <span class="x-red">*</span>积分值
              </label>
              <div class="layui-input-inline">
                  <input type="text"  name="value" lay-verify="required|number" autocomplete="off" class="layui-input">
              </div>
          </div>
          <div  class="layui-form-item" >
              <label for="L_pass" class="layui-form-label">
                  <span class="x-red">*</span>开始日期
              </label>
              <div class="layui-input-inline">
                  <input class="layui-input" placeholder="开始日" name="begintime" id="begintime">
              </div>
          </div>
          <div  class="layui-form-item" >
              <label for="L_pass" class="layui-form-label">
                  <span class="x-red">*</span>结束日期
              </label>
              <div class="layui-input-inline">
                  <input  name="endtime" id="endtime" placeholder="结束日期" lay-verify="required"  autocomplete="off" class="layui-input">
              </div>
          </div>
          <div class="layui-form-item">
              <label for="L_repass" class="layui-form-label">
                 	详细描述
              </label>
              <div class="layui-input-inline">
                  <textarea placeholder="请输入内容"  name="message" class="layui-textarea">${userEvent.message}</textarea>
              </div>
          </div>
          <div class="layui-form-item">
              <label for="L_repass" class="layui-form-label"></label>
              <button  class="layui-btn" lay-filter="add" lay-submit="">添加</button>
          </div>
      </form>
    </div>
    <script>
        layui.use(['form','layer'], function(){
          var $ = layui.jquery;
          var form = layui.form;
          var layer = layui.layer;
         
          //监听提交
          form.on('submit(add)', function(data){
            console.log(data.field);
            //发异步，把数据提交给php
            $.post("admin/task/buildtask.jhtml",data.field,function(){
            	layer.alert("编辑成功", {icon: 6},function () {
                    // 获得frame索引
                    var index = parent.layer.getFrameIndex(window.name);
                    //关闭当前frame
                    parent.layer.close(index);
                    parent.location.reload();
                });
            });
            
            return false;
          });
          
          
        });
        
        layui.use('laydate', function(){
            var laydate = layui.laydate;
            
            //执行一个laydate实例
            laydate.render({
              elem: '#begintime' //指定元素
              ,format:'yyyy-MM-dd HH:mm:ss'
              ,type:'datetime'
            });

            //执行一个laydate实例
            laydate.render({
              elem: '#endtime' //指定元素
              ,format:'yyyy-MM-dd HH:mm:ss'
              ,type:'datetime'
            });
          });
    </script>
  </body>

</html>