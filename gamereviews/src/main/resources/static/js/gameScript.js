let commentBox = document.getElementById("commentBox")
let postButton = document.getElementById("postButton")

function enableComments(isAuthenticated) {
	if (isAuthenticated) {
		console.log("Logged in");
		commentBox.placeholder = "Enter comment";
		commentBox.disabled = false;
		postButton.hidden = false;
	} else {
		console.log("Logged out");
		commentBox.placeholder = "Please login to post a comment";
		commentBox.disabled = true;
		postButton.hidden = true;
	}
}

function showCommentUpdate(id) {
	let commentInfo = document.getElementById("comment-info-" + id)
	commentInfo.hidden = true;
	
	let comment = document.getElementById("comment-" + id)
	comment.hidden = true;
	
	let update = document.getElementById("update-" + id)
	update.hidden = false;
	
	let updateBox = document.getElementById("update-comment-" + id)
	updateBox.value = comment.innerText.trim();
	
}

function cancelUpdate(id) {
	let commentInfo = document.getElementById("comment-info-" + id)
	commentInfo.hidden = false;
	
	let comment = document.getElementById("comment-" + id)
	comment.hidden = false;
	
	let update = document.getElementById("update-" + id)
	update.hidden = true;
}