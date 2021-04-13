/*
 *  Document   : op_auth_signup.js
 *  Author     : pixelcave
 */
var OpAuthSignUp = function() {
    var e = function() {
        jQuery(".js-validation-signup").validate({
            errorClass: "invalid-feedback animated fadeInDown",
            errorElement: "div",
            errorPlacement: function(e, s) {
                jQuery(s).parents(".form-group > div").append(e)
            },
            highlight: function(e) {
                jQuery(e).closest(".form-group").removeClass("is-invalid").addClass("is-invalid")
            },
            success: function(e) {
                jQuery(e).closest(".form-group").removeClass("is-invalid"),
                    jQuery(e).remove()
            },
            rules: {
                "signup-username": { required: !0, minlength: 3 },
                "signup-email": { required: !0, email: !0 },
                "signup-password": { required: !0, minlength: 6 },
                "signup-password-confirm": { required: !0, equalTo: "#signup-password" },
                "signup-terms": { required: !0 }
            },
            messages: {
                "signup-username": {
                    required: "Yêu cầu nhập tên người dùng",
                    minlength: "Tên người dùng của bạn phải có ít nhất 3 ký tự"
                },
                "signup-email": "Yêu cầu nhập địa chỉ email hợp lệ",
                "signup-password": {
                    required: "Yêu cầu nhập mật khẩu",
                    minlength: "Mật khẩu của bạn phải có ít nhất 6 ký tự"
                },
                "signup-password-confirm": {
                    required: "Yêu cầu nhập mật khẩu xác nhận",
                    minlength: "Mật khẩu của bạn phải có ít nhất 6 ký tự",
                    equalTo: "Xác nhận mật khẩu không khớp"
                },
                "signup-terms": "Bạn cần đồng ý với điều khoản sử dụng trước khi có thể đăng ký!"
            }
        })
    };
    return { init: function() { e() } }
}();
jQuery(function() { OpAuthSignUp.init() });