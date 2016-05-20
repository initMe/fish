
var times=0;
var loaded = true;

if (times == 0){
    init_Data();
}

function init_Data(){
    times++;
    $.ajax(
        {
            type: "POST",
            url: appServer + "/blog/ajax_load.htm",
            data: {"currentPage" : times, "pageSize" : 5},
            success: function(data)
            {
                if(data.trim() == '')
                {
                    $(".loader").hide();
                    $(".no-data").show();
                    loaded=false;
                }else{
                    $("#blog-list").append(data);
                }
            }
        }
    );
}
function load_Data()
{
    var top = $(document).height();
    if(loaded && (parseFloat($(window).height()) + parseFloat($(window).scrollTop()) >= top))
    {
        $(".loader").show();
        init_Data();
    }
}

$(window).scroll(load_Data);
