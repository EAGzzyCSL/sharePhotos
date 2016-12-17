/*实现上传图片时的图片预览功能*/
var uploadImg = function(div_getimg, input_getimg, img_preview) {
	div_getimg.ondragover = function() {
		this.classList.add("dragover");
	}
	div_getimg.ondrop = function() {
		this.classList.remove("dragover");
	}
	if (typeof FileReader != 'undefined') {
		input_getimg.onchange = function() {
			console.log(this.files.length);
			if (this.files[0] != undefined) {
				var imgReader = new FileReader();
				imgReader.onload = function(event) {
					img_preview.setAttribute("src", event.target.result);
				};
				imgReader.readAsDataURL(this.files[0]);
			}
		};
	} else {
		alert("you webbrowser could not support FileReader");
	}

};