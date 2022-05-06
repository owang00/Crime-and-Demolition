var NYC = NYC || {};

/**
 * Class NYC.InfoShare
 *  used with the event-details-infoshare module
 */
NYC.InfoShare = Class.extend({

    iOS : ( navigator.userAgent.match(/(iPad|iPhone|iPod)/i) ? true : false ),
        
    /* PLEASE UPDATE THIS PATH IN THE PRODUCTION ENVIRONMENT */
    //swfPath : 'http://proto.nycgov.dev.nyicolo.ad.hugeinc.com/qa/assets/home/js/libs/ZeroClipboard.swf',
    swfPath : window.location.hostname + '/assets/home/js/libs/ZeroClipboard.swf',

    init : function init(options, elem) {
        console.log("init InfoShare");
		
        this.element = elem;
        this.options = $.extend({}, this.options, options); 
		this.isMobile = window.orientation || Modernizr.touch;

        if(this.element !== undefined && this.element.length > 0) { 
        	this.bindEvents(); 
		}
		
		this.bindAddToCalButton();

    },

    options : {},

	bindAddToCalButton : function() {
	
		var self = this;
		
		$(document).on("click", ".btn-add-cal", function(e) {
			var calendarSelect = $(this).prev();
			$(this).attr("href", calendarSelect.val());
			$(this).attr("target", "_blank");
		});	
	
	},
    
    bindEvents : function() {
        var self = this;


        // currently getting all .event-detail div's on page
        // children aren't encapsulated within

        var moduleContainer  = self.element;
		//var calendarSelect   = moduleContainer.find('select.select-calendar');
        var copyButton       = moduleContainer.find('button.btn-copy-link');
        var phoneLink        = moduleContainer.find('a.phone-number')
        //this.moduleTools      = this.moduleContainer.find('div.event-tools');
        //this.calendarSelect   = this.moduleTools.find('select.select-calendar');
        //this.copyButton       = this.moduleTools.find('button.btn-copy-link');
        //this.phoneLink        = this.moduleContainer.find('a.phone-number');
		
		console.log("------ BIND EVENTS ----------------");

        
        //add to calendar
        //this.calendarSelect.on('change', function(e) {
        //  var target = $(this);
        //  target.siblings('a.btn-add-cal').attr('href', target.val());  //avoid updating all a href's on page
        //});
    
        if( !self.isMobile ) {

          // if not mobile, phone link shouldn't work
          phoneLink.on('click', function(e) {
            e.preventDefault();
          });

          //if not mobile, and button exists - init copy url button
          if(copyButton.length > 0) { self.bindCopyButton() };
        
        } else {
        
          if(self.iOS) {
            $(".btn-copy-link").remove();
          } else {
            if(copyButton.length > 0) { 
              self.bindCopyButton() 
            };
          }
        }
        
    },

    
    bindCopyButton: function () {
      var self = this;

      console.log('SWF PATH =>>>>>>>> ', self.swfPath);

      /* PLEASE UPDATE TRUSTED DOMAINS IN THE PRODUCTION ENVIRONMENT */
      var hostname = window.location.hostname;
      console.log (hostname);
      var clip = new ZeroClipboard( this.copyButton, {
        moviePath: self.swfPath,
        'trustedDomains': hostname,
        'allowScriptAccess': 'always'
      });

      clip.on( 'load', function(client) {
        console.log( "ZeroClipboard swf is loaded", client );
      });

      clip.on( 'complete', function(client, args) {
        // this.style.display = 'none'; // "this" is the element that was clicked
        console.log("Copied text to clipboard: " + args.text );
      });
      /*
      clip.on( 'mouseover', function(client) {
        console.log("mouse over");
      });

      clip.on( 'mouseout', function(client) {
        console.log("mouse out");
      });

      clip.on( 'mousedown', function(client) {
        console.log("mouse down");
      });

      clip.on( 'mouseup', function(client) {
        console.log("mouse up");
      });
      */
    },
    
    reInit : function(){
	    initShareLinks();
    },

    setGCalUrl : function() {

      var self = this;
      var selects=$("select.select-calendar");
      selects.each(function(){
		for(var i=0;i<this.options.length;i++){
			if(this.options[i].text=="GCal"){
				this.options[i].value=self.generateGCalUrl($(this).parents("div.event-tools"));
			}
		}
      });

    },


    generateGCalUrl : function(element) {
		var self = this;
		//if (this.element == undefined) return;

		//this.moduleContainer    = element;
		this.moduleTools        = element;//this.moduleContainer.find('div.event-tools');
		this.eventDateEl        = this.moduleTools.find(".event-date");
		this.name               = $(".main-title").text();
		if(!this.name||this.name.length==0){
			this.name=this.moduleTools.find(".event-title").val();
		}
		this.desc               = this.moduleTools.find(".event-short-desc").val();
		this.location           = this.moduleTools.find(".event-location").val();
		if(!this.location||this.location.length==0){
			this.location=this.moduleTools.find(".location").text();
		}
		if(this.moduleTools.find(".event-url").val()){
			this.permalink        = this.moduleTools.find(".event-url").val();
		}else{
			this.permalink=document.location;
		}
		
		this.startDate          = this.eventDateEl.data("start-date");
		this.formattedStartDate = ISODateString(new Date(this.startDate));
		this.endDate            = this.eventDateEl.data("end-date");
		if(!this.endDate||this.endDate.length==0){
			this.endDate=this.startDate;
		}
		this.formattedEndDate   = ISODateString(new Date(this.endDate));
		
		var gCalUrl = "https://www.google.com/calendar/render?action=TEMPLATE&text=" + this.name + "&details=" + this.desc + " " + this.permalink + " &dates=" + this.formattedStartDate + "/" + this.formattedEndDate + "&sprop=name:NYC.GOV&sprop=website:www.nyc.gov/events&location=" + this.location + "&sf=true&output=xml";
		
		return gCalUrl;

    }

});



