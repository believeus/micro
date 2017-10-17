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
          <div class="layui-form-item">
              <label for="L_email" class="layui-form-label">
                  <span class="x-red">*</span>标题
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="L_email" name="title" lay-verify="required" autocomplete="off" class="layui-input">
              </div>
          </div>
          <div class="layui-form-item">
              <label for="L_username" class="layui-form-label">
                  <span class="x-red">*</span>类型
              </label>
              <div class="layui-input-inline">
                  <select lay-filter="select"  name="type" class="valid">
                    <option value="学习加分项:学习区域管理条例">加分项:学习区域管理条例</option>
                    <option value="生活加分项:住宿区域管理条例">加分项:住宿区域管理条例</option>
                    <option value="学习加分项:学习状态管理条例">加分项:学习状态管理条例</option>
                    <option value="生活加分项:生活状态管理条例">加分项:生活状态管理条例</option>
                    <option value="学习减分项:学习区域管理条例">减分项:学习区域管理条例</option>
                    <option value="生活减分项:住宿区域管理条例">减分项:住宿区域管理条例</option>
                    <option value="学习减分项:学习状态管理条例">减分项:学习状态管理条例</option>
                    <option value="生活减分项:生活状态管理条例">减分项:生活状态管理条例</option>
                  </select>
              </div>
          </div>
          <div class="layui-form-item">
              <label for="L_pass" class="layui-form-label">
                  <span class="x-red">*</span>分数
              </label>
              <div class="layui-input-inline">
                  <input id="L_value" name="learnValue"  lay-verify="required"  autocomplete="off" class="layui-input">
              </div>
          </div>
          <div class="layui-form-item">
              <label for="L_repass" class="layui-form-label">
                  	详细描述
              </label>
              <div class="layui-input-inline">
                  <textarea placeholder="请输入内容" id="desc" name="description" class="layui-textarea"></textarea>
              </div>
          </div>
          <div class="layui-form-item">
              <label for="L_repass" class="layui-form-label"></label>
              <button  class="layui-btn" lay-filter="add" lay-submit=""> 增加</button>
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
            //发异步，把数据提交给php
            $.post("admin/score/update.jhtml",data.field,function(data){
            	layer.alert("增加成功", {icon: 6},function () {
                    // 获得frame索引
                    var index = parent.layer.getFrameIndex(window.name);
                    //关闭当前frame
                    parent.layer.close(index);
                    parent.location.reload();
                });
            });
            
            return false;
          });
          
          form.on('select(select)', function(data){
        	  if(data.value.indexOf("生活")>=0){
        		 $("#L_value").attr("name","liveValue");
        	  }else{
        		  $("#L_value").attr("name","learnValue");
        	  }
        	  console.log(data.elem); //得到select原始DOM对象
        	  console.log(data.value); //得到被选中的值
        	  return false;
        	});
          
        });
    </script>
  </body>

</html>