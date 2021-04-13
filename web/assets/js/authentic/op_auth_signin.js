/*
 *  Document   : op_auth_signin.js
 *  Author     : pixelcave
 */
var OpAuthSignIn = function() {
    var e = function() {
        jQuery(".js-validation-signin").validate({
            errorClass: "invalid-feedback animated fadeInDown",
            errorElement: "div",
            errorPlacement: function(e, n) {
                jQuery(n).parents(".form-group > div").append(e)
            },
            highlight: function(e) {
                jQuery(e).closest(".form-group").removeClass("is-invalid").addClass("is-invalid")
            },
            success: function(e) {
                jQuery(e).closest(".form-group").removeClass("is-invalid"),
                    jQuery(e).remove()
            },
            rules: {
                "login-email": { required: !0, minlength: 8 },
                "login-password": { required: !0, minlength: 8 }
            },
            messages: {
                "login-email": {
                    required: "Yêu cầu nhập email",
                    minlength: "Email của bạn phải có ít nhất 8 ký tự và chứa ký tự @"
                },
                "login-password": {
                    required: "Yêu cầu nhập mật khẩu",
                    minlength: "Mật khẩu của bạn phải có ít nhất 8 ký tự"
                }
            }
        })
    };
    return { init: function() { e() } }
}();
jQuery(function() { OpAuthSignIn.init() });