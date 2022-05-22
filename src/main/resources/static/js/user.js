$(function(){
    const id=$('#id');
    const username=$('#username');
    const password=$('#password');
    const btnEdit=$('.btn-edit');
    $("#editUser").on('show.bs.modal',function(e){
        const btn=$(e.relatedTarget);
        const ind=btnEdit.index(btn);
        const items=$('.user-item').eq(ind).children('td');
        
        id.attr('value',items.eq(0).text());
        
        username.attr('value',items.eq(1).text());
        
        password.attr('value',items.eq(2).text());
    });
});