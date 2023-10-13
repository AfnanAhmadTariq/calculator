function updateDisplay() {
    var userInput = document.getElementById("userInput").value;
    var displayArea = document.getElementById("displayArea");
    displayArea.textContent = Caclulator.calSimple(userInput);
}
