<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
pageContext.setAttribute("newline", "\n");
%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/assets/css/guestbook-spa.css"
	rel="stylesheet" type="text/css">
<link rel="stylesheet"
	href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
var no=-1;
var render = function(vo) {
	console.log("render");
	var html = 
			"<li data-no='" + vo.no + "'>" +
			"<strong>" + vo.name + "</strong>" +
			"<p>" + vo.message + "</p>" +
			"<strong></strong>" +
			"<a href='' data-no='" + vo.no + "'>삭제</a>" + 
			"</li>";
			
	 return html;		
}

var fetch = function() {
	console.log("fetch");
	// if$("#list-guestbook").length =0  
	//var no=$("#list-guestbook li").data("no")
	$.ajax({
		url: '${pageContext.request.contextPath }/api/guestbook/list/'+no,
		type: 'get',
		dataType: 'json',
		success: function(response) {
	
			if(response.result !== 'success') {
				console.error(response.message);
				return;
			}
			

			for(var i = 0; i < response.data.length; i++) {
				var vo = response.data[i];
				var html = render(vo);
				no += 3;
				$("#list-guestbook").append(html);
				startNo  = response.data[i].no;
			}
		}
	});	
}

$(function() {
	$("#add-form").submit(function(event) {
		event.preventDefault();
		
		var vo = {};
		vo.name = $("#input-name").val();
		vo.password = $("#input-password").val();
		vo.message = $("#tx-content").val();
		
		console.log(vo);
		
		$.ajax({
			url: '${pageContext.request.contextPath }/api/guestbook/add',
			type: 'post',
			dataType: 'json',
			contentType: 'application/json',
			data: JSON.stringify(vo),
			success: function(response) {
				if(response.result !== 'success') {
					
					console.error(response.message);
					return;
				}
				
				var html = render(response.data);
				$("#list-guestbook").prepend(html);
			}
		});
	});
	
	// 삭제 다이알로그 객체 만들기
	var dialogDelete = $("#dialog-delete-form").dialog({
		autoOpen: false,
		modal: true,
		buttons: {
			"삭제": function() {
				var no = $("#hidden-no").val();
				var password = $("#password-delete").val();
				var url = "${pageContext.request.contextPath }/api/guestbook/delete/" + no;
				
				$.ajax({
					url: url,
					type: 'delete',
					dataType: 'json',
					data: "password=" + password,
					success: function(response) {
						if(response.result !== 'success') {
							console.error(response.message);
							return;
						}
						
						if(response.data == -1) {
							$(".validateTips.error").show();
							$("#password-delete").val("").focus();
							return;
						}
						
						// 삭제가 된 경우
						$("#list-guestbook li[data-no='"   +  response.data + "']").remove();
						dialogDelete.dialog('close');
					}
				});
			},
			"취소": function() {
				$(this).dialog('close');
			}
		},
		close: function() {
			$(".validateTips.error").hide();
			$("#password-delete").val("");
			$("#hidden-no").val("");
		}
	});
 
	// 글삭제 버튼 Click 이벤트 처리(Live Event)
	$(document).on('click', "#list-guestbook li a", function(event) { //이벤트를 위임시킬때는 on 사용
		event.preventDefault(); 
		
		var no = $(this).data("no");
		$("#hidden-no").val(no);
		dialogDelete.dialog('open');
	});
	
	// 최초 리스트 가져오기
	fetch();
	
	$(window).scroll(function() {
		var $window = $(this);
		var $document = $(document);
		var windowHeight = $window.height();
		var documentHeight = $document.height();
		var scrollTop = $window.scrollTop();
		
		if (scrollTop + windowHeight == documentHeight) {
		
			fetch();
		}
	});
});
</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="guestbook">
				<h1>방명록</h1>
				<form id="add-form" action="" method="post">
					<input type="text" id="input-name" placeholder="이름"> <input
						type="password" id="input-password" placeholder="비밀번호">
					<textarea id="tx-content" placeholder="내용을 입력해 주세요."></textarea>
					<input type="submit" value="보내기" />
				</form>
				<ul id="list-guestbook">



				</ul>
			</div>
			<div id="dialog-delete-form" title="메세지 삭제" style="display: none">
				<p class="validateTips normal">작성시 입력했던 비밀번호를 입력하세요.</p>
				<p class="validateTips error" style="display: none">비밀번호가 틀립니다.</p>
				<form>
					<input type="password" id="password-delete" value=""
						class="text ui-widget-content ui-corner-all"> <input
						type="hidden" id="hidden-no" value=""> <input
						type="submit" tabindex="-1"
						style="position: absolute; top: -1000px">
				</form>
			</div>
			<div id="dialog-message" title="" style="display: none">
				<p></p>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="guestbook-spa" />
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>