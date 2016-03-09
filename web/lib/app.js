/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function() {
    var checkvalue = window.location.pathname;
    $("a").each(function() {
        if ($(this).attr('href') == checkvalue || $(this).data("path") == checkvalue) {
            $(this).addClass("active");
        }
    });
});
