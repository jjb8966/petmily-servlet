<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <title>Petmily-Don't buy, Do Adopt</title>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css?family=Montserrat:200,300,400,500,600,700,800&display=swap">
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

<section class="hero-wrap hero-wrap-2"
         style="background-image: url('../../../petsitting-master/images/bg_2.jpg');"
         data-stellar-background-ratio="0.5">
    <div class="overlay"></div>
    <div class="container">
        <div class="row no-gutters slider-text align-items-end">
            <div class="col-md-9 ftco-animate pb-5">
                <p class="breadcrumbs mb-2"><span>Find Animal Board<i class="ion-ios-arrow-forward"></i></span></p>
                <h1 class="mb-0 bread">반려동물 찾아요</h1>
            </div>
        </div>
    </div>
</section>

<section class="ftco-section bg-light">
    <div class="container">
        <div class="row d-flex">
            <c:forEach var="find" items="${Finds.content}">
                <div class="col-md-4 d-flex ftco-animate" id="d-flex-out">
                    <div class="blog-entry align-self-stretch" id="d-flex-in">
                        <a href="${pageContext.request.contextPath}/find/in.do?faNumber=${find.faNumber}"
                           class="block-20 rounded"
                           style="background-image: url('/upload/${find.imgPath}');">
                        </a>
                        <div class="text p-4">
                            <div class="meta mb-2">
                                <div><a href="#">${find.species}</a></div>
                                <div><a href="#">${find.location}</a></div>
                                <br/>
                                <div><a href="#">작성자: ${find.name}</a></div>
                                <br/>
                                <div><a href="#">${find.wrTime}</a></div>
                                <br/>
                                <div><a href="#">상태: ${find.animalState}</a></div>
                            </div>
                            <h3 class="heading"><a href="#">${find.title}</a></h3>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>

        <span class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="location.href='/find/write.do'">글쓰기</button>
        </span>


        <div class="row mt-5">
            <div class="col text-center">
                <div class="block-27">
                    <ul>
                        <li>
                            <c:if test="${Finds.startPage > 5}">
                                <a href="${pageContext.request.contextPath}/find/list.do?pageNo=${Finds.startPage - 5}">&lt;</a>
                            </c:if>
                        </li>
                        <c:forEach var="pNo" begin="${Finds.startPage}" end="${Finds.endPage}">
                            <c:if test="${Finds.currentPage == pNo}">
                                <li class="active">
                                    <a href="${pageContext.request.contextPath}/find/list.do?pageNo=${pNo}">${pNo}</a>
                                </li>
                            </c:if>
                            <c:if test="${Finds.currentPage != pNo}">
                                <li>
                                    <a href="${pageContext.request.contextPath}/find/list.do?pageNo=${pNo}">${pNo}</a>
                                </li>
                            </c:if>
                        </c:forEach>
                        <li>
                            <c:if test="${Finds.endPage < Finds.totalPages}">
                                <a href="${pageContext.request.contextPath}/find/list.do?pageNo=${Finds.startPage + 5}">&gt;</a>
                            </c:if>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</section>

<%@ include file="/WEB-INF/view/include/footer.jspf" %>

<!-- loader -->
<div id="ftco-loader" class="show fullscreen">
    <svg class="circular" width="48px" height="48px">
        <circle class="path-bg" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke="#eeeeee"></circle>
        <circle class="path" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke-miterlimit="10"
                stroke="#F96D00"></circle>
    </svg>
</div>

<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVWaKrjvy3MaE7SQ74_uJiULgl1JY0H2s&sensor=false"></script>
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
<script src="../../../petsitting-master/js/google-map.js"></script>
<script src="../../../petsitting-master/js/main.js"></script>

</html>