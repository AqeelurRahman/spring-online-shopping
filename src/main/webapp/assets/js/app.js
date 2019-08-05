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
    var products = [
        ['1', 'ABC'],
            ['2', 'ABC'],
            ['3', 'AsdfBC'],
            ['4', 'ABsdfC'],
            ['5', 'ABasdC'],
            ['6', 'ABasdfasdfC']


    ];

    var $table = $('#productlistTable');
//    execute the code where this table is present
    if ($table.length){

        console.log('Inside the table');
        $table.DataTable({
            data:products
        });


    }



});



