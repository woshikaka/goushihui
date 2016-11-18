<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>ueditor demo</title>
</head>
<body>
	<!-- 加载编辑器的容器 -->
	<script id="container" name="content" type="text/plain"></script>
	<!-- 配置文件 -->
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/umeditor/ueditor.config.js"></script>
	<!-- 编辑器源码文件 -->
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/umeditor/ueditor.all.min.js"></script>
	<!-- 实例化编辑器 -->
	<script type="text/javascript">
		//var ue = UE.getEditor('container');
		//获取html内容，返回: <p>hello</p>
    	//var html = ue.getContent();
		var ue = UE.getEditor('container', {
			initialFrameHeight : 320,
			toolbars : [ [ 'undo', //撤销
			'redo', //重做
			'bold', //加粗
			'indent', //首行缩进
			'italic', //斜体
			'underline', //下划线
			'strikethrough', //删除线
			'formatmatch', //格式刷
			'horizontal', //分隔线
			'removeformat', //清除格式
			'fontfamily', //字体
			'fontsize', //字号
			'paragraph', //段落格式
			'simpleupload', //单图上传
			'insertimage', //多图上传
			'link', //超链接
			'unlink', //取消链接
			'emotion', //表情
			'justifyleft', //居左对齐
			'justifyright', //居右对齐
			'justifycenter', //居中对齐
			'justifyjustify', //两端对齐
			'forecolor', //字体颜色
			'backcolor', //背景色
			'insertorderedlist', //有序列表
			'insertunorderedlist', //无序列表
			'fullscreen', //全屏
			'lineheight' //行间距
			] ]
		});
	</script>
</body>
</html>