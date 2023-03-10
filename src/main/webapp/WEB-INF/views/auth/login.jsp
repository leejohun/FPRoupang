<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.1/font/bootstrap-icons.css">
<meta charset="UTF-8">
<meta charset="UTF-8">
<title>login</title>
<style>
    p{font-weight: bold; font-size: 18px; }
    body{font-family: dotum,sans-serif;}
    input{width: 450px;height: 60px;}
    .login_btn{
   		border: none;
   	    background-color: #0074e9;
	    color: #fff;
	    box-shadow: inset 0 -2px 0 rgb(0 0 0 / 38%);
	    padding: 16px 17px;
	    font-family: apple sd gothic neo,malgun gothic,nanumbarungothic,nanumgothic,dotum,sans-serif;
	    font-size: 17px;
	    line-height: 20px;
	    display: block;
	    padding-left: 0;
	    padding-right: 0;
	    width: 100%;}
   	.regist_btn{
	   	border: 1px solid #ccc; 
	    background-color: #fff;
	    color: #0073e9;
	    box-shadow: inset 0 -1px 0 rgb(0 0 0 / 15%);
	    padding: 16px 17px;
	    font-family: apple sd gothic neo,malgun gothic,nanumbarungothic,nanumgothic,dotum,sans-serif;
	    font-size: 17px;
	    line-height: 20px;
	    display: block;
	    padding-left: 0;
	    padding-right: 0;
	    margin: 18px 0 0;
	    width: 49%;}
    .btn_text{font-weight: bold; font-size: 17px;} 
    hr{
	    width: 100%;
	    margin: 18px 0 0;
	    border-top: 1px solid gray;}
	a{
		text-decoration: none;
		float: right;
	}
	.login_check{
        position: relative;
	    width: 24px;
	    height: 24px;
	    margin: 0;
	    padding: 0;
	    border: 0;
	    border-radius: 1px;
	    vertical-align: middle;
    }
    .check_box{
   	    vertical-align: middle;
	    color: #555;
	    font-size: 14px;
	    line-height: 24px;
    }
</style>
</head>
<body style="width: 100%; overflow: hidden;">
<!-- ?????????, ???????????? ??????????????? ???????????? ?????? ???????????? -->
<script type="text/javascript">
function loginValidate(f)
{
	if(f.my_id.value==""){
		alert("???????????? ???????????????");
		f.id.focus();
		return false;
	}
	if(f.my_pass.value==""){
		alert("??????????????? ???????????????"); 
		f.pass.focus();
		return false;
	} 
}
</script>
<div class="container"> 
	<!-- ???????????? ??? ?????? ??????????????? ???????????? ????????? ????????????. -->
		<s:authorize access="isAuthenticated()">
			<s:authentication property="name" var="name" />
		</s:authorize>
			<!-- ???????????? ??????????????? ??????????????? ????????????. -->
			<!-- ???????????? ????????? ?????? ?????????????????? ???????????? ?????? -->
			<span style="font-size:1.5em; color:red;">${LoginNG }</span>
			<form name="loginForm" method="post" action="/myLoginAction.do" onsubmit="return loginValidate(this);">
			 	<div class="container d-flex justify-content-center">
        			<!-- ??????????????? -->
       				<a href="/">
            			<img src="../images/roupang.png"  
                			style="width: 350px; margin:40px;">
       			 	</a>
    			</div>
				<input type="hidden" name="backUrl" value="${param.backUrl }"/>
				 <div class="container">
						<div class="row">
							<div class="col-3"></div>
							<div class="col-6">
								<!-- ?????? ?????? -->
								<div class="input-group mb-4">
									<span class="input-group-text"><i class="bi bi-envelope"></i></span>
									<input type="text" name="my_id" class="form-control"
										placeholder="?????????(?????????)">
								</div>
								<div class="input-group mb-3">
									<span class="input-group-text"><i
										class="bi bi-shield-lock"></i></span> 
										<input type="password" name="my_pass" class="form-control" placeholder="????????????">
								</div>
								<div style="display: block;">
									<label>
										<input class="login_check" type="checkbox">
										<span class="check_box">????????? ?????? ??????</span>
									</label>
									<a href="/search_id.do">?????????/??????????????????
									<span style="font-weight: bold;">></span></a>
								</div>
								<button class="login_btn mt-3">
									<span class="btn_text" style="color: white;">?????????</span>
								</button>
								<hr>
							</div>
							<div class="col-3"></div>
						</div>
					</div>
			</form>
</div>
</body>
</html>
