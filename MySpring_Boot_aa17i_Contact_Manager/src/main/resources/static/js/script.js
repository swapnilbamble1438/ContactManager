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