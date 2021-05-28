const $form = document.forms[0];
$form.addEventListener("submit", event => {
    event.preventDefault();

    if($form.title.value === "") {
        alert("제목을 입력해주세요");

        return $form.title.focus();
    };

    if($form.content.value === "") {
        alert("내용을 입력해주세요");

        return $form.content.focus();
    };

    return $form.submit();
});