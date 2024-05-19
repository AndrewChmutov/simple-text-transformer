const transformationButtons = [
    "To upper",
    "To lower",
    "Capitalize",
    "Reverse",
    "Integer conversion",
    "Acronym compression",
    "Acronym expansion",
    "Latex",
    "Trim repetitions"
]

const transformationOptions = [
    "upper",
    "lower",
    "capitalize",
    "reverse",
    "int_conversion",
    "acronym_compress",
    "acronym_expand",
    "latex",
    "trim_repetitions"
]

let transformationMapping = {};
for (let i = 0; i < transformationOptions.length; i++) {
    transformationMapping[transformationButtons[i]] = transformationOptions[i];
}

// create list of transformations
const dragBox = document.querySelector('.dragBox')
new Sortable(dragBox,{
    handle: '.glyphicon-move',
    animation: 100,
})



transformationButtons.forEach(label => {
    const newButton = document.createElement("button");
    newButton.classList.add("transformation-button")
    newButton.textContent = label;
    newButton.addEventListener('click', function () {
        const controls = document.createElement('div');
        controls.classList.add('controls')

        const moveButton = document.createElement('i');
        moveButton.classList.add('fas', 'fa-bars', 'control', 'glyphicon-move');

        const deleteButton = document.createElement('i');
        deleteButton.classList.add('fas', 'fa-minus', 'control', 'remove-handle');
        deleteButton.addEventListener('click', function () {
            const listItem = this.parentNode;
            listItem.parentNode.parentNode.removeChild(listItem.parentNode);
        });

        controls.appendChild(deleteButton);
        controls.appendChild(moveButton);

        const text = document.createElement('span');
        text.classList.add('text');
        text.textContent = label;

        const newItem = document.createElement('div');
        newItem.classList.add('listItem')
        newItem.appendChild(text);
        newItem.appendChild(controls);
        document.getElementById('dragBox').appendChild(newItem);
    });
    document.getElementById('transformation-buttons').appendChild(newButton);
});


function transform() {
    const outputText = document.getElementById("outputText");
    const inputText = document.getElementById("inputText").value;
    if (inputText.length === 0) {
        outputText.style.color = "#aaa";
        outputText.textContent = "Empty..."
        return;
    }

    const to_do = document.querySelectorAll(".listItem");
    const labels = Array
        .from(to_do)
        .map(x => x.firstChild)
        .map(x => x.textContent)
        .map(x => transformationMapping[x]);
    const apiUrl = 'http://localhost:8080/get?q=' + encodeURIComponent(inputText) + '&transforms=' + labels.join(',');
    console.log(apiUrl);

    fetch(apiUrl)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            console.log(data);
            outputText.textContent = null;
            outputText.style.color = null;
            outputText.textContent = data.output;
        })
        .catch(error => {
            console.error('Error:', error);
        });
}

function copyClicked() {
    const copyText = document.getElementById("outputText");
    navigator.clipboard.writeText(copyText.textContent);

    const tooltip = document.getElementById("copyText");
    tooltip.innerHTML = "Copied!";
}

function outFunc() {
    const tooltip = document.getElementById("copyText");
    tooltip.innerHTML = "Copy to clipboard";
}

function autoExpandTextarea() {
    const textarea = this;
    textarea.style.height = "auto"
    textarea.style.height = textarea.scrollHeight + "px";
}

const textarea = document.getElementById("inputText");
textarea.addEventListener("input", autoExpandTextarea);
autoExpandTextarea.call(textarea)