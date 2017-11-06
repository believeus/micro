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
    <link rel="stylesheet" href="static/public/lib/layui/css/modules/laydate/default/laydate.css">
    <link rel="stylesheet" href="static/public/css/xadmin.css">
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript" src="static/public/lib/layui/layui.all.js" charset="utf-8"></script>
    <script type="text/javascript" src="static/public/js/xadmin.js"></script>
    <link rel="stylesheet" href="static/public/lib/zTree/css/bootstrapStyle.css" type="text/css">
<script type="text/javascript" src="static/public/lib/zTree/js/jquery.ztree.core.min.js"></script>
<script type="text/javascript" src="static/public/lib/zTree/js/jquery.ztree.exedit.min.js"></script>
    <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
    <!--[if lt IE 9]>
      <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
      <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    
    <style type="text/css">
    	ul.ztree {
	    	margin-top: 8px;
		    border: 1px solid #e6e6e6;
		    background: #D4D4D4;
		    width: 236px;
		    height: 300px;
		    overflow-y: scroll;
		    overflow-x: auto;
    	}
    	.menuContent{
    		position: absolute;
		    left: 109px;
		    top: 30px;
		    z-index: 1;
		    display: none;
    	}
    	.tarea{
    	    border: 1px solid #e6e6e6;
   			border-radius: 2px;
    	}
    </style>
     <SCRIPT type="text/javascript">
        var setting = {
            view: {
            	dblClickExpand: false
            },
            data: {
                simpleData: {
                    enable: true
                }
            },
			callback: {
				beforeClick: beforeClick,
				onClick: onClick
			}
        };

        function beforeClick(treeId, treeNode) {
        	// 设置不能点击父节点
			var check = (treeNode && !treeNode.isParent);
			return check;
		}
		
		function onClick(e, treeId, treeNode) {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
			nodes = zTree.getSelectedNodes();
			nodes.sort(function compare(a,b){return a.id-b.id;});
			var v = nodes[0].name ; // 名称
			var id = nodes[0].id ;  // ID
			var cityObj = $("#categorySel");
			cityObj.attr("value", v); // 把选中值设置在标签里
			$("#category_id").attr("value", id);
			hideMenu();
		}

		function showMenu() {
			$("#menuContent").slideDown("fast");
			$("body").bind("mousedown", onBodyDown);
		}
		// 隐藏
		function hideMenu() {
			$("#menuContent").fadeOut("fast");
			$("body").unbind("mousedown", onBodyDown);
		}
		function onBodyDown(event) {
			if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
				hideMenu();
			}
		}
        $(document).ready(function(){
        	$.ajax({
                url: 'admin/course/loadTree.jhtml',
                type: 'get',
                dataType: 'json',
                success: function (data) {
                    $.fn.zTree.init($("#treeDemo"), setting, data);
                }
            });
        });  
    </SCRIPT>
  </head>
  
  <body>
    <div class="x-body">
        <form class="layui-form">
          <div class="layui-form-item">
              <label for="L_email" class="layui-form-label">
               <span class="x-red">*</span>课程分类: 	
              </label>
              <div class="layui-input-inline" >
               <input id="category_id" name="category_id" type="hidden">
               <input id="categorySel" readonly="" lay-verify="required" placeholder="点击选择" class="layui-input" onclick="showMenu(); return false;">
              </div>
          </div>
          <div class="x-body menuContent" id="menuContent" >
				<ul id="treeDemo" class="ztree"></ul>
		  </div>
          <div class="layui-form-item">
              <label for="L_pass" class="layui-form-label">
                  <span class="x-red">*</span>课程名称:
              </label>
              <div class="layui-input-inline">
                  <input  name="name"  lay-verify="required"  autocomplete="off" class="layui-input">
              </div>
          </div>
           <div class="layui-form-item">
              <label for="L_username" class="layui-form-label">
                  <span class="x-red">*</span>视频总时长:
              </label>
              <div class="layui-input-inline">
                   <input name="vidHour"  lay-verify="required"  autocomplete="off" class="layui-input">
              </div>
          </div>
          <div class="layui-form-item">
              <label for="L_username" class="layui-form-label">
                  <span class="x-red">*</span>时间规划:
              </label>
              <div class="layui-input-inline">
                  <input name="studyTime"  lay-verify="required"  autocomplete="off" class="layui-input">
              </div>
          </div>
           <div class="layui-form-item">
              <label for="L_username" class="layui-form-label">
                  <span class="x-red"></span>课程简介:
              </label>
              <div class="layui-input-inline">
                  	<textarea rows="10" cols="38" maxlength="255" name="introduce"  lay-verify="required" class="tarea"></textarea>
              </div>
          </div>
          <div class="layui-form-item">
              <label for="L_repass" class="layui-form-label"></label>
              <button  class="layui-btn" lay-filter="add" lay-submit="">增加</button>
              <button  class="layui-btn" >取消</button>
          </div>
      </form>
    </div>
    <script>
     $(function(){
    	   layui.use(['form','layer'], function(){
    	          var $ = layui.jquery;
    	          var form = layui.form;
    	          form.render();
    	          //监听提交
    	          form.on('submit(add)', function(data){
    	            //发异步，把数据提交给php
    	            $.post("admin/course/edit_course.jhtml",data.field,function(data,status){
    	            	if(data=="ok"){
    	            		layer.alert("增加成功", {icon: 6},function () {
        	                    // 获得frame索引
        	                    var index = parent.layer.getFrameIndex(window.name);
        	                    //关闭当前frame
        	                    parent.layer.close(index);
        	                    parent.location.reload();
        	                });
    			    	}else{
    			    		layer.msg("添加失败，请重新刷新页面！");
    			    	}
    	            	
    	            });
    	            
    	            return false;
    	          });
    	        });
     });
     
    </script>
    
  </body>

</html>