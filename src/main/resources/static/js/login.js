const $form = $("form");
$form.on("submit", event => {
    event.preventDefault();

    if($form.find("#id").val() === "") {
        alert("아이디를 입력해주세요");

        return $form.find("#id").focus();
    };

    if($form.find("#password").val() === "") {
        alert("비밀번호를 입력해주세요");

        return $form.find("#password").focus();
    };

    $.ajax(`/user/${$form.find("#id").val()}/check`, {
        method: "POST"
    })
        .done(data => data === true ? $form[0].submit() : alert("존재하지 않는 아이디입니다"));
});