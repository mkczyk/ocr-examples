const $form = document.querySelector("#form")
const $path = $form.querySelector("#path")
const $textarea = document.querySelector("#response_area")

$form.addEventListener("submit", e => {
    e.preventDefault();
    fetch(`/api/tess/path?absolute=${$path.value}`)
        .then(response => response.text())
        .then(data => $textarea.value = data)
        .catch(err => console.log(err))
})
