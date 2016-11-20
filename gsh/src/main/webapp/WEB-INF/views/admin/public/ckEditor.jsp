<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>CKEditor Sample</title>
	<script src="${pageContext.request.contextPath}/resources/ckEditor/ckeditor.js"></script>
	<script src="${pageContext.request.contextPath}/resources/ckEditor/sample.js"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/ckEditor/samples.css">
</head>
<body id="main">
		<!-- <div class="grid-container">
			<div class="grid-width-100">
				<div id="editor">
				</div>
			</div>
		</div> -->
		<div id="editor"></div>
<script>
	CKEDITOR.replace( 'editor1' );
	CKEDITOR.editorConfig = function( config ) {
		config.language = 'zh-cn';
	};
	initSample();
</script>
</body>
</html>
