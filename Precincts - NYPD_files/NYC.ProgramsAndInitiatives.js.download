var NYC = NYC || {};

/**
 * Class NYC.ProgramsAndInitiatives
 *
 */
NYC.ProgramsAndInitiatives = Class.extend({

    self : this,
    
    init : function init(options, elem) {

        var self = this;

        console.log("init programs and initiatives");

        this.options = $.extend({}, this.options, options); 

        this.bindEvents();

    },

    options : {},

    bindEvents: function() {

        var self = this;

        //programs and initiatives 
        $('div.module-programs-and-initiatives').on('click', 'div.initiative', function(e) {
            var target = $(e.currentTarget);
            window.location = target.attr('data-link');
        });

        // $('div.module-programs-and-initiatives').on('mouseover mouseout', 'div.initiative', function(e) {
        //     // $(e.currentTarget).toggleClass('hovered');
        //     // console.log(e.type, '::::: ', $(e.target) );
        // });

        // $('.list-items').on('mouseover mouseout', 'div.program-item', function(e) {
        //     // $(e.currentTarget).toggleClass('hovered');
        //     // console.log(e.type, '::::: ', $(e.target) );
        // });

    }

});

$(document).ready(function() {
    var programs = new NYC.ProgramsAndInitiatives(); 
});


