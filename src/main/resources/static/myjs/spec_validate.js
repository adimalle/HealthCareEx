$(document).ready(function(){
      //1.hide erro section
       $("#specCodeError").hide();
       $("#specNameError").hide();
       $("#specNoteError").hide();
      //2.define error variable
      var specCodeError=false;
      var specNameError=false;
      var specNoteError=false;
      //3.validate the function
      function validate_code(){
                                   //validate function
                                   //read values
                                   var val=$("#specCode").val();
                                   var exp=/^[A-Z]{4,12}$/;

                                   if(val==''){
                                       $("#specCodeError").show();
                                       $("#specCodeError").html("**Enter code"); 
                                       $("#specCodeError").css('color','red');
                                       specCodeError=false;
                                   }
                                   else if(!exp.test(val)){
                                       $("#specCodeError").show();
                                       $("#specCodeError").html("**please Enter only A-Z 4-12 characters"); 
                                       $("#specCodeError").css('color','red');
                                       specCodeError=false;
                                   }
                                   else{
	                         var id=0;//for register
                             if($("#id").val()!=undefined){// edit page
	                          specCodeError=true;
                              id=$("#id").val();  
                                 }    

                                 $.ajax({
	                          url:'checkCode',
                              data:{"code":val,"id":id},
                              success:function(respTxt){
	                           if(respTxt!=''){
		                               $("#specCodeError").show();
                                       $("#specCodeError").html(respTxt); 
                                       $("#specCodeError").css('color','red');
                                       specCodeError=false;
                              	}
                              	else{
		                              $("#specCodeError").hide();
                                      
                                       specCodeError=true;
	                                 }
                                     } 

                               });

                                   }
          return specCodeError;
      }
      function validate_name(){
           //validate function
                                   //read values
                                   var val=$("#specName").val();
                                   var exp=/^[A-Za-z0-9/./,/@/#/$]{5,30}$/;
                                   if(val==''){
                                       $("#specNameError").show();
                                       $("#specNameError").html("**Enter name"); 
                                       $("#specNameError").css('color','red');
                                       specNameError=false;
                                   }
                                   else if(!exp.test(val)){
                                       $("#specNameError").show();
                                       $("#specNameError").
                                       html("**pleasse select only this type [A-Za-z0-9/./,/@/#/$] 5-30 characters"); 
                                       $("#specNameError").css('color','red');
                                       specNameError=false;
                                   }
                                   else{
                                    $("#specNameError").hide();
                                    specNameError=true;
                                   }
          return specNameError;
      }
      function validate_note(){
           //validate function
                                   //read values
                                   var val=$("#specNote").val();
                                   var exp=/^[A-Za-z0-9/./,/!/@/#/$]{5,500}$/;
                                   if(val==''){
                                       $("#specNoteError").show();
                                       $("#specNoteError").html("**Enter Note"); 
                                       $("#specNoteError").css('color','red');
                                       specCodeError=false;
                                   }
                                   else if(!exp.test(val)){
                                       $("#specNoteError").show();
                                       $("#specNoteError").
                                       html("**please select only this type [A-Za-z0-9/./,/!/@/#/ /$] 5-500 characters"); 
                                       $("#specNoteError").css('color','red');
                                       specCodeError=false;
                                   }
                                   else{
                                    $("#specNoteError").hide();
                                    specNoteError=true;
                                   }
          return specNoteError;
      }
      //4.function link with action
      $("#specCode").keyup(function(){
        validate_code();

      });
      $("#specName").keyup(function(){
        validate_name();

      });
      $("#specNote").keyup(function(){
        validate_note();

      });
      //5.on-submit
         $("#save").submit(function(){
            validate_code();
            validate_name();
            validate_note();
            if(specCodeError&&specNameError&&specNameError){
                return true;
            }
                else

                return false;
            
         });
     });