function updateslider(min, max) {
	new Control.Slider('handle1', 'track1', {
		range : $R(parseInt(min), parseInt(max)),
		sliderValue : parseInt(min),
		onSlide : function(v) {
			$('slide').value = Math.ceil(v)
		},
		onChange : function(v) {
			$('slide').value = Math.ceil(v);
			updatePawn()
		}
	});
}
function initPawn() {
	document.pawn.slide.value = document.pawn.minimum.value;
	updateslider(document.pawn.minimum.value, document.pawn.maximum.value);
	updatePawn();
}
function updatePawn() {
	document.pawn.loanamt.value = document.pawn.slide.value
			* document.pawn.weight.value;
	document.pawn.net.value = document.pawn.loanamt.value;
	document.pawn.loanword.value = AmtInWords(document.pawn.loanamt.value,
			'Pesos Only');
	document.pawn.appamt.value = parseFloat(document.pawn.loanamt.value) + 100;
	document.pawn.appword.value = AmtInWords(document.pawn.appamt.value,
			'Pesos Only');
	document.pawn.pri.value = document.pawn.loanamt.value;

}