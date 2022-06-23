<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <link rel="stylesheet" href="/css/join.css">
</head>
<body>

<div class="section">
    <div class="container">
        <div class="form">
            <div class="left-side">
                <div>
                	<span class="brand">
                		<img src="https://i.imgur.com/zpXluzv.png">
                		<small>Petmily</small>
                	</span>
                    <form action="/join.do" method="post">
                        <div class="form-inputs">
                            <input type="text" name="id" placeholder="아이디">
                            
                            
                            <div class="password">
                                <input id="password" name="password" type="password" placeholder="비밀번호">
                                <span class="showpass" onclick="toggle()">
                            		<img id="changepasseye" src="https://i.imgur.com/d1M6y1W.jpg">
                            	</span>
                                <p class="random_password"></p>
                                
                                <input id="passwordCheck" name="passwordCheck" type="password" placeholder="비밀번호 확인">
                                <span class="showpass" onclick="toggle()">
                            		<img id="changepasseye" src="https://i.imgur.com/d1M6y1W.jpg">
                            	</span>
                                <p class="random_password"></p>
                            </div>
                            
                            <input type="text" name="name" placeholder="이름(닉네임)">
                            <input type="text" name="birth" placeholder="생년월일">
                            <input type="text" name="gender" placeholder="성별">
                            <input type="text" name="email" placeholder="이메일 주소">
                            <input type="text" name="phone" placeholder="연락처">


                            <input id="submit_button" type="submit" value="회원가입">
                            <p class="login-text">계정이 이미 있습니까? <a href="/login.do">login</a></p>
                        </div>
                    </form>
                </div>
            </div>

            <div class="right-side">
            	<h2>Petmily</h2>
            	<span class="circle1"></span>
            	<span class="circle2"></span>
            	<span class="circle3"></span>
                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1440 320">
                    <path fill="#000" fill-opacity="1" d="M0,96L48,122.7C96,149,192,203,288,229.3C384,256,480,256,576,234.7C672,213,768,171,864,176C960,181,1056,235,1152,229.3C1248,224,1344,160,1392,128L1440,96L1440,320L1392,320C1344,320,1248,320,1152,320C1056,320,960,320,864,320C768,320,672,320,576,320C480,320,384,320,288,320C192,320,96,320,48,320L0,320Z"></path>
                </svg>
            </div>
        </div>
    </div>
</div>
</body>
</html>