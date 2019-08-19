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
        case 'Manage Products' :
            $('#manageProducts').addClass('active');
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
    if ($table.length) {

        var jsonurl = '';
        if (window.categoryId == '') {
            jsonurl = window.contextRoot + '/json/data/all/products';
        } else {
            jsonurl = window.contextRoot + '/json/data/category/' + window.categoryId + '/products';

        }
        console.log('Inside the table');
        $table.DataTable({
            ajax: {
                url: jsonurl,
                dataSrc: ''
            },
            columns: [
                {
                    data: 'code',
                    mRender: function (data, type, row) {
                        return '<img src="' + window.contextRoot + '/resources/images/' + data + '.jpg" class="dataTableImg"/>'
                    }
                },
                {
                    data: 'name'

                },
                {
                    data: 'brand'

                },
                {

                    data: 'unitPrice',
                    mRender: function (data, type, row) {
                        return '&#8360; ' + data;
                    }
                },
                {
                    data: 'quantity',
                    mRender: function (data, type, row) {
                        if (data < 1) {
                            return '<span style="color: red">Out Of stock</span>';
                        }
                        return data;
                    }

                },
                {
                    data: 'id',
                    bSortable: false,
                    mRender: function (data, type, row) {
                        var str = '';
                        str += '<a href="' + window.contextRoot + '/show/' + data + '/product" class="btn btn-sm btn-primary"><span class="glyphicon glyphicon-eye-open"/>ViewProduct</a>';

                        if (row.quantity < 1) {
                            str += '<a href="javascript:void(0)"class="btn btn-sm btn success disabled"><span class="glyphicon glyphicon-shopping-cart"/>Add to Cart</a>';
                        } else {
                            if (userRole == 'ADMIN'){
                                str += '<a href="' + window.contextRoot + '/manage/' + data + '/product"class="btn btn-sm btn-success"><span class="glyphicon glyphicon-edit"/>Add to Cart</a>';

                            }

                            str += '<a href="' + window.contextRoot + '/cart/add/' + data + '/product"class="btn btn-sm btn-success"><span class="glyphicon glyphicon-shopping-cart"/>Add to Cart</a>';
                        }
                        return str;
                    }

                }

            ]
        });


    }

    var $alert = $('.alert');
    if ($alert.length) {
        setTimeout(function () {
            $alert.fadeOut('slow')
        }, 10000)
    }

//    ----------------Switch Button ----------------------
    $('.switch input[type="checkbox"]').on('change', function () {
        var dText = (this.checked) ? 'You want to activate the Product?' : 'You want to de-activate the Product?';
        var checked = this.checked;
        var checkbox = $(this);
        debugger;
        bootbox.confirm({
            size: 'medium',
            title: 'Product Activation/Deactivation',
            message: dText,
            callback: function (confirmed) {
                if (confirmed) {
                    $.ajax({
                        type: 'GET',
                        url: window.contextRoot + '/manage/products/' + checkbox.prop('value') + '/activation',
                        timeout: 100000,
                        success: function (data) {
                            bootbox.alert(data);
                            console.log(data);
                        },
                        error: function (e) {
                            bootbox.alert('ERROR: ' + e);
                            //display(e);
                        }
                    });
                } else {
                    checkbox.prop('checked', !checked);
                }
            }
        });
    });


//    ---------------
//    Data Table For Admin
//    ---------------------


    var $adminProductsTable = $('#adminProductsTable');
