<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Petmily-Don't buy, Do Adopt</title>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<link
	href="https://fonts.googleapis.com/css?family=Montserrat:200,300,400,500,600,700,800&display=swap"
	rel="stylesheet">

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

<link href="https://fonts.googleapis.com/css?family=Merriweather"
	rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Raleway"
	rel="stylesheet">

<link rel="stylesheet" href="/petsitting-master/css/animate.css">

<link rel="stylesheet"
	href="/petsitting-master/css/owl.carousel.min.css">
<link rel="stylesheet"
	href="/petsitting-master/css/owl.theme.default.min.css">
<link rel="stylesheet" href="/petsitting-master/css/magnific-popup.css">


<link rel="stylesheet"
	href="/petsitting-master/css/bootstrap-datepicker.css">
<link rel="stylesheet"
	href="/petsitting-master/css/jquery.timepicker.css">

<link rel="stylesheet" href="/petsitting-master/css/flaticon.css">
<link rel="stylesheet" href="/petsitting-master/css/style.css">

<style>
.checkboxlabel {
	display: flex;
	justify-content: space-between;
	text-align: justify;
}

.message {
	display: flex;
	justify-content: space-between;
}

textarea {
	width: 82%;
}

.radiobuttons {
	display: flex;
}

.survey {
	font-family: 'Raleway', sans-serif;
	margin-top: 25px;
}

h1 {
	font-weight: 400;
	font-family: 'Merriweather', serif
}

#description {
	font-family: 'Merriweather', serif
}

#arrange {
	float: right;
	position: relative;
	left: -50%;
}

.arr {
	margin: auto;
	display: block;
	width: 100px;
}

.inline {
	display: block;
}

@media ( max-width :500px) {
	.checkboxlabel {
		display: block;
	}
}
</style>

<script>
function doOpenCheck(chk){
    var obj = document.getElementsByName("donaSum");
    for(var i=0; i<obj.length; i++){
        if(obj[i] != chk){
            obj[i].checked = false;
        }
    }
}

function zero() {
	document.getElementById('a').value = "";
}


</script>


</head>

<%@ include file="/WEB-INF/view/include/header.jspf"%>

<body>
	<section class="hero-wrap hero-wrap-2"
		style="background-image: url('images/bg_2.jpg');"
		data-stellar-background-ratio="0.5">
		<div class="overlay"></div>
		<div class="container">
			<div class="row no-gutters slider-text align-items-end">
				<div class="col-md-9 ftco-animate pb-5">
					<%--                <p class="breadcrumbs mb-2"><span class="mr-2">--%>
					<%--                    <a href="index.html">Home <i class="ion-ios-arrow-forward"></i></a></span> <span>Veterinarian <i class="ion-ios-arrow-forward"></i></span></p>--%>
					<h1 class="mb-0 bread">Donation</h1>
				</div>
			</div>
		</div>
	</section>
	<!-- class="container survey" -->
	<!-- servey form -->
	<br>
	<div class="container survey">
		<h1 id="title" class="text-center">후원하기</h1>
		<p id="description" class="text-center"></p>
		<a href="/abandoned_animal/donate.do" ></a>
		<br>


		<!-- --------------------------------------------- -->

		<br>
		<form name="donaform" id="survey-form" method="post" action="/abandoned_animal/auth/donate.do?abNumber=${param.abNumber}">
			<div class="form-row">
				<div class="col">
					<label for="exampleFormControlSelect2">은행명</label> 
					<select name="bank" class="form-control" id="dropdown">
						<option>국민은행</option>
						<option>IBK기업은행</option>
						<option>KEB하나은행</option>
						<option>신한은행</option>
						<option>우리은행</option>
						<option>한국씨티뱅크</option>
						<option>SC제일은행</option>
						<option>농협은행</option>
						<option>수협은행</option>
						<option>우체국</option>
						<option>토스</option>
						<option>카카오뱅크</option>
						<option>제주은행</option>
					</select>
				</div>
				<div class="col">
					<label id="name-label">예금주</label> <input name="accountHolder" id="name" type="name"
						class="form-control" placeholder="예금주" required>
				</div>
				<div class="col">
					<label for="formGroupExampleInput">계좌번호</label> <input
						name="accountNumber" id="last-name" type="name" class="form-control" placeholder="계좌번호"
						required>
				</div>
			</div>
