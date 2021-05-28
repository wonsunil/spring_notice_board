const $form = document.forms[0];
$form.addEventListener("submit", event => {
    event.preventDefault();

    if($form.id.value === "") {
        alert("아이디를 입력해주세요");

        return $form.id.focus();
    };

    if($form.password.value === "") {
        alert("비밀번호를 입력해주세요");

        return $form.password.focus();
    };

    if($form.name.value === "") {
        alert("이름을 입력해주세요");

        return $form.name.focus();
    };

    fetch(`/user/${$form.id.value}/check`, {
        method: "POST"
    })
        .then(res => res.text())
        .then(data => data === "true" ? (() => {
            alert("중복된 아이디입니다");

            return $form.id.focus();
        })() : $form.submit());
});