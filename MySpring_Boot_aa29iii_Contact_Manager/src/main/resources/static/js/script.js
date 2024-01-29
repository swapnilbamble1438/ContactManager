console.log("this is script file");

const toggleSidebar = () =>
{
    if($(".sidebar").is(":visible"))
    {
        $(".sidebar").css("display","none");
        $(".content").css("margin-left","0%");
    }
    else{
        $(".sidebar").css("display","block");
        $(".content").css("margin-left","20%");
    }
};




const search=()=>{
	// console.log("searching...");
	
	let keyword=$("#search-input").val();
	
	if(keyword == "")
	{
		$(".search-result").hide();
	}else{
		console.log(keyword);
		
		//sending request to server
		
		let url=`http://localhost:8080/search/${keyword}`;
		
		fetch(url)
		.then((response)=>{
			return response.json();
		})
		.then((data)=>{
		//	console.log(data);
			
			let text =`<div class='list-group'>`;
			
			
			data.forEach((contact)=> {
				
				text+=`<a href='/user/${contact.cid}/contactdetails' class='list-group-item list-group-item-action '> ${contact.cname}</a>`;
				
			});
			
			
			text+=`</div>`;

            $(".search-result").html(text);
            $(".search-result").show();
			
		});

	}
	
};



	// first request to server to create order
//----------- 2nd Step --------------------------------------------------------
const paymentStarted = () => {

//==================================================================================================================	
	
	console.log("payment started..");
	
	let amount = $("#paymentfield").val().trim();
	console.log(amount);
	
	if(amount.trim() ==='' || amount == null)
	{
	//	alert("amount is required ! ");
			swal({
		  title: "Error!",
		  text: "Amount is required !",
		  type: "error",
		});

		return;
	}
	
	// we will use ajax to send request to server
	// to create order - JQuery
	
	$.ajax(
		{
			url:'/user/createorder',
			data:JSON.stringify({amount:amount,info:'order_request'}),
			contentType:'application/json',
			type:'POST',
			dataType:'json',
			success:function(response){
				// invoked when success
				console.log(response);
				
//---------- 4th Step // do this part after 3rd step------------------------------------------------------				
				if(response.status == 'created')
				{
					// open payment form
					displayRazorpay(response);
					
				}	
//----------- 4th Step End -------------------------------------------------------------------------------			
							
			},
			error:function(error){
				// invoked when error
				console.log(error);
			//	alert("something went wrong !");
			swal({
					  title: "Error!",
					  text: "Something went wrong !",
					  type: "error",
					});
			}
			
		}
	)
	
	
	
 };
//-------------- 2nd Step End---------------------------------------
	


//------------4 th Step ------------------------------------------------	
	 function displayRazorpay(response) {
       
        // add this script before js file in your HTML file: 
        //   <script src="https://checkout.razorpay.com/v1/checkout.js"></script>

       
        const options = {
            key: "Enter here your razorpay key_id ", // Enter the Key ID generated from the razorpay
            amount: response.amount.toString(),
            currency: 'INR',
            name: "Contact Manager",
            description: "Test Transaction",
            image:'http:/image/diamond.jpg',
            order_id: response.id,
            handler:  function (response) {
				
				console.log(response.razorpay_payment_id);
				console.log(response.razorpay_order_id);
				console.log(response.razorpay_signature);
				console.log("payment successful !!");
			//  alert("congrates !! Payment Successfull!!");
			//	swal("Payment is Successful");
				console.log("Payment is Successful");
//-------------6th Step-----------------------------------------------------------------		
		
			updatePaymentOnServer(
				response.razorpay_payment_id,
				response.razorpay_order_id,
				"paid"
				);
	

//-------------6th Step End-------------------------------------------------------------	
					
        
            },
            prefill: {
                name: "",
                email: "",
                contact: "",
            },
            notes: {
                address: "Contact Manager ",
            },
            theme: {
                color: "#61dafb",
            },
        };

        const rzp = new Razorpay(options);
        
        rzp.on("payment.failed",function(response){
			console.log(response.error.code);
			console.log(response.error.description);
			console.log(response.error.source);
			console.log(response.error.step);
			console.log(response.error.reason);
			console.log(response.error.metadata.order_id);
			console.log(response.error.metadata.payment_id);
		//	alert("Oops payment failed !");
		
		swal({
			  title: "Error!",
			  text: "Oops Payment Failed !",
			  type: "error",
			});

			
		});
        
        rzp.open();
}

//-----------4th Step End-----------------------------------------------------




//----------6th Step-------------------------------------------------------------------------------------
function updatePaymentOnServer(payment_id,order_id,status)
{
	$.ajax(
		{
			url:'/user/updateorder',
			data:JSON.stringify({payment_id:payment_id,order_id:order_id,status:status}),
			contentType:'application/json',
			type:'POST',
			dataType:'json',
			success:function(response){
				console.log("Payment details is successfully store in server.");
				swal("Payment is Successful");
			},
			error:function(error){
				swal({
			  title: "Successful!",
			  text: "Your Payment is successfull, But we did not get on sever",
			  type: "error",
			});

			},
			
			
		}
	   )
}
//---------------6th Step End-------------------------------------------------------------------------------