<!-- 		</form> -->
		<br> <br>

		<!-- --------------------------------------------- -->


<!-- 	<form method="post" action="/donate.do"> -->
		<div class="form-row">
			<div class="col">
				<!-- 			<ul class="checkboxlabel"> -->
				<li style="list-style: none;"><label>
						<input name="donaSum" value="20000" onclick="doOpenCheck(this);zero();" type="checkbox" class="userRatings">
						20,000원
				</label></li>
			</div>
			<div class="col">
				<li style="list-style: none;"><label>
						<input name="donaSum" value="30000" onclick="doOpenCheck(this);zero();" type="checkbox" class="userRatings">
						30,000원
				</label></li>
			</div>

			<div class="col">
				<li style="list-style: none;"><label>
						<input name="donaSum" value="50000" onclick="doOpenCheck(this);zero();" type="checkbox" class="userRatings">
						50,000원
				</label></li>
			</div>

			<div class="col">
				<li style="list-style: none;"><label>
						<input name="donaSum" value="100000" onclick="doOpenCheck(this);zero();" type="checkbox" class="userRatings">
						100,000원
				</label></li>
			</div>

			<div class="inline">
				<input id="a" name="donaSum" onclick="doOpenCheck(this);" type="text" placeholder="직접 입력" /> 원
			</div>
		</div>
		
		<br>


		<!-- --------------------------------------------- -->
		
		
<!-- 		<form id="survey-form"> -->
<!-- 			<div class="form-row"> -->
<!-- 				<div class="col"> -->
<!-- 					<label id="name-label">이름</label> <input id="name" type="name" -->
<!-- 						class="form-control" maxlength="6" required> -->
<!-- 				</div> -->
<!-- 				<div class="col"> -->
<!-- 					<label for="formGroupExampleInput">휴대전화</label> <input -->
<!-- 						id="last-name" type="name" class="form-control" required> -->
<!-- 				</div> -->



<!-- 			</div> -->

<!-- 			<br> -->

<!-- 			<form id="survey-form"> -->
<!-- 				<div class="form-row"> -->
<!-- 					<div class="col"> -->
<!-- 						<label id="name-label">이메일</label> <input id="name" type="name" -->
<!-- 							class="form-control" placeholder="e-mail" required> -->
<!-- 					</div> -->
<!-- 					<div class="col"> -->
<!-- 						<label for="formGroupExampleInput">생년월일</label> <input -->
<!-- 							id="last-name" type="name" class="form-control" -->
<!-- 							placeholder="주민번호 앞 6자리" maxlength="6" required> -->
<!-- 					</div> -->
<!-- 				</div> -->


			<!-- --------------------------------------------- -->

				<br>
				<div class="arr">
					<button id="submit" type="submit" class="btn btn-secondary">후원하기</button>
				</div>
			
			</form>
			<br> <br>

			<%@ include file="/WEB-INF/view/include/footer.jspf"%>


			<script src="/petsitting-master/js/jquery.min.js"></script>
			<script src="/petsitting-master/js/jquery-migrate-3.0.1.min.js"></script>
			<script src="/petsitting-master/js/popper.min.js"></script>
			<script src="/petsitting-master/js/bootstrap.min.js"></script>
			<script src="/petsitting-master/js/jquery.easing.1.3.js"></script>
			<script src="/petsitting-master/js/jquery.waypoints.min.js"></script>
			<script src="/petsitting-master/js/jquery.stellar.min.js"></script>
			<script src="/petsitting-master/js/jquery.animateNumber.min.js"></script>
			<script src="/petsitting-master/js/bootstrap-datepicker.js"></script>
			<script src="/petsitting-master/js/jquery.timepicker.min.js"></script>
			<script src="/petsitting-master/js/owl.carousel.min.js"></script>
			<script src="/petsitting-master/js/jquery.magnific-popup.min.js"></script>
			<script src="/petsitting-master/js/scrollax.min.js"></script>
			<script
				src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVWaKrjvy3MaE7SQ74_uJiULgl1JY0H2s&sensor=false"></script>
			<script src="/petsitting-master/js/google-map.js"></script>
			<script src="/petsitting-master/js/main.js"></script>
</body>
</html>