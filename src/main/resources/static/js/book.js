$(function () {
    const id = $('#id');
    const bookname = $('#bookname');
    const writer = $('#writer');
    const publish = $('#publish');
    const btnEdit = $('.btn-edit');
    const ISBN = $('#ISBN');
    const btnDel = $('.btn-del')

    $("#editBook").on('show.bs.modal', function (e) {
        const btn = $(e.relatedTarget);
        const ind = btnEdit.index(btn);
        const items = $('.book-item').eq(ind).children('td');

        bookname.attr('value', items.eq(1).text());

        writer.attr('value', items.eq(2).text());

        publish.attr('value', items.eq(3).text());

        ISBN.attr('value', items.eq(4).text());

        const id = items.eq(0).text();

        document.getElementById("bookUpdate-form").setAttribute("action","/admin/bookUpdate/"+id);

    });

    $("#delBook").on('show.bs.modal', function (e) {
        const btn = $(e.relatedTarget);
        const ind = btnDel.index(btn);
        const items = $('.book-item').eq(ind).children('td');

        const id = items.eq(0).text();

        //alert(id);

        document.getElementById("delBook-btn").setAttribute("href","/admin/bookDelete/"+id)

    });


});
