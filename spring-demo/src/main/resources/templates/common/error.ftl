<html>
<#include "../common/header.ftl">

<body>
<script>
    window.onload = function () {
        var index = 3;
        var times = setInterval(times,1000);

        function times() {

            document.getElementById("time").innerHTML = --index;
            if (i == 0){
                clearInterval(times());
            }
        }

    }
</script>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="alert alert-dismissable alert-danger">
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                <h4>
                    错误!
                </h4> <strong>${msg}</strong><a href="${url}" class="alert-link"><span id="time">3</span>s后自动跳转</a>
            </div>
        </div>
    </div>
</div>

</body>
</html>
<script type="application/javascript">
    setTimeout('location.href="${url}"', 3000);
</script>