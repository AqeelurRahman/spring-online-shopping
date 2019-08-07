$(function () {
    switch (menu) {
        case 'About Us' :
            $('#about').addClass('active');
            break;
        case 'Contact Us' :
            $('#contact').addClass('active');
            break;
        case 'All Products' :
            $('#listAllProduct').addClass('active');
            break;
        default:
            $('#home').addClass('active');
            $('#a_' + menu).addClass('active');
            break;

    }

//    code for jquery data table
//    create a data set

    var $table = $('#productlistTable');
//    execute the code where this table is present
    if ($table.length){

        var jsonurl = '';
        if (window.categoryId==''){
            jsonurl = window.contextRoot + '/json/data/all/products';
        } else {
            jsonurl = window.contextRoot + '/json/data/category/'+window.categoryId+'/products';

        }
        console.log('Inside the table');
        $table.DataTable({
           ajax:{
               url:jsonurl,
               dataSrc:''
           },
            columns:[
                {
                    data:'code',
                    mRender: function (data, type, row) {
                        return'<img src="'+window.contextRoot+'/resources/images/'+data+'.jpg" class="dataTableImg"/>'
                    }
                },
                {
                    data:'name'

                },
                {
                    data:'brand'

                },
                {

                    data:'unitPrice',
                    mRender: function (data, type, row) {
                        return '&#8360; ' + data;
                    }
                },
                {
                    data:'quantity'

                },
                {
                    data:'id',
                    bSortable:false,
                    mRender:function (data, type, row) {
                        var str= '';
                        str+='<a href="'+window.contextRoot+'/show/'+data+'/product" class="btn btn-sm btn-primary"><span class="glyphicon glyphicon-eye-open"/>ViewProduct</a>';
                        str+='<a href="'+window.contextRoot+'/cart/add/'+data+'/product"class="btn btn-sm btn-success"><span class="glyphicon glyphicon-shopping-cart"/>Add to Cart</a>';
                        return str;
                    }

                }

            ]
        });


    }



});



