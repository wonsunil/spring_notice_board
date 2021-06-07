const $form = $("form");
$form.on("submit", event => {
    event.preventDefault();

    if($form.find("#title").val() === "") {
        alert("제목을 입력해주세요");

        return $form.find("#title").focus();
    };

    if($form.find("#content").val() === "") {
        alert("내용을 입력해주세요");

        return $form.find("#content").focus();
    };

    return $form[0].submit();
});