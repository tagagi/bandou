$(function(){
    const btnReplies=$(".btn-reply");
    const inputComment=$("#input-comment");
    const commnetForm=$("#comment-form");

    btnReplies.click(function(e){
        const commentData = btnReplies.eq(btnReplies.index(e.currentTarget));
        const parentId = commentData.data("parentcommentid");
        const bookId = commentData.data("bookid");

        inputComment.attr("placeholder",`@${commentData.data("username")}`);
        inputComment.focus();

        commnetForm.attr("action","/reply/"+bookId+"/"+parentId);

    });
});