//    execute the code where this table is present
    if ($adminProductsTable.length) {

        var jsonUrl = window.contextRoot + '/json/data/admin/all/products';
        console.log(jsonUrl);
        $adminProductsTable.DataTable({
            lengthMenu: [[10, 30, 50, -1], ['10 Records', '30 Records', '50 Records', 'ALL']],
            pageLength: 30,
            ajax: {
                url: jsonUrl,
                dataSrc: ''
            },
            columns: [
                {data: 'id'},

                {
                    data: 'code',
                    bSortable: false,
                    mRender: function (data, type, row) {
                        return '<img src="' + window.contextRoot
                            + '/resources/images/' + data
                            + '.jpg" class="dataTableImg"/>';
                    }
                },
                {
                    data: 'name'
                },
                {
                    data: 'brand'
                },
                {
                    data: 'quantity',
                    mRender: function (data, type, row) {

                        if (data < 1) {
                            return '<span style="color:red">Out of Stock!</span>';
                        }

                        return data;

                    }
                },
                {
                    data: 'unitPrice',
                    mRender: function (data, type, row) {
                        return '&#8377; ' + data
                    }
                },
                {
                    data: 'active',
                    bSortable: false,
                    mRender: function (data, type, row) {
                        var str = '';
                        if (data) {
                            str += '<label class="switch"> <input type="checkbox" value="' + row.id + '" checked="checked">  <div class="slider round"> </div></label>';

                        } else {
                            str += '<label class="switch"> <input type="checkbox" value="' + row.id + '">  <div class="slider round"> </div></label>';
                        }

                        return str;
                    }
                },
                {
                    data: 'id',
                    bSortable: false,
                    mRender: function (data, type, row) {

                        var str = '';
                        str += '<a href="'
                            + window.contextRoot
                            + '/manage/'
                            + data
                            + '/product" class="btn btn-primary"><span class="glyphicon glyphicon-pencil"></span></a> &#160;';

                        return str;
                    }
                }
            ],


            initComplete: function () {
                var api = this.api();
                api.$('.switch input[type="checkbox"]').on('change', function () {
                        var dText = (this.checked) ? 'You want to activate the Product?' : 'You want to de-activate the Product?';
                        var checked = this.checked;
                        var checkbox = $(this);
                        debugger;
                        bootbox.confirm({
                            size: 'medium',
                            title: 'Product Activation/Deactivation',
                            message: dText,
                            callback: function (confirmed) {
                                if (confirmed) {
                                    $.ajax({
                                        type: 'GET',
                                        url: window.contextRoot + '/manage/products/' + checkbox.prop('value') + '/activation',
                                        timeout: 100000,
                                        success: function (data) {
                                            bootbox.alert(data);
                                        },
                                        error: function (e) {
                                            bootbox.alert('ERROR: ' + e);
                                            //display(e);
                                        }
                                    });
                                } else {
                                    checkbox.prop('checked', !checked);
                                }
                            }
                        });
                    }
                );

            }

        });


        //Admin Table Data Ends Here
    }


//End OF  ADMIN data table


    //Validation Form
//     var $categoryForm = $('#categoryForm');
//     console.log($categoryForm);
//     if ($categoryForm.length) {
//         $categoryForm.validate({
//                 rules: {
//                     name: {
//                         requird: true,
//                         minlength: 2
//                     },
//                     description: {
//                         requird: true,
//
//                     }
//
//                 },
//             messages: {
//                         name: {
//                             requird: 'Please add the category name!',
//                             minlength: 'The Category name should not be less than 2 charecters'
//                         },
//                         description: {
//                             requird: 'Please add a description for this category!'
//                         },
//                         errorElement: 'em',
//                         errorPlacement: function (error, element) {
// //    Add the class of help-block
//                             error.add('help-block');
//                             //add the error element after the input element
//                             error.insertAfter(element);
//                         }
//
//                     }
//
//
//             }
//         )}


    $categoryForm = $('#categoryForm');

    if($categoryForm.length) {

        $categoryForm.validate({
                rules: {
                    name: {
                        required: true,
                        minlength: 3
                    },
                    description: {
                        required: true,
                        minlength: 3
                    }
                },
                messages: {
                    name: {
                        required: 'Please enter product name!',
                        minlength: 'Please enter atleast five characters'
                    },
                    description: {
                        required: 'Please enter product name!',
                        minlength: 'Please enter atleast five characters'
                    }
                },
                errorElement : "em",
                errorPlacement : function(error, element) {
                    errorPlacement(error, element);
                }
            }

        );

    }
//          validation for Login Page! ------------------------------------------
var $loginForm = $('$loginForm');
    if ($loginForm.length){
        $categoryForm.validator({
            rules:{
                name:{
                    required: true,
                    minlength: 2
                },
                description:{
                    required:true
                }
            },
            messages:{
                username:{
                    required:'Please Enter the Username',
                    email: 'Please enter a valid email address'
                },
                password:{
                    required:'Please enter the password!'
                }
            }
        });
    }
//          validation for Login Page! ------------------------------------------

//Tackling CSRF token here
var token = $('meta[name="_csrf"]'.attr('content'));
    var header = $('meta[name="_csrf_header"]'.attr('content'));
if (token.length>0 && hader.length>0){
    //set the token header for the header request
    $(document).ajaxSend(function (e, xhr, options) {
        xhr.setRequestHeader(header,token);
    });
}





});






