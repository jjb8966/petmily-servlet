<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Petmily-Don't buy, Do Adopt</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link href="https://fonts.googleapis.com/css?family=Montserrat:200,300,400,500,600,700,800&display=swap"
          rel="stylesheet">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

    <link rel="stylesheet" href="../../../petsitting-master/css/animate.css">

    <link rel="stylesheet" href="../../../petsitting-master/css/owl.carousel.min.css">
    <link rel="stylesheet" href="../../../petsitting-master/css/owl.theme.default.min.css">
    <link rel="stylesheet" href="../../../petsitting-master/css/magnific-popup.css">


    <link rel="stylesheet" href="../../../petsitting-master/css/bootstrap-datepicker.css">
    <link rel="stylesheet" href="../../../petsitting-master/css/jquery.timepicker.css">

    <link rel="stylesheet" href="../../../petsitting-master/css/flaticon.css">
    <link rel="stylesheet" href="../../../petsitting-master/css/style.css">
</head>

<%@ include file="/WEB-INF/view/include/header.jspf" %>

<section class="hero-wrap hero-wrap-2" style="background-image: url('images/bg_2.jpg');"
         data-stellar-background-ratio="0.5">
    <div class="overlay"></div>
    <div class="container">
        <div class="row no-gutters slider-text align-items-end">
            <div class="col-md-9 ftco-animate pb-5">
<%--                <p class="breadcrumbs mb-2"><span class="mr-2">--%>
<%--                    <a href="index.html">Home <i class="ion-ios-arrow-forward"></i></a></span> <span>Veterinarian <i class="ion-ios-arrow-forward"></i></span></p>--%>
                <h1 class="mb-0 bread">유기동물 조회</h1>
            </div>
        </div>
    </div>
</section>

<section class="ftco-section bg-light">
    <div class="container">
        <%--        <div class="row">--%>
        <%--            <div class="col-md-6 col-lg-3 ftco-animate">--%>
        <%--                <div class="staff">--%>
        <%--                    <div class="img-wrap d-flex align-items-stretch">--%>
        <%--                        <div class="img align-self-stretch" style="background-image: url(images/staff-1.jpg);"></div>--%>
        <%--                    </div>--%>
        <%--                    <div class="text pt-3 px-3 pb-4 text-center">--%>
        <%--                        <h3>Lloyd Wilson</h3>--%>
        <%--                        <span class="position mb-2">Health Coach</span>--%>
        <%--                        <div class="faded">--%>
        <%--                            <p>I am an ambitious workaholic, but apart from that, pretty simple person.</p>--%>
        <%--                            <ul class="ftco-social text-center">--%>
        <%--                                <li class="ftco-animate"><a href="#" class="d-flex align-items-center justify-content-center"><span class="fa fa-twitter"></span></a></li>--%>
        <%--                                <li class="ftco-animate"><a href="#" class="d-flex align-items-center justify-content-center"><span class="fa fa-facebook"></span></a></li>--%>
        <%--                                <li class="ftco-animate"><a href="#" class="d-flex align-items-center justify-content-center"><span class="fa fa-google"></span></a></li>--%>
        <%--                                <li class="ftco-animate"><a href="#" class="d-flex align-items-center justify-content-center"><span class="fa fa-instagram"></span></a></li>--%>
        <%--                            </ul>--%>
        <%--                        </div>--%>
        <%--                    </div>--%>
        <%--                </div>--%>
        <%--            </div>--%>
        <%--        </div>--%>

        <%--            <c:forEach var="boardVO" items="${boardList}">    --%>
        <%--                <p><c:out value="${boardVO.title}" /></p>--%>
        <%--            </c:forEach>--%>


        <%--                <tr>--%>
        <%--                    <td>${article.number}</td>--%>
        <%--                    <td>--%>
        <%--                        <a href="read.do?no=${article.number}&pageNo=${articlePage.currentPage}">--%>
        <%--                            <c:out value="${article.title}"/>--%>
        <%--                        </a>--%>
        <%--                    </td>--%>
        <%--                    <td>${article.writer.name}</td>--%>
        <%--                    <td>${article.readCount}</td>--%>
        <%--                </tr>--%>

        <div class="row">
            <c:forEach var="abandonedAnimal" items="${abandonedAnimals.content}">
                <div class="col-md-6 col-lg-3 ftco-animate" onclick="location.href='/abandoned_animal/detail.do?abNumber=${abandonedAnimal.abNumber}'">
                    <div class="staff">
                        <div class="img-wrap d-flex align-items-stretch">
                            <div class="img align-self-stretch" style="background-image: url(${abandonedAnimal.imgPath});"></div>
                        </div>

                        <div class="text pt-3 px-3 pb-4 text-center">
                            <h3>${abandonedAnimal.name}</h3>
                            <span class="position mb-2">${abandonedAnimal.location}</span>
                            <div class="faded">
                                <p>${abandonedAnimal.admissionDate}</p>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
<%--        <div>--%>
<%--            <c:if test="${abandonedAnimals.hasAnimals()}">--%>
<%--                <c:if test="${abandonedAnimals.startPage > 5}">--%>
<%--                    <a href="/abandoned_animal/list.do?pageNo=${abandonedAnimals.startPage - 5}">[이전]</a>--%>
<%--                </c:if>--%>
<%--                <c:forEach var="pNo"--%>
<%--                           begin="${abandonedAnimals.startPage}"--%>
<%--                           end="${abandonedAnimals.endPage}">--%>
<%--                    <a href="/abandoned_animal/list.do?pageNo=${pNo}">[${pNo}]</a>--%>
<%--                </c:forEach>--%>
<%--                <c:if test="${abandonedAnimals.endPage < abandonedAnimals.totalPages}">--%>
<%--                    <a href="/abandoned_animal/list.do?pageNo=${abandonedAnimals.startPage + 5}">[다음]</a>--%>
<%--                </c:if>--%>
<%--            </c:if>--%>
<%--        </div>--%>
    </div>
</section>

<%@ include file="/WEB-INF/view/include/footer.jspf" %>

<!-- loader -->
<div id="ftco-loader" class="show fullscreen">
    <svg class="circular" width="48px" height="48px">
        <circle class="path-bg" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke="#eeeeee"/>
        <circle class="path" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke-miterlimit="10"
                stroke="#F96D00"/>
    </svg>
</div>


<script src="../../../petsitting-master/js/jquery.min.js"></script>
<script src="../../../petsitting-master/js/jquery-migrate-3.0.1.min.js"></script>
<script src="../../../petsitting-master/js/popper.min.js"></script>
<script src="../../../petsitting-master/js/bootstrap.min.js"></script>
<script src="../../../petsitting-master/js/jquery.easing.1.3.js"></script>
<script src="../../../petsitting-master/js/jquery.waypoints.min.js"></script>
<script src="../../../petsitting-master/js/jquery.stellar.min.js"></script>
<script src="../../../petsitting-master/js/jquery.animateNumber.min.js"></script>
<script src="../../../petsitting-master/js/bootstrap-datepicker.js"></script>
<script src="../../../petsitting-master/js/jquery.timepicker.min.js"></script>
<script src="../../../petsitting-master/js/owl.carousel.min.js"></script>
<script src="../../../petsitting-master/js/jquery.magnific-popup.min.js"></script>
<script src="../../../petsitting-master/js/scrollax.min.js"></script>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVWaKrjvy3MaE7SQ74_uJiULgl1JY0H2s&sensor=false"></script>
<script src="../../../petsitting-master/js/google-map.js"></script>
<script src="../../../petsitting-master/js/main.js"></script>


</body>
</html>