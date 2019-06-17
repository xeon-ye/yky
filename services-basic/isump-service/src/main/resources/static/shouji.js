jQuery(document).ready(function () {
    checksjh = function () {
        var sj = jQuery("#field7716").val();
        alert(sj);
        var total = sj.length;
        if (typeof sj === 'number') {
            if (total == 11) {
                return true;
            }
        } else {
            alert("手机号不符合规范，请重新填写");
            return false;
        }
    }
});


function validatemobile() {
    if (mobile.length == 0) {
        alert('请输入手机号码！');
        document.form1.mobile.focus();
        return false;
    }
    //座机号
    var reg = /^ 0\d{2,3}-\d{7,8}$/
    if (mobile.length != 11) {
        //如果不是11位就判断是否是座机号,如果不是座机号,则提示错误
        if (!reg.test(mobile)) {
            alert('请输入有效的手机号码！');
            document.form1.mobile.focus();
        }
        return false;
    }
    // 手机号
    var myreg = /^1[34578]\d{9}$/;
    if (!myreg.test(mobile)) {
        alert('请输入有效的手机号码！');
        document.form1.mobile.focus();
        return false;
    }
}