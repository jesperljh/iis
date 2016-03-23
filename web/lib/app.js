/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

semantic.button = {};

$(document).ready(function () {
    $('.ui.accordion').accordion();

    var checkvalue = window.location.pathname;
    $("a").each(function () {
        if ($(this).attr('href') == checkvalue || $(this).data("path") == checkvalue) {
            $(this).addClass("active");
        }
    });

    var hash = window.location.hash;
    // now scroll to element with that id
    $('html, body').animate({scrollTop: $(hash).offset().top});
});


// ready event
semantic.button.ready = function() {

  // selector cache
  var
    $buttons = $('.ui.buttons .button'),
    $toggle  = $('.main .ui.toggle.button'),
    $button  = $('.ui.button').not($buttons).not($toggle),
    // alias
    handler = {

      activate: function() {
        $(this)
          .addClass('active')
          .siblings()
          .removeClass('active')
        ;
      }

    }
  ;

  $buttons
    .on('click', handler.activate)
  ;


  $toggle
    .state({
      text: {
        inactive : 'Vote',
        active   : 'Voted'
      }
    })
  ;

};

$('.ui.form').form({
    fields: {
        name: {
            identifier: 'name',
            rules: [
                {
                    type: 'empty',
                    prompt: 'Please enter your name'
                }
            ]
        },
        skills: {
            identifier: 'skills',
            rules: [
                {
                    type: 'minCount[2]',
                    prompt: 'Please select at least two skills'
                }
            ]
        },
        gender: {
            identifier: 'gender',
            rules: [
                {
                    type: 'empty',
                    prompt: 'Please select a gender'
                }
            ]
        },
        username: {
            identifier: 'username',
            rules: [
                {
                    type: 'empty',
                    prompt: 'Please enter a username'
                }
            ]
        },
        password: {
            identifier: 'password',
            rules: [
                {
                    type: 'empty',
                    prompt: 'Please enter a password'
                },
                {
                    type: 'minLength[6]',
                    prompt: 'Your password must be at least {ruleValue} characters'
                }
            ]
        },
        terms: {
            identifier: 'terms',
            rules: [
                {
                    type: 'checked',
                    prompt: 'You must agree to the terms and conditions'
                }
            ]
        }
    }
});
